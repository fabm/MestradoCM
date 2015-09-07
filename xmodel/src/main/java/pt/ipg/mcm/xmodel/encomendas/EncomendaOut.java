package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaOutRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class EncomendaOut {
  private long serverId;
  private int clientId;

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
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

  public EncomendaOutRest convert() {
    EncomendaOutRest encomendaOutRest = new EncomendaOutRest();
    encomendaOutRest.setServerId(serverId);
    encomendaOutRest.setClientId(clientId);
    return encomendaOutRest;
  }
}
