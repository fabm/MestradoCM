package pt.ipg.mcm.rs.conversors;

import pt.ipg.mcm.calls.client.model.RetornoRest;
import pt.ipg.mcm.xmodel.RetornoSoap;

public class RetornoSoap2Rest extends AbstractConversor<RetornoSoap, RetornoRest> {

  private final RetornoRest retornoRest;

  public RetornoSoap2Rest(RetornoSoap source, RetornoRest retornoRest) {
    super(source);
    this.retornoRest = retornoRest;
  }

  public RetornoSoap2Rest(RetornoSoap source) {
    this(source, new RetornoRest());
  }

  @Override
  public RetornoRest converted() {
    retornoRest.setCodigo(source.getCodigo());
    retornoRest.setMensagem(source.getMensagem());
    return retornoRest;
  }
}
