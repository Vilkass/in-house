package model;

import java.sql.SQLException;

public interface Authentication {
    public Seller login() throws Exception;
    public void register() throws SQLException;
}
