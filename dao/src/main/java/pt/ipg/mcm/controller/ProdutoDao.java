package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.entities.VProdutoCategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ProdutoXml;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ReqGetProdutosCategorias;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
/*
        String sqlString = "SELECT PRODUTO.ID_PRODUTO,\n" +
            "  PRODUTO.NOME,\n" +
            "  PRODUTO.PRECO_ATUAL,\n" +
            "  PRODUTO.FOTO,\n" +
            "  PRODUTO.ID_CATEGORIA,\n" +
            "  PRODUTO.SYNC\n" +
            "FROM PRODUTO\n" +
            "WHERE PRODUTO.SYNC > ?";

        List<ProdutoEntity> produtoEntities = new ArrayList<ProdutoEntity>();
        try {
            Connection connection = mestradoDataSource.getConnection();

            PreparedStatement call = connection.prepareStatement(sqlString);
            call.setLong(1, versao);
            ResultSet rs = call.executeQuery();

            while (rs.next()) {
                ProdutoEntity produtoEntity = new ProdutoEntity();
                produtoEntity.setIdProduto(rs.getLong(1));
                produtoEntity.setNome(rs.getString(2));
                produtoEntity.setPrecoAtual(rs.getBigDecimal(3));
                produtoEntity.setFoto(rs.getBytes(4));
                produtoEntity.setIdCategoria(rs.getLong(5));
                produtoEntity.setSync(rs.getLong(6));
                produtoEntities.add(produtoEntity);
            }

        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }


*/
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

    private ResultSet getResultProdutos(String strSql, long idCategoria) throws SQLException {
        Connection connection = mestradoDataSource.getConnection();
        PreparedStatement call = connection.prepareStatement(strSql.concat("\nWHERE ID_CATEGORIA = ?"));
        call.setLong(1, idCategoria);
        return call.executeQuery();
    }

    private ResultSet getResultProdutos(String strSql) throws SQLException {
        Connection connection = mestradoDataSource.getConnection();
        Statement call = connection.createStatement();
        return call.executeQuery(strSql);
    }

    public List<ProdutoCategoria> getProdutos(ReqGetProdutosCategorias reqGetProdutosCategorias) throws MestradoException {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getProdutosCategoria", reqGetProdutosCategorias);
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            session.close();
        }
    }


}
