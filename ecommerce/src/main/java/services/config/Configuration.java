package services.config;

import model.dao.DAOFactory;
import model.session.dao.SessionDAOFactory;

public class Configuration {
    // Database Configruation 
    public static final String DAO_IMPL=DAOFactory.MYSQLJDBCIMPL;
    public static final String DATABASE_DRIVER="com.mysql.jdbc.Driver";
    public static final String DATABASE_URL="jdbc:mysql://localhost/ecommerce?user=root&password=";
  
    //Session Configuration
    public static final String SESSION_IMPL=SessionDAOFactory.COOKIEIMPL;
}
