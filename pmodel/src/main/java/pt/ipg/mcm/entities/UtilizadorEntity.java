package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

public class UtilizadorEntity {
  private BigDecimal idUtilizador;
  private String login;
  private String password;


  @Id
  @Column(name = "ID_UTILIZADOR", nullable = false, insertable = true, updatable = true, precision = -127)
  public BigDecimal getIdUtilizador() {
    return idUtilizador;
  }

  public void setIdUtilizador(BigDecimal idUtilizador) {
    this.idUtilizador = idUtilizador;
  }

  @Basic
  @Column(name = "LOGIN", nullable = false, insertable = true, updatable = true, length = 100)
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  @Basic
  @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true, length = 256)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UtilizadorEntity that = (UtilizadorEntity) o;

    if (idUtilizador != null ? !idUtilizador.equals(that.idUtilizador) : that.idUtilizador != null) {
      return false;
    }
    if (login != null ? !login.equals(that.login) : that.login != null) {
      return false;
    }
    if (password != null ? !password.equals(that.password) : that.password != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = idUtilizador != null ? idUtilizador.hashCode() : 0;
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }
}
