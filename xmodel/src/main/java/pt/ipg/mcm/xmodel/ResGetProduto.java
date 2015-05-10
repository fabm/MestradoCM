package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-produto")
public class ResGetProduto extends ProdutoXml {

  @XmlElement(required = false)
  private RetornoSoap retorno;

  public RetornoSoap getRetorno() {
    return retorno;
  }

  public void setRetorno(RetornoSoap retorno) {
    this.retorno = retorno;
  }
}
