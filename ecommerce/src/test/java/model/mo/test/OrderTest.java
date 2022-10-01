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
}
