package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.calls.client.DateHelper;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaDetalheRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;

import static pt.ipg.mcm.calls.client.DateHelper.Format.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaDetalheXml {
  private long id;
  private Date dataCriacao;
  private int estado;
  private Date dataEntrega;
  private String observacoes;
  private long sync;
  private List<ProdutoEncomendadoComPreco> produtosEncomendados;

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

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public String getObservacoes() {
    return observacoes;
  }

  public void setObservacoes(String observacoes) {
    this.observacoes = observacoes;
  }

  public List<ProdutoEncomendadoComPreco> getProdutosEncomendados() {
    return produtosEncomendados;
  }

  public void setProdutosEncomendados(List<ProdutoEncomendadoComPreco> produtosEncomendados) {
    this.produtosEncomendados = produtosEncomendados;
  }

  public long getSync() {
    return sync;
  }

  public void setSync(long sync) {
    this.sync = sync;
  }


  public EncomendaDetalheRest convert() {
    EncomendaDetalheRest encomendaDetalheRest = new EncomendaDetalheRest();
    encomendaDetalheRest.setEstado(estado);
    encomendaDetalheRest.setDataEntrega(new DateHelper(COMPACT).toString(dataEntrega));
    encomendaDetalheRest.setDataCriacao(new DateHelper(COMPACT).toString(dataCriacao));
    encomendaDetalheRest.setObservacoes(observacoes);
    encomendaDetalheRest.setId(id);
    return encomendaDetalheRest;
  }
}
