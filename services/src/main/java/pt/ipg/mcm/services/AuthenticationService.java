package pt.ipg.mcm.services;

import pt.ipg.mcm.xmodel.ResLogin;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.jws.WebService;

@WebService(serviceName = "Authentication", portName = "AuthenticationPort")
public class AuthenticationService {
  public ResLogin login() {
    ResLogin resLogin = new ResLogin();
    resLogin.setRetorno(new RetornoSoap(1, "Login efetuado com sucesso"));
    return resLogin;
  }
}
