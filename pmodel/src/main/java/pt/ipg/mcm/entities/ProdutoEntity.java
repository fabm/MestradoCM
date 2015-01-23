package pt.ipg.mcm.entities;

import java.math.BigDecimal;

public class ProdutoEntity {
  private long idProduto;
  private String nome;
  private BigDecimal precoAtual;
  private byte[] foto;
  private long idCategoria;
  private long sync;

  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getPrecoAtual() {
    return precoAtual;
  }

  public void setPrecoAtual(BigDecimal precoAtual) {
    this.precoAtual = precoAtual;
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(long idCategoria) {
    this.idCategoria = idCategoria;
  }

  public long getSync() {
    return sync;
  }

  public void setSync(long sync) {
    this.sync = sync;
  }

}
