package pt.ipg.mcm.errors;

import pt.ipg.mcm.xmodel.TypeEnumResponse;
import pt.ipg.mcm.xmodel.TypeResponse;

public class ExceptionToTypeResponse {
  private MestradoException mestradoException;

  public ExceptionToTypeResponse(MestradoException mestradoException) {
    this.mestradoException = mestradoException;
  }

  public TypeResponse getTypeResponse(){
    TypeResponse typeResponse = new TypeResponse();
    typeResponse.setMensagem(mestradoException.getMessage());
    typeResponse.setTipoResposta(TypeEnumResponse.ERRO);
    return typeResponse;
  }
}
