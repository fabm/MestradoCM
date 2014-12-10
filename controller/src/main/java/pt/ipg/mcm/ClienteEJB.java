package pt.ipg.mcm;

import pt.ipg.mcm.xmodel.cliente.request.AddClienteRequest;
import pt.ipg.mcm.xmodel.cliente.request.ClienteTypeRequest;
import pt.ipg.mcm.xmodel.cliente.response.AddClienteResponse;
import pt.ipg.mcm.xmodel.cliente.response.ClienteTypeResponse;
import pt.ipg.mcm.xmodel.cliente.response.TypeResponse;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

@Stateless
public class ClienteEJB {

  @Resource(name = "jdbc/mestrado")
  private DataSource dataSource;

  @PersistenceContext(unitName = "mestrado")
  private EntityManager entityManager;

  public AddClienteResponse addClient(AddClienteRequest addClienteRequest) {
    ClienteTypeResponse clienteResponseType = new ClienteTypeResponse();
    Connection connection;
    try {
      connection = dataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_NOVO_CLIENTE(?,?,?,?,?,?,?,?,?)");
      call.setLong(1, addClienteRequest.getContribuinte());
      call.setString(2, addClienteRequest.getNome());
      call.setInt(3, addClienteRequest.getRole());
      call.setString(4, addClienteRequest.getMorada());
      call.setInt(5, addClienteRequest.getPorta());
      call.setDate(6, new Date(addClienteRequest.getDataNascimento().toGregorianCalendar().getTimeInMillis()));
      call.setString(7, addClienteRequest.getEmail());
      call.setString(8, addClienteRequest.getContacto());
      call.setLong(9, addClienteRequest.getLocalidade());
      call.registerOutParameter(10, Types.NUMERIC);
      call.execute();

      clienteResponseType.setId(call.getLong(10));
      clienteResponseType.setTypeResponse(TypeResponse.OK);
      return clienteResponseType;
    } catch (SQLException e) {
      clienteResponseType.setTypeResponse(TypeResponse.ERRO);
      return clienteResponseType;
    }

  }



}
