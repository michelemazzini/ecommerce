<script>
  function headerOnLoadHandler() {
    var usernameTextField = document.querySelector("#username");
    var usernameTextFieldMsg = "Lo username \xE8 obbligatorio.";
    var passwordTextField = document.querySelector("#password");
    var passwordTextFieldMsg = "La password \xE8 obbligatoria.";

    if (usernameTextField != undefined && passwordTextField != undefined ) {
      usernameTextField.setCustomValidity(usernameTextFieldMsg);
      usernameTextField.addEventListener("change", function () {
        this.setCustomValidity(this.validity.valueMissing ? usernameTextFieldMsg : "");
      });
      passwordTextField.setCustomValidity(passwordTextFieldMsg);
      passwordTextField.addEventListener("change", function () {
       this.setCustomValidity(this.validity.valueMissing ? passwordTextFieldMsg : "");
      });
    }
  }
</script>

<header class="clearfix"><!-- Defining the header section of the page -->

  <h1 class="logo"><!-- Defining the logo element -->
    E-sport
  </h1>

  <form name="logoutForm" action="Dispatcher" method="post">
    <input type="hidden" name="productName" value="<%=product.getNome()%>"/>
    <input type="hidden" name="controllerAction" value="CatalogManagement.logoutSearchView"/>
  </form>

  <nav><!-- Defining the navigation menu -->
    <ul>
      <li <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>>
        <a href="Dispatcher?controllerAction=HomeManagement.view">Home</a>
      </li>
      <li <%=menuActiveLink.equals("Catalogo")?"class=\"active\"":""%>>
        <a href="Dispatcher?controllerAction=CatalogManagement.view">Catalogo</a>
      </li>
      <%if (!loggedOn) {%>
        <li <%=menuActiveLink.equals("Registrati")?"class=\"active\"":""%>>
          <a href="Dispatcher?controllerAction=SignInManagement.view">Registrati</a>
        </li>
      <%}%>
      <%if (loggedOn) {%>
        <li <%=menuActiveLink.equals("Ordini")?"class=\"active\"":""%>>
          <a href="Dispatcher?controllerAction=OrderManagement.view">Ordini</a>
        </li>
      <%}%>
      <%if (loggedOn && loggedUser !=null) {%>
        <li <%=menuActiveLink.equals("Carrello")?"class=\"active\"":""%>>
          <a href="Dispatcher?controllerAction=ShoppingCartManagement.view">Carrello</a>
        </li>
      <%}%>
      <%if (loggedOn && loggedAdministrator !=null) {%>
        <li <%=menuActiveLink.equals("Utenti")?"class=\"active\"":""%>>
          <a href="Dispatcher?controllerAction=UserManagement.view">Utenti</a>
        </li>
      <%}%>
      <%if (loggedOn) {%>
        <li><a href="javascript:logoutForm.submit()">Logout</a></li>
      <%}%>
    </ul>
  </nav>

  <%if (!loggedOn) {%>
    <section id="login" class="clearfix">
      <form name="logonForm" action="Dispatcher" method="post">
        <label for="username">Utente</label>
        <input type="text" id="username"  name="username" maxlength="40" required>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" maxlength="40" required>
        <input type="hidden" name="productName" value="<%=product.getNome()%>"/>
        <input type="hidden" name="controllerAction" value="CatalogManagement.logonSearchView"/>
        <input type="submit" value="Ok">
      </form>
    </section>
  <%}%>

</header>