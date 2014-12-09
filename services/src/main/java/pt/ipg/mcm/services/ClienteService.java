package pt.ipg.mcm.services;

import pt.ipg.mcm.ClienteEJB;
import pt.ipg.mcm.xmodel.cliente.request.ClienteTypeRequest;
import pt.ipg.mcm.xmodel.cliente.response.ClienteTypeResponse;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(serviceName = "cliente", portName = "clientePort")
public class ClienteService {

  @EJB
  private ClienteEJB clienteEJB;

  public ClienteTypeResponse addCliente(ClienteTypeRequest clienteTypeRequest){
    return clienteEJB.addClient(clienteTypeRequest);
  }
}
