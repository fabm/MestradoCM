package pt.ipg.mcm.services;

import pt.ipg.mcm.xmodel.ResLogin;
import pt.ipg.mcm.xmodel.TypeEnumResponse;
import pt.ipg.mcm.xmodel.TypeResponse;

import javax.jws.WebService;

@WebService
public class AuthenticationService {
  public ResLogin login() {
    ResLogin resLogin = new ResLogin();
    TypeResponse response = new TypeResponse();
    response.setMensagem("sucesso");
    response.setTipoResposta(TypeEnumResponse.OK);
    resLogin.setTypeResponse(response);
    return resLogin;
  }
}
