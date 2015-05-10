package pt.ipg.mcm.rs.conversors.produto;

import pt.ipg.mcm.calls.client.model.produtos.GetProdutoDesyncRest;
import pt.ipg.mcm.calls.client.model.produtos.ProdutoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.ProdutoXml;
import pt.ipg.mcm.xmodel.ResGetProdutos;

import java.util.List;

public class GetProdutoDesyncXml2Rest extends AbstractConversor<ResGetProdutos,GetProdutoDesyncRest>{
  public GetProdutoDesyncXml2Rest(ResGetProdutos source) {
    super(source);
  }

  @Override
  public GetProdutoDesyncRest converted() {
    GetProdutoDesyncRest getProdutoDesyncRest = new GetProdutoDesyncRest();
    List<ProdutoRest> list = getProdutoDesyncRest.createProdutoRestList();
    for(ProdutoXml produtoXml:source.getResGetProdutos()){
      list.add(new ProdutoXml2Rest(produtoXml).converted());
    }
    getProdutoDesyncRest.setVersaoMax(source.getVersaoMax());
    return getProdutoDesyncRest;
  }
}
