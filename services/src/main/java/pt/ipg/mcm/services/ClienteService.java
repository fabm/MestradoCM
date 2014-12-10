package pt.ipg.mcm.services;

import pt.ipg.mcm.ClienteEJB;
import pt.ipg.mcm.xmodel.cliente.request.ReqAddCliente;
import pt.ipg.mcm.xmodel.cliente.request.ReqGetCliente;
import pt.ipg.mcm.xmodel.cliente.response.ResAddCliente;
import pt.ipg.mcm.xmodel.cliente.response.ResGetCliente;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "cliente", portName = "clientePort")
public class ClienteService {

  @EJB
  private ClienteEJB clienteEJB;


  @WebMethod
  public ResGetCliente getCliente(ReqGetCliente reqGetCliente) {
    return clienteEJB.getCliente(reqGetCliente);
  }

  @WebMethod
  public ResAddCliente addCliente(ReqAddCliente reqAddCliente) {
    return clienteEJB.addClient(reqAddCliente);
  }

}
