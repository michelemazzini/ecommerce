package model.mo.test;

import model.mo.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProductTest {
    @Test
    public void setNomeTest(){
        //Creo un prodotto di test
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
        
        prodottoTest.setNome("nomesbagliato");
        assertNotEquals(nome, prodottoTest.getNome());
    }
    
    @Test
    public void setProductIdTest(){
        //Creo un prodotto di test
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
        
        prodottoTest.setProductId("productidsbagliato");
        assertNotEquals(productId, prodottoTest.getProductId());
    }
}
