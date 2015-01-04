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
  private Retorno retorno;

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
