package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class VEncomendasLoginEntity {
  private String login;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  private Timestamp dataEntrega;

  public Timestamp getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Timestamp dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  private Long encomendaAssociada;

  public Long getEncomendaAssociada() {
    return encomendaAssociada;
  }

  public void setEncomendaAssociada(Long encomendaAssociada) {
    this.encomendaAssociada = encomendaAssociada;
  }

  private Long faturaId;

  public Long getFaturaId() {
    return faturaId;
  }

  public void setFaturaId(Long faturaId) {
    this.faturaId = faturaId;
  }

  private long calendario;

  public long getCalendario() {
    return calendario;
  }

  public void setCalendario(long calendario) {
    this.calendario = calendario;
  }

  private BigDecimal sync;

  public BigDecimal getSync() {
    return sync;
  }

  public void setSync(BigDecimal sync) {
    this.sync = sync;
  }

  private Timestamp dataCriacao;

  public Timestamp getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Timestamp dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  private Long estado;

  public Long getEstado() {
    return estado;
  }

  public void setEstado(Long estado) {
    this.estado = estado;
  }

  private String observacoes;

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  private Timestamp dataPrevista;

  public Timestamp getDataPrevista() {
    return dataPrevista;
  }

  public void setDataPrevista(Timestamp dataPrevista) {
    this.dataPrevista = dataPrevista;
  }

  private long idEncomenda;

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  private long quantidadeEncomendada;

  public long getQuantidadeEncomendada() {
    return quantidadeEncomendada;
  }

  public void setQuantidadeEncomendada(long quantidadeEncomendada) {
    this.quantidadeEncomendada = quantidadeEncomendada;
  }

  private long produtoEncomendado;

  public long getProdutoEncomendado() {
    return produtoEncomendado;
  }

  public void setProdutoEncomendado(long produtoEncomendado) {
    this.produtoEncomendado = produtoEncomendado;
  }

  private String produto;

  public String getProduto() {
    return produto;
  }

  public void setProduto(String produto) {
    this.produto = produto;
  }

  private BigDecimal precoAtual;

  public BigDecimal getPrecoAtual() {
    return precoAtual;
  }

  public void setPrecoAtual(BigDecimal precoAtual) {
    this.precoAtual = precoAtual;
  }

  private BigDecimal idCategoria;

  public BigDecimal getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(BigDecimal idCategoria) {
    this.idCategoria = idCategoria;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VEncomendasLoginEntity that = (VEncomendasLoginEntity) o;

    if (calendario != that.calendario) {
      return false;
    }
    if (idEncomenda != that.idEncomenda) {
      return false;
    }
    if (produtoEncomendado != that.produtoEncomendado) {
      return false;
    }
    if (quantidadeEncomendada != that.quantidadeEncomendada) {
      return false;
    }
    if (dataCriacao != null ? !dataCriacao.equals(that.dataCriacao) : that.dataCriacao != null) {
      return false;
    }
    if (dataEntrega != null ? !dataEntrega.equals(that.dataEntrega) : that.dataEntrega != null) {
      return false;
    }
    if (dataPrevista != null ? !dataPrevista.equals(that.dataPrevista) : that.dataPrevista != null) {
      return false;
    }
    if (encomendaAssociada != null ? !encomendaAssociada.equals(that.encomendaAssociada) : that.encomendaAssociada != null) {
      return false;
    }
    if (estado != null ? !estado.equals(that.estado) : that.estado != null) {
      return false;
    }
    if (faturaId != null ? !faturaId.equals(that.faturaId) : that.faturaId != null) {
      return false;
    }
    if (idCategoria != null ? !idCategoria.equals(that.idCategoria) : that.idCategoria != null) {
      return false;
    }
    if (login != null ? !login.equals(that.login) : that.login != null) {
      return false;
    }
    if (observacoes != null ? !observacoes.equals(that.observacoes) : that.observacoes != null) {
      return false;
    }
    if (precoAtual != null ? !precoAtual.equals(that.precoAtual) : that.precoAtual != null) {
      return false;
    }
    if (produto != null ? !produto.equals(that.produto) : that.produto != null) {
      return false;
    }
    if (sync != null ? !sync.equals(that.sync) : that.sync != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = login != null ? login.hashCode() : 0;
    result = 31 * result + (dataEntrega != null ? dataEntrega.hashCode() : 0);
    result = 31 * result + (encomendaAssociada != null ? encomendaAssociada.hashCode() : 0);
    result = 31 * result + (faturaId != null ? faturaId.hashCode() : 0);
    result = 31 * result + (int) (calendario ^ (calendario >>> 32));
    result = 31 * result + (sync != null ? sync.hashCode() : 0);
    result = 31 * result + (dataCriacao != null ? dataCriacao.hashCode() : 0);
    result = 31 * result + (estado != null ? estado.hashCode() : 0);
    result = 31 * result + (observacoes != null ? observacoes.hashCode() : 0);
    result = 31 * result + (dataPrevista != null ? dataPrevista.hashCode() : 0);
    result = 31 * result + (int) (idEncomenda ^ (idEncomenda >>> 32));
    result = 31 * result + (int) (quantidadeEncomendada ^ (quantidadeEncomendada >>> 32));
    result = 31 * result + (int) (produtoEncomendado ^ (produtoEncomendado >>> 32));
    result = 31 * result + (produto != null ? produto.hashCode() : 0);
    result = 31 * result + (precoAtual != null ? precoAtual.hashCode() : 0);
    result = 31 * result + (idCategoria != null ? idCategoria.hashCode() : 0);
    return result;
  }
}
