package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.ArrayList;
import java.util.List;

public class EncomendaRest extends EncomendaGenericRest {

  private List<ProdutoEncomendadoRest> produtoEncomendadoRests;

  public List<ProdutoEncomendadoRest> createProdutoEncomendadoRests() {
    produtoEncomendadoRests = new ArrayList<ProdutoEncomendadoRest>();
    return produtoEncomendadoRests;
  }

  public List<ProdutoEncomendadoRest> getProdutoEncomendadoRests() {
    return produtoEncomendadoRests;
  }
}
