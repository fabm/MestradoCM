package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoComPrecoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.ProdutoEncomendadoComPreco;

import java.math.BigDecimal;

public class ProdutoEncomendadoComPreco2Rest extends AbstractConversor<ProdutoEncomendadoComPreco, ProdutoEncomendadoComPrecoRest> {
  public ProdutoEncomendadoComPreco2Rest(ProdutoEncomendadoComPreco source) {
    super(source);
  }

  @Override
  public ProdutoEncomendadoComPrecoRest converted() {
    ProdutoEncomendadoComPrecoRest produtoEncomendadoComPrecoRest = new ProdutoEncomendadoComPrecoRest();
    produtoEncomendadoComPrecoRest.setIdProduto(source.getIdProduto());
    produtoEncomendadoComPrecoRest.setQuandidade(source.getQuantidade());
    final BigDecimal preco = source.getPreco();
    produtoEncomendadoComPrecoRest.setPreco(preco.multiply(new BigDecimal(100)).intValue());
    return produtoEncomendadoComPrecoRest;
  }
}
