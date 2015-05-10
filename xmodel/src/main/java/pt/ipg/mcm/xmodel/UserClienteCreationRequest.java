package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserClienteCreationRequest {
  private Long contribuinte;
  private String nome;
  private String morada;
  private String porta;
  private Date dataNascimento;
  private String email;
  private String contacto;
  private Integer localidade;
  private String login;
  private String password;

  public void setContribuinte(Long contribuinte) {
    this.contribuinte = contribuinte;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public void setPorta(String porta) {
    this.porta = porta;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public void setLocalidade(Integer localidade) {
    this.localidade = localidade;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getContribuinte() {
    return contribuinte;
  }

  public String getNome() {
    return nome;
  }

  public String getMorada() {
    return morada;
  }

  public String getPorta() {
    return porta;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public String getContacto() {
    return contacto;
  }

  public Integer getLocalidade() {
    return localidade;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }
}
