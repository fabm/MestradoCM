package pt.ipg.mcm.controller;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.entities.ProdutoEntity;
import pt.ipg.mcm.entities.VProdutoCategoriaEntity;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.ReqAddProduto;
import pt.ipg.mcm.xmodel.ResAddProduto;
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
            sqlSession.close();
        } catch (PersistenceException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new MestradoException(Erro.TECNICO);
        } finally {
            sqlSession.close();
        }
        resAddProduto.setRetorno(new RetornoSoap(1, "Produto inserido com sucesso"));

        return resAddProduto;
    }

    public List<ProdutoEntity> getProdutos(long versao) throws MestradoException {
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


        return produtoEntities;
    }

    public ProdutoEntity getProduto(long idProduto) throws MestradoException {

        ProdutoEntity produtoEntity = new ProdutoEntity();
        try {
            String sqlString = "SELECT PRODUTO.NOME,\n" +
                "  PRODUTO.PRECO_ATUAL,\n" +
                "  PRODUTO.ID_CATEGORIA\n" +
                "FROM PRODUTO\n" +
                "WHERE PRODUTO.ID_PRODUTO = ?";

            Connection connection = mestradoDataSource.getConnection();

            PreparedStatement call = connection.prepareStatement(sqlString);
            call.setLong(1, idProduto);
            ResultSet rs = call.executeQuery();

            if (!rs.next()) {
                throw new MestradoException(Erro.PRODUTO_NAO_ENCONTRADO, idProduto);
            }

            produtoEntity.setNome(rs.getString(1));
            produtoEntity.setPrecoAtual(rs.getBigDecimal(2));
            //produtoEntity.setIdCategoria(rs.getLong(3));
            // produtoEntity.setIdProduto(rs.getLong(4));

        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }

        return produtoEntity;
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

    public List<VProdutoCategoriaEntity> getProdutos(boolean withFoto, Long idCategoria) throws MestradoException {

        try {
            Connection connection = mestradoDataSource.getConnection();
            Statement call = connection.createStatement();
            String sqlStr;
            if (withFoto) {
                sqlStr = ",FOTO";
            } else {
                sqlStr = "";
            }

            sqlStr = String.format("SELECT NOME_CATEGORIA, DESCRICAO,PRECO_ATUAL,NOME_PRODUTO, ID_PRODUTO%s from V_PRODUTO_CATEGORIA", sqlStr);

            ResultSet rs;
            if (idCategoria != null) {
                rs = getResultProdutos(sqlStr, idCategoria);
            } else {
                rs = getResultProdutos(sqlStr);
            }

            List<VProdutoCategoriaEntity> vProdutoCategoriaEntities = new ArrayList<VProdutoCategoriaEntity>();

            while (rs.next()) {
                VProdutoCategoriaEntity vProdutoCategoriaEntity = new VProdutoCategoriaEntity();
                vProdutoCategoriaEntity.setNomeCategoria(rs.getString(1));
                vProdutoCategoriaEntity.setDescricao(rs.getString(2));
                vProdutoCategoriaEntity.setPrecoAtual(rs.getBigDecimal(3));
                vProdutoCategoriaEntity.setNomeProduto(rs.getString(4));
                vProdutoCategoriaEntity.setIdproduto(rs.getLong(5));

                if (withFoto) {
                    vProdutoCategoriaEntity.setFoto(rs.getBytes(6));
                }

                vProdutoCategoriaEntities.add(vProdutoCategoriaEntity);
            }
            return vProdutoCategoriaEntities;
        } catch (SQLException e) {
            throw new MestradoException(Erro.TECNICO);
        }
    }


}
