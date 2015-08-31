package pt.ipg.mcm.services;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.controller.ClienteDao;
import pt.ipg.mcm.controller.ps.PsAddUtilizadorCliente;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.services.authorization.Role;
import pt.ipg.mcm.services.authorization.SecureService;
import pt.ipg.mcm.validacao.Validacao;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqAddClienteUtilizador;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResAddClienteUtilizador;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.RetornoSoap;

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

@WebService(serviceName = "Cliente", portName = "ClientePort")
public class ClienteService extends SecureService {

  @EJB
  private ClienteDao clienteDao;

  @Resource
  private WebServiceContext webServiceContext;

  @WebMethod
  public ResGetCliente getCliente(@WebParam(name = "id") long id) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.ADMINISTRADOR, Role.CLIENTE);
    return clienteDao.getCliente(id);
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "add-cliente")
  @ResponseWrapper(localName = "add-cliente-response")
  public ResAddCliente addCliente(@WebParam(name = "request") @XmlElement(required = true) ReqAddCliente reqAddCliente) throws LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.ADMINISTRADOR, Role.CONVIDADO);
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("dataNascimento", "date de nascimento");
      Validacao.getInstance().valida(reqAddCliente, aliasMap);
      return clienteDao.addClient(reqAddCliente);
    } catch (MestradoException e) {
      ResAddCliente resAdCliente = new ResAddCliente();
      resAdCliente.setRetorno(new RetornoSoap(e));
      return resAdCliente;
    }
  }


  @WebMethod
  public ResAddClienteUtilizador addClienteUtilizador(@WebParam(name = "request") @XmlElement(required = true) ReqAddClienteUtilizador
                                                          reqAddClienteUtilizador) throws
      LoginException {
    setWsc(webServiceContext);
    checkAuthorization(Role.ADMINISTRADOR, Role.CONVIDADO);
    try {
      Map<String, String> aliasMap = new HashMap<String, String>();
      aliasMap.put("dataNascimento", "date de nascimento");
      Validacao.getInstance().valida(reqAddClienteUtilizador, aliasMap);

      PsAddUtilizadorCliente psAddUtilizadorCliente = new PsAddUtilizadorCliente();
      psAddUtilizadorCliente.setContribuinte(reqAddClienteUtilizador.getContribuinte());
      psAddUtilizadorCliente.setNome(reqAddClienteUtilizador.getNome());
      psAddUtilizadorCliente.setMorada(reqAddClienteUtilizador.getMorada());
      psAddUtilizadorCliente.setNPorta(reqAddClienteUtilizador.getPorta());
      psAddUtilizadorCliente.setDataNascimento(reqAddClienteUtilizador.getDataNascimento());
      psAddUtilizadorCliente.setEmail(reqAddClienteUtilizador.getEmail());
      psAddUtilizadorCliente.setContacto(reqAddClienteUtilizador.getContacto());
      psAddUtilizadorCliente.setLocalidade(reqAddClienteUtilizador.getLocalidade());
      psAddUtilizadorCliente.setLogin(reqAddClienteUtilizador.getLogin());
      psAddUtilizadorCliente.setPassword(reqAddClienteUtilizador.getPassword());

      clienteDao.addClienteUtilizador(psAddUtilizadorCliente);
      return new ResAddClienteUtilizador(1, "Registo efectuado com sucesso");
    } catch (MestradoException e) {
      return new ResAddClienteUtilizador(e);
    }
  }

  @WebMethod
  public RetornoSoap deleteCliente(@WebParam(name = "id") long id){
      try {
          clienteDao.deleteCliente(id);
          return new RetornoSoap(1,"Cliente removido com sucesso");
      } catch (MestradoException e) {
          return new RetornoSoap(e);
      }
  }


}
