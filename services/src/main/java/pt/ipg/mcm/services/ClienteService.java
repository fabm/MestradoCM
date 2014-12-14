package pt.ipg.mcm.services;

import pt.ipg.mcm.ClienteEJB;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;
import java.util.logging.Logger;

@WebService(serviceName = "cliente", portName = "clientePort")
public class ClienteService {
  Logger LOGGER = Logger.getLogger(ClienteService.class.getName());

  @EJB
  private ClienteEJB clienteEJB;

  @Inject
  private RolesAuthorized rolesAuthorized;

  @Resource
  private WebServiceContext webServiceContext;

  @PostConstruct
  private void init() {
    rolesAuthorized = new RolesAuthorized(webServiceContext);
  }


  @WebResult(name = "response")
  @WebMethod(operationName = "get-cliente")
  @ResponseWrapper(localName = "get")
  public ResGetCliente getCliente(@WebParam(name = "request") ReqGetCliente reqGetCliente) throws LoginException {
    rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR,Role.CLIENTE);
    return clienteEJB.getCliente(reqGetCliente);
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "add-cliente")
  @ResponseWrapper(localName = "add")
  public ResAddCliente addCliente(@WebParam(name = "request") ReqAddCliente reqAddCliente) throws LoginException {
    rolesAuthorized.checkAuthorization(Role.CLIENTE);
    return clienteEJB.addClient(reqAddCliente);
  }

}
