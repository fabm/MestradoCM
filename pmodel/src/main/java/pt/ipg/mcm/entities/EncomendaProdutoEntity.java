package pt.ipg.mcm.entities;

public class EncomendaProdutoEntity {
  private long quantidade;
  private EncomendaEntity encomenda;
  private ProdutoEntity produto;

  public long getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(long quantidade) {
    this.quantidade = quantidade;
  }

  public EncomendaEntity getEncomenda() {
    return encomenda;
  }

  public void setEncomenda(EncomendaEntity encomenda) {
    this.encomenda = encomenda;
  }

  public ProdutoEntity getProduto() {
    return produto;
  }

  public void setProduto(ProdutoEntity produto) {
    this.produto = produto;
  }
}
