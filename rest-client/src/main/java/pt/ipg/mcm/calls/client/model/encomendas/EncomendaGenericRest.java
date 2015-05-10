package pt.ipg.mcm.calls.client.model.encomendas;

public abstract class EncomendaGenericRest {
  private String date;
  private int clientId;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else if (!(obj instanceof EncomendaGenericRest)) {
      return false;
    }
    EncomendaGenericRest that = ((EncomendaGenericRest) obj);

    return this.clientId == that.clientId && this.date.equals(that.date);
  }
}
