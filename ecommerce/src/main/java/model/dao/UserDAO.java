package model.dao;

import model.dao.exception.DuplicatedObjectException;
import model.mo.User;
import java.util.List;

public interface UserDAO {

  public void insertIntoUtenteRegistrato(String nome, String cognome, String username, String password, String cellulare, String email, String tipo_u) throws DuplicatedObjectException;
  
  public void insertIntoCliente(String username);
  
  public void insertIntoAmministratore(String username);
  
  public User findClienteByUsername(String username);
  
  public User findAmministratoreByUsername(String username);
  
  public List<User> listOfClienti();
  
  public List<User> listOfAmministratori();
  
  public void removeUser(String username);
  
  public int numberOfOrdersByUser(String username);
}
