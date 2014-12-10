package pt.ipg.mcm.client.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pt.ipg.mcm.client.Cliente;
import pt.ipg.mcm.client.ClienteService;
import pt.ipg.mcm.client.ReqAddCliente;
import pt.ipg.mcm.client.ResAddCliente;
import pt.ipg.mcm.client.TypeResponse;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

public class ClientTest {

  @Rule
  public ClientRule clientRule = new ClientRule();
  private ClienteService clienteService;

  public ClienteService getClienteService() {
    if (clienteService == null) {
      clienteService = new Cliente().getClientePort();
    }
    return clienteService;
  }

  @Test
  public void testAddClient() throws Exception {
    ClienteService cliente = getClienteService();
    ReqAddCliente clientTypeRequest = new ReqAddCliente();
    clientTypeRequest.setContacto("999999999");
    clientTypeRequest.setContribuinte(1111111111);
    XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory
        .newInstance()
        .newXMLGregorianCalendar(new GregorianCalendar());
    clientTypeRequest.setDataNascimento(xmlGregorianCalendar);
    clientTypeRequest.setEmail("francisco@sapo.com");
    clientTypeRequest.setLocalidade(6300559);
    clientTypeRequest.setMorada("uma avenida na Guarda");
    clientTypeRequest.setNome("Francisco Monteiro");
    clientTypeRequest.setPorta(99);
    clientTypeRequest.setRole(1);
    ResAddCliente response = cliente.addCliente(clientTypeRequest);

    Assert.assertEquals(TypeResponse.OK, response.getTypeResponse());
    Assert.assertTrue(response.getId()>=0);
  }
}
