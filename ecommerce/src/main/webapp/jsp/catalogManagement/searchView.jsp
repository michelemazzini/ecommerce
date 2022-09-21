<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Product"%>

<%
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Catalogo";
  Product product = (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="/include/htmlHead.inc"%>
    <style>
        #searchbar{
          float:left;
          height:20px;
          width:125px;  
          border: 1px solid #A32900;
          border-radius: 4px;
          margin-top: 2px;
          font-size: 11px;
        }
        
      #SearchProductSection {
        margin: 12px 0;
      }     
      
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
      
      #shopping-cart{
          float: right;
          height: 25px;
          width: 25px;
      }
      .nome {
        font-size: 0.9em;
        color: #a3271f
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
      
    </style>
    <script language="javascript">
      function viewProduct(productName) {
        document.detailsForm.productName.value = productName;
        document.detailsForm.submit();
      }
      function addToShoppingCart(productName){
        document.shoppingCartForm.productName.value = productName;
        document.shoppingCartForm.submit();
      }
    </script>
  </head>
  <body>
    <%@include file="/include/headerCatalogSearchView.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Prodotti</h1>
      </section>
      <section id="SearchProductSection">
        <p>Cerca il prodotto di interesse per nome:</p>
        <br/>
        <form name="searchForm" action="Dispatcher" method="post">
          <input type="text" id="searchbar" name="productName" maxlenght="20" placeholder="Cerca...">
          <input type="hidden" name="controllerAction" value="CatalogManagement.searchView"/>
          <input type="submit" id="newSearchButton" name="newSearchButton" class="button" value="Vai"/>
        </form>
      </section>
        
      <section id="products" class="clearfix">          
        <%if ((product.getNome()) != null){%>
          <article>
            <%if (loggedOn) {%>
            <a href="javascript:addToShoppingCart('<%=product.getNome()%>')"><img id="shopping-cart" src="https://img.icons8.com/dotty/80/000000/shopping-cart.png"></a>
            <%}%>  
            <h1><a href="javascript:viewProduct('<%=product.getNome()%>');"><%=product.getNome()%></a></h1>
            <span class="marca">Marca: <%= product.getMarca()%></span>
            <br/>
            <span class="prezzo">Prezzo: <%= product.getPrezzo()%></span>
          </article>
        <%} else{%>
          <p>Non &egrave; stato trovato nessun prodotto con il nome inserito! Riprova o sfoglia il catalogo per categoria.</p>
        <%}%>   
      </section>
      
      <form name="shoppingCartForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>  
        <input type="hidden" name="controllerAction" value="ShoppingCartManagement.addProductToCartFromSearchView"/>
      </form>
      <form name="detailsForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.detailsView"/>
      </form>
    </main>
      
    <%@include file="/include/footer.inc"%>
  </body>
</html>
