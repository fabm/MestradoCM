package pt.ipg.mcm.calls.client.model.encomendas;

public class ProdutoEncomendadoRest {
  private int quantidade;
  private long idProduto;

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    } else if (!(obj instanceof ProdutoEncomendadoRest)) {
      return false;
    }
    return equals((ProdutoEncomendadoRest) obj);
  }

  protected boolean equals(ProdutoEncomendadoRest that){
    return this.idProduto == that.idProduto && this.quantidade == that.quantidade;
  }
}
