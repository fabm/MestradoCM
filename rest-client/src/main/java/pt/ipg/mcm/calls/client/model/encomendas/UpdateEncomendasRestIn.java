package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateEncomendasRestIn {
  private List<EncomendaRest> encomendas;

  public UpdateEncomendasRestIn() {
    encomendas = new ArrayList<EncomendaRest>();
  }

  public List<EncomendaRest> getEncomendas() {
    return encomendas;
  }

  public List<EncomendaRest> createEncomendas() {
    this.encomendas = new ArrayList<EncomendaRest>();
    return this.encomendas;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof UpdateEncomendasRestIn)) {
      return false;
    }

    UpdateEncomendasRestIn that = ((UpdateEncomendasRestIn) obj);

    if (that.encomendas == null && this.encomendas == null) {
      return true;
    }

    Iterator<EncomendaRest> itThisEncomendas = this.encomendas.iterator();
    Iterator<EncomendaRest> itThatEncomendas = that.encomendas.iterator();

    while (itThisEncomendas.hasNext()) {
      if (!itThatEncomendas.hasNext()) {
        return false;
      } else if (!itThisEncomendas.next().equals(itThatEncomendas.next())) {
        return false;
      }
    }
    return true;
  }

}
