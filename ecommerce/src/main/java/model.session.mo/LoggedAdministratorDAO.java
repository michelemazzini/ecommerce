package model.session.dao;

import model.session.mo.LoggedAdministrator;

public interface LoggedAdministratorDAO {
  public LoggedAdministrator create(String username, String nome, String cognome);

  public void update(LoggedAdministrator loggedUser);

  public void destroy();

  public LoggedAdministrator find();
  
}
