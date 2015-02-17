package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaXmlComPreco extends EncomendaXml<ProdutoEncomendadoComPreco> {

  private List<ProdutoEncomendadoComPreco> produtoEncomendadoComPrecoList;

  @Override
  public List<ProdutoEncomendadoComPreco> getProdutoList() {
    return produtoEncomendadoComPrecoList;
  }

  @Override
  public void setProdutoList(List<ProdutoEncomendadoComPreco> produtoEncomendadoComPrecoList) {
    this.produtoEncomendadoComPrecoList = produtoEncomendadoComPrecoList;
  }
}
