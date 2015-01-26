package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReqAddEncomenda {

  private Date dataEntrega;

  private List<ProdutoEncomendado> produtosEncomendados;

  public Date getDataEntrega() {
    return dataEntrega;
  }

  public void setDataEntrega(Date dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public List<ProdutoEncomendado> getProdutosEncomendados() {
    return produtosEncomendados;
  }

  public void setProdutosEncomendados(List<ProdutoEncomendado> produtosEncomendados) {
    this.produtosEncomendados = produtosEncomendados;
  }
}
