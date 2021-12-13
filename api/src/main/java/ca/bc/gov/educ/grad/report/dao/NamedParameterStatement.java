package ca.bc.gov.educ.grad.report.dao;

import oracle.jdbc.OraclePreparedStatement;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by alex.rybakov
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class NamedParameterStatement {

    /**
     * The statement this object is wrapping.
     */
    private final OraclePreparedStatement statement;

    /**
     * Maps parameter names to arrays of ints which are the parameter indices.
     */
    private final Map indexMap;

    /**
     * Creates a NamedParameterStatement.  Wraps a call to
     * c.{@link Connection#prepareStatement(String)
     * prepareStatement}**.
     *
     * @param connection the database connection
     * @param query      the parameterized query
     * @throws SQLException if the statement could not be created
     */
    public NamedParameterStatement(Connection connection, String query) throws
            SQLException {
        indexMap = new HashMap();
        String parsedQuery = parse(query, indexMap);
        statement = (OraclePreparedStatement)connection.prepareStatement(parsedQuery);
    }


    /**
     * Parses a query with named parameters.  The parameter-index mappings are
     * put into the map, and the
     * parsed query is returned.  DO NOT CALL FROM CLIENT CODE.  This
     * method is non-private so JUnit code can
     * test it.
     *
     * @param query    query to parse
     * @param paramMap map to hold parameter-index mappings
     * @return the parsed query
     */
    static final String parse(String query, Map paramMap) {

        // I was originally using regular expressions, but they didn't work well for ignoring
        // parameter-like strings inside quotes.

        int length = query.length();
        StringBuffer parsedQuery = new StringBuffer(length);
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        int index = 1;

        for (int i = 0; i < length; i++) {
            char c = query.charAt(i);
            if (inSingleQuote) {
                if (c == '\'') {
                    inSingleQuote = false;
                }
            } else if (inDoubleQuote) {
                if (c == '"') {
                    inDoubleQuote = false;
                }
            } else {
                if (c == '\'') {
                    inSingleQuote = true;
                } else if (c == '"') {
                    inDoubleQuote = true;
                } else if (c == ':' && i + 1 < length &&
                        Character.isJavaIdentifierStart(query.charAt(i + 1))) {
                    int j = i + 2;
                    while (j < length && Character.isJavaIdentifierPart(query.charAt(j))) {
                        j++;
                    }
                    String name = query.substring(i + 1, j);
                    c = '?'; // replace the parameter with a question mark
                    i += name.length(); // skip past the end if the parameter

                    List indexList = (List) paramMap.get(name);
                    if (indexList == null) {
                        indexList = new LinkedList();
                        paramMap.put(name, indexList);
                    }
                    indexList.add(new Integer(index));

                    index++;
                }
            }
            parsedQuery.append(c);
        }

        // replace the lists of Integer objects with arrays of ints
        for (Iterator itr = paramMap.entrySet().iterator(); itr.hasNext(); ) {
            Map.Entry entry = (Map.Entry) itr.next();
            List list = (List) entry.getValue();
            int[] indexes = new int[list.size()];
            int i = 0;
            for (Iterator itr2 = list.iterator(); itr2.hasNext(); ) {
                Integer x = (Integer) itr2.next();
                indexes[i++] = x.intValue();
            }
            entry.setValue(indexes);
        }

        return parsedQuery.toString();
    }


    /**
     * Returns the indexes for a parameter.
     *
     * @param name parameter name
     * @return parameter indexes
     * @throws IllegalArgumentException if the parameter does not exist
     */
    private int[] getIndexes(String name) {
        int[] indexes = (int[]) indexMap.get(name);
        if (indexes == null) {
            indexes = new int[0];
        }
        return indexes;
    }


    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setObject(int, Object) PreparedStatement#setObject(int, java.lang.Object)PreparedStatement#setObject(int, java.lang.Object)
     */
    public void setObject(String name, Object value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setObject(indexes[i], value);
        }
    }

    public void setBytes(String name, byte[] bytes) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setBytes(indexes[i], bytes);
        }
    }

    public void setClob(String name, Clob value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setClob(indexes[i], value);
        }
    }

    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setString(int, String) PreparedStatement#setString(int, java.lang.String)PreparedStatement#setString(int, java.lang.String)
     */
    public void setString(String name, String value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setString(indexes[i], value);
        }
    }

    public void setStringForClob(String name, String clobString) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setStringForClob(indexes[i], clobString);
        }
    }

    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setInt(int, int) PreparedStatement#setInt(int, int)PreparedStatement#setInt(int, int)
     */
    public void setInt(String name, int value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setInt(indexes[i], value);
        }
    }

    public void setBlob(String columnName, InputStream is) throws SQLException {
        int[] indexes = getIndexes(columnName);
        for (int i = 0; i < indexes.length; i++) {
            statement.setBinaryStream(indexes[i], is);
        }
    }

    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setInt(int, int) PreparedStatement#setInt(int, int)PreparedStatement#setInt(int, int)
     */
    public void setLong(String name, long value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setLong(indexes[i], value);
        }
    }


    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setTimestamp(int, Timestamp) PreparedStatement#setTimestamp(int, java.sql.Timestamp)PreparedStatement#setTimestamp(int, java.sql.Timestamp)
     */
    public void setTimestamp(String name, Timestamp value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setTimestamp(indexes[i], value);
        }
    }

    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setDate(int, java.sql.Date) PreparedStatement#setDate(int, java.sql.Date)PreparedStatement#setDate(int, java.sql.Date)
     */
    public void setDate(String name, java.sql.Date value) throws SQLException {
        int[] indexes = getIndexes(name);
        for (int i = 0; i < indexes.length; i++) {
            statement.setDate(indexes[i], value);
        }
    }

    /**
     * Sets a parameter.
     *
     * @param name  parameter name
     * @param value parameter value
     * @throws SQLException if an error occurred
     * @see PreparedStatement#setDate(int, java.sql.Date) PreparedStatement#setDate(int, java.sql.Date)PreparedStatement#setDate(int, java.sql.Date)
     */
    public void setDate(String name, java.util.Date value) throws SQLException {
        Timestamp sqlTimeStamp;
        if (value != null) {
            sqlTimeStamp = new Timestamp(value.getTime());
            setTimestamp(name, sqlTimeStamp);
        } else {
            setTimestamp(name, null);
        }
    }


    /**
     * Returns the underlying statement.
     *
     * @return the statement
     */
    public PreparedStatement getStatement() {
        return statement;
    }


    /**
     * Executes the statement.
     *
     * @return true if the first result is a {@link ResultSet}
     * @throws SQLException if an error occurred
     * @see PreparedStatement#execute() PreparedStatement#execute()PreparedStatement#execute()
     */
    public boolean execute() throws SQLException {
        return statement.execute();
    }


    /**
     * Executes the statement, which must be a query.
     *
     * @return the query results
     * @throws SQLException if an error occurred
     * @see PreparedStatement#executeQuery() PreparedStatement#executeQuery()PreparedStatement#executeQuery()
     */
    public ResultSet executeQuery() throws SQLException {
        return statement.executeQuery();
    }


    /**
     * Executes the statement, which must be an SQL INSERT, UPDATE or DELETE
     * statement;
     * or an SQL statement that returns nothing, such as a DDL statement.
     *
     * @return number of rows affected
     * @throws SQLException if an error occurred
     * @see PreparedStatement#executeUpdate() PreparedStatement#executeUpdate()PreparedStatement#executeUpdate()
     */
    public int executeUpdate() throws SQLException {
        return statement.executeUpdate();
    }


    /**
     * Closes the statement.
     *
     * @throws SQLException if an error occurred
     * @see Statement#close() Statement#close()Statement#close()
     */
    public void close() throws SQLException {
        statement.close();
    }


    /**
     * Adds the current set of parameters as a batch entry.
     *
     * @throws SQLException if something went wrong
     */
    public void addBatch() throws SQLException {
        statement.addBatch();
    }


    /**
     * Executes all of the batched statements.
     * <p>
     * See {@link Statement#executeBatch()} for details.
     *
     * @return update counts for each statement
     * @throws SQLException if something went wrong
     */
    public int[] executeBatch() throws SQLException {
        return statement.executeBatch();
    }

}

