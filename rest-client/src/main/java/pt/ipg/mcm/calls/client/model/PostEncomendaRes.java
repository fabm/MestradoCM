package pt.ipg.mcm.calls.client.model;

import com.google.gson.JsonObject;

public class PostEncomendaRes {
  private long clientId;
  private long serverId;

  public void fromJson(JsonObject jsonObject){
    serverId = jsonObject.get("serverId").getAsLong();
    clientId = jsonObject.get("clientId").getAsInt();
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

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
    if(!(obj instanceof PostEncomendaRes)){
        return false;
    }
    PostEncomendaRes that = (PostEncomendaRes) obj;

    return this.clientId == that.clientId &&
    this.serverId == that.serverId;
  }
}
