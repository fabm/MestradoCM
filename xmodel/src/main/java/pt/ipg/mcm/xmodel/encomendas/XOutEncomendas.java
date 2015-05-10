package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class XOutEncomendas extends RetornoSoap {

  public XOutEncomendas(MestradoException e) {
    super(e);
  }

  public XOutEncomendas() {
    XOutEncomendas = new ArrayList<XOutEncomenda>();
  }

  @XmlElementWrapper(name = "encomendas")
  @XmlElement(name = "encomenda")
  private List<XOutEncomenda> XOutEncomendas;

  public List<XOutEncomenda> getXOutEncomendas() {
    return XOutEncomendas;
  }
}
