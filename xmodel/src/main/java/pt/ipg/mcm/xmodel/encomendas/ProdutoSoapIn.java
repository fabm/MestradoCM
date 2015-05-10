package pt.ipg.mcm.xmodel.encomendas;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProdutoSoapIn {
  private int quantidade;
  private long idProduto;

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }
}
