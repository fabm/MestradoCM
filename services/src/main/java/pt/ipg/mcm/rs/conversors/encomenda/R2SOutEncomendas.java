package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaIdsRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestOut;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaOut;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasOut;

import java.util.List;

public class R2SOutEncomendas extends AbstractConversor<AddEncomendasOut,UpdateEncomendasRestOut> {
  public R2SOutEncomendas(AddEncomendasOut inObject) {
    super(inObject);
  }

  @Override
  public UpdateEncomendasRestOut converted() {
    UpdateEncomendasRestOut updateEncomendasRestOut = new UpdateEncomendasRestOut();
    List<EncomendaIdsRest> list = updateEncomendasRestOut.createEncomendaIdseRests();
    for (EncomendaOut encomendaOut :source.getEncomendaOutList()){
      list.add(new Encomenda2EncomendaIdsRest(encomendaOut).converted());
    }
    return updateEncomendasRestOut;
  }
}
