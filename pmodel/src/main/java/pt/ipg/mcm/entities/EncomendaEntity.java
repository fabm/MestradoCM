package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class EncomendaEntity {
  private long idEncomenda;
  private Timestamp dataEntrega;
  private long encomendaAssociada;
  private long quantidade;
  private BigDecimal sync;

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  public Timestamp getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Timestamp dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public long getEncomendaAssociada() {
    return encomendaAssociada;
  }

  public void setEncomendaAssociada(long encomendaAssociada) {
    this.encomendaAssociada = encomendaAssociada;
  }

  public long getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(long quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getSync() {
    return sync;
  }

  public void setSync(BigDecimal sync) {
    this.sync = sync;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EncomendaEntity that = (EncomendaEntity) o;

    if (encomendaAssociada != that.encomendaAssociada) {
      return false;
    }
    if (idEncomenda != that.idEncomenda) {
      return false;
    }
    if (quantidade != that.quantidade) {
      return false;
    }
    if (dataEntrega != null ? !dataEntrega.equals(that.dataEntrega) : that.dataEntrega != null) {
      return false;
    }
    if (sync != null ? !sync.equals(that.sync) : that.sync != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (idEncomenda ^ (idEncomenda >>> 32));
    result = 31 * result + (dataEntrega != null ? dataEntrega.hashCode() : 0);
    result = 31 * result + (int) (encomendaAssociada ^ (encomendaAssociada >>> 32));
    result = 31 * result + (int) (quantidade ^ (quantidade >>> 32));
    result = 31 * result + (sync != null ? sync.hashCode() : 0);
    return result;
  }
}
