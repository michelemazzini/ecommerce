package controller;

import java.util.List;
import model.mo.Order;
import model.dao.OrderDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdministratorDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;
import model.session.mo.LoggedAdministrator;
import services.config.Configuration;

public class OrderManagement {
  private OrderManagement() {
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
        request.setAttribute("viewUrl", "orderManagement/view");
        
        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();
        List<Order> orders;
        String username;
        
        if(loggedUser != null){
          username = loggedUser.getUsername();
          OrderDAO orderDAO = daoFactory.getOrderDAO();
          orders = orderDAO.listOfOrdersByUser(username);
        } else{
          OrderDAO orderDAO = daoFactory.getOrderDAO();
          orders = orderDAO.listOfOrders();
        }
        request.setAttribute("orders", orders);
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
  
  public static void detailsView(HttpServletRequest request, HttpServletResponse response) {
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
      request.setAttribute("viewUrl", "orderManagement/orderView");
      String numberString = request.getParameter("orderNumber");
      int number = Integer.parseInt(numberString);
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      
      OrderDAO orderDAO = daoFactory.getOrderDAO();
      Order order = orderDAO.searchOrderByNumber(number);
      request.setAttribute("order", order);
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
