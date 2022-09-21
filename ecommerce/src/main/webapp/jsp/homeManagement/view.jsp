<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Home";
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>  
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <%if (loggedOn) {%>
        <%if (loggedUser!=null) {%>
          Benvenuto ${loggedUser.getNome()} ${loggedUser.getCognome()}!<br/>
          Ora puoi acquistare i tuoi prodotti e vedere i tuoi ordini.
        <%} else {%>
          Benvenuto ${loggedAdministrator.getNome()} ${loggedAdministrator.getCognome()}!<br/>
          Ora puoi accedere alle funzioni di amministratore.
        <%}%>
      <%} else {%>
      Benvenuto.
      Fai il logon per acquistare i prodotti e vedere i tuoi ordini, oppure dai un'occhiata in giro.
      <%}%>
    </main>
    <%@include file="/include/footer.inc"%>
</html>
