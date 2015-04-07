package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResPostMinhasEncomendas {

  public ResPostMinhasEncomendas() {
    resPostMinhasEncomendasDetalhes = new ArrayList<ResPostMinhasEncomendasDetalhe>();
  }

  @XmlElementWrapper(name = "encomendas")
  @XmlElement(name = "encomenda")
  private List<ResPostMinhasEncomendasDetalhe> resPostMinhasEncomendasDetalhes;

  public List<ResPostMinhasEncomendasDetalhe> getResPostMinhasEncomendasDetalhes() {
    return resPostMinhasEncomendasDetalhes;
  }
}
