package pt.ipg.mcm.rs.conversors.categoria;

import pt.ipg.mcm.calls.client.model.categoria.CategoriaRest;
import pt.ipg.mcm.calls.client.model.categoria.GetCategoriaDesyncRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ResCategoriasDesync;

import java.util.List;

public class GetCategoriaDesyncS2R extends AbstractConversor<ResCategoriasDesync, GetCategoriaDesyncRest> {
  public GetCategoriaDesyncS2R(ResCategoriasDesync source) {
    super(source);
  }

  @Override
  public GetCategoriaDesyncRest converted() {
    GetCategoriaDesyncRest getCategoriaDesyncRest = new GetCategoriaDesyncRest();

    List<CategoriaRest> list = getCategoriaDesyncRest.createCategorias();
    for (Categoria categoria : source.getCategorias()) {
      CategoriaRest categoriaRest = new Categoria2Rest(categoria).converted();
      list.add(categoriaRest);
    }
    getCategoriaDesyncRest.setMaxVersao(source.getMaxVersao());
    return getCategoriaDesyncRest;
  }
}
