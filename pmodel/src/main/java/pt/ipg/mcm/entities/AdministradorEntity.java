package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by francisco on 09/12/14.
 */
@Entity
@Table(name = "ADMINISTRADOR")
public class AdministradorEntity {
  private long idadministrador;
  private String adminNome;
  private String tipoUtilizador;

  @Basic
  @Column(name = "IDADMINISTRADOR", nullable = false, insertable = true, updatable = true, precision = 0)
  public long getIdadministrador() {
    return idadministrador;
  }

  public void setIdadministrador(long idadministrador) {
    this.idadministrador = idadministrador;
  }

  @Basic
  @Column(name = "ADMIN_NOME", nullable = true, insertable = true, updatable = true, length = 200)
  public String getAdminNome() {
    return adminNome;
  }

  public void setAdminNome(String adminNome) {
    this.adminNome = adminNome;
  }

  @Basic
  @Column(name = "TIPO_UTILIZADOR", nullable = true, insertable = true, updatable = true, length = 200)
  public String getTipoUtilizador() {
    return tipoUtilizador;
  }

  public void setTipoUtilizador(String tipoUtilizador) {
    this.tipoUtilizador = tipoUtilizador;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AdministradorEntity that = (AdministradorEntity) o;

    if (idadministrador != that.idadministrador) {
      return false;
    }
    if (adminNome != null ? !adminNome.equals(that.adminNome) : that.adminNome != null) {
      return false;
    }
    if (tipoUtilizador != null ? !tipoUtilizador.equals(that.tipoUtilizador) : that.tipoUtilizador != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (idadministrador ^ (idadministrador >>> 32));
    result = 31 * result + (adminNome != null ? adminNome.hashCode() : 0);
    result = 31 * result + (tipoUtilizador != null ? tipoUtilizador.hashCode() : 0);
    return result;
  }
}
