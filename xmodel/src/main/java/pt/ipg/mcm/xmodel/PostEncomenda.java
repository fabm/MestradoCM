package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class PostEncomenda {

  private int clientId;

  @XmlJavaTypeAdapter(JaxbDateSerializer.class)
  private Date dataEntrega;

  @XmlElementWrapper(name = "produtos")
  @XmlElement(name = "produto")
  private List<PostEncomendaDetalhe> encomendas;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public List<PostEncomendaDetalhe> getProdutosEncomendados() {
    return encomendas;
  }

  public void setEncomendas(List<PostEncomendaDetalhe> encomendas) {
    this.encomendas = encomendas;
  }
}
