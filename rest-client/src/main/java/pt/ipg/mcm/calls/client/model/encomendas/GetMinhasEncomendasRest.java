package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.List;

public class GetMinhasEncomendasRest {
  private List<EncomendaDetalheRest> encomendaDetalheRestList;
  private long maxSync;

  public List<EncomendaDetalheRest> getEncomendaDetalheRestList() {
    return encomendaDetalheRestList;
  }

  public List<EncomendaDetalheRest> createEncomendaDetalheRestList() {
    encomendaDetalheRestList = new ArrayList<>();
    return encomendaDetalheRestList;
  }

  public void setMaxSync(long maxSync) {
    this.maxSync = maxSync;
  }

  public long getMaxSync() {
    return maxSync;
  }
}
