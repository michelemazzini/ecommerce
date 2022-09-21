<%@page session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.session.mo.LoggedAdministrator"%>
<%@page import="model.mo.Product"%>

<%int i = 0;
  boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
  LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
  LoggedAdministrator loggedAdministrator = (LoggedAdministrator) request.getAttribute("loggedAdministrator");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Catalogo";
  String selectedCategory = (String) request.getAttribute("selectedCategory");
  List<String> categories = (List<String>) request.getAttribute("categories");
  List<Product> products = (List<Product>) request.getAttribute("products");
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
      
      #InsertProductSection {
        margin: 12px 0;
      }  
      
      .field {
        margin: 5px 0;
      }
      
      #InsertProductSection > form > div > label {
        float: left;
        width: 56px;
        font-size: 0.8em;
        margin-right: 10px;
        padding-top: 3px;
        text-align: left;
      }
      
      #categorySelector {
        margin: 12px 0;
      }

      .category {  
        color: #a3271f;
        letter-spacing: 2px;
      }

      .selectedCategory { 
        color: black;
        letter-spacing: 2px;
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
          color: rgb(102, 102, 102);
      }
      
      #shoppingCart{
          float: right;
          height: 25px;
          width: 25px;
      }
      
      #deleteItem{
          float: right;
          height: 25px;
          width: 25px;
      }
      
      .marca {
        font-size: 0.8em;
        color: rgb(102, 102, 102);
      } 
      
      .prezzo {
        font-size: 0.8em;
        font-style: italic;  
        color: rgb(102, 102, 102);
      }
      
      #invia1{
        margin-top: 10px;
        position: relative;
        left: 160px;
      }
      
    </style>
    <script language="javascript">
      function viewProduct(productName) {
        document.detailsForm.productName.value = productName;
        document.detailsForm.submit();
      }
      function changeCategory(category) {
        document.changeCategoryForm.selectedCategory.value = category;
        document.changeCategoryForm.submit();
      }
      function addToShoppingCart(productName, category){
        document.shoppingCartForm.selectedCategory.value = category;
        document.shoppingCartForm.productName.value = productName;
        document.shoppingCartForm.submit();
      }
      function deleteItem(productName, category){
        document.deleteForm.productName.value = productName;
        document.deleteForm.selectedCategory.value = category;
        document.deleteForm.submit();
      }
    </script>
  </head>
  <body>
    <%@include file="/include/headerCatalog.inc"%>
    <main>
      <section id="pageTitle">
        <h1>Prodotti</h1>
      </section>
      <%if (loggedAdministrator==null) {%>
        <section id="SearchProductSection">
          <p>Cerca il prodotto di interesse per nome:</p>
          <br/>
          <form name="searchForm" action="Dispatcher" method="post">
            <input type="text" id="searchbar" name="productName" maxlenght="20" placeholder="Cerca...">
            <input type="hidden" name="controllerAction" value="CatalogManagement.searchView"/>
            <input type="submit" id="newSearchButton" name="newSearchButton" class="button" value="Vai"/>
          </form>
        </section>
      <%}%>
      <%if (loggedAdministrator!=null) {%>
        <section id="InsertProductSection">
          <p>Inserisci nuovo prodotto:</p>
          <br/>
          <form name="insertForm" action="Dispatcher" method="post">
            <div class="field clearfix">
              <label for="productName">Nome:</label>
              <input type="text" id="productName" name="productName" maxlength="20"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="marcaProdotto">Marca:</label>
              <input type="text" id="marcaProdotto" name="marcaProdotto" maxlength="20"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="categoriaProdotto">Categoria:</label>
              <input type="text" id="categoriaProdotto" name="categoriaProdotto" maxlength="20"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="codiceProdotto">Codice:</label>
              <input type="text" id="codiceProdotto" name="codiceProdotto" maxlength="20"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="prezzoProdotto">Prezzo:</label>
              <input type="text" id="prezzoProdotto" name="prezzoProdotto"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="larghezzaProdotto">Larghezza:</label>
              <input type="text" id="larghezzaProdotto" name="larghezzaProdotto"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="altezzaProdotto">Altezza:</label>
              <input type="text" id="altezzaProdotto" name="altezzaProdotto"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="spessoreProdotto">Spessore:</label>
              <input type="text" id="spessoreProdotto" name="spessoreProdotto"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <div class="field clearfix">
              <label for="quantitaProdotto">Quantit&agrave;:</label>
              <input type="text" id="quantitaProdotto" name="quantitaProdotto"/>
              <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/> 
            </div>
            <input type="hidden" name="selectedCategory" value="<%=selectedCategory%>"/>
            <input type="hidden" name="controllerAction" value="CatalogManagement.insertProduct"/>
            <div class="field clearfix">
              <input type="submit" id="invia1" class="button" value="Invia"/>
            </div>
          </form>
        </section>
      <%}%>
      <br/>
      <section id="pageTitle">
        <h1>Categorie</h1>
      </section>
      <nav id="categorySelector">
        <%for (i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(selectedCategory)) {%>
              <span class="selectedCategory"><%=categories.get(i)%></span>
          <%} else {%>
              <a class="category" href="javascript:changeCategory('<%=categories.get(i)%>');"><%=categories.get(i)%></a>
          <%}%>  
        <%}%>
      </nav>
      <section id="products" class="clearfix">
        <%for (i = 0; i < products.size(); i++) {%>           
          <article>
            <%if (loggedOn && loggedUser!=null) {%>
              <a href="javascript:addToShoppingCart('<%=products.get(i).getNome()%>', '<%=selectedCategory%>');"><img id="shoppingCart" src="https://img.icons8.com/dotty/80/000000/shopping-cart.png"/></a>      
            <%}%>
            <%if (loggedOn && loggedAdministrator!=null) {%>
              <a href="javascript:deleteItem('<%=products.get(i).getNome()%>', '<%=selectedCategory%>');"><img id="deleteItem" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHg9IjBweCIgeT0iMHB4Igp3aWR0aD0iNTAiIGhlaWdodD0iNTAiCnZpZXdCb3g9IjAgMCAxNzIgMTcyIgpzdHlsZT0iIGZpbGw6IzAwMDAwMDsiPjxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0ibm9uemVybyIgc3Ryb2tlPSJub25lIiBzdHJva2Utd2lkdGg9IjEiIHN0cm9rZS1saW5lY2FwPSJidXR0IiBzdHJva2UtbGluZWpvaW49Im1pdGVyIiBzdHJva2UtbWl0ZXJsaW1pdD0iMTAiIHN0cm9rZS1kYXNoYXJyYXk9IiIgc3Ryb2tlLWRhc2hvZmZzZXQ9IjAiIGZvbnQtZmFtaWx5PSJub25lIiBmb250LXdlaWdodD0ibm9uZSIgZm9udC1zaXplPSJub25lIiB0ZXh0LWFuY2hvcj0ibm9uZSIgc3R5bGU9Im1peC1ibGVuZC1tb2RlOiBub3JtYWwiPjxwYXRoIGQ9Ik0wLDE3MnYtMTcyaDE3MnYxNzJ6IiBmaWxsPSJub25lIj48L3BhdGg+PGcgZmlsbD0iIzY2NjY2NiI+PHBhdGggZD0iTTMxLjQ5NzUsMjEuNzE1bC05Ljc4MjUsOS43ODI1bDU0LjUwMjUsNTQuNTAyNWwtNTQuODI1LDU0LjkzMjVsOS42NzUsOS42NzVsNTQuOTMyNSwtNTQuODI1bDU0LjgyNSw1NC44MjVsOS43ODI1LC05Ljc4MjVsLTU0LjgyNSwtNTQuODI1bDU0LjUwMjUsLTU0LjUwMjVsLTkuNzgyNSwtOS43ODI1bC01NC41MDI1LDU0LjUwMjV6Ij48L3BhdGg+PC9nPjwvZz48L3N2Zz4="/></a>      
            <%}%>
            <h1><a href="javascript:viewProduct('<%=products.get(i).getNome()%>');"><%=products.get(i).getNome()%></a></h1>
            <span class="marca">Marca: <%= products.get(i).getMarca()%></span>
            <br/>
            <span class="prezzo">Prezzo: <%= products.get(i).getPrezzo()%></span>
          </article>
        <%}%>
      </section>
     
      <form name="changeCategoryForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedCategory"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.view"/>      
      </form>    
     
      <form name="detailsForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.detailsView"/>
      </form>
      
      <form name="deleteForm" method="post" action="Dispatcher">
        <input type="hidden" name="productName"/>
        <input type="hidden" name="selectedCategory"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.deleteProduct"/>
      </form>
      
      <form name="shoppingCartForm" method="post" action="Dispatcher">
        <input type="hidden" name="selectedCategory"/>
        <input type="hidden" name="productName"/>  
        <input type="hidden" name="controllerAction" value="ShoppingCartManagement.addProductToCartFromCatalogView"/>
      </form>
    </main>
    <%@include file="/include/footer.inc"%>
  </body>
</html>
