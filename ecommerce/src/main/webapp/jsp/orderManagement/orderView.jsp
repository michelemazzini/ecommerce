<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Order"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Ordini";
  Order order = (Order) request.getAttribute("order");
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
        <h1>Informazioni ordine</h1>
      </section>
      <%if (loggedAdministrator!=null) {%>
      <p>Username cliente: <%=order.getUsername_c()%></p>
      <%}%>
      <p>Codice ordine: <%=order.getNumeroOrdine()%></p> 
      <p>Indirizzo di spedizione: <%=order.getIndirizzo()%></p> 
      <p>Citt&agrave;: <%=order.getCitta()%></p> 
      <p>Numero carta di credito: <%=order.getNum_carta()%></p>
      <br/>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>