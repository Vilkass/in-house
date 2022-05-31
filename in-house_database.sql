use InHouse;

drop table PropertyType;
CREATE TABLE PropertyType (ID int primary key auto_increment, TYPE varchar(255) UNIQUE KEY NOT NULL);
select * from PropertyType;
insert into PropertyType values(0, "House");
insert into PropertyType values(0, "Apartment");

CREATE TABLE PropertyState (ID int primary key auto_increment, STATE varchar(255) UNIQUE KEY NOT NULL);
insert into PropertyState values(0, "For sale");
insert into PropertyState values(0, "For rent");
select * from PropertyState;



DROP TABLE Seller;
CREATE TABLE Seller (ID int primary key auto_increment, FIRST_NAME varchar(255) NOT NULL, LAST_NAME varchar(255) NOT NULL, EMAIL varchar(255) UNIQUE KEY NOT NULL, PASSWORD varchar(255) NOT NULL, PHONE varchar(255) NOT NULL);

drop table property;
CREATE TABLE Property(ID int primary key auto_increment, PropertyTypeID int, PropertyStateID int, PRICE DOUBLE NOT NULL, BEDROOMS BIT NOT NULL, BATHROOMS BIT NOT NULL, COUNTRY varchar(255) NOT NULL, CITY varchar(255) NOT NULL, ADDRESS varchar(255) UNIQUE KEY NOT NULL, SQRFT DOUBLE NOT NULL, FLOOR_HEATING BOOLEAN, BATH BOOLEAN, BALCONY BOOLEAN, PARKING BOOLEAN, FIREPLACE BOOLEAN, TERRACE BOOLEAN, STORAGE BOOLEAN, WARDROBE BOOLEAN, HIGH_CEILINGS BOOLEAN, SECURITY BOOLEAN, INTERNET BOOLEAN, CABLE_TV BOOLEAN, ALARM BOOLEAN, CAMERAS BOOLEAN, ENTRANCE BOOLEAN, DISHWASHER BOOLEAN, WASHING_MACHINE BOOLEAN, CONDITIONING BOOLEAN, SellerID int, NAME varchar(255) NOT NULL, DESCRIPTION TEXT, CREATED_DATE DATE NOT NULL, YEAR int NOT NULL);
select * from property;

ALTER TABLE Property
  ADD CONSTRAINT `property_ibfk_1` FOREIGN KEY (PropertyTypeID) REFERENCES PropertyType (ID);
  
  ALTER TABLE Property
  ADD CONSTRAINT `property_ibfk_2` FOREIGN KEY (PropertyStateID) REFERENCES PropertyState (ID);
  
  ALTER TABLE Property
  ADD CONSTRAINT `property_ibfk_3` FOREIGN KEY (SellerID) REFERENCES Seller (ID);

CREATE TABLE Announcement(ID int primary key auto_increment, SellerID int, PropertyID int, NAME varchar(255) NOT NULL, DESCRIPTION TEXT, CREATED_DATE DATE NOT NULL);

ALTER TABLE Announcement
  ADD CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (SellerID) REFERENCES Seller (ID);

ALTER TABLE Announcement
  ADD CONSTRAINT `announcement_ibfk_2` FOREIGN KEY (PropertyID) REFERENCES Property (ID);
  
CREATE TABLE Images(PropertyID int, IMAGE BLOB NOT NULL);

ALTER TABLE Images
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (PropertyID) REFERENCES Property (ID);