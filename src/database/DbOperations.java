package database;

import model.Property;
import model.Seller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbOperations {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/InHouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
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
        sql = "SELECT FIRST_NAME, LAST_NAME, PHONE, PASSWORD FROM Seller WHERE EMAIL = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, seller.getEmail());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            seller.setFirstName(rs.getString(1));
            seller.setLastName(rs.getString(2));
            seller.setPhone(rs.getString(3));
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(!encoder.matches(seller.getPassword(), rs.getString(4))){
                throw new Exception("Wrong username/password!");
            }
        }else {
            throw new Exception("Wrong username/password!");
        }
        disconnectFromDb();
        return seller;
    }

    public static void savePropertyImage(File file, int propertyID, int index) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        sql = "INSERT INTO Images VALUES(?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, propertyID);
        statement.setBinaryStream(2, fis, (int) file.length());
        statement.setInt(3, index);
        statement.execute();
    }

    public static ArrayList<File> getPropertyImages(int propertyID) throws Exception{
        ArrayList<File> images = new ArrayList<>();
        connection = connectToDb();
        sql = "SELECT IMAGE, INDEKS FROM Images WHERE PropertyID = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, propertyID);
        ResultSet rs = statement.executeQuery();
        Blob blob;
        File file;
        FileOutputStream fos;
        byte b[];
        while(rs.next()){
            file = new File("/Users/anton/Desktop/Projektai/in-house/src/resources/temp/Property"+propertyID+"file"+rs.getInt(2)+".png");
            fos = new FileOutputStream(file);
            blob = rs.getBlob(1);
            b=blob.getBytes(1,(int)blob.length());
            fos.write(b);
            images.add(file);
        }
        disconnectFromDb();
        return images;
    }

    public static ArrayList<String> getPropertyTypes(){
        ArrayList<String> types = new ArrayList<>();
        try{
            connection = connectToDb();
            sql = "SELECT TYPE FROM PropertyType";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                types.add(rs.getString(1));
            }
            disconnectFromDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        return types;
    }


    public static ArrayList<Property> getPropertyList(Seller seller) {
       ArrayList<Property> properties = new ArrayList<>();
        try{
            connection = connectToDb();
            int sellerID = getSellerID(seller);
            sql = "select p.NAME, p.DESCRIPTION, pt.TYPE, ps.STATE, p.PRICE, p.COUNTRY, p.CITY, p.ADDRESS, p.BATHROOMS, p.BEDROOMS, p.SQRFT, p.YEAR, p.FLOOR_HEATING, p.BATH,p.BALCONY, p.PARKING,p.FIREPLACE, p.TERRACE, p.STORAGE, p.WARDROBE, p.HIGH_CEILINGS, p.SECURITY, p.INTERNET, p.CABLE_TV, p.ALARM, p.CAMERAS, p.ENTRANCE, p.DISHWASHER, p.WASHING_MACHINE, p.CONDITIONING, p.ID from property as p INNER JOIN PropertyType as pt ON p.PropertyTypeID = pt.ID INNER JOIN PropertyState as ps ON p.PropertyStateID = ps.ID WHERE p.SellerID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, sellerID);
            ResultSet rs = statement.executeQuery();
            Property property;
            while (rs.next()){
                property = new Property(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getByte(9), rs.getByte(10), rs.getDouble(11), rs.getInt(12), rs.getBoolean(13), rs.getBoolean(14),  rs.getBoolean(15), rs.getBoolean(16) , rs.getBoolean(17) , rs.getBoolean(18) , rs.getBoolean(19) , rs.getBoolean(20) , rs.getBoolean(21), rs.getBoolean(22), rs.getBoolean(23), rs.getBoolean(24), rs.getBoolean(25), rs.getBoolean(26), rs.getBoolean(27), rs.getBoolean(28), rs.getBoolean(29), rs.getBoolean(30)            );
                property.setImages(getPropertyImages(rs.getInt(31)));
                properties.add(property);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }


    public static void saveProperty(Property property, Seller seller) throws Exception {
        connection = connectToDb();
        int typeID = getPropertyTypeID(property);
        int stateID = getPropertySateID(property);
        int sellerID = getSellerID(seller);
        sql = "INSERT INTO Property VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, typeID);
        statement.setInt(3, stateID);
        statement.setDouble(4, property.getPrice());
        statement.setByte(5, property.getBedrooms());
        statement.setByte(6, property.getBathrooms());
        statement.setString(7, property.getCountry());
        statement.setString(8, property.getCity());
        statement.setString(9, property.getAddress());
        statement.setDouble(10, property.getSqrft());
        statement.setBoolean(11, property.isFloorHeating());
        statement.setBoolean(12, property.isBath());
        statement.setBoolean(13, property.isBalcony());
        statement.setBoolean(14, property.isParking());
        statement.setBoolean(15, property.isFireplace());
        statement.setBoolean(16, property.isTerrace());
        statement.setBoolean(17, property.isStorage());
        statement.setBoolean(18, property.isWardrobe());
        statement.setBoolean(19, property.isHighCeilings());
        statement.setBoolean(20, property.isSecurity());
        statement.setBoolean(21, property.isInternet());
        statement.setBoolean(22, property.isCableTV());
        statement.setBoolean(23, property.isSecurityAlarm());
        statement.setBoolean(24, property.isCameras());
        statement.setBoolean(25, property.isSeparateEntrance());
        statement.setBoolean(26, property.isDishwasher());
        statement.setBoolean(27, property.isWashingMachine());
        statement.setBoolean(28, property.isConditioning());
        statement.setInt(29, sellerID);
        statement.setString(30, property.getName());
        statement.setString(31, property.getDesc());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        statement.setString(32, formatter.format(date));
        statement.setInt(33, property.getYearBuild());
        statement.execute();

        ArrayList<File> images = property.getImages();
        int propertyID  = getPropertyID(property);
        for(int i = 0; i < images.size(); i++){
            savePropertyImage(images.get(i), propertyID, i);
        }
        disconnectFromDb();
    }

    private static int getPropertyID(Property property) throws SQLException {
        int propertyID = 0;
        sql = "SELECT ID FROM Property WHERE ADDRESS = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, property.getAddress());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            propertyID = rs.getInt(1);
        }
        return propertyID;
    }

    private static int getSellerID(Seller seller) throws SQLException {
        int sellerID = 0;
        sql = "SELECT ID FROM Seller WHERE EMAIL = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, seller.getEmail());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            sellerID = rs.getInt(1);
        }
        return sellerID;
    }

    private static int getPropertyTypeID(Property property) throws SQLException {
        int typeID = 0;
        sql = "SELECT ID FROM PropertyType WHERE TYPE = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, property.getPropertyType());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            typeID = rs.getInt(1);
        }
        return typeID;
    }

    private static int getPropertySateID(Property property) throws SQLException {
        int stateID = 0;
        sql = "SELECT ID FROM PropertyState WHERE STATE = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, property.getPropertyState());
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
            stateID = rs.getInt(1);
        }
        return stateID;
    }

}
