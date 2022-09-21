package controller;

import model.session.mo.LoggedUser;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.dao.exception.DuplicatedObjectException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOFactory;
import services.config.Configuration;
import model.session.mo.LoggedAdministrator;
import model.dao.UserDAO;
import model.session.dao.LoggedAdministratorDAO;

public class SignInManagement {
  private SignInManagement(){  
  }
  public static void view(HttpServletRequest request, HttpServletResponse response) {
    SessionDAOFactory sessionDAOFactory;
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
      request.setAttribute("viewUrl", "signInManagement/view");
       
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  public static void register(HttpServletRequest request, HttpServletResponse response) {
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
      request.setAttribute("viewUrl", "homeManagement/view");
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      String nome=request.getParameter("nome");
      String cognome=request.getParameter("cognome");
      String username=request.getParameter("username");
      String password=request.getParameter("password");
      String cellulare=request.getParameter("cellulare");
      String email=request.getParameter("email");
      String tipo_u="cliente";
      
      UserDAO userDAO = daoFactory.getUserDAO();
      try{
        userDAO.insertIntoUtenteRegistrato(nome, cognome, username, password, cellulare, email, tipo_u);
        userDAO.insertIntoCliente(username);
      }catch(DuplicatedObjectException e) {
        applicationMessage = "Cliente già esistente";
        request.setAttribute("applicationMessage", applicationMessage);
        request.setAttribute("viewUrl", "signInManagement/view");
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
