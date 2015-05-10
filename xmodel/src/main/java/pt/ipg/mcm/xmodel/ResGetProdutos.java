package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResGetProdutos {
  private RetornoSoap retorno;
  private Long versaoMax;

  public ResGetProdutos(RetornoSoap retorno) {
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

  public RetornoSoap getRetorno() {
    return retorno;
  }

  public void setRetorno(RetornoSoap retorno) {
    this.retorno = retorno;
  }
}
