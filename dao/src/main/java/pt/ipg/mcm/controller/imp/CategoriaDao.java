package pt.ipg.mcm.controller.imp;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.entities.CategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ReqAddCategoria;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CategoriaDao {

    @EJB
    private MappedSql mappedSql;

    @Resource(lookup = "jdbc/mestrado")
    private DataSource mestradoDataSource;


    public Integer addCategoria(final Categoria categoria) {
        SqlSession session = mappedSql.getSqlSession();
        final HashMap<String, Object> map;
        try {
            session.insert("addCategoria", categoria);
        } finally {
            session.close();
        }
        return categoria.getId();
    }

    public void updateCategoria(CategoriaEntity categoriaEntity) throws SQLException {
        Connection connection = mestradoDataSource.getConnection();

        CallableStatement call;
        call = connection.prepareCall("{call P_UPDATE_CATEGORIA(?,?,?)}");
        call.setLong(1, categoriaEntity.getIdCategoria());
        call.setString(2, categoriaEntity.getNome());
        call.setString(3, categoriaEntity.getDescricao());

        call.execute();

    }

    public CategoriaEntity getCategoria(long idCategoria) throws MestradoException {

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        try {
            String sqlStriing = "SELECT CATEGORIA.NOME,\n" +
                "  CATEGORIA.DESCRICAO\n" +
                "  FROM CATEGORIA\n" +
                "  WHERE CATEGORIA.ID_CATEGORIA = ?";


            Connection connection = mestradoDataSource.getConnection();
            PreparedStatement call = connection.prepareStatement(sqlStriing);
            call.setLong(1, idCategoria);

            ResultSet rs = call.executeQuery();

            if (!rs.next()) {
                throw new MestradoException(Erro.CATEGORIA_NAO_ENCONTRADO, idCategoria);
            }

            categoriaEntity.setNome(rs.getString(1));
            categoriaEntity.setDescricao(rs.getString(2));


        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }

        return categoriaEntity;
    }

    public List<Categoria> getAll() throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("allCategorias");
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        }finally {
            session.close();
        }
    }


    public List<Categoria> getDesync(final Long versao) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("categoriasDesync",new HashMap<String,Long>(){{
                put("id", versao);
            }});
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }


}
