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
    
    @Test
    public void setMarcaTest(){
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
        
        prodottoTest.setMarca("marcasbagliata");
        assertNotEquals(marca, prodottoTest.getMarca());
    }
    
    @Test
    public void setCategoriaTest(){
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
        
        prodottoTest.setCategoria("marcasbagliata");
        assertNotEquals(categoria, prodottoTest.getCategoria());
    }
    
    @Test
    public void setPrezzoTest(){
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
        
        prodottoTest.setPrezzo(99);
        assertNotEquals(prezzo, prodottoTest.getPrezzo());
    }
    
    @Test
    public void setLarghezzaTest(){
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
        
        prodottoTest.setLarghezza(99);
        assertNotEquals(larghezza, prodottoTest.getLarghezza());
    }
    
    @Test
    public void setAltezzaTest(){
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
        
        prodottoTest.setAltezza(99);
        assertNotEquals(altezza, prodottoTest.getAltezza());
    }
    
    @Test
    public void setSpessoreTest(){
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
        
        prodottoTest.setSpessore(99);
        assertNotEquals(spessore, prodottoTest.getSpessore());
    }
    
    @Test
    public void setQuantitaTest(){
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
        
        prodottoTest.setQuantita(99);
        assertNotEquals(quantita, prodottoTest.getQuantita());
    }
}
