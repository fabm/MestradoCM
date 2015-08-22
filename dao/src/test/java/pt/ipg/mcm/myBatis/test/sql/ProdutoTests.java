package pt.ipg.mcm.myBatis.test.sql;

import org.apache.ibatis.session.SqlSession;
import pt.ipg.mcm.myBatis.test.BeanUtil;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ProdutoXml;
import pt.ipg.mcm.xmodel.ReqAddProduto;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static pt.ipg.mcm.myBatis.test.SqlUtils.SQL_SESSION_FACTORY;

public class ProdutoTests {

    public void createProduto(ReqAddProduto reqAddProduto) {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        session.<ProdutoXml>insert("insertProduto", reqAddProduto);
        session.commit();

        session.close();
    }

    public ProdutoXml getProduto(long id) {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        ProdutoXml produto = session.selectOne("getProduto", id);

        session.close();
        return produto;
    }

    public void getProdutosCategoriaComFoto() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(ProdutoCategoria.class, 20)
            .printProperties(session.<ProdutoCategoria>selectList("getProdutosCategoria", new HashMap<String, Object>() {{
                put("comFoto", true);
            }}));

        session.close();
    }

    public void getProdutosCategoriaSemFoto() {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(ProdutoCategoria.class, 20)
            .printProperties(session.<ProdutoCategoria>selectList("getProdutosCategoria", new HashMap<String, Object>() {{
                put("comFoto", false);
            }}));

        session.close();
    }


    public void getProdutos(int syncId) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        new BeanUtil<>(ProdutoXml.class, 20)
            .printProperties(session.<ProdutoXml>selectList("getProdutos", syncId));

        session.close();
    }

    public void deleteProduto(int id) {
        SqlSession session = SQL_SESSION_FACTORY.openSession();

        session.delete("deleteProduto", id);
        session.commit();

        session.close();
    }
}
