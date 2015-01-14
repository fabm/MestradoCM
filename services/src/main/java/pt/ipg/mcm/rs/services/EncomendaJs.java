package pt.ipg.mcm.rs.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaJs {

  @XmlElement(name = "tt")
  private String teste;

  public String getTeste() {
    return teste;
  }

  public void setTeste(String teste) {
    this.teste = teste;
  }
}
