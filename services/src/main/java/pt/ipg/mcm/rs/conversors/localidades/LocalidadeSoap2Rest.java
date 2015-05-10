package pt.ipg.mcm.rs.conversors.localidades;

import pt.ipg.mcm.calls.client.model.localidades.LocalidadeRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.Localidade;

public class LocalidadeSoap2Rest extends AbstractConversor<Localidade, LocalidadeRest> {
  public LocalidadeSoap2Rest(Localidade source) {
    super(source);
  }

  @Override
  public LocalidadeRest converted() {
    LocalidadeRest localidadeRest = new LocalidadeRest();
    localidadeRest.setLocalidade(source.getLocalidade());
    localidadeRest.setId(source.getId());
    localidadeRest.setCodPostal(source.getCodigoPostal());
    return localidadeRest;
  }
}
