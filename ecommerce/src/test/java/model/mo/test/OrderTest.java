package model.mo.test;

import model.mo.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {
    @Test
    public void setNumeroOrdineTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setNumeroOrdine(1);
        assertNotEquals(numero, ordineTest.getNumeroOrdine());
    }
    
    @Test
    public void setUsername_cTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setUsername_c("usernamesbagliato");
        assertNotEquals(username_c, ordineTest.getUsername_c());
    }
    
    @Test
    public void setIndirizzoTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setIndirizzo("indirizzosbagliato");
        assertNotEquals(indirizzo, ordineTest.getIndirizzo());
    }
    
    @Test
    public void setCittaTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setCitta("cittasbagliata");
        assertNotEquals(citta, ordineTest.getCitta());
    }
    
    @Test
    public void setNum_cartaTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setNum_carta("numerocartasbagliato");
        assertNotEquals(num_carta, ordineTest.getNum_carta());
    }
    
    @Test
    public void setCVVTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setCVV("CVVsbagliato");
        assertNotEquals(CVV, ordineTest.getCVV());
    }
    
    @Test
    public void setAnno_scad_cartaTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setAnno_scad_carta("annosbagliato");
        assertNotEquals(anno_scad_carta, ordineTest.getAnno_scad_carta());
    }
    
    @Test
    public void setMese_scad_cartaTest(){
        //Creo un ordine di test
        int numero = 0;
        String indirizzo = "Via di test";
        String username_c ="MicTest";
        String citta = "test";
        String num_carta = "1234567812345678";
        String CVV = "123";
        String anno_scad_carta = "2022";
        String mese_scad_carta = "11";
        Order ordineTest = new Order(numero, indirizzo, username_c, citta, num_carta, CVV, anno_scad_carta, mese_scad_carta);
        
        ordineTest.setMese_scad_carta("mesesbagliato");
        assertNotEquals(mese_scad_carta, ordineTest.getMese_scad_carta());
    }
}
