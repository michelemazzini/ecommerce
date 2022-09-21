<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Order"%>

<%
  int i = 0;
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Ordini";
  List<Order> orders = (List<Order>) request.getAttribute("orders");
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
    </style>
    <script language="javascript">
      function viewOrder(numberOrder){
        document.orderForm.orderNumber.value = numberOrder;
        document.orderForm.submit();
      }
    </script>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Ordini</h1>
      </section>
      <%if (loggedAdministrator==null) {%>
        <%if(orders.isEmpty()){%>
          <p>Non sono stati effettuati ordini!</p>
        <%} else{%>
          <table class="table1">
            <tr><td>Codice dell'ordine</td><td>Indirizzo di spedizione</td><td>Carta utilizzata per pagamento</td></tr>
            <%for (i = 0; i < orders.size(); i++) {%>
              <tr><td><a id="link" href="javascript:viewOrder('<%=orders.get(i).getNumeroOrdine()%>');"><%=orders.get(i).getNumeroOrdine()%></a></td><td><%=orders.get(i).getIndirizzo()%></td><td><%=orders.get(i).getNum_carta()%></td></tr>
            <%}%>
          </table>
        <%}%>
      <%}%>
      <%if (loggedAdministrator!=null) {%>
        <%if(orders.isEmpty()){%>
          <p>Non sono stati effettuati ordini!</p>
        <%} else{%>
          <table class="table1">
              <tr><td>Codice dell'ordine</td><td>Username cliente</td><td>Indirizzo di spedizione</td><td>Carta utilizzata per pagamento</td></tr>
              <%for (i = 0; i < orders.size(); i++) {%>
                <tr><td><a id="link" href="javascript:viewOrder('<%=orders.get(i).getNumeroOrdine()%>');"><%=orders.get(i).getNumeroOrdine()%></a><td><%=orders.get(i).getUsername_c()%></td></td><td><%=orders.get(i).getIndirizzo()%></td><td><%=orders.get(i).getNum_carta()%></td></tr>
              <%}%>
          </table>
        <%}%>
      <%}%>
      <form name="orderForm" method="post" action="Dispatcher">
        <input type="hidden" name="orderNumber"/>
        <input type="hidden" name="controllerAction" value="OrderManagement.detailsView"/>
      </form>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>
