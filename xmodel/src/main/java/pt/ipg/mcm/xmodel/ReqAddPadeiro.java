package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.validacao.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.security.NoSuchAlgorithmException;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReqAddPadeiro {

  @NotEmpty
  @XmlElement(required = true)
  private String login;

  @NotEmpty
  @XmlElement(required = true)
  private String password;

  @NotEmpty
  @XmlElement(required = true)
  private String nome;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getPassword() throws NoSuchAlgorithmException {
    return SHAUtils.hashingSHA256(password);
  }
}
