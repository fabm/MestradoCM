package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.Categoria;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ProdutoXml;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqUpdateProduto;
import pt.ipg.mcm.xmodel.ResAddProduto;
import pt.ipg.mcm.xmodel.ResGetProduto;
import pt.ipg.mcm.xmodel.RetornoSoap;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProdutoDao {
    @Resource(lookup = "jdbc/mestrado")
    private DataSource mestradoDataSource;

    @EJB
    private MappedSql mappedSql;

    public ResAddProduto addProduto(ReqAddProduto reqAddProduto) throws MestradoException {
        ResAddProduto resAddProduto = new ResAddProduto();
        SqlSession sqlSession = mappedSql.getSqlSession();

        try {
            sqlSession.insert("insertProduto", reqAddProduto);
            resAddProduto.setId(reqAddProduto.getId());
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            sqlSession.close();
        }
        resAddProduto.setRetorno(new RetornoSoap(1, "Produto inserido com sucesso"));

        return resAddProduto;
    }

    public List<ProdutoXml> getProdutos(final long versao) throws MestradoException {
        List<ProdutoXml> list = mappedSql.getSqlSession().selectList("getProdutos", new HashMap<String, Object>() {{
            put("id", versao);
        }});
        return list;
    }

    public ResGetProduto getProduto(final long idProduto) throws MestradoException {

        ResGetProduto resGetProduto;
        try {

            SqlSession session = mappedSql.getSqlSession();
            resGetProduto = session.selectOne("getProduto", new HashMap<String, Object>() {{
                put("id", idProduto);
            }});

            if (resGetProduto == null) {
                throw new MestradoException(Erro.PRODUTO_NAO_ENCONTRADO, idProduto);
            }

        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        }

        return resGetProduto;
    }

    public void saveFoto(long id, InputStream inputStream) throws MestradoException {
        try {
            String sqlString = "UPDATE PRODUTO\n" +
                    "SET FOTO = ?\n" +
                    "WHERE ID_PRODUTO = ?";
            Connection connection = mestradoDataSource.getConnection();
            CallableStatement call = connection.prepareCall(sqlString);

            call.setLong(2, id);

            int qt = call.executeUpdate();

            if (qt == 0) {
                throw new MestradoException(Erro.TECNICO);
            }
        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }
    }

    public InputStream getFoto(Long id) throws MestradoException {
        String sqlString = "SELECT PRODUTO.FOTO\n" +
                "FROM PRODUTO\n" +
                "WHERE PRODUTO.ID_PRODUTO = ?";

        try {
            Connection connection = mestradoDataSource.getConnection();

            CallableStatement call = connection.prepareCall(sqlString);

            call.setLong(1, id);
            ResultSet rs = call.executeQuery();

            if (!rs.next()) {
                throw new MestradoException(Erro.PRODUTO_NAO_ENCONTRADO, id);
            }

            return rs.getBinaryStream(1);
        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }

    }


    public List<ProdutoCategoria> getProdutos(Categoria categoria) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getProdutosCategoria", categoria);
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }


    public void updateProduto(ReqUpdateProduto reqUpdateProduto) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.update("updateProduto", reqUpdateProduto);
        } finally {
            session.close();
        }
    }

    public void deleteProduto(Long id) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.delete("deleteProduto", id);
        } catch (PersistenceException e) {
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }


    public List<ProdutoCategoria> getProdutosCategoria(final long categoria) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getProdutosCategoria",new HashMap<String,Object>(){{
                put("categoria",categoria);
            }});
        } finally {
            session.close();
        }
    }
}
