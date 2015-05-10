package pt.ipg.mcm.calls.client.model.localidades;

import java.util.ArrayList;
import java.util.List;

public class GetLocalidadeRest {
  private List<LocalidadeRest> localidadeRestList;

  public List<LocalidadeRest> getLocalidadeRestList() {
    return localidadeRestList;
  }
  public List<LocalidadeRest> createLocalidadeRestList() {
    localidadeRestList = new ArrayList<>();
    return localidadeRestList;
  }


}
