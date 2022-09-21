<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.User"%>

<%
  int n_ordini = (int) request.getAttribute("n_ordini");
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  User cliente = (User) request.getAttribute("cliente");
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
        <h1>Informazioni cliente</h1>
      </section>
      <p>Username: <%=cliente.getUsername()%></p>
      <p>Nome: <%=cliente.getNome()%></p>
      <p>Cognome: <%=cliente.getCognome()%></p>
      <p>E-mail: <%=cliente.getEmail()%></p>
      <p>Cellulare: <%=cliente.getCellulare()%></p>
      <p>Numero ordini effettuati: <%=n_ordini%></p>
      <br/>
      <form name="deleteForm" action="Dispatcher" method="post">
        <input type="hidden" name="username" value="<%=cliente.getUsername()%>"/>
        <input type="hidden" name="controllerAction" value="UserManagement.removeCliente"/>
        <div class="field clearfix">
          <input type="submit" id="elimina2" class="button" value="Elimina"/>
        </div>
      </form>
    </main>
  </body>
  <%@include file="/include/footer.inc"%>
</html>