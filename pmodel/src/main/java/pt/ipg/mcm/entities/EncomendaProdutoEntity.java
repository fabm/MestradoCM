package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class EncomendaProdutoEntity {
  private long quantidade;
  private long idEncomenda;

  public long getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(long quantidade) {
    this.quantidade = quantidade;
  }

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EncomendaProdutoEntity that = (EncomendaProdutoEntity) o;

    if (idEncomenda != that.idEncomenda) {
      return false;
    }
    if (quantidade != that.quantidade) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (quantidade ^ (quantidade >>> 32));
    result = 31 * result + (int) (idEncomenda ^ (idEncomenda >>> 32));
    return result;
  }
}
