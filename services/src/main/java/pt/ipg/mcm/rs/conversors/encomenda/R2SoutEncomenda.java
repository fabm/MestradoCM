package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaOut;

public class R2SoutEncomenda extends AbstractConversor<EncomendaIdsRest, EncomendaOut> {
  public R2SoutEncomenda(EncomendaIdsRest inObject) {
    super(inObject);
  }

  @Override
  public EncomendaOut converted() {
    EncomendaOut encomendaOut = new EncomendaOut();
    encomendaOut.setServerId(source.getServerId());
    return encomendaOut;
  }
}
