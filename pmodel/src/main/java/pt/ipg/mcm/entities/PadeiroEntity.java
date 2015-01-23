package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class PadeiroEntity {
  private int idPadeiro;
  private String nome;

  @Id
  @Column(name = "ID_PADEIRO", nullable = false, insertable = true, updatable = true, precision = 0)
  public int getIdPadeiro() {
    return idPadeiro;
  }

  public void setIdPadeiro(int idPadeiro) {
    this.idPadeiro = idPadeiro;
  }

  @Basic
  @Column(name = "NOME", nullable = false, insertable = true, updatable = true, length = 100)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PadeiroEntity that = (PadeiroEntity) o;

    if (idPadeiro != that.idPadeiro) {
      return false;
    }
    if (nome != null ? !nome.equals(that.nome) : that.nome != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = idPadeiro;
    result = 31 * result + (nome != null ? nome.hashCode() : 0);
    return result;
  }
}
