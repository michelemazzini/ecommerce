package controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import services.config.Configuration;
import model.mo.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.session.mo.LoggedUser;
import model.session.mo.LoggedAdministrator;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdministratorDAO;


public class HomeManagement {

  private HomeManagement() {
  }

  public static void view(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    LoggedUser loggedUser;
    LoggedAdministrator loggedAdministrator;
    
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
      else if(loggedAdministrator != null) {
        request.setAttribute("loggedAdministrator", loggedAdministrator);
        request.setAttribute("loggedUser", null);
      }else{
        request.setAttribute("loggedAdministrator", null); 
        request.setAttribute("loggedUser", null);
      }
      
      request.setAttribute("viewUrl", "homeManagement/view");

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  public static void logon(HttpServletRequest request, HttpServletResponse response) {
    int i = 0; 
    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedAdministrator loggedAdministrator;
    LoggedUser loggedUser;
    String applicationMessage = null;
    
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
      request.setAttribute("viewUrl", "homeManagement/view");

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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    request.setAttribute("loggedOn",false);
    request.setAttribute("loggedUser", null);
    request.setAttribute("loggedAdministrator", null);
    request.setAttribute("viewUrl", "homeManagement/view");
  }
}
