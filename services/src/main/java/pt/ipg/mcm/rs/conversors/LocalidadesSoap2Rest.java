package pt.ipg.mcm.rs.conversors;

import pt.ipg.mcm.calls.client.model.localidades.LocalidadeRest;
import pt.ipg.mcm.xmodel.Localidade;
import pt.ipg.mcm.xmodel.ResGetAllLocalidades;

import java.util.ArrayList;
import java.util.List;

public class LocalidadesSoap2Rest extends AbstractConversor<ResGetAllLocalidades, List<LocalidadeRest>> {
  public LocalidadesSoap2Rest(ResGetAllLocalidades source) {
    super(source);
  }

  @Override
  public List<LocalidadeRest> converted() {
    List<LocalidadeRest> list = new ArrayList<LocalidadeRest>();
    for (Localidade localidade : source.getLocalidades()) {
      LocalidadeRest localidadeRest = new LocalidadeRest();
      localidadeRest.setCodPostal(localidade.getCodigoPostal());
      localidadeRest.setId((int) localidade.getId());
      localidadeRest.setLocalidade(localidade.getLocalidade());
    }
    return list;
  }
}
