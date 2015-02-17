package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReqAddEncomendas {
  private List<EncomendaXmlSemPreco> encomendas;

  public List<EncomendaXmlSemPreco> getEncomendas() {
    return encomendas;
  }

  public void setEncomendas(List<EncomendaXmlSemPreco> encomendas) {
    this.encomendas = encomendas;
  }
}
