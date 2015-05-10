package pt.ipg.mcm.rs.conversors.localidades;

import pt.ipg.mcm.calls.client.model.localidades.GetLocalidadeRest;
import pt.ipg.mcm.calls.client.model.localidades.LocalidadeRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.Localidade;
import pt.ipg.mcm.xmodel.ResGetAllLocalidades;

import java.util.List;

public class LocalidadesSoap2Rest extends AbstractConversor<ResGetAllLocalidades,GetLocalidadeRest>{

  public LocalidadesSoap2Rest(ResGetAllLocalidades source) {
    super(source);
  }

  @Override
  public GetLocalidadeRest converted() {
    GetLocalidadeRest getLocalidadeRest = new GetLocalidadeRest();
    List<LocalidadeRest> list = getLocalidadeRest.createLocalidadeRestList();
    for(Localidade localidade:source.getLocalidades()){
      list.add(new LocalidadeSoap2Rest(localidade).converted());
    }
    return getLocalidadeRest;
  }
}
