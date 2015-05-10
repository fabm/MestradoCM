package pt.ipg.mcm.calls.client.model;

import pt.ipg.mcm.calls.client.Retorno;

public class RetornoRest implements Retorno{
  private int codigo;
  private String mensagem;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  @Override
  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
