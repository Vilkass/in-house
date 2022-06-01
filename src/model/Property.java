package model;

import java.io.File;
import java.util.ArrayList;

public class Property {

    private String name;
    private String desc;
    private String propertyType;
    private String propertyState;
    private double price;
    private String country;
    private String city;
    private String address;
    private byte bathrooms;
    private byte bedrooms;
    private double sqrft;
    private int yearBuild;

    private boolean floorHeating;
    private boolean bath;
    private boolean balcony;
    private boolean parking;
    private boolean fireplace;
    private boolean terrace;
    private boolean storage;
    private boolean wardrobe;
    private boolean highCeilings;
    private boolean security;
    private boolean internet;
    private boolean cableTV;
    private boolean securityAlarm;
    private boolean cameras;
    private boolean separateEntrance;
    private boolean dishwasher;
    private boolean washingMachine;
    private boolean conditioning;
    private ArrayList<File> images;


    public Property(String propertyType, String name, String desc, String propertyState, double price, String country, String city, String address, double sqrft, byte bedrooms, byte bathrooms){
        this.propertyType = propertyType;
        this.name = name;
        this.desc = desc;
        this.propertyState = propertyState;
        this.price = price;
        this.country = country;
        this.city = city;
        this.address = address;
        this.sqrft = sqrft;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
    }

    public Property(String name, String desc, String propertyType, String propertyState, double price, String country, String city, String address, byte bathrooms, byte bedrooms, double sqrft, int yearBuild, boolean floorHeating, boolean bath, boolean balcony, boolean parking, boolean fireplace, boolean terrace, boolean storage, boolean wardrobe, boolean highCeilings, boolean security, boolean internet, boolean cableTV, boolean securityAlarm, boolean cameras, boolean separateEntrance, boolean dishwasher, boolean washingMachine, boolean conditioning) {
        this.name = name;
        this.desc = desc;
        this.propertyType = propertyType;
        this.propertyState = propertyState;
        this.price = price;
        this.country = country;
        this.city = city;
        this.address = address;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.sqrft = sqrft;
        this.yearBuild = yearBuild;
        this.floorHeating = floorHeating;
        this.bath = bath;
        this.balcony = balcony;
        this.parking = parking;
        this.fireplace = fireplace;
        this.terrace = terrace;
        this.storage = storage;
        this.wardrobe = wardrobe;
        this.highCeilings = highCeilings;
        this.security = security;
        this.internet = internet;
        this.cableTV = cableTV;
        this.securityAlarm = securityAlarm;
        this.cameras = cameras;
        this.separateEntrance = separateEntrance;
        this.dishwasher = dishwasher;
        this.washingMachine = washingMachine;
        this.conditioning = conditioning;
    }

    public Property(){}

    public int getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(int yearBuild) {
        this.yearBuild = yearBuild;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyState() {
        return propertyState;
    }

    public double getPrice() {
        return price;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public byte getBathrooms() {
        return bathrooms;
    }

    public byte getBedrooms() {
        return bedrooms;
    }

    public double getSqrft() {
        return sqrft;
    }

    public boolean isFloorHeating() {
        return floorHeating;
    }

    public boolean isBath() {
        return bath;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public boolean isParking() {
        return parking;
    }

    public boolean isFireplace() {
        return fireplace;
    }

    public boolean isTerrace() {
        return terrace;
    }

    public boolean isStorage() {
        return storage;
    }

    public boolean isWardrobe() {
        return wardrobe;
    }

    public boolean isHighCeilings() {
        return highCeilings;
    }

    public boolean isSecurity() {
        return security;
    }

    public boolean isInternet() {
        return internet;
    }

    public boolean isCableTV() {
        return cableTV;
    }

    public boolean isSecurityAlarm() {
        return securityAlarm;
    }

    public boolean isCameras() {
        return cameras;
    }

    public boolean isSeparateEntrance() {
        return separateEntrance;
    }

    public boolean isDishwasher() {
        return dishwasher;
    }

    public boolean isWashingMachine() {
        return washingMachine;
    }

    public boolean isConditioning() {
        return conditioning;
    }

    public void setFloorHeating(boolean floorHeating) {
        this.floorHeating = floorHeating;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public void setFireplace(boolean fireplace) {
        this.fireplace = fireplace;
    }

    public void setTerrace(boolean terrace) {
        this.terrace = terrace;
    }

    public void setStorage(boolean storage) {
        this.storage = storage;
    }

    public void setWardrobe(boolean wardrobe) {
        this.wardrobe = wardrobe;
    }

    public void setHighCeilings(boolean highCeilings) {
        this.highCeilings = highCeilings;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public void setCableTV(boolean cableTV) {
        this.cableTV = cableTV;
    }

    public void setSecurityAlarm(boolean securityAlarm) {
        this.securityAlarm = securityAlarm;
    }

    public void setCameras(boolean cameras) {
        this.cameras = cameras;
    }

    public void setSeparateEntrance(boolean separateEntrance) {
        this.separateEntrance = separateEntrance;
    }

    public void setDishwasher(boolean dishwasher) {
        this.dishwasher = dishwasher;
    }

    public void setWashingMachine(boolean washingMachine) {
        this.washingMachine = washingMachine;
    }

    public void setConditioning(boolean conditioning) {
        this.conditioning = conditioning;
    }

    public String getProperties() {
        String property = "";
        property += bedrooms + " bed";
        if(bedrooms > 1){
            property += "s";
        }
        property += " | " + bathrooms + " ba | " + sqrft + " sqft | " + propertyState;
        return property;
    }

    public void setImages(ArrayList<File> images) {
        this.images = images;
    }
    public ArrayList<File> getImages() {
        return images;
    }
}
