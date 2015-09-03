package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaOut {
  private long serverId;

  public EncomendaOut(long idEncomenda) {
    this.serverId = idEncomenda;
  }

  public EncomendaOut() {
  }

  public long getServerId() {
    return serverId;
  }

  public void setServerId(long serverId) {
    this.serverId = serverId;
  }
}
