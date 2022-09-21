<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.User"%>

<%
  int i = 0;
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Utenti";
  List<User> clienti = (List<User>) request.getAttribute("clienti");
  List<User> amministratori = (List<User>) request.getAttribute("amministratori");
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>   
  <style>
      .table1 {
        font-size: 14px;
        border-collapse: collapse;
        background: #f9dcb4;
      }
      
      .table1 td{
        padding: 10px 20px;
      }
      
      .table1 td {
        border-style: solid;
        border-width: 0 1px 1px 0;
        border-color: white;
      }
      
      #link{
        color: #666666;
      }
      
      #newAmministratore > form > div > label {
        float: left;
        width: 56px;
        font-size: 0.8em;
        margin-right: 10px;
        padding-top: 3px;
        text-align: left;
      }
      
      .field {
        margin: 5px 0;
      }
      
      #newAmministratore {
        margin: 12px 0px;
      }
      
      #invia1{
        margin-top: 10px;
        position: relative;
        left: 160px;
      }
    </style>
    <script language="javascript">
      function viewCliente(username){
        document.viewClienteForm.usernameCliente.value = username;
        document.viewClienteForm.submit();
      }
      function viewAmministratore(username){
        document.viewAmministratoreForm.usernameAmministratore.value = username;
        document.viewAmministratoreForm.submit();
      }
    </script>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Clienti</h1>
      </section>
      <%if(clienti.isEmpty()){%>
        <p>Non ci sono clienti registrati!</p>
      <%} else{%>
        <table class="table1">
          <tr><td>Username</td><td>Nome</td><td>Cognome</td><td>E-mail</td></tr>
          <%for (i = 0; i < clienti.size(); i++) {%>
          <tr><td><a id = "link" href="javascript:viewCliente('<%=clienti.get(i).getUsername()%>');"><%=clienti.get(i).getUsername()%></a></td><td><%=clienti.get(i).getNome()%></td><td><%=clienti.get(i).getCognome()%></td><td><%=clienti.get(i).getEmail()%></td></tr>
          <%}%>
        </table>
      <%}%> 
      <br/>
      <section id="pageTitle">
        <h1>Amministratori</h1>  
      </section>
      <%if(amministratori.size()==1){%>
        <p>Non ci sono amministratori oltre a te!</p>
      <%} else{%>
        <table class="table1">
          <tr><td>Username</td><td>Nome</td><td>Cognome</td><td>E-mail</td></tr>
          <%for (i = 0; i < amministratori.size(); i++) {%>
            <%if(!amministratori.get(i).getUsername().equals(loggedAdministrator.getUsername())){%>
              <tr><td><a id = "link" href="javascript:viewAmministratore('<%=amministratori.get(i).getUsername()%>');"><%=amministratori.get(i).getUsername()%></a></td><td><%=amministratori.get(i).getNome()%></td><td><%=amministratori.get(i).getCognome()%></td><td><%=amministratori.get(i).getEmail()%></td></tr>
            <%}%>
          <%}%>
        </table>
      <%}%>
      <br/>
      <br/>
      <p>Inserisci un nuovo amministratore:</p>
      <section id="newAmministratore">
        <form name="newAmministratoreForm" action="Dispatcher" method="post">
          <div class="field clearfix">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
            <label for="usernamea">Username:</label>
            <input type="text" id="usernamea" name="usernamea" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
            <label for="passworda">Password:</label>
            <input type="password" id="passworda" name="passworda" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
            <label for="cellulare">Cellulare:</label>
            <input type="text" id="cellulare" name="cellulare" maxlength="10"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" maxlength="45"/>
            <input type="hidden" name="controllerAction" value="UserManagement.newAmministratore"/> 
          </div>
          <div class="field clearfix">
              <input type="submit" id="invia1" class="button" value="Invia"/>
          </div>
        </form>
      </section>
      <form name="viewClienteForm" method="post" action="Dispatcher">
        <input type="hidden" name="usernameCliente"/>
        <input type="hidden" name="controllerAction" value="UserManagement.detailsViewCliente"/>
      </form>
      <form name="viewAmministratoreForm" method="post" action="Dispatcher">
        <input type="hidden" name="usernameAmministratore"/>
        <input type="hidden" name="controllerAction" value="UserManagement.detailsViewAmministratore"/>
      </form>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>