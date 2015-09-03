package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaRest;
import pt.ipg.mcm.calls.client.model.encomendas.UpdateEncomendasRestIn;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaIn;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasIn;

import java.util.ArrayList;
import java.util.List;

public class R2SinEncomendas extends AbstractConversor<UpdateEncomendasRestIn, AddEncomendasIn> {
  public R2SinEncomendas(UpdateEncomendasRestIn inObject) {
    super(inObject);
  }

  @Override
  public AddEncomendasIn converted() {
    return toXInEncomendas();
  }

  public AddEncomendasIn toXInEncomendas() {
    AddEncomendasIn addEncomendasIn = new AddEncomendasIn();
    List<EncomendaIn> encomendaInList = new ArrayList<EncomendaIn>();
    for (EncomendaRest encomendaRest : source.getEncomendas()) {
      encomendaInList.add(new R2SinEncomenda(encomendaRest).converted());
    }
    addEncomendasIn.setEncomendaInList(encomendaInList);
    return addEncomendasIn;
  }

}
