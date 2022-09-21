<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Carrello";
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
      section{
        margin: 12px 0;
      }
      main>section>p{
        text-transform: uppercase;
        font-size: 1.3em; 
        font-weight: normal;
      }
    </style>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
        <section>
          <p>Il tuo ordine &egrave; avvenuto con successo!</p>
          <br/>
        </section>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>