package database;

import java.sql.*;

public class DbOperations {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=InHouse;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "root";
    private static final String PASS = "slaptazodis";

    private static Connection connection;
    private static PreparedStatement statement;

    private static Connection connectToDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void disconnectFromDb() {
        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}
