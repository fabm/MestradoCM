package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateEncomendasRestOut {
  private List<EncomendaIdsRest> encomendaIdseRests;

  public List<EncomendaIdsRest> getEncomendaIdseRests() {
    return encomendaIdseRests;
  }

  public List<EncomendaIdsRest> createEncomendaIdseRests() {
    encomendaIdseRests = new ArrayList<>();
    return encomendaIdseRests;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof UpdateEncomendasRestOut)) {
      return false;
    }
    UpdateEncomendasRestOut that = (UpdateEncomendasRestOut) obj;
    if (this.encomendaIdseRests == null && that.encomendaIdseRests == null) {
      return true;
    }
    if (this.encomendaIdseRests.size() != that.encomendaIdseRests.size()) {
      return false;
    }
    Iterator<EncomendaIdsRest> itThis = this.encomendaIdseRests.iterator();
    Iterator<EncomendaIdsRest> itThat = that.encomendaIdseRests.iterator();
    while (itThis.hasNext()) {
      if (!itThis.next().equals(itThat.next())) {
        return false;
      }
    }
    return true;
  }
}
