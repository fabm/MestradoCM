package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaOut;

public class Encomenda2EncomendaIdsRest extends AbstractConversor<EncomendaOut,EncomendaIdsRest>{
  public Encomenda2EncomendaIdsRest(EncomendaOut encomendaOut) {
    super(encomendaOut);
  }

  @Override
  public EncomendaIdsRest converted() {
    EncomendaIdsRest encomendaIdsRest = new EncomendaIdsRest();
    encomendaIdsRest.setServerId(source.getServerId());
    return encomendaIdsRest;
  }
}
