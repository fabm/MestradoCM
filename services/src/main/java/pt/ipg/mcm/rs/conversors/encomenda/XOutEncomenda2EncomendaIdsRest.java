package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomenda;

public class XOutEncomenda2EncomendaIdsRest extends AbstractConversor<XOutEncomenda,EncomendaIdsRest>{
  public XOutEncomenda2EncomendaIdsRest(XOutEncomenda xOutEncomenda) {
    super(xOutEncomenda);
  }

  @Override
  public EncomendaIdsRest converted() {
    EncomendaIdsRest encomendaIdsRest = new EncomendaIdsRest();
    encomendaIdsRest.setServerId(source.getServerId());
    encomendaIdsRest.setClientId(source.getClientId());
    return encomendaIdsRest;
  }
}
