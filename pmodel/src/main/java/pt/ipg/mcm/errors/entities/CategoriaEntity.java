package pt.ipg.mcm.errors.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by francisco on 09/12/14.
 */
@Entity
@Table(name = "CATEGORIA")
public class CategoriaEntity {
  private long idCategoria;
  private String nome;
  private String descricao;

  @Id
  @Column(name = "ID_CATEGORIA", nullable = false, insertable = true, updatable = true, precision = 0)
  public long getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(long idCategoria) {
    this.idCategoria = idCategoria;
  }

  @Basic
  @Column(name = "NOME", nullable = false, insertable = true, updatable = true, length = 100)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Basic
  @Column(name = "DESCRICAO", nullable = true, insertable = true, updatable = true, length = 300)
  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CategoriaEntity that = (CategoriaEntity) o;

    if (idCategoria != that.idCategoria) {
      return false;
    }
    if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) {
      return false;
    }
    if (nome != null ? !nome.equals(that.nome) : that.nome != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (idCategoria ^ (idCategoria >>> 32));
    result = 31 * result + (nome != null ? nome.hashCode() : 0);
    result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
    return result;
  }
}
