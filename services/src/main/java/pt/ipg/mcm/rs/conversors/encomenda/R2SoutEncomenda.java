package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomenda;

public class R2SoutEncomenda extends AbstractConversor<EncomendaIdsRest, XOutEncomenda> {
  public R2SoutEncomenda(EncomendaIdsRest inObject) {
    super(inObject);
  }

  @Override
  public XOutEncomenda converted() {
    XOutEncomenda xOutEncomenda = new XOutEncomenda();
    xOutEncomenda.setClientId(source.getClientId());
    xOutEncomenda.setServerId(source.getServerId());
    return xOutEncomenda;
  }
}
