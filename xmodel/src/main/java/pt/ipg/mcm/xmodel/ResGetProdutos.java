package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResGetProdutos {
  private Retorno retorno;
  private Long versaoMax;

  public ResGetProdutos(Retorno retorno) {
    this.retorno = retorno;
  }

  private List<ProdutoXml> resGetProdutos;

  public ResGetProdutos() {
  }

  public Long getVersaoMax() {
    return versaoMax;
  }

  public void setVersaoMax(Long maxVersion) {
    this.versaoMax = maxVersion;
  }

  public List<ProdutoXml> getResGetProdutos() {
    return resGetProdutos;
  }

  public void setResGetProdutos(List<ProdutoXml> produtoXmls) {
    this.resGetProdutos = produtoXmls;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
