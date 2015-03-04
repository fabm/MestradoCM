package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReqAddClienteUtilizador {

  @XmlElement(required = true)
  private long contribuinte;

  @XmlElement(required = true)
  private String nome;

  @NotNull
  @XmlElement(required = true)
  private String morada;

  @NotNull
  @XmlElement(required = true)
  private String porta;

  @NotNull
  @XmlElement(name = "data-nascimento", required = true)
  private Date dataNascimento;

  @NotNull
  @XmlElement(required = true)
  private String email;

  @NotNull
  @XmlElement(required = true)
  private String contacto;

  @NotNull
  @XmlElement(required = true)
  private int localidade;
  private String login;
  private String password;

  public long getContribuinte() {
    return contribuinte;
  }

  public void setContribuinte(long value) {
    this.contribuinte = value;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String value) {
    this.nome = value;
  }

  public String getMorada() {
    return morada;
  }

  public void setMorada(String value) {
    this.morada = value;
  }

  public String getPorta() {
    return porta;
  }

  public void setPorta(String value) {
    this.porta = value;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date value) {
    this.dataNascimento = value;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String value) {
    this.email = value;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String value) {
    this.contacto = value;
  }

  public int getLocalidade() {
    return localidade;
  }

  public void setLocalidade(int value) {
    this.localidade = value;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getLogin() {
    return login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }
}
