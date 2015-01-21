package pt.ipg.mcm.entities;

import java.math.BigDecimal;

public class UtilizadorEntity {
  private BigDecimal idUtilizador;
  private String login;
  private String password;

  public BigDecimal getIdUtilizador() {
    return idUtilizador;
  }

  public void setIdUtilizador(BigDecimal idUtilizador) {
    this.idUtilizador = idUtilizador;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
