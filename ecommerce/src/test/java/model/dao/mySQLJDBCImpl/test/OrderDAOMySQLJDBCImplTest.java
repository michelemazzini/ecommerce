package model.dao.mySQLJDBCImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.UserDAO;
import services.config.Configuration;
import model.mo.Order;

public class OrderDAOMySQLJDBCImplTest {
    @Test
    public void searchOrderByNumberTest(){
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
            
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.insertIntoUtenteRegistrato(nome, cognome, username, password, cellulare, email, tipo_u);
            userDAO.insertIntoCliente(username);
            
            //Inserisco un ordine di test nel database
            int numero = 0;
            String indirizzo = "Via di test";
            String username_c ="MicTest";
            String citta = "test";
            String num_carta = "1234567812345678";
            String CVV = "123";
            String anno_scad_carta = "2022";
            String mese_scad_carta = "11";
            Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
            
            OrderDAO orderDAO = daoFactory.getOrderDAO();
            orderDAO.newOrder(numero, citta, indirizzo, num_carta, CVV, anno_scad_carta, mese_scad_carta, username_c);

            //Test vero e proprio
            Order ordine = orderDAO.searchOrderByNumber(numero);
            assertEquals(ordine, ordineTest);
            
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
