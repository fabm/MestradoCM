package pt.ipg.mcm.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class VUtilizadorClienteEntity {
  private String login;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private Long contribuinte;

  public Long getContribuinte() {
    return contribuinte;
  }

  public void setContribuinte(Long contribuinte) {
    this.contribuinte = contribuinte;
  }

  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  private Date datanascimento;

  public Date getDatanascimento() {
    return datanascimento;
  }

  public void setDatanascimento(Date datanascimento) {
    this.datanascimento = datanascimento;
  }

  private String contacto;

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  private String nporta;

  public String getNporta() {
    return nporta;
  }

  public void setNporta(String nporta) {
    this.nporta = nporta;
  }

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private Integer localidade;

  public Integer getLocalidade() {
    return localidade;
  }

  public void setLocalidade(Integer localidade) {
    this.localidade = localidade;
  }

  private String morada;

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

    VUtilizadorClienteEntity that = (VUtilizadorClienteEntity) o;

    if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) {
      return false;
    }
    if (contribuinte != null ? !contribuinte.equals(that.contribuinte) : that.contribuinte != null) {
      return false;
    }
    if (datanascimento != null ? !datanascimento.equals(that.datanascimento) : that.datanascimento != null) {
      return false;
    }
    if (email != null ? !email.equals(that.email) : that.email != null) {
      return false;
    }
    if (localidade != null ? !localidade.equals(that.localidade) : that.localidade != null) {
      return false;
    }
    if (login != null ? !login.equals(that.login) : that.login != null) {
      return false;
    }
    if (morada != null ? !morada.equals(that.morada) : that.morada != null) {
      return false;
    }
    if (nome != null ? !nome.equals(that.nome) : that.nome != null) {
      return false;
    }
    if (nporta != null ? !nporta.equals(that.nporta) : that.nporta != null) {
      return false;
    }
    if (password != null ? !password.equals(that.password) : that.password != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = login != null ? login.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (contribuinte != null ? contribuinte.hashCode() : 0);
    result = 31 * result + (nome != null ? nome.hashCode() : 0);
    result = 31 * result + (datanascimento != null ? datanascimento.hashCode() : 0);
    result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
    result = 31 * result + (nporta != null ? nporta.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (localidade != null ? localidade.hashCode() : 0);
    result = 31 * result + (morada != null ? morada.hashCode() : 0);
    return result;
  }
}
