package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoSoap {

  private Integer codigo;

  private String mensagem;

  public RetornoSoap() {
  }

  public RetornoSoap(int codigo, String mensagem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public RetornoSoap(Exception e){
    this(new MestradoException(Erro.TECNICO));
    e.printStackTrace();
  }

  public RetornoSoap(MestradoException e) {
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
