package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-padeiro")
public class ResAddPadeiro {

  private Long id;

  private RetornoSoap retorno;

  public ResAddPadeiro() {
  }

  public ResAddPadeiro(RetornoSoap retorno) {
    this.retorno = retorno;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public RetornoSoap getRetorno() {
    return retorno;
  }

  public void setRetorno(RetornoSoap retorno) {
    this.retorno = retorno;
  }
}
