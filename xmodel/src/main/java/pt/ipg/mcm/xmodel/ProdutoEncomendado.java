package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProdutoEncomendado {


  private long idProduto;
  private int quantidade;

  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }
}
