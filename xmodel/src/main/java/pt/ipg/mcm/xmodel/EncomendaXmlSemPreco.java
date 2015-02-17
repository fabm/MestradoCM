package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaXmlSemPreco extends EncomendaXml<ProdutoEncomendado>{

  private List<ProdutoEncomendado> produtoEncomendadoList;

  @Override
  public List<ProdutoEncomendado> getProdutoList() {
    return produtoEncomendadoList;
  }

  @Override
  public void setProdutoList(List<ProdutoEncomendado> produtoEncomendadoList) {
    this.produtoEncomendadoList = produtoEncomendadoList;
  }
}
