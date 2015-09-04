package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-produto")
public class ResAddProduto {

  @XmlElement(required = true)
  private long id;

  @XmlElement(required = false)
  private RetornoSoap retorno;

  public ResAddProduto() {
  }

  public ResAddProduto(long id) {
    this.id = id;
    this.retorno = new RetornoSoap(1,"Produto inserido com sucesso");
  }

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  public RetornoSoap getRetorno() {
    return retorno;
  }

  public void setRetorno(RetornoSoap retorno) {
    this.retorno = retorno;
  }
}
