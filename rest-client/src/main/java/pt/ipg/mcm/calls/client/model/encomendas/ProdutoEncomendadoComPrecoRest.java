package pt.ipg.mcm.calls.client.model.encomendas;

public class ProdutoEncomendadoComPrecoRest extends ProdutoEncomendadoRest{
  private int preco;

  public int getPreco() {
    return preco;
  }

  public void setPreco(int preco) {
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
