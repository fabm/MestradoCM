package pt.ipg.mcm.entities;

import java.math.BigDecimal;

public class VProdutoCategoriaEntity {
  private Long idproduto;
  private String nomeCategoria;
  private String descricao;
  private BigDecimal precoAtual;
  private byte[] foto;
  private String nomeProduto;

  public Long getIdProduto() {
    return idproduto;
  }

  public void setIdproduto(Long idproduto) {
    this.idproduto = idproduto;
  }

  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
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

  public String getNomeProduto() {
    return nomeProduto;
  }

  public void setNomeProduto(String nomeProduto) {
    this.nomeProduto = nomeProduto;
  }

}
