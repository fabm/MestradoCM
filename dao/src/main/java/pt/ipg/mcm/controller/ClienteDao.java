package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.controller.ps.PsAddUtilizadorCliente;
import pt.ipg.mcm.entities.ClienteEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddCliente;
import pt.ipg.mcm.xmodel.ReqGetCliente;
import pt.ipg.mcm.xmodel.ResAddCliente;
import pt.ipg.mcm.xmodel.ResGetCliente;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

@Stateless
public class ClienteDao {

  @Resource(lookup = "jdbc/mestrado")
  private DataSource mestradoDataSource;

  @EJB
  private MappedSql mappedSql;

  @PersistenceContext(unitName = "mestrado")
  private EntityManager entityManager;

  public void addClienteUtilizador(PsAddUtilizadorCliente psAddUtilizadorCliente) throws MestradoException {
    try {
      Connection connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_ADD_UTILIZADOR_CLIENTE(?,?,?,?,?,?,?,?,?,?)}");

      call.setLong(1, psAddUtilizadorCliente.getContribuinte());
      call.setString(2, psAddUtilizadorCliente.getNome());
      call.setString(3, psAddUtilizadorCliente.getMorada());
      call.setString(4, psAddUtilizadorCliente.getNPorta());
      call.setDate(5, new Date(psAddUtilizadorCliente.getDataNascimento().getTime()));
      call.setString(6, psAddUtilizadorCliente.getEmail());
      call.setString(7, psAddUtilizadorCliente.getContacto());
      call.setLong(8, psAddUtilizadorCliente.getLocalidade());
      call.setString(9, psAddUtilizadorCliente.getLogin());
      call.setString(10, psAddUtilizadorCliente.getPassword());

      call.execute();

    } catch (SQLException e) {
      throw new MestradoException(Erro.TECNICO);
    }
  }


  public ResAddCliente addClient(ReqAddCliente reqAddCliente) throws MestradoException {
    ResAddCliente clienteResponseType = new ResAddCliente();
    Connection connection;
    try {
      connection = mestradoDataSource.getConnection();

      CallableStatement call = connection.prepareCall("{call P_ADD_CLIENTE(?,?,?,?,?,?,?,?,?)}");
      call.setLong(1, reqAddCliente.getContribuinte());
      call.setString(2, reqAddCliente.getNome());
      call.setString(3, reqAddCliente.getMorada());
      call.setString(4, reqAddCliente.getPorta());
      call.setDate(5, new Date(reqAddCliente.getDataNascimento().toGregorianCalendar().getTimeInMillis()));
      call.setString(6, reqAddCliente.getEmail());
      call.setString(7, reqAddCliente.getContacto());
      call.setLong(8, reqAddCliente.getLocalidade());
      call.registerOutParameter(9, Types.NUMERIC);
      call.execute();
      clienteResponseType.setId(call.getLong(9));

      clienteResponseType.setRetorno(new RetornoSoap(1, "Cliente inserido com sucesso"));
      return clienteResponseType;
    }catch (SQLException e){
      throw new MestradoException(Erro.TECNICO);
    }

  }


  public ResGetCliente getCliente(long id) {
    SqlSession session = mappedSql.getSqlSession();
    try {
      return session.selectOne("getCliente", id);
    } finally {
      session.close();
    }
  }

  public void deleteCliente(final long id) throws MestradoException {
    SqlSession session = mappedSql.getSqlSession();
    try {
      session.delete("deleteCliente",id);
    } catch (PersistenceException e) {
      e.printStackTrace();
      throw new MestradoException(Erro.TECNICO);
    } finally {
      session.close();
    }
  }
}
