package org.jkutkut.db;

import org.jkutkut.exception.SQLQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class with logic to simplify the queries in a SQL database.
 *
 * @author Jkutkut
 */
public class SQLQuery {
    /**
     * Obtains the values from the database required.
     * @param db Database to use.
     * @param outputLength Size that each result has (number of columns).
     * @param query String SQL with the SELECT statement to perform.
     * @param input Set of values to replace in the query (the ?).
     * @return Arraylist with rows in the form of Array[Object] with the result.
     * @throws SQLQueryException If there is an error in the query.
     */
    public static ArrayList<Object[]> getFromDB(AccessDB db, int outputLength, String query, Object[] input) throws SQLQueryException {
        ArrayList<Object[]> output = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rslt = null;

        try {
            con = db.getConnection();

            pstmt = con.prepareStatement(query);
            for (int i = 0; i < input.length; i++) { // Fill the query
                pstmt.setObject(i + 1, input[i]);
            }

            rslt = pstmt.executeQuery();

            while (rslt.next()) { // Get the results
                Object[] row = new Object[outputLength]; // New object with the columns of a row.
                for (int i = 0; i < outputLength; i++) { // Fill the row
                    row[i] = rslt.getObject(i + 1);
                }
                output.add(row); // Add the row to the rows
            }
        }
        catch (ClassNotFoundException e) {
            throw new SQLQueryException("The chosen driver is not valid:\n" + e.getMessage());
        }
        catch (SQLException e) {
            throw new SQLQueryException("Error on database:\n" + e.getMessage());
        }
        finally {
            try {
                if (rslt != null) rslt.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new SQLQueryException("Error closing database:\n" + e.getMessage());
            }
        }
        return output;
    }

    /**
     * Protective function of getFromDB to enable the entry of variable arguments.
     * @param db Database to use.
     * @param outputLength Size that each result has (number of columns).
     * @param query String SQL with the SELECT statement to perform.
     * @param input Set of values to replace in the query (the ?).
     * @return Arraylist with rows in the form of Array[Object] with the result.
     * @throws SQLQueryException If there is an error in the query.
     */
    public static ArrayList<Object[]> get(AccessDB db, int outputLength, String query, Object... input) throws SQLQueryException {
        return getFromDB(db, outputLength, query, input);
    }

    /**
     * Executes a change in the database.
     * @param db Database to use.
     * @param query Desired query
     * @param input Set of values to replace in the query (the ?)
     * @return Result code given by the database.
     * @throws SQLQueryException If there is an error in the query.
     */
    public static int execute(AccessDB db, String query, Object... input) throws SQLQueryException {
        int result;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = db.getConnection();

            pstmt = con.prepareStatement(query);

            for (int i = 0; i < input.length; i++) {
                pstmt.setObject(i + 1, input[i]);
            }

            result = pstmt.executeUpdate();
        }
        catch (ClassNotFoundException e) {
            throw new SQLQueryException("The chosen driver is not valid:\n" + e.getMessage());
        }
        catch (SQLException e) {
            throw new SQLQueryException("Error on database:\n" + e.getMessage());
        }
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            }
            catch (SQLException e) {
                throw new SQLQueryException("Error closing database:\n" + e.getMessage());
            }
        }

        return result;
    }
}
