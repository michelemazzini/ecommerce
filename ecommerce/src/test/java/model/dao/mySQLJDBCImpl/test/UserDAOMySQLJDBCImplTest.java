package model.dao.mySQLJDBCImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import services.config.Configuration;
import model.mo.User;

public class UserDAOMySQLJDBCImplTest {
    @Test
    public void findClienteByUsernameTest(){
        DAOFactory daoFactory = null;
        
        try {
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction(); 
            
            //Inserisco un utente di test nel database
            String nome= "Michele";
            String cognome="Mazzini";
            String username="MicTest";
            String password="MicTest";
            String cellulare="3317901153";
            String email="michele.mazzini1996@gmail.com";
            String tipo_u="cliente";
            User clienteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
            
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.insertIntoUtenteRegistrato(nome, cognome, username, password, cellulare, email, tipo_u);
            userDAO.insertIntoCliente(username);
            
            //Test vero e proprio
            User cliente = userDAO.findClienteByUsername(username);
            assertEquals(cliente, clienteTest); 
            
            //Elimino l'utente di test dal database
            userDAO.removeUser(username);
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
