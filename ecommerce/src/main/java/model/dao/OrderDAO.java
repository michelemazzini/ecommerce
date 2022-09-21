package model.dao;

import java.util.List;
import model.mo.Order;
import model.dao.exception.DuplicatedObjectException;

public interface OrderDAO {
  public List<Order> listOfOrdersByUser(String username);
  
  public List<Order> listOfOrders();
  
  public void newOrder(int numero, String citta, String indirizzo, String num_carta, String CVV, String anno_scad_carta, String mese_scad_carta, String usernme_c);
  
  public void updateComprende(int numero_o, String productId, int item_compresi); 
  
  public int maxNumeroOrdine();
  
  public Order searchOrderByNumber(int number);
}
