package pt.ipg.mcm.calls.client.model.encomendas;

import java.math.BigDecimal;

public class ProdutoEncomendadoComPrecoRest extends ProdutoEncomendadoRest{
  private BigDecimal preco;

  public BigDecimal getPreco() {
    return preco;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else if (!(obj instanceof ProdutoEncomendadoComPrecoRest)) {
      return false;
    }
    return equals((ProdutoEncomendadoComPrecoRest) obj);
  }

  private boolean equals(ProdutoEncomendadoComPrecoRest that) {
    return super.equals(that) && this.preco == that.preco;
  }
}
