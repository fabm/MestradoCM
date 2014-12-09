package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by francisco on 09/12/14.
 */
@Entity
@Table(name = "LOCALIDADE")
public class LocalidadeEntity {
  private String codigoPostal;
  private String localidade;

  @Basic
  @Column(name = "CODIGO_POSTAL", nullable = false, insertable = true, updatable = true, length = 10)
  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  @Basic
  @Column(name = "LOCALIDADE", nullable = true, insertable = true, updatable = true, length = 40)
  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LocalidadeEntity that = (LocalidadeEntity) o;

    if (codigoPostal != null ? !codigoPostal.equals(that.codigoPostal) : that.codigoPostal != null) {
      return false;
    }
    if (localidade != null ? !localidade.equals(that.localidade) : that.localidade != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = codigoPostal != null ? codigoPostal.hashCode() : 0;
    result = 31 * result + (localidade != null ? localidade.hashCode() : 0);
    return result;
  }
}
