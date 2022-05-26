package model;

public class AuthenticationModel extends Seller{


    public AuthenticationModel(String email, String password) {
        super(email, password);
    }


    @Override
    protected Seller login() {
        return this;
    }

    @Override
    protected void register() {

    }


}
