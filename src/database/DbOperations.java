package database;

import javafx.scene.image.Image;
import model.Seller;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class DbOperations {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/InHouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "slaptazodis";

    private static Connection connection;
    private static PreparedStatement statement;
    private static String sql;

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

    public static void registerSeller(Seller seller) throws SQLException {
        connection = connectToDb();
        sql = "INSERT INTO Seller(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHONE) VALUES(?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, seller.getFirstName());
        statement.setString(2, seller.getLastName());
        statement.setString(3, seller.getEmail());
        statement.setString(4, seller.getPassword());
        statement.setString(5, seller.getPhone());
        statement.execute();
        disconnectFromDb();
    }

    public static Seller loginSeller(Seller seller) throws Exception {
        connection = connectToDb();
        sql = "SELECT FIRST_NAME, LAST_NAME, PHONE FROM Seller WHERE EMAIL = ? AND PASSWORD = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, seller.getEmail());
        statement.setString(2, seller.getPassword());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            seller.setFirstName(rs.getString(1));
            seller.setLastName(rs.getString(2));
            seller.setPhone(rs.getString(3));
        }else {
            throw new Exception("Wrong username/password!");
        }
        disconnectFromDb();
        return seller;
    }

    public static void savePropertyImage(File file) throws Exception {

        int propertyID = 0;
        FileInputStream fis = new FileInputStream(file);
        connection = connectToDb();
        sql = "INSERT INTO Images VALUES(?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, propertyID);
        statement.setBinaryStream(2, fis, (int) file.length());
        statement.execute();
        disconnectFromDb();
    }

    public static ArrayList<BufferedImage> getPropertyImages(int propertyID) throws Exception{
        ArrayList<BufferedImage> images = new ArrayList<>();
        connection = connectToDb();
        sql = "SELECT IMAGE FROM Images WHERE PropertyID = ?";
        statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            InputStream is = rs.getBinaryStream(1);
            images.add(ImageIO.read(is));
        }
        disconnectFromDb();
        return images;
    }


}
