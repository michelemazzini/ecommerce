package model.mo.test;

import model.mo.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {
    @Test
    public void setNomeTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setNome("Nomesbagliato");
        assertNotEquals(nome, utenteTest.getNome());
    }
    
    @Test
    public void setCognomeTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setCognome("Cognomesbagliato");
        assertNotEquals(cognome, utenteTest.getCognome());
    }
    
    @Test
    public void setUsernameTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setUsername("Usernamesbagliato");
        assertNotEquals(username, utenteTest.getUsername());
    }
    
    @Test
    public void setPasswordTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setPassword("Passwordsbagliata");
        assertNotEquals(password, utenteTest.getPassword());
    }
    
    @Test
    public void setCellulareTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setCellulare("Cellularesbagliato");
        assertNotEquals(cellulare, utenteTest.getCellulare());
    }
    
    @Test
    public void setEmailTest(){
        //Creo un utente di test
        String nome= "Michele";
        String cognome="Mazzini";
        String username="MicTest";
        String password="MicTest";
        String cellulare="3317901153";
        String email="michele.mazzini1996@gmail.com";
        String tipo_u="";
        User utenteTest = new User(nome, cognome, username, password, cellulare, email, tipo_u);
        
        utenteTest.setEmail("Emailsbagliata");
        assertNotEquals(email, utenteTest.getEmail());
    }
}
