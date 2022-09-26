package model.session.dao;

import model.session.mo.LoggedUser;

public interface LoggedUserDAO {

  public LoggedUser create(String username, String nome, String cognome);

  public void update(LoggedUser loggedUser);

  public void destroy();

  public LoggedUser find();
  
}
