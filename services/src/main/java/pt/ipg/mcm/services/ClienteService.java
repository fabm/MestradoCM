package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ClienteDao;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.xmodel.ResAddCliente;

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
  private ClienteDao clienteDao;

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
    rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
    return clienteDao.getCliente(reqGetCliente);
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "add-cliente")
  @ResponseWrapper(localName = "add")
  public ResAddCliente addCliente(@WebParam(name = "request") ReqAddCliente reqAddCliente) throws LoginException {
    rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
    return clienteDao.addClient(reqAddCliente);
  }

}
