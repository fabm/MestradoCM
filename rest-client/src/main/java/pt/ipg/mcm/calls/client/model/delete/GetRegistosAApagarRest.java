package pt.ipg.mcm.calls.client.model.delete;

import java.util.ArrayList;
import java.util.List;

public class GetRegistosAApagarRest {
  private List<RegistoAApagarRest> registoAApagarRestList;

  public List<RegistoAApagarRest> getRegistoAApagarRestList() {
    return registoAApagarRestList;
  }
  public List<RegistoAApagarRest> createRegistoAApagarList() {
    registoAApagarRestList = new ArrayList<>();
    return registoAApagarRestList;
  }
}
