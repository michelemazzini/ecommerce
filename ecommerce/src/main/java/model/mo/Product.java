package model.mo;

public class Product {
  private String nome;
  private String productId;
  private String marca;
  private String categoria;
  private float prezzo;
  private float larghezza;
  private float altezza;
  private float spessore;
  private int quantita;

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
  
  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    this.prezzo = prezzo;
  }

  public float getLarghezza() {
    return larghezza;
  }

  public void setLarghezza(float larghezza) {
    this.larghezza = larghezza;
  }

  public float getAltezza() {
    return altezza;
  }

  public void setAltezza(float altezza) {
    this.altezza = altezza;
  }

  public float getSpessore() {
    return spessore;
  }

  public void setSpessore(float spessore) {
    this.spessore = spessore;
  }
}
