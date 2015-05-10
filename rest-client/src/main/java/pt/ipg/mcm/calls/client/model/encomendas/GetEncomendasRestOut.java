package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetEncomendasRestOut {
  private List<EncomendaRest> encomendasRest;

  public List<EncomendaRest> createEncomendasRest() {
    encomendasRest = new ArrayList<EncomendaRest>();
    return encomendasRest;
  }

  public List<EncomendaRest> getEncomendaRest() {
    return encomendasRest;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else if (!(obj instanceof GetEncomendasRestOut)) {
      return false;
    }
    return equals((GetEncomendasRestOut) obj);
  }

  private boolean equals(GetEncomendasRestOut that) {
    if (this.encomendasRest == null && this.encomendasRest == null) {
      return true;
    } else if (this.encomendasRest == null || that.encomendasRest == null) {
      return false;
    } else {
      Iterator<EncomendaRest> thisIt = this.encomendasRest.iterator();
      Iterator<EncomendaRest> thatIt = that.encomendasRest.iterator();

      while (thisIt.hasNext()) {
        if (!thatIt.hasNext()) {
          return false;
        }else if(!thisIt.next().equals(thatIt.next())){
          return false;
        }
      }
    }
    return true;
  }
}
