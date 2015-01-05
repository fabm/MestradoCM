package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.ClienteDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.RolesAuthorized;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.Retorno;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.WebServiceContext;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebService(serviceName = "Cliente", portName = "ClientePort")
public class ClienteService {
  Logger LOGGER = Logger.getLogger(ClienteService.class.getName());

  @EJB
  private ClienteDao clienteDao;

  private RolesAuthorized rolesAuthorized;

  @Resource
  private WebServiceContext webServiceContext;

  @PostConstruct
  private void init() {
    rolesAuthorized = new RolesAuthorized(webServiceContext);
  }


  @WebResult(name = "response")
  @WebMethod(operationName = "get-cliente")
  @ResponseWrapper(localName = "get-cliente-response")
  public ResGetCliente getCliente(@WebParam(name = "request") @XmlElement(required = true) ReqGetCliente reqGetCliente) throws LoginException {
    rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
    return clienteDao.getCliente(reqGetCliente);
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "add-cliente")
  @ResponseWrapper(localName = "add-cliente-response")
  public ResAddCliente addCliente(@WebParam(name = "request")@XmlElement(required = true) ReqAddCliente reqAddCliente) throws LoginException {
    rolesAuthorized.checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("dataNascimento","date de nascimento");
      Validacao.getInstance().valida(reqAddCliente,aliasMap);
      return clienteDao.addClient(reqAddCliente);
    } catch (MestradoException e) {
      ResAddCliente resAdCliente = new ResAddCliente();
      resAdCliente.setRetorno(new Retorno(e));
      return resAdCliente;
    }
  }

}
