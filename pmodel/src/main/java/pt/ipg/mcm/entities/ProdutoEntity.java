package pt.ipg.mcm.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by francisco on 02/01/15.
 */
@Entity
@Table(name = "PRODUTO", schema = "BDA_1010136", catalog = "")
public class ProdutoEntity {
  private long idProduto;
  private String nome;
  private BigDecimal precoAtual;
  private byte[] foto;

  @Id
  @Column(name = "ID_PRODUTO", nullable = false, insertable = true, updatable = true, precision = 0)
  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }

  @Basic
  @Column(name = "NOME", nullable = true, insertable = true, updatable = true, length = 100)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Basic
  @Column(name = "PRECO_ATUAL", nullable = true, insertable = true, updatable = true, precision = 2)
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProdutoEntity that = (ProdutoEntity) o;

    if (idProduto != that.idProduto) {
      return false;
    }
    if (!Arrays.equals(foto, that.foto)) {
      return false;
    }
    if (nome != null ? !nome.equals(that.nome) : that.nome != null) {
      return false;
    }
    if (precoAtual != null ? !precoAtual.equals(that.precoAtual) : that.precoAtual != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (idProduto ^ (idProduto >>> 32));
    result = 31 * result + (nome != null ? nome.hashCode() : 0);
    result = 31 * result + (precoAtual != null ? precoAtual.hashCode() : 0);
    result = 31 * result + (foto != null ? Arrays.hashCode(foto) : 0);
    return result;
  }
}
