package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-categoria")
public class ResAddCategoria {


  @XmlElement(required = true)
  private long id;

  @XmlElement(required = true)
  private RetornoSoap retorno;

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
