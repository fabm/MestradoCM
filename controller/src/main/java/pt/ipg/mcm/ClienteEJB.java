package pt.ipg.mcm;

import pt.ipg.mcm.xmodel.cliente.request.ClienteTypeRequest;
import pt.ipg.mcm.xmodel.cliente.response.ClienteTypeResponse;
import pt.ipg.mcm.xmodel.cliente.response.TypeResponse;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
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

  public ClienteTypeResponse addClient(ClienteTypeRequest clienteTypeRequest) {
    ClienteTypeResponse clienteResponseType = new ClienteTypeResponse();
    Connection connection;
    try {
      connection = dataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_NOVO_CLIENTE(?,?,?,?,?,?,?,?,?)");
      call.setLong(1, clienteTypeRequest.getContribuinte());
      call.setString(2, clienteTypeRequest.getNome());
      call.setInt(3, clienteTypeRequest.getRole());
      call.setString(4, clienteTypeRequest.getMorada());
      call.setInt(5, clienteTypeRequest.getPorta());
      call.setDate(6, new Date(clienteTypeRequest.getDataNascimento().toGregorianCalendar().getTimeInMillis()));
      call.setString(7, clienteTypeRequest.getEmail());
      call.setString(8, clienteTypeRequest.getContacto());
      call.setLong(9, clienteTypeRequest.getLocalidade());
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
