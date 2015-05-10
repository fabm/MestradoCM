package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.xmodel.JaxbDateSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaSoapIn {

  private int clientId;

  @XmlJavaTypeAdapter(JaxbDateSerializer.class)
  private Date dataEntrega;

  @XmlElementWrapper(name = "produtos")
  @XmlElement(name = "produto")
  private List<ProdutoSoapIn> produtoSoapIns;

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

  public List<ProdutoSoapIn> getXInProdutos() {
    return produtoSoapIns;
  }

  public void setXInProdutos(List<ProdutoSoapIn> produtoSoapIns) {
    this.produtoSoapIns = produtoSoapIns;
  }
}
