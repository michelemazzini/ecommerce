package model.dao.mySQLJDBCImpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import services.config.Configuration;
import model.mo.Product;

public class ProductDAOMySQLJDBCImplTest {
    @Test
    public void searchProductByNameTest(){
        DAOFactory daoFactory = null;
        
        try {
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction(); 
            
            //Inserisco un prodotto di test nel database
            String nome = "prodotto_test";
            String productId = "id_test";
            String marca = "marcaTest";
            String categoria = "categoriaTest";
            double prezzo = 1.1;
            double larghezza = 1.1;
            double altezza = 1.1;
            double spessore = 1.1;
            int quantita = 1;
            
            Product prodottoTest = new Product(nome, productId, marca, categoria, prezzo, larghezza, altezza, spessore, quantita);
            
            ProductDAO productDAO = daoFactory.getProductDAO();
            productDAO.insertProduct(nome, productId, marca, categoria, prezzo, larghezza, altezza, spessore, quantita);
            
            //Test vero e proprio
            Product prodotto = productDAO.searchProductByName(nome);
            assertEquals(prodotto, prodottoTest);
                    
            productDAO.deleteProduct(nome);
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
