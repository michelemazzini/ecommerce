package model.mo;

public class User {
  private String nome;
  private String cognome;
  private String username;
  private String password;
  private String cellulare;
  private String email;
  private String tipo_u;

  public User() {

  }
  
  public User (String nome, String cognome, String username, String password, String cellulare, String email, String tipo_u){
      this.nome = nome;
      this.cognome = cognome;
      this.username = username;
      this.password = password;
      this.cellulare = cellulare;
      this.email = email;
      this.tipo_u = tipo_u;
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword(){
    return password;
  }
  
  public void setPassword(String password){
    this.password = password;
  }
  
  public String getCellulare(){
    return cellulare;
  }
  
  public void setCellulare(String cellulare){
    this.cellulare = cellulare;
  }
  
  public String getEmail(){
    return email;
  }
  
  public void setEmail(String email){
    this.email = email;
  }
  
  public String getTipo_u(){
    return tipo_u;
  }
  
  public void setTipo_u(String tipo_u){
    this.tipo_u = tipo_u;
  }
  
  @Override
  public boolean equals(Object o){
      User cliente = (User)o;
      if(this.nome.equals(cliente.getNome()) && this.cognome.equals(cliente.getCognome()) && this.username.equals(cliente.getUsername()) && this.password.equals(cliente.getPassword()) && this.cellulare.equals(cliente.getCellulare()) && this.email.equals(cliente.getEmail()) && this.tipo_u.equals(cliente.getTipo_u())){
          return true;
      } else {
          return false;
      }
  }
}
