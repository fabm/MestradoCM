package pt.ipg.mcm.calls.client.model.encomendas;

public class EncomendaOutRest {
    private long serverId;
    private int clientId;

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
