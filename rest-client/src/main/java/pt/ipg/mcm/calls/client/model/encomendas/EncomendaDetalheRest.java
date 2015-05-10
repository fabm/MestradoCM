package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EncomendaDetalheRest {
  private long id;
  private String dataCriacao;
  private int estado;
  private String dataEntrega;
  private String observacoes;
  private List<ProdutoEncomendadoComPrecoRest> produtosEncomendados;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public String getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(String dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public List<ProdutoEncomendadoComPrecoRest> createProdutosEncomendados() {
    produtosEncomendados = new ArrayList<ProdutoEncomendadoComPrecoRest>();
    return produtosEncomendados;
  }

  public List<ProdutoEncomendadoComPrecoRest> getProdutosEncomendados() {
    return produtosEncomendados;
  }
}
