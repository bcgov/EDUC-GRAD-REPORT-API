import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BackupTransactDataUtils {

    public static void backupTable(String sourceUser, String sourceUrl, String targetUser, String targetUrl, String table) {
        Connection srcConn;
        Connection trgConn;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            srcConn = DriverManager.getConnection(sourceUrl);
            trgConn = DriverManager.getConnection(targetUrl);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        String dropTableSql = "DROP TABLE " + table +  " CASCADE CONSTRAINTS PURGE ";
        String setMetadataSql = "begin " +
                "dbms_metadata.set_transform_param (dbms_metadata.session_transform,'SEGMENT_ATTRIBUTES',FALSE); " +
                "dbms_metadata.set_transform_param (dbms_metadata.session_transform,'REF_CONSTRAINTS',FALSE); " +
                "end;";
        String extractDdlSql = "SELECT dbms_metadata.get_ddl('TABLE',?) FROM dual";
        String getDataSql = "select * from " + table;
        String insertDataSql = "insert into " + table + " select ";

        //drop target table if exists
        try {
            PreparedStatement dropTablePstm = trgConn.prepareStatement(dropTableSql);
            dropTablePstm.execute();
            dropTablePstm.close();
        }
        catch (SQLException e) {
            System.err.println("Drop table statement fails " + e.getMessage());
        }

        //extract ddl from source schema and create table
        try {
            PreparedStatement metadataPstm = srcConn.prepareStatement(setMetadataSql);
            metadataPstm.execute();
            metadataPstm.close();

            PreparedStatement ddlPstm = srcConn.prepareStatement(extractDdlSql);
            ddlPstm.setString(1, table);
            ResultSet tableDdlRs = ddlPstm.executeQuery();
            if(tableDdlRs.next()) {
                String ddlSql = tableDdlRs.getString(1).replace(sourceUser, targetUser);
                PreparedStatement createTablePstm = trgConn.prepareStatement(ddlSql);
                createTablePstm.execute();
                createTablePstm.close();
            }
            metadataPstm.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //copy data from source to target
        try {
            PreparedStatement getDataPstm = srcConn.prepareStatement(getDataSql);
            ResultSet loadDataRs = getDataPstm.executeQuery();
            ResultSetMetaData loadDataMd = loadDataRs.getMetaData();
            while(loadDataRs.next()) {
                StringBuilder loadDataSql = new StringBuilder(insertDataSql);
                for(int i = 1; i <= loadDataMd.getColumnCount(); i ++) {
                    loadDataSql.append("?").append(",");
                }
                loadDataSql.deleteCharAt(loadDataSql.length() - 1);
                loadDataSql.append(" from dual ");
                PreparedStatement insertDataPstm = trgConn.prepareStatement(loadDataSql.toString());
                for(int i = 1; i <= loadDataMd.getColumnCount(); i ++) {
                    String columnType = loadDataMd.getColumnTypeName(i);
                    switch (columnType) {
                        case "VARCHAR2":
                            insertDataPstm.setString(i, loadDataRs.getString(i));
                            break;
                        case "RAW":
                            insertDataPstm.setBytes(i, loadDataRs.getBytes(i));
                            break;
                        case "CLOB":
                            insertDataPstm.setClob(i, loadDataRs.getClob(i));
                            break;
                        case "DATE":
                            insertDataPstm.setDate(i, loadDataRs.getDate(i));
                            break;
                        case "INTEGER":
                            insertDataPstm.setInt(i, loadDataRs.getInt(i));
                            break;
                        case "TIMESTAMP":
                            insertDataPstm.setTimestamp(i, loadDataRs.getTimestamp(i));
                            break;
                        default:
                            break;
                    }
                }
                insertDataPstm.executeUpdate();
                insertDataPstm.close();
            }
            getDataPstm.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                srcConn.close();
                trgConn.close();
            } catch (SQLException e) {
                //IGNORE
            }
        }
    }

    public static void main(String[] args) {
        String sourceUrl = "jdbc:oracle:thin:API_GRAD_REPORT/********@oltp-scan01-dt.educ.gov.bc.ca:1521/oltpd.world";
        String targetUrl = "jdbc:oracle:thin:GRAD_CONV/********@oltp-scan01-dt.educ.gov.bc.ca:1521/oltpd.world";
        String[] srcTables = { "STUDENT_CERTIFICATE" };
        if(args == null || args.length < 3) {
            System.out.println("Parameters required:");
            System.out.println("java -classpath <path to oracle driver jar> BackupTransactDataUtils.class sourceUrl targetUrl sourceTables ...");
            System.out.println("sourceUrl and targetUrl format: jdbc:oracle:thin:<USERNAME>/<PASSWORD>@<HOSTNAME>:<PORT>/<SERVICE_NAME>");
            return;
        } else {
            sourceUrl = args[0];
            targetUrl = args[1];
            List<String> srcTablesList = new ArrayList<>();
            for(int i = 2; i < args.length; i ++) {
                srcTablesList.add(args[i]);
            }
            if(srcTablesList.isEmpty()) {
                System.out.println("At least 1 source table must be specified");
                return;
            }
            srcTables = new String[srcTablesList.size()];
            srcTablesList.toArray(srcTables);
        }

        String sourceSchema = sourceUrl.substring("jdbc:oracle:thin:".length(), sourceUrl.indexOf("/", "jdbc:oracle:thin:".length()));
        String targetSchema = targetUrl.substring("jdbc:oracle:thin:".length(), targetUrl.indexOf("/", "jdbc:oracle:thin:".length()));;

        System.out.println("Migration with parameters:");
        System.out.println("sourceUrl = " + sourceUrl);
        System.out.println("targetUrl = " + targetUrl);

        System.out.println("Running data migration from " + sourceSchema + " to " + targetSchema);
        for(int i = 0; i < srcTables.length; i ++) {
            System.out.println("Migrating " + srcTables[i] + " table ...");
            BackupTransactDataUtils.backupTable(sourceSchema, sourceUrl, targetSchema, targetUrl, srcTables[i]);
        }
        System.out.println("Completed.");
    }
}
