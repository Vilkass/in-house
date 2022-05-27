package model;

import java.util.regex.Pattern;

public class Seller {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String phone;

    public Seller(String firstName, String lastName, String email, String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Seller(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public void verifyEmail() throws Exception{
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            throw new RuntimeException("Email cannot be empty");
        if(!pat.matcher(email).matches())
            throw new RuntimeException("Invalid email format");
    }
    public void verifyPassword() throws Exception{
        if(!(password.length() >= 7)){
            throw new RuntimeException("Invalid password length");
        }
    }
    public void verifyFirstName() throws Exception{
        if(!((firstName).matches("[a-zA-Z]+"))){
            throw new RuntimeException("Invalid first name format");
        }
    }
    public void verifyLastName() throws Exception{
        if(!((lastName).matches("[a-zA-Z]+"))){
            throw new RuntimeException("Invalid last name format");
        }
    }
    public void verifyPhone() throws Exception{
        if(!((phone).matches("[+][0-9]+"))){
            throw new RuntimeException("Invalid phone format (should be +XXXXX)");
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
