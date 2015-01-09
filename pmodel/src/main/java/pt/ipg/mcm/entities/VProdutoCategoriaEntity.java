package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;

@Table(name = "V_PRODUTO_CATEGORIA")
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

  public void setIdproduto(Long idproduto){this.idproduto = idproduto;}


  @Basic
  @Column(name = "NOME_CATEGORIA", nullable = true, insertable = true, updatable = true, length = 200)
  public String getNomeCategoria() {
    return nomeCategoria;
  }

  public void setNomeCategoria(String nomeCategoria) {
    this.nomeCategoria = nomeCategoria;
  }

  @Basic
  @Column(name = "DESCRICAO", nullable = true, insertable = true, updatable = true, length = 500)
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Basic
  @Column(name = "PRECO_ATUAL", nullable = false, insertable = true, updatable = true, precision = 2)
  public BigDecimal getPrecoAtual() {
    return precoAtual;
  }

  public void setPrecoAtual(BigDecimal precoAtual) {
    this.precoAtual = precoAtual;
  }

  @Basic
  @Column(name = "FOTO", nullable = true, insertable = true, updatable = true)
  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  @Basic
  @Column(name = "NOME_PRODUTO", nullable = false, insertable = true, updatable = true, length = 100)
  public String getNomeProduto() {
    return nomeProduto;
  }

  public void setNomeProduto(String nomeProduto) {
    this.nomeProduto = nomeProduto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VProdutoCategoriaEntity that = (VProdutoCategoriaEntity) o;

    if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) {
      return false;
    }
    if (!Arrays.equals(foto, that.foto)) {
      return false;
    }
    if (nomeCategoria != null ? !nomeCategoria.equals(that.nomeCategoria) : that.nomeCategoria != null) {
      return false;
    }
    if (nomeProduto != null ? !nomeProduto.equals(that.nomeProduto) : that.nomeProduto != null) {
      return false;
    }
    if (precoAtual != null ? !precoAtual.equals(that.precoAtual) : that.precoAtual != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = nomeCategoria != null ? nomeCategoria.hashCode() : 0;
    result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
    result = 31 * result + (precoAtual != null ? precoAtual.hashCode() : 0);
    result = 31 * result + (foto != null ? Arrays.hashCode(foto) : 0);
    result = 31 * result + (nomeProduto != null ? nomeProduto.hashCode() : 0);
    return result;
  }
}
