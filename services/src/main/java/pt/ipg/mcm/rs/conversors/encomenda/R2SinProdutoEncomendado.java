package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.ProdutoAEncomendar;

public class R2SinProdutoEncomendado extends AbstractConversor<ProdutoEncomendadoRest,ProdutoAEncomendar> {

  public R2SinProdutoEncomendado(ProdutoEncomendadoRest inObject) {
    super(inObject);
  }

  @Override
  public ProdutoAEncomendar converted() {
    ProdutoAEncomendar produtoAEncomendar = new ProdutoAEncomendar();
    produtoAEncomendar.setIdProduto(source.getIdProduto());
    produtoAEncomendar.setQuantidade(source.getQuandidade());
    return produtoAEncomendar;
  }
}
