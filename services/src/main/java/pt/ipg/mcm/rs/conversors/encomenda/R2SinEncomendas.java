package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestIn;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaSoapIn;
import pt.ipg.mcm.xmodel.encomendas.XInEncomendas;

import java.util.ArrayList;
import java.util.List;

public class R2SinEncomendas extends AbstractConversor<UpdateEncomendasRestIn, XInEncomendas> {
  public R2SinEncomendas(UpdateEncomendasRestIn inObject) {
    super(inObject);
  }

  @Override
  public XInEncomendas converted() {
    return toXInEncomendas();
  }

  public XInEncomendas toXInEncomendas() {
    XInEncomendas xInEncomendas = new XInEncomendas();
    List<EncomendaSoapIn> encomendaSoapInList = new ArrayList<EncomendaSoapIn>();
    for (EncomendaRest encomendaRest : source.getEncomendas()) {
      encomendaSoapInList.add(new R2SinEncomenda(encomendaRest).converted());
    }
    xInEncomendas.setEncomendaSoapIns(encomendaSoapInList);
    return xInEncomendas;
  }

}
