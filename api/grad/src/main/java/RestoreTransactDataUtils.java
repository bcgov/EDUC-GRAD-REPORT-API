import ca.bc.gov.educ.grad.dao.NamedParameterStatement;
import oracle.jdbc.OracleBlob;
import oracle.jdbc.OracleClob;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestoreTransactDataUtils {

    public static void restoreTable(String sourceUser, String sourceUrl, String targetUser, String targetUrl, String table) {
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

        String deleteDataSql = "delete from " + table;
        String getDataSql = "select * from " + table;
        String insertDataSql = "insert into " + table + " select ";

        //delete data from target table
        try {
            PreparedStatement deleteDataPstm = trgConn.prepareStatement(deleteDataSql);
            deleteDataPstm.executeUpdate();
            deleteDataPstm.close();
        }
        catch (SQLException e) {
            System.err.println("Delete data statement fails " + e.getMessage());
        }

        //copy data from source to target
        try {
            PreparedStatement getDataPstm = srcConn.prepareStatement(getDataSql);
            PreparedStatement putDataPstm = trgConn.prepareStatement(getDataSql);

            ResultSet getDataRs = getDataPstm.executeQuery();
            ResultSetMetaData getDataMd = getDataRs.getMetaData();

            ResultSet putDataRs = putDataPstm.executeQuery();
            ResultSetMetaData putDataMd = putDataRs.getMetaData();

            while(getDataRs.next()) {
                StringBuilder loadDataSql = new StringBuilder(insertDataSql);
                int getRowCount = getDataMd.getColumnCount();
                int putRowCount = putDataMd.getColumnCount();
                if(getRowCount <= putRowCount) {
                    for(int i = 1; i <= getRowCount; i ++) {
                        String colName = getDataMd.getColumnName(i);
                        loadDataSql.append(":" + colName).append(",");
                    }
                } else if(getRowCount > putRowCount) {
                    for(int i = 1; i <= putRowCount; i ++) {
                        String colName = putDataMd.getColumnName(i);
                        loadDataSql.append(":" + colName).append(",");
                    }
                }
                loadDataSql.deleteCharAt(loadDataSql.length() - 1);
                loadDataSql.append(" from dual ");
                NamedParameterStatement insertDataPstm = new NamedParameterStatement(trgConn, loadDataSql.toString());
                for(int i = 1; i <= putDataMd.getColumnCount(); i ++) {
                    String columnType = putDataMd.getColumnTypeName(i);
                    String columnName = putDataMd.getColumnName(i);
                    switch (columnType) {
                        case "VARCHAR2":
                            insertDataPstm.setString(columnName, getDataRs.getString(columnName));
                            break;
                        case "RAW":
                            insertDataPstm.setBytes(columnName, getDataRs.getBytes(columnName));
                            break;
                        case "CLOB":
                            OracleClob clob = (OracleClob)getDataRs.getClob(columnName);
                            String clobString;
                            if(clob == null) {
                                clobString = "";
                            } else {
                                StringBuilder sb = new StringBuilder();
                                try {
                                    Reader reader = clob.getCharacterStream();
                                    BufferedReader br = new BufferedReader(reader);
                                    String line;
                                    while (null != (line = br.readLine())) {
                                        sb.append(line);
                                    }
                                    br.close();
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                                clobString = sb.toString();
                            }
                            insertDataPstm.setStringForClob(columnName, clobString);
                            break;
                        case "BLOB":
                            OracleBlob blob = (OracleBlob)getDataRs.getBlob(i);
                            byte[] blobBytes;
                            if(blob == null) {
                                blobBytes = new byte[0];
                            } else {
                                blobBytes = blob.getBytes(0, (int)blob.length());
                            }
                            insertDataPstm.setBytes(columnName, blobBytes);
                            break;
                        case "DATE":
                            insertDataPstm.setDate(columnName, getDataRs.getDate(columnName));
                            break;
                        case "INTEGER":
                            insertDataPstm.setInt(columnName, getDataRs.getInt(columnName));
                            break;
                        case "TIMESTAMP":
                            insertDataPstm.setTimestamp(columnName, getDataRs.getTimestamp(columnName));
                            break;
                        default:
                            break;
                    }
                }
                insertDataPstm.executeUpdate();
                insertDataPstm.close();
            }
            getDataPstm.close();
            putDataPstm.close();
        } catch (SQLException e) {
            try {
                trgConn.rollback();
            } catch (SQLException ex) {
                //IGNORE
            }
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
        String sourceUrl = "jdbc:oracle:thin:GRAD_CONV/********@oltp-scan01-dt.educ.gov.bc.ca:1521/oltpd.world";
        String targetUrl = "jdbc:oracle:thin:API_GRAD_REPORT/********@oltp-scan01-dt.educ.gov.bc.ca:1521/oltpd.world";
        String[] srcTables = { "STUDENT_CERTIFICATE" };
        if(args == null || args.length < 3) {
            System.out.println("Parameters required:");
            System.out.println("java -classpath <path to oracle driver jar> RestoreTransactDataUtils sourceUrl targetUrl sourceTables ...");
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
            RestoreTransactDataUtils.restoreTable(sourceSchema, sourceUrl, targetSchema, targetUrl, srcTables[i]);
        }
        System.out.println("Completed.");
    }
}
