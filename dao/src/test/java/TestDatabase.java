import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pt.ipg.mcm.xmodel.ProdutoCategoria;
import pt.ipg.mcm.xmodel.ProdutoXml;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

public class TestDatabase {
    private static SqlSessionFactory SQL_SESSION_FACTORY;
    static {
        try {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(TestDatabase.class.getResource("/config.xml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDatabase() throws IOException, URISyntaxException {

        System.out.println(new File(getClass().getResource("mappers/produto.xml").toURI()).exists());

        SqlSession session = SQL_SESSION_FACTORY.openSession();

        final List<ProdutoXml> produtoXmlList = session.selectList("getProdutos",0);

        for (ProdutoXml produtoXml:produtoXmlList){
            System.out.println(produtoXml.getId());
        }

        session.close();

        session = SQL_SESSION_FACTORY.openSession();

        List<ProdutoCategoria> produtoCategoriaList = session.selectList("getProdutosCategoria", new HashMap<String,Object>(){{
            put("comFoto",false);
        }});

        for(ProdutoCategoria produtoCategoria:produtoCategoriaList){
            System.out.println(produtoCategoria.getIdproduto());
        }

        session.close();
    }


}
