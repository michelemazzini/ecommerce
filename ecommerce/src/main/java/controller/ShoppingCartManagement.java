package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.OrderDAO;
import model.mo.Product;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdministratorDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;
import model.session.mo.LoggedAdministrator;
import services.config.Configuration;

public class ShoppingCartManagement {
  private ShoppingCartManagement(){
  }
  
  public static void view(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "shoppingCartManagement/view");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName=null;
      ArrayList<Product> products = new ArrayList<Product>();
      Product product = null;
      ProductDAO productDAO = daoFactory.getProductDAO();
      Cookie[] cookies = request.getCookies();
      if(cookies != null){
        for(int i=0; i<cookies.length; i++){
          Cookie c = cookies[i];
          productName = c.getValue();
          product = productDAO.searchProductByName(productName);
          if(product!=null){
            products.add(product);  
          }
          product=null;
        }
      }
      request.setAttribute("products", products);
      daoFactory.commitTransaction();
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }

    }
  }
  public static void addProductToCartFromCatalogView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);
      
      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/view");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName = request.getParameter("productName");
      Product product=null;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      if(product != null){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
          Cookie c = null;
          c = new Cookie("productName", productName);
          response.addCookie(c);
        }else{
          boolean yes = false;
          for(int i=0; i<cookies.length; i++){
            if(cookies[i].getValue().equals(productName)) yes=true;
          }
          if(yes!=true){
            int i=cookies.length-1;
            Cookie c = null;
            String cookieName="productName"+i;
            c = new Cookie(cookieName, productName);
            response.addCookie(c);
          }
        }
      }
      
      List<String> categories;
      List<Product> products;
      
      categories = productDAO.listOfCategories();

      String selectedCategory = request.getParameter("selectedCategory");

      if (selectedCategory == null) {
        selectedCategory = categories.get(0);
      }

      products = productDAO.listOfProductByCategory(selectedCategory);

      request.setAttribute("selectedCategory", selectedCategory);
      request.setAttribute("categories", categories);
      request.setAttribute("products", products);
      daoFactory.commitTransaction();
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }
    }
  }
  
  public static void addProductToCartFromProductView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);
      
      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/productView");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName = request.getParameter("productName");
      Product product=null;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      if(product != null){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
          Cookie c = null;
          c = new Cookie("productName", productName);
          response.addCookie(c);
        }else{
          boolean yes = false;
          for(int i=0; i<cookies.length; i++){
            if(cookies[i].getValue().equals(productName)) yes=true;
          }
          if(yes!=true){
            int i=cookies.length-1;
            Cookie c = null;
            String cookieName="productName"+i;
            c = new Cookie(cookieName, productName);
            response.addCookie(c);
          }
        }
      }
      daoFactory.commitTransaction();
      request.setAttribute("product", product);
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }
    }
  }
  
  public static void addProductToCartFromSearchView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;

    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);
      
      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/searchView");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName = request.getParameter("productName");
      Product product=null;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      if(product != null){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
          Cookie c = null;
          c = new Cookie("productName", productName);
          response.addCookie(c);
        }else{
          boolean yes = false;
          for(int i=0; i<cookies.length; i++){
            if(cookies[i].getValue().equals(productName)) yes=true;
          }
          if(yes!=true){
            int i=cookies.length-1;
            Cookie c = null;
            String cookieName="productName"+i;
            c = new Cookie(cookieName, productName);
            response.addCookie(c);
          }
        }
      }
      daoFactory.commitTransaction();
      request.setAttribute("product", product);
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }
    }
  }
  
  public static void confirmOrder(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;

    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);
      
      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "shoppingCartManagement/confirmOrderView");
      
      String priceString = request.getParameter("price");
      float price = Float.parseFloat(priceString);
      request.setAttribute("price", price);
      
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }
    }
  }
  public static void newOrder(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try{
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);
      
      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();
    
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(loggedUser != null) {
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(loggedAdministrator != null){
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null);
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "shoppingCartManagement/successfulOrderView");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      OrderDAO orderDAO = daoFactory.getOrderDAO();
      ProductDAO productDAO = daoFactory.getProductDAO();
      int maxNumero = orderDAO.maxNumeroOrdine();
      int numero = maxNumero + 1;
      String citta = request.getParameter("citta");
      String indirizzo = request.getParameter("indirizzo");
      String num_carta = request.getParameter("numeroCarta");
      String CVV = request.getParameter("CVV");
      String anno_scad_carta = request.getParameter("annoScadenza");
      String mese_scad_carta = request.getParameter("meseScadenza");
      String username_c = loggedUser.getUsername();
      String productName = null;
      String productId = null;
      Product product =null;
      
      orderDAO.newOrder(numero, citta, indirizzo, num_carta, CVV, anno_scad_carta, mese_scad_carta, username_c);
      
      Cookie[] cookies = request.getCookies();
      for(int i=0; i<((cookies.length)-2); i++){
        Cookie c = cookies[i];
        productName = c.getValue();
        product = productDAO.searchProductByName(productName);
        productId = product.getProductId();
        orderDAO.updateComprende(numero, productId, 1);
      }
      
      //elimina cookie dei prodotti, perchè effettuando il pagamento il carrello si svuota
      for(int i=0; i<((cookies.length)-1); i++){
        Cookie c= cookies[i];
        c.setValue("");
        c.setMaxAge(0);
        response.addCookie(c);
      }
      
      daoFactory.commitTransaction();
    }catch (Exception e) {
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction();
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction();
        }
      } catch (Throwable t) {
      }
    }
  }
}
