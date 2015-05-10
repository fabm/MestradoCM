package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XInEncomendas {

  @XmlElementWrapper(name = "encomendas")
  @XmlElement(name = "encomenda")
  private List<EncomendaSoapIn> encomendaSoapIns;

  public List<EncomendaSoapIn> getEncomendaSoapIns() {
    return encomendaSoapIns;
  }

  public void setEncomendaSoapIns(List<EncomendaSoapIn> encomendaSoapIns) {
    this.encomendaSoapIns = encomendaSoapIns;
  }
}
