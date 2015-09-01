package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.Email;
import pt.ipg.mcm.validacao.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class UserClienteCreationRequest {

  @NotNull
  private Long contribuinte;
  @NotEmpty
  private String nome;
  @NotEmpty
  private String morada;
  @NotEmpty
  private String porta;
  @NotNull
  private Date dataNascimento;
  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String contacto;
  @NotNull
  private Integer localidade;
  @NotEmpty
  private String login;
  @NotEmpty
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

  public String getPasswordHash() throws NoSuchAlgorithmException {
    return SHAUtils.hashingSHA256(password);
  }

    public String getStrDataNascimento(){
       return new SimpleDateFormat("yyyy-MM-dd").format(dataNascimento);
    }

    public void setRetorno(Long retorno){

        System.out.println(retorno);
    }
}
