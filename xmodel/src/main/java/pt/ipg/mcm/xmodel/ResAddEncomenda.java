package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResAddEncomenda {
  private Long idEncomenda;
  private RetornoSoap retorno;

  public ResAddEncomenda() {
  }

  public ResAddEncomenda(Long idEncomenda, RetornoSoap retorno) {
    this.idEncomenda = idEncomenda;
    this.retorno = retorno;
  }

  public ResAddEncomenda(RetornoSoap retorno) {
    this.retorno = retorno;
  }

  public Long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(Long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  public RetornoSoap getRetorno() {
    return retorno;
  }

  public void setRetorno(RetornoSoap retorno) {
    this.retorno = retorno;
  }
}
