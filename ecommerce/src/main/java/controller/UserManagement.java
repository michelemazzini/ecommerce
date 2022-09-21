package controller;

import java.util.List;
import model.dao.UserDAO;
import model.mo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOFactory;
import model.dao.exception.DuplicatedObjectException;
import model.session.dao.LoggedAdministratorDAO;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedAdministrator;
import model.session.mo.LoggedUser;
import services.config.Configuration;

public class UserManagement {
  private UserManagement(){
  }
  
  public static void newAmministratore(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/view");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String nome=request.getParameter("nome");
      String cognome=request.getParameter("cognome");
      String username=request.getParameter("usernamea");
      String password=request.getParameter("passworda");
      String cellulare=request.getParameter("cellulare");
      String email=request.getParameter("email");
      String tipo_u="amministratore";
      
      UserDAO userDAO = daoFactory.getUserDAO();
      try{
        userDAO.insertIntoUtenteRegistrato(nome, cognome, username, password, cellulare, email, tipo_u);
        userDAO.insertIntoAmministratore(username);
      }catch(DuplicatedObjectException e) {
        applicationMessage = "Amministratore già esistente";
        request.setAttribute("applicationMessage", applicationMessage);
        request.setAttribute("viewUrl", "signInManagement/view");
      }
      
      List<User> clienti;
      List<User> amministratori;
      
      clienti = userDAO.listOfClienti();
      amministratori = userDAO.listOfAmministratori();
      
      request.setAttribute("clienti", clienti);
      request.setAttribute("amministratori", amministratori);
      
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
  
  public static void view(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/view");
        
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      List<User> clienti;
      List<User> amministratori;
      
      UserDAO userDAO = daoFactory.getUserDAO();
      clienti = userDAO.listOfClienti();
      amministratori = userDAO.listOfAmministratori();
      
      request.setAttribute("clienti", clienti);
      request.setAttribute("amministratori", amministratori);
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
  
  public static void detailsViewCliente(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/detailsViewCliente");
        
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      String username = request.getParameter("usernameCliente");
      User user;
      int n_ordini = 0;
      
      daoFactory.beginTransaction();
      UserDAO userDAO = daoFactory.getUserDAO();
      user = userDAO.findClienteByUsername(username);
      n_ordini = userDAO.numberOfOrdersByUser(username);
      
      daoFactory.commitTransaction();
      request.setAttribute("cliente", user);
      request.setAttribute("n_ordini", n_ordini);
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
  
  public static void removeAmministratore(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/view");
        
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      String username = request.getParameter("username");
      List<User> clienti;
      List<User> amministratori;
      daoFactory.beginTransaction();
      UserDAO userDAO = daoFactory.getUserDAO();
      userDAO.removeUser(username);
      
      clienti = userDAO.listOfClienti();
      amministratori = userDAO.listOfAmministratori();
      
      request.setAttribute("clienti", clienti);
      request.setAttribute("amministratori", amministratori);
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
  
  public static void removeCliente(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/view");
        
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      String username = request.getParameter("username");
      List<User> clienti;
      List<User> amministratori;
      daoFactory.beginTransaction();
      UserDAO userDAO = daoFactory.getUserDAO();
      userDAO.removeUser(username);
      
      clienti = userDAO.listOfClienti();
      amministratori = userDAO.listOfAmministratori();
      
      request.setAttribute("clienti", clienti);
      request.setAttribute("amministratori", amministratori);
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
  
  public static void detailsViewAmministratore(HttpServletRequest request, HttpServletResponse response){
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
      request.setAttribute("viewUrl", "userManagement/detailsViewAmministratore");
        
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      String username = request.getParameter("usernameAmministratore");
      User user;
      daoFactory.beginTransaction();
      UserDAO userDAO = daoFactory.getUserDAO();
      user = userDAO.findAmministratoreByUsername(username);
      daoFactory.commitTransaction();
      request.setAttribute("amministratore", user);
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
}
