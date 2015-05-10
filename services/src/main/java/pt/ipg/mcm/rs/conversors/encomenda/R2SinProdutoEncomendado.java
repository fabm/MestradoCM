package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.ProdutoSoapIn;

public class R2SinProdutoEncomendado extends AbstractConversor<ProdutoEncomendadoRest,ProdutoSoapIn> {

  public R2SinProdutoEncomendado(ProdutoEncomendadoRest inObject) {
    super(inObject);
  }

  @Override
  public ProdutoSoapIn converted() {
    ProdutoSoapIn produtoSoapIn = new ProdutoSoapIn();
    produtoSoapIn.setIdProduto(source.getIdProduto());
    produtoSoapIn.setQuantidade(source.getQuandidade());
    return produtoSoapIn;
  }
}
