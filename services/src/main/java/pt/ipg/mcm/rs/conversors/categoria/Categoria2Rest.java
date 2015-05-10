package pt.ipg.mcm.rs.conversors.categoria;

import pt.ipg.mcm.calls.client.model.categoria.CategoriaRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.Categoria;

public class Categoria2Rest extends AbstractConversor<Categoria,CategoriaRest>{
  public Categoria2Rest(Categoria source) {
    super(source);
  }

  @Override
  public CategoriaRest converted() {
    CategoriaRest categoriaRest = new CategoriaRest();
    categoriaRest.setDescricao(source.getDescricao());
    categoriaRest.setId(source.getId());
    categoriaRest.setNome(source.getNome());
    return categoriaRest;
  }
}
