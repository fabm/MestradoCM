package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-get-produto")
public class ReqGetProduto {

  @NotNull
  @XmlElement(required = true)
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long value) {
    this.id = value;
  }

}
