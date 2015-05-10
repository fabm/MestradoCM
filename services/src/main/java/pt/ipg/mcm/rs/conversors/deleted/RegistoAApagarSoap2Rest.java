package pt.ipg.mcm.rs.conversors.deleted;

import pt.ipg.mcm.calls.client.model.delete.RegistoAApagarRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.RegistoAApagar;

public class RegistoAApagarSoap2Rest extends AbstractConversor<RegistoAApagar,RegistoAApagarRest>{
  public RegistoAApagarSoap2Rest(RegistoAApagar source) {
    super(source);
  }

  @Override
  public RegistoAApagarRest converted() {
    RegistoAApagarRest registoAApagarRest = new RegistoAApagarRest();
    registoAApagarRest.setId(source.getId());
    registoAApagarRest.setTabela(source.getTabela());
    return registoAApagarRest;
  }
}
