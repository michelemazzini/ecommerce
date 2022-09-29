package model.dao;

import java.util.List;
import model.mo.Product;
import model.dao.exception.DuplicatedObjectException;

public interface ProductDAO {
  public List<String> listOfCategories();
  
  public List<Product> listOfProductByCategory(String category);
  
  public Product searchProductByName(String productName);
  
  public void deleteProduct(String productName);
  
  public void insertProduct(String nome, String codice, String marca, String categoria, double prezzo, double larghezza, double altezza, double spessore, int quantita) throws DuplicatedObjectException;
}
