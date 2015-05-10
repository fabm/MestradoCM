package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RegistosAApagar extends RetornoSoap {
  public RegistosAApagar() {
  }
  private List<RegistoAApagar> registoAApagarList;

  public RegistosAApagar(MestradoException e) {
    super(e);
  }

  public RegistosAApagar(List<RegistoAApagar> registoAApagarList) {
    this.registoAApagarList = registoAApagarList;
  }

  public List<RegistoAApagar> getRegistoAApagarList() {
    return registoAApagarList;
  }

}
