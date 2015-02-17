package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RegistosAApagar extends Retorno{
  public RegistosAApagar() {
  }

  public RegistosAApagar(MestradoException e) {
    super(e);
  }

  public RegistosAApagar(List<RegistoAApagar> registoAApagarList) {
    this.registoAApagarList = registoAApagarList;
  }

  private List<RegistoAApagar> registoAApagarList;

  public List<RegistoAApagar> getRegistoAApagarList() {
    return registoAApagarList;
  }

}
