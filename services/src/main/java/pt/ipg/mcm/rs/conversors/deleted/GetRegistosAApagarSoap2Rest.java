package pt.ipg.mcm.rs.conversors.deleted;

import pt.ipg.mcm.calls.client.model.delete.GetRegistosAApagarRest;
import pt.ipg.mcm.calls.client.model.delete.RegistoAApagarRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.RegistoAApagar;
import pt.ipg.mcm.xmodel.RegistosAApagar;

import java.util.List;

public class GetRegistosAApagarSoap2Rest extends AbstractConversor<RegistosAApagar,GetRegistosAApagarRest>{
  public GetRegistosAApagarSoap2Rest(RegistosAApagar source) {
    super(source);
  }

  @Override
  public GetRegistosAApagarRest converted() {
    GetRegistosAApagarRest getRegistosAApagarRest = new GetRegistosAApagarRest();
    List<RegistoAApagarRest> list = getRegistosAApagarRest.createRegistoAApagarList();
    for(RegistoAApagar registoAApagar:source.getRegistoAApagarList()){
      list.add(new RegistoAApagarSoap2Rest(registoAApagar).converted());
    }
    return getRegistosAApagarRest;
  }
}
