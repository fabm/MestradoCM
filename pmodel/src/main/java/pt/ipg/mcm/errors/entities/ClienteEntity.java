package pt.ipg.mcm.errors.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by francisco on 09/12/14.
 */
@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {
  private long idCliente;
  private BigDecimal contribuinte;
  private String nome;
  private BigDecimal prole;
  private String morada;

  @Id
  @Column(name = "ID_CLIENTE", nullable = false, insertable = true, updatable = true, precision = 0)
  public long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(long idCliente) {
    this.idCliente = idCliente;
  }

  @Basic
  @Column(name = "CONTRIBUINTE", nullable = false, insertable = true, updatable = true, precision = -127)
  public BigDecimal getContribuinte() {
    return contribuinte;
  }

  public void setContribuinte(BigDecimal contribuinte) {
    this.contribuinte = contribuinte;
  }

  @Basic
  @Column(name = "NOME", nullable = false, insertable = true, updatable = true, length = 150)
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Basic
  @Column(name = "PROLE", nullable = false, insertable = true, updatable = true, precision = -127)
  public BigDecimal getProle() {
    return prole;
  }

  public void setProle(BigDecimal prole) {
    this.prole = prole;
  }

  @Basic
  @Column(name = "MORADA", nullable = false, insertable = true, updatable = true, length = 200)
  public String getMorada() {
    return morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ClienteEntity that = (ClienteEntity) o;

    if (idCliente != that.idCliente) {
      return false;
    }
    if (contribuinte != null ? !contribuinte.equals(that.contribuinte) : that.contribuinte != null) {
      return false;
    }
    if (morada != null ? !morada.equals(that.morada) : that.morada != null) {
      return false;
    }
    if (nome != null ? !nome.equals(that.nome) : that.nome != null) {
      return false;
    }
    if (prole != null ? !prole.equals(that.prole) : that.prole != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = (int) (idCliente ^ (idCliente >>> 32));
    result = 31 * result + (contribuinte != null ? contribuinte.hashCode() : 0);
    result = 31 * result + (nome != null ? nome.hashCode() : 0);
    result = 31 * result + (prole != null ? prole.hashCode() : 0);
    result = 31 * result + (morada != null ? morada.hashCode() : 0);
    return result;
  }
}
