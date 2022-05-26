use InHouse;

CREATE TABLE PropertyType (ID int primary key auto_increment, TYPE varchar(255) UNIQUE KEY NOT NULL);

CREATE TABLE PropertyState (ID int primary key auto_increment, STATE varchar(255) UNIQUE KEY NOT NULL);

CREATE TABLE Seller (ID int primary key auto_increment, FIRST_NAME varchar(255) NOT NULL, LAST_NAME varchar(255) NOT NULL, EMAIL varchar(255) UNIQUE KEY NOT NULL, PASSWORD varchar(255) NOT NULL);

CREATE TABLE Property(ID int primary key auto_increment, PropertyTypeID int, PropertyStateID int, PRICE DOUBLE NOT NULL, BEDROOMS BIT NOT NULL, BATHROOMS BIT NOT NULL, COUNTRY varchar(255) NOT NULL, CITY varchar(255) NOT NULL, ADDRESS varchar(255) UNIQUE KEY NOT NULL);

ALTER TABLE Property
  ADD CONSTRAINT `property_ibfk_1` FOREIGN KEY (PropertyTypeID) REFERENCES PropertyType (ID);
  
  ALTER TABLE Property
  ADD CONSTRAINT `property_ibfk_2` FOREIGN KEY (PropertyStateID) REFERENCES PropertyState (ID);

CREATE TABLE Announcement(ID int primary key auto_increment, SellerID int, PropertyID int, TITLE varchar(255) NOT NULL, DESCRIPTION TEXT, CREATED_DATE DATE NOT NULL);

ALTER TABLE Announcement
  ADD CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (SellerID) REFERENCES Seller (ID);

ALTER TABLE Announcement
  ADD CONSTRAINT `announcement_ibfk_2` FOREIGN KEY (PropertyID) REFERENCES Property (ID);
  
CREATE TABLE Images(PropertyID int, IMAGE BLOB NOT NULL);

ALTER TABLE Images
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (PropertyID) REFERENCES Property (ID);
  
  
  