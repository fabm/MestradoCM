package pt.ipg.mcm.entities;

public class AdministradorEntity {
  private long idadministrador;
  private String adminNome;
  private String tipoUtilizador;

  public long getIdadministrador() {
    return idadministrador;
  }

  public void setIdadministrador(long idadministrador) {
    this.idadministrador = idadministrador;
  }

  public String getAdminNome() {
    return adminNome;
  }

  public void setAdminNome(String adminNome) {
    this.adminNome = adminNome;
  }

  public String getTipoUtilizador() {
    return tipoUtilizador;
  }

  public void setTipoUtilizador(String tipoUtilizador) {
    this.tipoUtilizador = tipoUtilizador;
  }

}
