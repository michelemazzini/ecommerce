package controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.config.Configuration;
import model.mo.User;
import model.dao.UserDAO;
import model.mo.Product;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.exception.DuplicatedObjectException;
import model.session.mo.LoggedUser;
import model.session.mo.LoggedAdministrator;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdministratorDAO;

public class CatalogManagement {

  private CatalogManagement() {
  }
  
  public static void detailsView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory=null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage=null;
    
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
      Product product;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
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
  
  public static void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try {
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
      ProductDAO productDAO = daoFactory.getProductDAO();
      
      String nomeProdotto = request.getParameter("productName");
      
      productDAO.deleteProduct(nomeProdotto);
      
      List<String> categories;
      List<Product> products;

      categories = productDAO.listOfCategories();

      String selectedCategory = selectedCategory = categories.get(0);
      
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
  
  public static void insertProduct(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try {
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
      ProductDAO productDAO = daoFactory.getProductDAO();
      
      String nomeProdotto = request.getParameter("productName");
      String codiceProdotto = request.getParameter("codiceProdotto");
      String categoriaProdotto = request.getParameter("categoriaProdotto");
      String marcaProdotto = request.getParameter("marcaProdotto");
      String prezzoProdottoString = request.getParameter("prezzoProdotto");
      float prezzoProdotto = Float.parseFloat(prezzoProdottoString);
      String larghezzaProdottoString = request.getParameter("larghezzaProdotto");
      float larghezzaProdotto = Float.parseFloat(larghezzaProdottoString);
      String altezzaProdottoString = request.getParameter("altezzaProdotto");
      float altezzaProdotto = Float.parseFloat(altezzaProdottoString);
      String spessoreProdottoString = request.getParameter("spessoreProdotto");
      float spessoreProdotto = Float.parseFloat(spessoreProdottoString);
      String quantitaProdottoString = request.getParameter("quantitaProdotto");
      int quantitaProdotto = Integer.parseInt(quantitaProdottoString);
      
      try{
        productDAO.insertProduct(nomeProdotto, codiceProdotto, marcaProdotto, categoriaProdotto, prezzoProdotto, larghezzaProdotto, altezzaProdotto, spessoreProdotto, quantitaProdotto);
      }catch(DuplicatedObjectException e) {
        applicationMessage = "Prodotto già esistente";
        request.setAttribute("applicationMessage", applicationMessage);
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
  
  public static void view(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;

    try {
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
      
      List<String> categories;
      List<Product> products;

      ProductDAO productDAO = daoFactory.getProductDAO();
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
      
    } catch (Exception e) {
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

  public static void logon(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    int i = 0;
    
    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      String username = request.getParameter("username");
      String password = request.getParameter("password");

      UserDAO userDAO = daoFactory.getUserDAO();
      User user = userDAO.findClienteByUsername(username);
              
      if (user == null || !user.getPassword().equals(password)) {
        loggedUserDAO.destroy();
        loggedUser = null;
        i = 1;
        user = userDAO.findAmministratoreByUsername(username);
        if(user == null || !user.getPassword().equals(password)){
          loggedAdministratorDAO.destroy();
          loggedAdministrator = null;
          i = 2;
        }
      }
      
      if(i == 0){
        loggedUser = loggedUserDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else if(i == 1){
        loggedAdministrator = loggedAdministratorDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else {
        applicationMessage = "Username e password errati!";
      }
      
      List<String> categories;
      List<Product> products;

      ProductDAO productDAO = daoFactory.getProductDAO();
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
    
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(i==0){
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(i==1) {
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null); 
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/view");
    } catch (Exception e) {
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

  public static void logout(HttpServletRequest request, HttpServletResponse response) {
    DAOFactory daoFactory = null;
    SessionDAOFactory sessionDAOFactory;

    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUserDAO.destroy();
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministratorDAO.destroy();
      
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
        for(int i=0; i<cookies.length; i++){
          Cookie c= cookies[i];
          c.setValue("");
          c.setMaxAge(0);
          response.addCookie(c);
        }  
      }
      
      List<String> categories;
      List<Product> products;
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      
      ProductDAO productDAO = daoFactory.getProductDAO();
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
    } catch (Exception e) {
      throw new RuntimeException(e);

    }
    request.setAttribute("loggedOn",false);
    request.setAttribute("loggedUser", null);
    request.setAttribute("loggedAdministrator", null);
    request.setAttribute("viewUrl", "catalogManagement/view");
  }
  
  public static void logonViewProduct(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    int i = 0;
    
    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      String username = request.getParameter("username");
      String password = request.getParameter("password");

      UserDAO userDAO = daoFactory.getUserDAO();
      User user = userDAO.findClienteByUsername(username);
              
      if (user == null || !user.getPassword().equals(password)) {
        loggedUserDAO.destroy();
        loggedUser = null;
        i = 1;
        user = userDAO.findAmministratoreByUsername(username);
        if(user == null || !user.getPassword().equals(password)){
          loggedAdministratorDAO.destroy();
          loggedAdministrator = null;
          i = 2;
        }
      }
      
      if(i == 0){
        loggedUser = loggedUserDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else if(i == 1){
        loggedAdministrator = loggedAdministratorDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else {
        applicationMessage = "Username e password errati!";
      }
      
      String productName = request.getParameter("productName");
      Product product;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      daoFactory.commitTransaction();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(i==0){
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(i==1) {
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null); 
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("product", product);
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/productView");
    } catch (Exception e) {
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
  
  public static void logoutViewProduct(HttpServletRequest request, HttpServletResponse response) {
    DAOFactory daoFactory = null;
    SessionDAOFactory sessionDAOFactory;

    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUserDAO.destroy();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministratorDAO.destroy();
      
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
        for(int i=0; i<cookies.length; i++){
          Cookie c= cookies[i];
          c.setValue("");
          c.setMaxAge(0);
          response.addCookie(c);
        }  
      }
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName = request.getParameter("productName");
      Product product;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      
      daoFactory.commitTransaction();
      request.setAttribute("product", product);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    request.setAttribute("loggedOn",false);
    request.setAttribute("loggedUser", null);
    request.setAttribute("loggedAdministrator", null);
    request.setAttribute("viewUrl", "catalogManagement/productView");
  }
  
  public static void searchView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try {
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
      
      Product product;
      String productName = request.getParameter("productName");
      
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      daoFactory.commitTransaction();
      request.setAttribute("product", product);
      
    } catch (Exception e) {
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
  
  public static void logoutSearchView(HttpServletRequest request, HttpServletResponse response) {
    DAOFactory daoFactory = null;
    SessionDAOFactory sessionDAOFactory;

    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUserDAO.destroy();
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministratorDAO.destroy();
      
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
        for(int i=0; i<cookies.length; i++){
          Cookie c= cookies[i];
          c.setValue("");
          c.setMaxAge(0);
          response.addCookie(c);
        }  
      }
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String productName = request.getParameter("productName");
      Product product;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      
      daoFactory.commitTransaction();
      request.setAttribute("product", product);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    request.setAttribute("loggedOn",false);
    request.setAttribute("loggedUser", null);
    request.setAttribute("loggedAdministrator", null);
    request.setAttribute("viewUrl", "catalogManagement/searchView");
  }
  
  public static void logonSearchView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    int i = 0;
    
    try {
      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      LoggedAdministratorDAO loggedAdministratorDAO = sessionDAOFactory.getLoggedAdministratorDAO();
      loggedAdministrator = loggedAdministratorDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      String username = request.getParameter("username");
      String password = request.getParameter("password");

      UserDAO userDAO = daoFactory.getUserDAO();
      User user = userDAO.findClienteByUsername(username);
              
      if (user == null || !user.getPassword().equals(password)) {
        loggedUserDAO.destroy();
        loggedUser = null;
        i = 1;
        user = userDAO.findAmministratoreByUsername(username);
        if(user == null || !user.getPassword().equals(password)){
          loggedAdministratorDAO.destroy();
          loggedAdministrator = null;
          i = 2;
        }
      }
      
      if(i == 0){
        loggedUser = loggedUserDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else if(i == 1){
        loggedAdministrator = loggedAdministratorDAO.create(user.getUsername(), user.getNome(), user.getCognome());
      } else {
        applicationMessage = "Username e password errati!";
      }
      
      String productName = request.getParameter("productName");
      Product product;
      ProductDAO productDAO = daoFactory.getProductDAO();
      product = productDAO.searchProductByName(productName);
      daoFactory.commitTransaction();
      
      if(loggedUser != null) request.setAttribute("loggedOn", true);
      else if(loggedAdministrator != null) request.setAttribute("loggedOn", true);
      else request.setAttribute("loggedOn", false);
      
      if(i==0){
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("loggedAdministrator", null);
      }
      else if(i==1) {
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }
      else{
        request.setAttribute("loggedAdministrator", null); 
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("product", product);
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "catalogManagement/searchView");
    } catch (Exception e) {
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
  public static void deleteProductFromProductView(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    String applicationMessage = null;
    
    try {
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
      ProductDAO productDAO = daoFactory.getProductDAO();
      
      String nomeProdotto = request.getParameter("productName");
      
      productDAO.deleteProduct(nomeProdotto);
      
      List<String> categories;
      List<Product> products;

      categories = productDAO.listOfCategories();

      String selectedCategory = selectedCategory = categories.get(0);
      
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
}