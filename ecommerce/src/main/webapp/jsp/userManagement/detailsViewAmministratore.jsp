<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.User"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  User amministratore = (User) request.getAttribute("amministratore");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Utenti";
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>   
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Informazioni amministratore</h1>
      </section>
      <p>Username: <%=amministratore.getUsername()%></p>
      <p>Nome: <%=amministratore.getNome()%></p>
      <p>Cognome: <%=amministratore.getCognome()%></p>
      <p>E-mail: <%=amministratore.getEmail()%></p>
      <p>Cellulare: <%=amministratore.getCellulare()%></p>
      <br/>
      <form name="deleteForm" action="Dispatcher" method="post">
          <input type="hidden" name="username" value="<%=amministratore.getUsername()%>"/>
          <input type="hidden" name="controllerAction" value="UserManagement.removeAmministratore"/>
        <div class="field clearfix">
          <input type="submit" id="elimina1" class="button" value="Elimina"/>
        </div>
      </form>
    </main>
  </body>
  <%@include file="/include/footer.inc"%>
</html>