package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Categoria")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CategoriaComId extends Categoria{

  public void setId(Long id) {
    this.id = id;
  }
}
