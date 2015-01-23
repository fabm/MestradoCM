package pt.ipg.mcm.controller;

import pt.ipg.mcm.entities.ClienteEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.Retorno;

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
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ClienteDao {

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  @PersistenceContext(unitName = "mestrado")
  private EntityManager entityManager;

  public ResAddCliente addClient(ReqAddCliente reqAddCliente) throws MestradoException {
    ResAddCliente clienteResponseType = new ResAddCliente();
    Connection connection;
    try {
      connection = mestradoDataSource.getConnection();


      CallableStatement call = connection.prepareCall("{call P_ADD_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");
      call.setLong(1, reqAddCliente.getContribuinte());
      call.setString(2, reqAddCliente.getNome());
      call.setInt(3, reqAddCliente.getRole());
      call.setString(4, reqAddCliente.getMorada());
      call.setString(5, reqAddCliente.getPorta());
      call.setDate(6, new Date(reqAddCliente.getDataNascimento().toGregorianCalendar().getTimeInMillis()));
      call.setString(7, reqAddCliente.getEmail());
      call.setString(8, reqAddCliente.getContacto());
      call.setLong(9, reqAddCliente.getLocalidade());
      call.registerOutParameter(10, Types.NUMERIC);
      call.execute();

      clienteResponseType.setId(call.getLong(10));

      clienteResponseType.setRetorno(new Retorno(1, "Cliente inserido com sucesso"));
      return clienteResponseType;
    }catch (SQLException e){
      throw new MestradoException(Erro.TECNICO);
    }

  }


  public ResGetCliente getCliente(ReqGetCliente reqGetCliente) {
    ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, reqGetCliente.getId());
    ResGetCliente resGetCliente = new ResGetCliente();
    if (clienteEntity == null) {
      return resGetCliente;
    }
    resGetCliente.setContribuinte(clienteEntity.getContribuinte().longValue());
    resGetCliente.setMorada(clienteEntity.getMorada());
    resGetCliente.setRole(clienteEntity.getProle().intValue());
    resGetCliente.setNome(clienteEntity.getNome());
    return resGetCliente;
  }
}
