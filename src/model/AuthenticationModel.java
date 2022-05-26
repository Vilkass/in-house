package model;

public class AuthenticationModel extends Seller{


    public AuthenticationModel(String email, String password) {
        super(email, password);
    }
    public AuthenticationModel(String firstName, String lastName, String email, String password, String phone) {
        super(firstName, lastName, email, password, phone);
    }


    @Override
    protected Seller login() {
        return this;
    }

    @Override
    protected void register() {

    }


}
