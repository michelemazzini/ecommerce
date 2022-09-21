<%@page session="false"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Product"%>

<%boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
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
      #products {
        margin: 12px 0;
      }

      #products article {
          float: left;
          width: 700px;
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
          color: rgb(102, 102, 102);
      }
      
      #shopping-cart{
          float: right;
          height: 25px;
          width: 25px;
      }
      
      #deleteItem{
          float: right;
          height: 25px;
          width: 25px;
      }
      
      .nome {
        font-size: 0.9em;
        color: rgb(102, 102, 102);
      }

      .marca {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
      
      .prezzo {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
      
      .altezza {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
      
      .spessore {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
     
      .larghezza {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
      
      #pushButton {
        float: right;
        position: relative;
        margin-top: 20px;
      }
    </style>
    <script language="javascript">
      function addToShoppingCart(productName){
        document.shoppingCartForm.productName.value = productName;
        document.shoppingCartForm.submit();
      }
      function deleteItem(productName){
        document.deleteForm.productName.value = productName;
        document.deleteForm.submit();
      }
    </script>
  </head>
  <body>
    <%@include file="/include/headerCatalogViewProduct.inc"%>
    <main>
      <section id="products" class="clearfix">         
        <article>
          <%if (loggedOn && loggedUser!=null) {%>
          <a href="javascript:addToShoppingCart('<%=product.getNome()%>')"><img id="shopping-cart" src="https://img.icons8.com/dotty/80/000000/shopping-cart.png"></a>
          <%}%>
          <%if (loggedOn && loggedAdministrator!=null) {%>
              <a href="javascript:deleteItem('<%=product.getNome()%>');"><img id="deleteItem" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHg9IjBweCIgeT0iMHB4Igp3aWR0aD0iNTAiIGhlaWdodD0iNTAiCnZpZXdCb3g9IjAgMCAxNzIgMTcyIgpzdHlsZT0iIGZpbGw6IzAwMDAwMDsiPjxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0ibm9uemVybyIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIHN0cm9rZS1saW5lY2FwPSJidXR0IiBzdHJva2UtbGluZWpvaW49Im1pdGVyIiBzdHJva2UtbWl0ZXJsaW1pdD0iMTAiIHN0cm9rZS1kYXNoYXJyYXk9IiIgc3Ryb2tlLWRhc2hvZmZzZXQ9IjAiIGZvbnQtZmFtaWx5PSJub25lIiBmb250LXdlaWdodD0ibm9uZSIgZm9udC1zaXplPSJub25lIiB0ZXh0LWFuY2hvcj0ibm9uZSIgc3R5bGU9Im1peC1ibGVuZC1tb2RlOiBub3JtYWwiPjxwYXRoIGQ9Ik0wLDE3MnYtMTcyaDE3MnYxNzJ6IiBmaWxsPSJub25lIj48L3BhdGg+PGcgZmlsbD0iIzY2NjY2NiI+PHBhdGggZD0iTTMxLjQ5NzUsMjEuNzE1bC05Ljc4MjUsOS43ODI1bDU0LjUwMjUsNTQuNTAyNWwtNTQuODI1LDU0LjkzMjVsOS42NzUsOS42NzVsNTQuOTMyNSwtNTQuODI1bDU0LjgyNSw1NC44MjVsOS43ODI1LC05Ljc4MjVsLTU0LjgyNSwtNTQuODI1bDU0LjUwMjUsLTU0LjUwMjVsLTkuNzgyNSwtOS43ODI1bC01NC41MDI1LDU0LjUwMjV6Ij48L3BhdGg+PC9nPjwvZz48L3N2Zz4="/></a>      
            <%}%>
          <h1><%=product.getNome()%></h1>
          <br/>
          <span class="marca">Marca: <%= product.getMarca()%></span>
          <br/>
          <span class="prezzo">Prezzo: <%= product.getPrezzo()%></span>
          <br/>
          <span class="larghezza">Larghezza: <%= product.getLarghezza()%></span>
          <br/>
          <span class="altezza">Altezza: <%= product.getAltezza()%></span>
          <br/>
          <span class="spessore">Spessore: <%= product.getSpessore()%></span>
          <br/>
        </article>
      </section>
          
      <form name="shoppingCartForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>  
        <input type="hidden" name="controllerAction" value="ShoppingCartManagement.addProductToCartFromProductView"/>
      </form>
          
      <form name="deleteForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.deleteProductFromProductView"/>
      </form>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>
