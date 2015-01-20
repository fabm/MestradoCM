package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResGetProdutos {
  private Retorno retorno;

  private List<ResGetProduto> resGetProdutos;

  public List<ResGetProduto> getResGetProdutos() {
    return resGetProdutos;
  }

  public void setResGetProdutos(List<ResGetProduto> resGetProdutos) {
    this.resGetProdutos = resGetProdutos;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
