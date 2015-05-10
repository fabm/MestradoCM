package pt.ipg.mcm.calls.client.model.user;

import pt.ipg.mcm.calls.client.model.CheckException;

public class CreateUserClientRestIn {
  private long contribuinte;
  private String nome;
  private String morada;
  private String porta;
  private String dataNascimento;
  private String email;
  private String contacto;
  private int localidade;
  private String login;
  private String password;
  private String codigoPostal;

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public long getContribuinte() {
    return contribuinte;
  }

  public void setAndCheckContribuinte(String contribuinte) throws CheckException {
    CheckException.checkErroVazio(contribuinte, "contribuinte");
    try {
      setContribuinte(Long.parseLong(contribuinte));
    } catch (NumberFormatException e) {
      throw CheckException.erroFormato(contribuinte);
    }
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

  public void setAndCheckNome(String nome) throws CheckException {
    CheckException.checkErroVazio(nome, "nome");
    setNome(nome);
  }

  public String getMorada() {
    return morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public void setAndCheckMorada(String morada) throws CheckException {
    CheckException.checkErroVazio(morada, "morada");
    setMorada(morada);
  }

  public String getPorta() {
    return porta;
  }

  public void setPorta(String porta) {
    this.porta = porta;
  }

  public void setAndCheckPorta(String porta) throws CheckException {
    CheckException.checkErroVazio(porta, "porta");
    setPorta(porta);
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void checkDataDeNascimento() throws CheckException {
    CheckException.checkErroVazio(dataNascimento, "data de nascimento");
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public void setAndCheckEmail(String email) throws CheckException {
    CheckException.checkErroVazio("email", email);
    setEmail(email);
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContacto() {
    return contacto;
  }


  public void setContacto(String contacto) {
    this.contacto = contacto;
  }

  public void setAndCheckContacto(String contacto) throws CheckException {
    CheckException.checkErroVazio("contacto",contacto);
    setContacto(contacto);
  }

  public int getLocalidade() {
    return localidade;
  }

  public void checkLocalidade() throws CheckException {
    CheckException.checkErroVazio(localidade, "localidade");
  }

  public void setLocalidade(int localidade) {
    this.localidade = localidade;
  }

  public String getLogin() {
    return login;
  }

  public void setAndCheckLogin(String login) throws CheckException {
    CheckException.checkErroVazio(login, "login");
    setLogin(login);
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setAndCheckPassword(String password) throws CheckException {
    CheckException.checkErroVazio(password, "password");
    setPassword(password);
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
