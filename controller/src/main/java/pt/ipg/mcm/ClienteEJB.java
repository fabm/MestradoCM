package pt.ipg.mcm;

import pt.ipg.mcm.entities.ClienteEntity;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.TypeResponse;

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
  private EntityManager entityManager;

  public ResAddCliente addClient(ReqAddCliente reqAddCliente) {
    ResAddCliente clienteResponseType = new ResAddCliente();
    Connection connection;
    try {
      connection = dataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_NOVO_CLIENTE(?,?,?,?,?,?,?,?,?)");
      call.setLong(1, reqAddCliente.getContribuinte());
      call.setString(2, reqAddCliente.getNome());
      call.setInt(3, reqAddCliente.getRole());
      call.setString(4, reqAddCliente.getMorada());
      call.setInt(5, reqAddCliente.getPorta());
      call.setDate(6, new Date(reqAddCliente.getDataNascimento().toGregorianCalendar().getTimeInMillis()));
      call.setString(7, reqAddCliente.getEmail());
      call.setString(8, reqAddCliente.getContacto());
      call.setLong(9, reqAddCliente.getLocalidade());
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


  public ResGetCliente getCliente(ReqGetCliente reqGetCliente) {
    ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, reqGetCliente.getId());
    ResGetCliente resGetCliente = new ResGetCliente();
    resGetCliente.setContribuinte(clienteEntity.getContribuinte().longValue());
    resGetCliente.setMorada(clienteEntity.getMorada());
    resGetCliente.setRole(clienteEntity.getProle().intValue());
    resGetCliente.setNome(clienteEntity.getNome());
    return resGetCliente;
  }
}
