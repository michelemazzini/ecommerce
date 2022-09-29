package model.mo;

public class Product {
  private String nome;
  private String productId;
  private String marca;
  private String categoria;
  private double prezzo;
  private double larghezza;
  private double altezza;
  private double spessore;
  private int quantita;

  public Product(){
      this.nome = null;
      this.productId = null;
      this.marca = null;
      this.categoria = null;
      this.prezzo = 0;
      this.larghezza = 0;
      this.altezza = 0;
      this.spessore = 0;
      this.quantita = 0;
  }
  
  public Product(String nome, String productId, String marca, String categoria, double prezzo, double larghezza, double altezza, double spessore, int quantita){
      this.nome = nome;
      this.productId = productId;
      this.marca = marca;
      this.categoria = categoria;
      this.prezzo = prezzo;
      this.larghezza = larghezza;
      this.altezza = altezza;
      this.spessore = spessore;
      this.quantita = quantita;
  }
  
  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }
  
  public int getQuantita(){
    return quantita;
  }
  
  public void setQuantita(int quantita){
    this.quantita = quantita;  
  }
  
  public double getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(double prezzo) {
    this.prezzo = prezzo;
  }

  public double getLarghezza() {
    return larghezza;
  }

  public void setLarghezza(double larghezza) {
    this.larghezza = larghezza;
  }

  public double getAltezza() {
    return altezza;
  }

  public void setAltezza(double altezza) {
    this.altezza = altezza;
  }

  public double getSpessore() {
    return spessore;
  }

  public void setSpessore(double spessore) {
    this.spessore = spessore;
  }
  
  @Override
  public boolean equals(Object o){
      Product prodotto = (Product)o;
      if(this.nome.equals(prodotto.getNome()) && this.productId.equals(prodotto.getProductId()) && this.marca.equals(prodotto.getMarca()) && this.categoria.equals(prodotto.getCategoria()) && this.prezzo == prodotto.getPrezzo() && this.larghezza == prodotto.getLarghezza() && this.altezza == prodotto.getAltezza() && this.spessore == prodotto.getSpessore() && this.quantita == prodotto.getQuantita()){
          return true;
      } else {
          return false;
      }
  }
}
