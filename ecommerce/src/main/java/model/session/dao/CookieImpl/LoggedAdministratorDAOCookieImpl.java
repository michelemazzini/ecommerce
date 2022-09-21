package model.session.dao.CookieImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.session.mo.LoggedAdministrator;
import model.session.dao.LoggedAdministratorDAO;

public class LoggedAdministratorDAOCookieImpl implements LoggedAdministratorDAO {
  HttpServletRequest request;
  HttpServletResponse response;
  
  public LoggedAdministratorDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }
  
  @Override
  public LoggedAdministrator create(String username, String nome, String cognome){
    LoggedAdministrator loggedAdministrator = new LoggedAdministrator();
    loggedAdministrator.setUsername(username);
    loggedAdministrator.setNome(nome);
    loggedAdministrator.setCognome(cognome);

    Cookie cookie;
    cookie = new Cookie("loggedAdministrator", encode(loggedAdministrator));
    cookie.setPath("/");
    response.addCookie(cookie);

    return loggedAdministrator;

  }
  
  @Override
  public void update(LoggedAdministrator loggedAdministrator) {

    Cookie cookie;
    cookie = new Cookie("loggedAdministrator", encode(loggedAdministrator));
    cookie.setPath("/");
    response.addCookie(cookie);

  }

  @Override
  public void destroy() {

    Cookie cookie;
    cookie = new Cookie("loggedAdministrator", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);

  }
  
  @Override
  public LoggedAdministrator find() {

    Cookie[] cookies = request.getCookies();
    LoggedAdministrator loggedAdministrator = null;

    if (cookies != null) {
      for (int i = 0; i < cookies.length && loggedAdministrator == null; i++) {
        if (cookies[i].getName().equals("loggedAdministrator")) {
          loggedAdministrator = decode(cookies[i].getValue());
        }
      }
    }
    return loggedAdministrator;

  }
  
  private String encode(LoggedAdministrator loggedAdministrator) {

    String encodedLoggedAdministrator;
    encodedLoggedAdministrator = loggedAdministrator.getUsername() + "#" + loggedAdministrator.getNome() + "#" + loggedAdministrator.getCognome();
    return encodedLoggedAdministrator;

  }
  
  private LoggedAdministrator decode(String encodedLoggedAdministrator) {

    LoggedAdministrator loggedAdministrator = new LoggedAdministrator();
    
    String[] values = encodedLoggedAdministrator.split("#");

    loggedAdministrator.setUsername(values[0]);
    loggedAdministrator.setNome(values[1]);
    loggedAdministrator.setCognome(values[2]);

    return loggedAdministrator;
    
  }
  
}