package pt.ipg.mcm.services;

import pt.ipg.mcm.ClienteEJB;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(serviceName = "cliente", portName = "clientePort")
public class ClienteService {

  @EJB
  private ClienteEJB clienteEJB;


  @WebResult(name = "response")
  @WebMethod(operationName = "get-cliente")
  @ResponseWrapper(localName = "get")
  public ResGetCliente getCliente(@WebParam(name = "request")ReqGetCliente reqGetCliente) {
    return clienteEJB.getCliente(reqGetCliente);
  }

  @WebResult(name = "response")
  @WebMethod(operationName = "add-cliente")
  @ResponseWrapper(localName = "add")
  public ResAddCliente addCliente(@WebParam(name = "request")ReqAddCliente reqAddCliente) {
    return clienteEJB.addClient(reqAddCliente);
  }

}
