package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResAddClienteUtilizador extends Retorno{
  public ResAddClienteUtilizador(MestradoException e) {
    super(e);
  }

  public ResAddClienteUtilizador(int codigo, String mensagem) {
    super(codigo, mensagem);
  }

  public ResAddClienteUtilizador() {
  }
}
