<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Registrati";
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%> 
    <style>
      .field {
        margin: 5px 0;
      }     
      
      #signInSection > form > div > label {
        float: left;
        width: 56px;
        font-size: 0.8em;
        margin-right: 10px;
        padding-top: 3px;
        text-align: left;
      }
      
      #invia1{
        margin-top: 10px;
        position: relative;
        left: 160px;
      }
    </style>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Registrazione</h1>
      </section>
      <section id="signInSection">
        <form name="signInForm" action="Dispatcher" method="post">
          <div class="field clearfix">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <label for="cognome">Cognome:</label>
            <input type="text" id="cognome" name="cognome" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <label for="cellulare">Cellulare:</label>
            <input type="cellulare" id="cellulare" name="cellulare" maxlength="10"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <label for="email">E-mail:</label>
            <input type="email" id="email" name="email" maxlength="45"/>
            <input type="hidden" name="controllerAction" value="SignInManagement.register"/> 
          </div>
          <div class="field clearfix">
            <input type="submit" id="invia1" class="button" value="Invia"/>
          </div>
        </form>  
      </section>
    </main>
    <%@include file="/include/footer.inc"%>
</html>
