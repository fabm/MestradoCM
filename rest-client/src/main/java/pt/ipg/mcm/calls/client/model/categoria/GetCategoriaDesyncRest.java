package pt.ipg.mcm.calls.client.model.categoria;

import java.util.ArrayList;
import java.util.List;

public class GetCategoriaDesyncRest {
  private List<CategoriaRest> categorias;

  private long maxVersao;

  public List<CategoriaRest> createCategorias() {
    categorias = new ArrayList<>();
    return categorias;
  }
  public List<CategoriaRest> getCategorias() {
    if (categorias == null) {
      categorias = new ArrayList<>();
    }
    return categorias;
  }

  public long getMaxVersao() {
    return maxVersao;
  }

  public void setMaxVersao(long maxVersao) {
    this.maxVersao = maxVersao;
  }
}
