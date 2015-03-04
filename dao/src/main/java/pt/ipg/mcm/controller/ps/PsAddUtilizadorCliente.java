package pt.ipg.mcm.controller.ps;

import pt.ipg.mcm.controller.parameters.Procedure;
import pt.ipg.mcm.controller.parameters.ProcedureValue;

import java.util.Date;

@Procedure(name = "P_ADD_UTILIZADOR_CLIENTE", fieldsNumber = 10)
public class PsAddUtilizadorCliente {

  @ProcedureValue(index = 0)
  private long contribuinte;
  @ProcedureValue(index = 1)
  private String nome;
  @ProcedureValue(index = 2)
  private String morada;
  @ProcedureValue(index = 3)
  private String nPorta;
  @ProcedureValue(index = 4)
  private Date dataNascimento;
  @ProcedureValue(index = 5)
  private String email;
  @ProcedureValue(index = 6)
  private String contacto;
  @ProcedureValue(index = 7)
  private long localidade;
  @ProcedureValue(index = 8)
  private String login;
  @ProcedureValue(index = 9)
  private String password;

  public long getContribuinte() {
    return contribuinte;
  }

  public void setContribuinte(long contribuinte) {
    this.contribuinte = contribuinte;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMorada() {
    return morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public String getNPorta() {
    return nPorta;
  }

  public void setNPorta(String nPorta) {
    this.nPorta = nPorta;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getLocalidade() {
    return localidade;
  }

  public void setLocalidade(long localidade) {
    this.localidade = localidade;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getContacto() {
    return contacto;
  }

  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public String getnPorta() {
    return nPorta;
  }

  public void setnPorta(String nPorta) {
    this.nPorta = nPorta;
  }
}
