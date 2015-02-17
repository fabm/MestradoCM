package pt.ipg.mcm.entities;

public class LocalidadeEntity {
  private String codigoPostal;
  private String localidade;
  private long idLocalidade;

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public long getIdLocalidade() {
    return idLocalidade;
  }

  public void setIdLocalidade(long idLocalidade) {
    this.idLocalidade = idLocalidade;
  }


}
