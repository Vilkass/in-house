package model;

import database.DbOperations;

import java.sql.SQLException;

public class AuthenticationModel extends Seller implements Authentication{


    public AuthenticationModel(String email, String password) {
        super(email, password);
    }
    public AuthenticationModel(String firstName, String lastName, String email, String password, String phone) {
        super(firstName, lastName, email, password, phone);
    }


    @Override
    public Seller login() throws Exception {
        return DbOperations.loginSeller(this);
    }

    @Override
    public void register() throws SQLException {
        DbOperations.registerSeller(this);
    }
}
