package model.dao.mySQLJDBCImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.dao.DAOFactory;
import model.dao.OrderDAO;
import services.config.Configuration;
import model.mo.Order;

public class OrderDAOMySQLJDBCImplTest {
    @Test
    public void searchProductByNameTest(){
        DAOFactory daoFactory = null;
        
        try {
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction(); 
           
            //Inserisco un ordine di test nel database
            
            
            //Test vero e proprio
            
            daoFactory.commitTransaction();
        } catch (Exception e) {
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
}
