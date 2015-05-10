package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomenda;
import pt.ipg.mcm.xmodel.encomendas.XOutEncomendas;

import java.util.List;

public class R2SoutEncomendas extends AbstractConversor<XOutEncomendas,UpdateEncomendasRestOut> {
  public R2SoutEncomendas(XOutEncomendas inObject) {
    super(inObject);
  }

  @Override
  public UpdateEncomendasRestOut converted() {
    UpdateEncomendasRestOut updateEncomendasRestOut = new UpdateEncomendasRestOut();
    List<EncomendaIdsRest> list = updateEncomendasRestOut.createEncomendaIdseRests();
    for (XOutEncomenda xOutEncomenda:source.getXOutEncomendas()){
      list.add(new XOutEncomenda2EncomendaIdsRest(xOutEncomenda).converted());
    }
    return updateEncomendasRestOut;
  }
}
