<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>

<%
  double price = (double) request.getAttribute("price");
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
      .field {
        margin: 5px 0;
      }     
      
      #confirmSection > form > div > label {
        float: left;
        width: 150px;
        font-size: 0.8em;
        margin-right: 10px;
        padding-top: 3px;
        text-align: left;
      }
      
      #meseScadenza{
        width: 67px;
      }
      
      #annoScadenza{
        width: 67px;
      }
 
      #invia1{
        margin-top: 10px;
        position: relative;
        left: 227px;
      }
    </style>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Conferma ordine</h1>
        <br/>
        <p>Totale: <%=price%></p>
      </section>
      <section id="confirmSection">
        <form name="confirmForm" action="Dispatcher" method="post">
          <div class="field clearfix">
            <label for="numeroCarta">Numero carta:</label>
            <input type="text" id="numeroCarta" name="numeroCarta" maxlength="16"/>
            <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/> 
          </div>
          <div class="field clearfix">
            <label for="scadenza">Mese e anno di scadenza:</label>
            <input type="text" id="meseScadenza" name="meseScadenza" maxlength="2"/>
            <input type="text" id="annoScadenza" name="annoScadenza" maxlength="4"/>
            <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/> 
          </div>
          <div class="field clearfix">
            <label for="CVV">CVV:</label>
            <input type="text" id="CVV" name="CVV" maxlength="3"/>
            <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/> 
          </div>
          <div class="field clearfix">
            <label for="indirizzo">Indirizzo:</label>
            <input type="text" id="indirizzo" name="indirizzo" maxlength="45"/>
            <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/> 
          </div>
          <div class="field clearfix">
            <label for="citta">Città:</label>
            <input type="text" id="citta" name="citta" maxlength="20"/>
            <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/> 
          </div>
          <input type="hidden" name="price" value="<%=price%>"/>
          <input type="hidden" name="controllerAction" value="ShoppingCartManagement.newOrder"/>
          <div class="field clearfix">
            <input type="submit" id="invia1" class="button" value="Conferma"/>
          </div>
        </form>  
      </section>
    </main>
    <%@include file="/include/footer.inc"%>
</html>
