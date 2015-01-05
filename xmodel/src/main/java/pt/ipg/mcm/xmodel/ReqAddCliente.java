package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-add-cliente")
public class ReqAddCliente {

  @NotNull
  @XmlElement(required = true)
  private long contribuinte;

  @NotNull
  @XmlElement(required = true)
  private String nome;

  @NotNull
  @XmlElement(required = true)
  private int role;

  @NotNull
  @XmlElement(required = true)
  private String morada;

  @NotNull
  @XmlElement(required = true)
  private String porta;

  @NotNull
  @XmlElement(name = "data-nascimento", required = true)
  @XmlSchemaType(name = "date")
  private XMLGregorianCalendar dataNascimento;

  @NotNull
  @XmlElement(required = true)
  private String email;

  @NotNull
  @XmlElement(required = true)
  private String contacto;

  @NotNull
  @XmlElement(required = true)
  private int localidade;

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

  public int getRole() {
    return role;
  }

  public void setRole(int value) {
    this.role = value;
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

  public XMLGregorianCalendar getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(XMLGregorianCalendar value) {
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

}
