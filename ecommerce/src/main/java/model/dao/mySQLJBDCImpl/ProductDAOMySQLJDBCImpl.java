package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.mo.Product;
import model.dao.ProductDAO;
import model.dao.exception.DuplicatedObjectException;


public class ProductDAOMySQLJDBCImpl implements ProductDAO {
  Connection conn;

  public ProductDAOMySQLJDBCImpl(Connection conn) {
    this.conn = conn;
  }
  
  @Override
  public Product searchProductByName(String productName){
    PreparedStatement ps;
    Product product;
      
    try {
      String sql = " SELECT * FROM item WHERE nome = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, productName);
      ResultSet resultSet = ps.executeQuery();
      
      resultSet.next();
      product = read(resultSet);
      
      resultSet.close();
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return product;
  }
  
  @Override
  public void insertProduct(String nome, String codice, String marca, String categoria, float prezzo, float larghezza, float altezza, float spessore, int quantita) throws DuplicatedObjectException{
    PreparedStatement ps;
    
    String prezzoString = Float.toString(prezzo);
    String larghezzaString = Float.toString(larghezza);
    String altezzaString = Float.toString(altezza);
    String spessoreString = Float.toString(spessore);
    String quantitaString = Integer.toString(quantita);
    
    try{
      String sql = " SELECT * FROM item WHERE nome = ? AND codice = ?";
      
      ps = conn.prepareStatement(sql);
      ps.setString(1, nome);
      ps.setString(2, codice);
      
      ResultSet resultSet = ps.executeQuery();
      
      boolean exist;
      exist = resultSet.next();
      resultSet.close();
      
      if (exist) {
        throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un prodotto già esistente.");
      }
      
      sql = " INSERT INTO item(nome, codice, marca, categoria, prezzo, larghezza, altezza, spessore, quantita) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
      ps = conn.prepareStatement(sql);
      
      ps.setString(1, nome);
      ps.setString(2, codice);
      ps.setString(3, marca);
      ps.setString(4, categoria);
      ps.setString(5, prezzoString);
      ps.setString(6, larghezzaString);
      ps.setString(7, altezzaString);
      ps.setString(8, spessoreString);
      ps.setString(9, quantitaString);
      
      ps.executeUpdate();
      ps.close();
      
    }catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void deleteProduct(String productName){
    PreparedStatement ps;
    
    try{
      String sql = " DELETE FROM item WHERE nome = ?";
      
      ps = conn.prepareStatement(sql);
      ps.setString(1, productName);
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
          
  @Override
  public List<String> listOfCategories() {
    PreparedStatement ps;
    String category;
    ArrayList<String> categories = new ArrayList<String>();

    try {
      String sql = " SELECT DISTINCT categoria FROM item";

      ps = conn.prepareStatement(sql);
      
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        category = resultSet.getString("categoria");
        categories.add(category);
      }

      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return categories;
  }
  
  @Override
  public List<Product> listOfProductByCategory(String category) {
    PreparedStatement ps;
    Product product;
    ArrayList<Product> products = new ArrayList<Product>();

    try {
      String sql = " SELECT * FROM item WHERE categoria = ?";

      ps = conn.prepareStatement(sql);
      ps.setString(1, category);

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        product = read(resultSet);
        products.add(product);
      }

      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return products;
  }

  Product read(ResultSet rs) {
    Product product = new Product();
    try {
      product.setNome(rs.getString("nome"));
    } catch (SQLException sqle) {
    }
    try {
      product.setProductId(rs.getString("codice"));
    } catch (SQLException sqle) {
    }
    try {
      product.setMarca(rs.getString("marca"));
    } catch (SQLException sqle) {
    }
    try {
      product.setCategoria(rs.getString("categoria"));
    } catch (SQLException sqle) {
    }
    try {
      product.setPrezzo(rs.getFloat("prezzo"));
    } catch (SQLException sqle) {
    }
    try {
      product.setLarghezza(rs.getFloat("larghezza"));
    } catch (SQLException sqle) {
    }
    try {
      product.setAltezza(rs.getFloat("altezza"));
    } catch (SQLException sqle) {
    }
    try {
      product.setSpessore(rs.getFloat("spessore"));
    } catch (SQLException sqle) {
    }
    try {
      product.setQuantita(rs.getInt("quantita"));
    } catch (SQLException sqle) {
    }
    return product;
  }
}
