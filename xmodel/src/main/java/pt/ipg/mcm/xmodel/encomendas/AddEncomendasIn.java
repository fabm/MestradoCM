package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddEncomendasIn {

  @XmlElementWrapper(name = "encomendas")
  @XmlElement(name = "encomenda")
  private List<EncomendaIn> encomendaInList;

  public List<EncomendaIn> getEncomendaInList() {
    return encomendaInList;
  }

  public void setEncomendaInList(List<EncomendaIn> encomendaIns) {
    this.encomendaInList = encomendaIns;
  }
}
