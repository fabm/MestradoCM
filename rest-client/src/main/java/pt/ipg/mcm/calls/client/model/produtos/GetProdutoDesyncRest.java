package pt.ipg.mcm.calls.client.model.produtos;

import java.util.ArrayList;
import java.util.List;

public class GetProdutoDesyncRest {
  private List<ProdutoRest> produtoRestList;
  private Long versaoMax;


  public Long getVersaoMax() {
    return versaoMax;
  }

  public void setVersaoMax(Long versaoMax) {
    this.versaoMax = versaoMax;
  }

  public List<ProdutoRest> createProdutoRestList() {
    produtoRestList = new ArrayList<ProdutoRest>();
    return produtoRestList;
  }

  public List<ProdutoRest> getProdutoRestList() {
    return produtoRestList;
  }

}
