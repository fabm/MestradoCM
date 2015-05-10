package pt.ipg.mcm.calls.client.model.encomendas;

public class EncomendaIdsRest extends EncomendaGenericRest{
  private long serverId;

  public long getServerId() {
    return serverId;
  }

  public void setServerId(long serverId) {
    this.serverId = serverId;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj ==null){
        return false;
    }
    if (!(obj instanceof EncomendaIdsRest)) {
        return false;
    }
    EncomendaIdsRest that = (EncomendaIdsRest) obj;

    return this.serverId == that.serverId;
  }
}
