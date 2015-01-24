package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResAddEncomenda {
  private Long idEncomenda;
  private Retorno retorno;

  public ResAddEncomenda() {
  }

  public ResAddEncomenda(Long idEncomenda, Retorno retorno) {
    this.idEncomenda = idEncomenda;
    this.retorno = retorno;
  }

  public ResAddEncomenda(Retorno retorno) {
    this.retorno = retorno;
  }

  public Long getIdEncomenda() {
    return idEncomenda;
  }

  public void setIdEncomenda(Long idEncomenda) {
    this.idEncomenda = idEncomenda;
  }

  public Retorno getRetorno() {
    return retorno;
  }

  public void setRetorno(Retorno retorno) {
    this.retorno = retorno;
  }
}
