<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Product"%>

<%
  float tot=0;
  int i = 0;
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Carrello";
  List<Product> products = (List<Product>) request.getAttribute("products");
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
      #products {
        margin: 12px 0;
      }

      #products article {
          float: left;
          width: 250px;
          border-width: 1px;
          border-style: solid;
          border-radius: 10px;
          border-color: #a3271f;
          padding: 10px 8px 10px 20px;
          margin: 0 18px 16px 0;
          background: linear-gradient(to right,#fdfbfb,#ebedee);
          box-shadow: 0 3px 2px #777;
      }

      #products article h1 a {
          color: #a3271f;
      }
      
      #shoppingCart{
          float: right;
          height: 25px;
          width: 25px;
      }

      .marca {
        font-size: 0.8em;
        color: #a3271f
      } 
      
      .prezzo {
        font-size: 0.8em;
        font-style: italic;  
        color: black;
      }
      
      #invia2{
        position: relative;
        left: 750px;
      }
    </style>
  </head>
  <body>
    <%@include file="/include/headerHome.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Prodotti nel carrello</h1>
      </section>
      <%if(products.get(0).getNome()==null){%>
        <p>Non ci sono prodotti nel carrello!</p>
      <%} else{%>
        <section id="products" class="clearfix">
          <%for (i = 0; i < (products.size()-2); i++) {%>
            <%tot=tot + products.get(i).getPrezzo();%>
            <article>
             <h1><%=products.get(i).getNome()%></h1>
              <span class="marca">Marca: <%= products.get(i).getMarca()%></span>
              <br/>
              <span class="prezzo">Prezzo: <%= products.get(i).getPrezzo()%></span>
            </article>
          <%}%>
        </section>
        <section id="pageTitle">
          <br/>
          <h1>Totale: <%=tot%></h1>
        </section>
      <%}%>
      <%if(products.get(0).getNome()!=null){%>
        <form name="priceForm" action="Dispatcher" method="post">
          <input type="hidden" name="price" value="<%=tot%>"/>
          <input type="hidden" name="controllerAction" value="ShoppingCartManagement.confirmOrder"/>
          <div class="field clearfix">
            <input type="submit" id="invia2" class="button" value="Procedi all'acquisto"/>
          </div>
        </form>
      <%}%>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>
