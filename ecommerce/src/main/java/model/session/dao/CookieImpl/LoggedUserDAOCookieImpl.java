package model.session.dao.CookieImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;

public class LoggedUserDAOCookieImpl implements LoggedUserDAO {

  HttpServletRequest request;
  HttpServletResponse response;

  public LoggedUserDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  @Override
  public LoggedUser create(String username, String nome, String cognome){
    LoggedUser loggedUser = new LoggedUser();
    loggedUser.setUsername(username);
    loggedUser.setNome(nome);
    loggedUser.setCognome(cognome);

    Cookie cookie;
    cookie = new Cookie("loggedUser", encode(loggedUser));
    cookie.setPath("/");
    response.addCookie(cookie);

    return loggedUser;

  }

  @Override
  public void update(LoggedUser loggedUser) {

    Cookie cookie;
    cookie = new Cookie("loggedUser", encode(loggedUser));
    cookie.setPath("/");
    response.addCookie(cookie);

  }

  @Override
  public void destroy() {

    Cookie cookie;
    cookie = new Cookie("loggedUser", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);

  }

  @Override
  public LoggedUser find() {

    Cookie[] cookies = request.getCookies();
    LoggedUser loggedUser = null;

    if (cookies != null) {
      for (int i = 0; i < cookies.length && loggedUser == null; i++) {
        if (cookies[i].getName().equals("loggedUser")) {
          loggedUser = decode(cookies[i].getValue());
        }
      }
    }
    return loggedUser;

  }

  private String encode(LoggedUser loggedUser) {

    String encodedLoggedUser;
    encodedLoggedUser = loggedUser.getUsername() + "#" + loggedUser.getNome() + "#" + loggedUser.getCognome();
    return encodedLoggedUser;

  }

  private LoggedUser decode(String encodedLoggedUser) {

    LoggedUser loggedUser = new LoggedUser();
    
    String[] values = encodedLoggedUser.split("#");

    loggedUser.setUsername(values[0]);
    loggedUser.setNome(values[1]);
    loggedUser.setCognome(values[2]);

    return loggedUser;
    
  }
  
}

