package pt.ipg.mcm.calls.client.model.localidades;

public class LocalidadeRest {
  private String localidade;
  private int codPostal;
  private long id;

  public LocalidadeRest() {
  }

  public LocalidadeRest(int id, int codPostal, String localidade) {
    this.id = id;
    this.codPostal = codPostal;
    this.localidade = localidade;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public int getCodPostal() {
    return codPostal;
  }

  public void setCodPostal(int codPostal) {
    this.codPostal = codPostal;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
