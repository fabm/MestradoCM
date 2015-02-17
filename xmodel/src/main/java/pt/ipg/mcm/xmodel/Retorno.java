package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Retorno {

  @NotNull
  @XmlElement(required = true)
  private Integer codigo;

  @NotNull
  @XmlElement(required = true)
  private String mensagem;

  public Retorno() {
  }

  public Retorno(int codigo, String mensagem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public Retorno(MestradoException e) {
    codigo = e.getCodigo();
    mensagem = e.getMessage();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
