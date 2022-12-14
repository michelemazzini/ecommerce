package model.session.dao.CookieImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.dao.LoggedAdministratorDAO;

public class CookieSessionDAOFactory extends SessionDAOFactory {

  private HttpServletRequest request;
  private HttpServletResponse response;

  @Override
  public void initSession(HttpServletRequest request, HttpServletResponse response) {

    try {
      this.request=request;
      this.response=response;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public LoggedUserDAO getLoggedUserDAO() {
    return new LoggedUserDAOCookieImpl(request,response);
  }
  
  @Override
  public LoggedAdministratorDAO getLoggedAdministratorDAO(){
    return new LoggedAdministratorDAOCookieImpl(request,response);
  }
}