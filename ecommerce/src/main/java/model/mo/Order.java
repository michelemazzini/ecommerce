package model.mo;

public class Order {
  private int numero;
  private String indirizzo;
  private String username_c;
  private String citta;
  private String num_carta;
  private String CVV;
  private String anno_scad_carta;
  private String mese_scad_carta;
  
  public int getNumeroOrdine() {
    return numero;
  }

  public void setNumeroOrdine(int numero) {
    this.numero = numero;
  }
  
  public void setUsername_c(String username_c) {
    this.username_c = username_c;
  }
  
  public String getUsername_c() {
    return username_c;
  }
  
  public String getIndirizzo(){
      return indirizzo;
  }
  
  public void setIndirizzo(String indirizzo){
      this.indirizzo = indirizzo;
  }
  
  public String getCitta() {
    return citta;
  }
  
  public void setCitta(String citta) {
    this.citta = citta;
  }
  
  public String getNum_carta() {
    return num_carta;
  }
  
  public void setNum_carta(String num_carta) {
    this.num_carta = num_carta;
  }
  
  public String getCVV() {
    return CVV;
  }
  
  public void setCVV(String CVV) {
    this.CVV = CVV;
  }
  
  public String getAnno_scad_carta() {
    return anno_scad_carta;
  }
  
  public void setAnno_scad_carta(String anno_scad_carta) {
    this.anno_scad_carta = anno_scad_carta;
  }
  
  public String getMese_scad_carta() {
    return mese_scad_carta;
  }
  
  public void setMese_scad_carta(String mese_scad_carta) {
    this.mese_scad_carta = mese_scad_carta;
  }
}
