package pt.ipg.mcm.controller;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Stateless
public class ProdutoDao {

    @EJB
    private MappedSql mappedSql;

    public ResAddProduto addProduto(ReqAddProduto reqAddProduto) {

        SqlSession sqlSession = mappedSql.getSqlSession();
        try {
            sqlSession.insert("insertProduto", reqAddProduto);
            return new ResAddProduto(reqAddProduto.getId());
        } finally {
            sqlSession.close();
        }

    }

    public List<ProdutoXml> getProdutos(final long versao) {
        List<ProdutoXml> list = mappedSql.getSqlSession().selectList("getProdutos", new HashMap<String, Object>() {{
            put("id", versao);
        }});
        return list;
    }

    public ResGetProduto getProduto(final long idProduto) throws MestradoException {

        SqlSession session = mappedSql.getSqlSession();
        try {

            ResGetProduto resGetProduto = session.selectOne("getProduto", new HashMap<String, Object>() {{
                put("id", idProduto);
            }});

            if (resGetProduto == null) {
                throw new MestradoException(Erro.PRODUTO_NAO_ENCONTRADO, idProduto);
            }

            return resGetProduto;
        } finally {
            session.close();
        }

    }

    public InputStream getFoto(Long id) throws MestradoException {

        SqlSession session = mappedSql.getSqlSession();

        try {
            InputStream foto = session.selectOne("getFoto", id);

            if (foto == null) {
                throw new MestradoException(Erro.PRODUTO_NAO_ENCONTRADO, id);
            }

            return foto;
        } finally {
            session.close();
        }

    }


    public List<ProdutoCategoria> getProdutos(Categoria categoria) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getProdutosCategoria", categoria);
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

    public void deleteProduto(Long id){
        SqlSession session = mappedSql.getSqlSession();
        try {
            session.delete("deleteProduto", id);
        } finally {
            session.close();
        }
    }


    public List<ProdutoCategoria> getProdutosCategoria(final long categoria) {
        SqlSession session = mappedSql.getSqlSession();
        try {
            return session.selectList("getProdutosCategoria", new HashMap<String, Object>() {{
                put("categoria", categoria);
            }});
        } finally {
            session.close();
        }
    }

    public void saveFoto(long id, InputStream is) {

    }
}
