package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.dao.exception.DuplicatedObjectException;
import model.mo.User;
import model.dao.UserDAO;


public class UserDAOMySQLJDBCImpl implements UserDAO {  
  Connection conn;

  public UserDAOMySQLJDBCImpl(Connection conn) {
    this.conn = conn;
  }
 
  @Override
  public void removeUser(String username){
    PreparedStatement ps;
    
    try{
      String sql = " DELETE FROM utenteregistrato WHERE username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ps.executeUpdate();
      
      ps.close();
    }catch(SQLException e){
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public List<User> listOfAmministratori(){
    User user;
    ArrayList<User> users = new ArrayList<User>();
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM utenteregistrato, amministratore WHERE amministratore.username = utenteregistrato.username";
      ps = conn.prepareStatement(sql);
      
      ResultSet resultSet = ps.executeQuery();
      
      while (resultSet.next()) {
        user = read(resultSet);
        users.add(user);
      }
      
      resultSet.close();
      ps.close();
    } catch(SQLException e){
      throw new RuntimeException(e);
    }
    
    return users;
  }
  
  @Override
  public List<User> listOfClienti(){
    User user;
    ArrayList<User> users = new ArrayList<User>();
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM utenteregistrato, cliente WHERE cliente.username = utenteregistrato.username";
      ps = conn.prepareStatement(sql);
      
      ResultSet resultSet = ps.executeQuery();
      
      while (resultSet.next()) {
        user = read(resultSet);
        users.add(user);
      }
      
      resultSet.close();
      ps.close();
    } catch(SQLException e){
      throw new RuntimeException(e);
    }
    
    return users;
  }
  
  @Override
  public int numberOfOrdersByUser(String username){
    PreparedStatement ps;
    int n = 0;
    
    try{
      String sql = " SELECT COUNT(*) AS nordini FROM ordine WHERE username_c = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ResultSet resultSet = ps.executeQuery();
      resultSet.next();
      
      n = resultSet.getInt("nordini");
              
      resultSet.close();
      ps.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return n;
  }
  
  @Override
  public void insertIntoUtenteRegistrato(String nome, String cognome, String username, String password, String cellulare, String email, String tipo_u) throws DuplicatedObjectException{
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM utenteregistrato WHERE username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ResultSet resultSet = ps.executeQuery();
      
      boolean exist;
      exist = resultSet.next();
      resultSet.close();

      if (exist) {
        throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un contatto già esistente.");
      }
      
      sql = " INSERT INTO utenteregistrato(nome, cognome, username, password, cellulare, email, tipo_u) VALUES(?, ?, ?, ?, ?, ?, ?)";
      ps = conn.prepareStatement(sql);
      ps.setString(1, nome);
      ps.setString(2, cognome);
      ps.setString(3, username);
      ps.setString(4, password);
      ps.setString(5, cellulare);
      ps.setString(6, email);
      ps.setString(7, tipo_u);
      
      ps.executeUpdate();
      ps.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void insertIntoAmministratore(String username){
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM amministratore WHERE username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ResultSet resultSet = ps.executeQuery();
      
      boolean exist;
      exist = resultSet.next();
      resultSet.close();
      
      if (!exist) {
        sql = " INSERT INTO amministratore(username) VALUES(?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
      
        ps.executeUpdate();
        ps.close();
      } 
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } 
  }
  
  @Override
  public void insertIntoCliente(String username){
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM cliente WHERE username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ResultSet resultSet = ps.executeQuery();
      
      boolean exist;
      exist = resultSet.next();
      resultSet.close();

      if (!exist) {
        sql = " INSERT INTO cliente(username) VALUES(?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, username);
      
        ps.executeUpdate();
        ps.close();
      } 
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public User findClienteByUsername(String username) {

    PreparedStatement ps;
    User user = null;

    try {

      String sql = " SELECT * FROM utenteregistrato, cliente WHERE cliente.username = ? AND cliente.username = utenteregistrato.username";

      ps = conn.prepareStatement(sql);
      ps.setString(1, username);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return user;

  }  
  
  @Override
  public User findAmministratoreByUsername(String username) {
    PreparedStatement ps;
    User user = null;
    
    try {
      String sql = " SELECT * FROM utenteregistrato, amministratore WHERE amministratore.username = ? AND amministratore.username = utenteregistrato.username";
      
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return user;    
  }
  
  User read(ResultSet rs) {
    
    User user = new User();
    try {
      user.setNome(rs.getString("nome"));
    } catch (SQLException sqle) {
    }
    try {
      user.setCognome(rs.getString("cognome"));
    } catch (SQLException sqle) {
    }
    try {
      user.setUsername(rs.getString("username"));
    } catch (SQLException sqle) {
    }
    try {
      user.setPassword(rs.getString("password"));
    } catch (SQLException sqle) {
    }
    try {
      user.setCellulare(rs.getString("cellulare"));
    } catch (SQLException sqle) {
    }
    try {
      user.setEmail(rs.getString("email"));
    } catch (SQLException sqle) {
    }
    try {
      user.setTipo_u(rs.getString("tipo_u"));
    } catch (SQLException sqle) {
    }
    return user;
  }
}
