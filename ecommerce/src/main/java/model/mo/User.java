package model.mo;

public class User {

  private String nome;
  private String cognome;
  private String username;
  private String password;
  private String cellulare;
  private String email;
  private String tipo_u;

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
}
