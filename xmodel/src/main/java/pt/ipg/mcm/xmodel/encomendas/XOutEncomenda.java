package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class XOutEncomenda {
  private int clientId;
  private long serverId;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public long getServerId() {
    return serverId;
  }

  public void setServerId(long serverId) {
    this.serverId = serverId;
  }
}
