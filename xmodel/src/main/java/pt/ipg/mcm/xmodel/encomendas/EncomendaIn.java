package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaIn {

  private Date dataEntrega;

  @XmlTransient
  private long calendario;
  @XmlTransient
  private long idEncomenda;
  @XmlTransient
  private String login;
  @XmlTransient
  private String encomendaAssociada;

  @XmlElementWrapper(name = "produtos")
  @XmlElement(name = "produto")
  private List<ProdutoAEncomendar> produtoAEncomendarList;

  public static EncomendaIn convert(EncomendaInRest encomendaInRest) {
    EncomendaIn encomendaIn = new EncomendaIn();
    encomendaIn.produtoAEncomendarList = new ArrayList<ProdutoAEncomendar>();
    for(ProdutoEncomendadoRest produtoEncomendadoRest :encomendaInRest.getProdutoEncomendadoRestList()){
      ProdutoAEncomendar produtoAEncomendar = new ProdutoAEncomendar();
      produtoAEncomendar.convert(produtoEncomendadoRest);
      encomendaIn.produtoAEncomendarList.add(produtoAEncomendar);
    }
    encomendaIn.dataEntrega = encomendaInRest.getDataEntrega();
    return encomendaIn;
  }

  public Date getDataEntrega() {
    return dataEntrega;
  }


  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public List<ProdutoAEncomendar> getProdutoAEncomendarList() {
    return produtoAEncomendarList;
  }

  public void setProdutoAEncomendarList(List<ProdutoAEncomendar> produtoAEncomendarList) {
    this.produtoAEncomendarList = produtoAEncomendarList;
  }

  public long getCalendario() {
    return calendario;
  }

  public void setCalendario(long calendario) {
    this.calendario = calendario;
  }

  public long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getEncomendaAssociada() {
    return encomendaAssociada;
  }
  public EncomendaInRest convert() {
    EncomendaInRest encomendaInRest = new EncomendaInRest();

    encomendaInRest.setDataEntrega(dataEntrega);

    List<ProdutoEncomendadoRest> produtoEncomendadoRestList = new ArrayList<ProdutoEncomendadoRest>();
    for(ProdutoAEncomendar produtoAEncomendar:produtoAEncomendarList){
      produtoEncomendadoRestList.add(produtoAEncomendar.convert());
    }
    encomendaInRest.setProdutoEncomendadoRestList(produtoEncomendadoRestList);

    return encomendaInRest;
  }
}
