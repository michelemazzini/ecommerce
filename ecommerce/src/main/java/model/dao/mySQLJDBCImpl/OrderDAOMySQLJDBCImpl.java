package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.mo.Order;
import model.dao.OrderDAO;

public class OrderDAOMySQLJDBCImpl implements OrderDAO {
  Connection conn;

  public OrderDAOMySQLJDBCImpl(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public Order searchOrderByNumber(int number){
    Order order;
    PreparedStatement ps;
    String numberString = String.valueOf(number).toString();
    try{
      String sql = " SELECT * FROM ordine WHERE numero = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, numberString);
      ResultSet resultSet = ps.executeQuery();
      resultSet.next();
      order = read(resultSet);
      
      resultSet.close();
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return order;
  }
  
  @Override
  public List<Order> listOfOrdersByUser(String username){
    PreparedStatement ps;
    Order order;
    ArrayList<Order> orders = new ArrayList<Order>();
    try{
      String sql = " SELECT * FROM ordine WHERE username_c = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      
      ResultSet resultSet = ps.executeQuery();
      
      while (resultSet.next()) {
        order = read(resultSet);
        orders.add(order);
      }
      
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    
    return orders;
  }
  
  @Override
  public List<Order> listOfOrders(){
    Order order;
    ArrayList<Order> orders = new ArrayList<Order>();
    PreparedStatement ps;
    
    try{
      String sql = " SELECT * FROM ordine";
      ps = conn.prepareStatement(sql);
      
      ResultSet resultSet = ps.executeQuery();
      
      while (resultSet.next()) {
        order = read(resultSet);
        orders.add(order);
      }
      
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
      
    return orders;
  }
  
  @Override
  public void newOrder(int numero, String citta, String indirizzo, String num_carta, String CVV, String anno_scad_carta, String mese_scad_carta, String username_c){
    PreparedStatement ps;
    
    try{
      String sql = " INSERT INTO ordine(numero, citta, indirizzo, num_carta, CVV, anno_scad_carta, mese_scad_carta, username_c) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, numero);
      ps.setString(2, citta);
      ps.setString(3, indirizzo);
      ps.setString(4, num_carta);
      ps.setString(5, CVV);
      ps.setString(6, anno_scad_carta);
      ps.setString(7, mese_scad_carta);
      ps.setString(8, username_c);
      
      ps.executeUpdate();
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void updateComprende(int numero_o, String productId, int item_compresi){
    PreparedStatement ps;
    
    try{
      String sql = " INSERT INTO comprende(numero_o, codice_i, item_compresi) VALUES(?, ?, ?)";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, numero_o);
      ps.setString(2, productId);
      ps.setInt(3, item_compresi);
      
      ps.executeUpdate();
      ps.close();
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public int maxNumeroOrdine(){
    PreparedStatement ps;
    int maxNumeroOrdine = 1;
    try{
      String sql = " SELECT MAX(numero) AS numero FROM ordine";
      ps = conn.prepareStatement(sql);
      
      ResultSet resultSet = ps.executeQuery();
      resultSet.next();
      maxNumeroOrdine = resultSet.getInt("numero");
      
      resultSet.close();
      ps.close(); 
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return maxNumeroOrdine;
  }
  
  @Override
  public void deleteOrder(int numero){
    PreparedStatement ps;
    
    try{
      String sql = " DELETE FROM order WHERE nome = ?";
      
      ps = conn.prepareStatement(sql);
      ps.setInt(1, numero);
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  Order read(ResultSet rs) {
    Order order = new Order();
    try {
      order.setNumeroOrdine(rs.getInt("numero"));
    } catch (SQLException sqle) {
    }
    try {
      order.setCitta(rs.getString("citta"));
    } catch (SQLException sqle) {
    }
    try {
      order.setIndirizzo(rs.getString("indirizzo"));
    } catch (SQLException sqle){
    }
    try {
      order.setNum_carta(rs.getString("num_carta"));
    } catch (SQLException sqle) {
    }
    try {
      order.setCVV(rs.getString("CVV"));
    } catch (SQLException sqle) {
    }
    try {
      order.setAnno_scad_carta(rs.getString("anno_scad_carta"));
    } catch (SQLException sqle) {
    }
    try {
      order.setMese_scad_carta(rs.getString("mese_scad_carta"));
    } catch (SQLException sqle) {
    }
    try {
      order.setUsername_c(rs.getString("username_c"));
    } catch (SQLException sqle) {
    }
    return order;
  }
}
