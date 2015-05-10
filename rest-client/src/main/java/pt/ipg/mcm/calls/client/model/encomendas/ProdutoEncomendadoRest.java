package pt.ipg.mcm.calls.client.model.encomendas;

public class ProdutoEncomendadoRest {
  private int quandidade;
  private long idProduto;

  public int getQuandidade() {
    return quandidade;
  }

  public void setQuandidade(int quandidade) {
    this.quandidade = quandidade;
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
    return this.idProduto == that.idProduto && this.quandidade == that.quandidade;
  }
}
