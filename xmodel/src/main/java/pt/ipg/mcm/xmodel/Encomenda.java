package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Encomenda {
  private long id;
  private Date dataCriacao;
  private int estado;
  private Date dataPrevista;
  private String observacoes;
  private List<ProdutoEncomendado> produtosEncomendados;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public Date getDataPrevista() {
    return dataPrevista;
  }

  public void setDataPrevista(Date dataPrevista) {
    this.dataPrevista = dataPrevista;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public List<ProdutoEncomendado> getProdutosEncomendados() {
    return produtosEncomendados;
  }

  public void setProdutosEncomendados(List<ProdutoEncomendado> produtosEncomendados) {
    this.produtosEncomendados = produtosEncomendados;
  }
}
