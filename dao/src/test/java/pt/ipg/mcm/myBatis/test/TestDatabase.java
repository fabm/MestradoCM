package pt.ipg.mcm.myBatis.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.ibatis.session.SqlSession;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.myBatis.test.sql.ProdutoTests;
import pt.ipg.mcm.xmodel.*;

import javax.ejb.EJBInjector;
import javax.ejb.EJBModule;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestDatabase {

    @Test
    public void testInjectEJB() {
        Injector injector = Guice.createInjector(new EJBModule());
        ProdutoDao produtoDao = injector.getInstance(ProdutoDao.class);
        MappedSql mapped = injector.getInstance(MappedSql.class);
        EJBInjector.runPostConstruct(mapped);

        List<ProdutoCategoria> produtosCategoria = produtoDao.getProdutosCategoria(1);
        assertThat(produtosCategoria.size(), greaterThan(0));
        final SqlSession sqlSession = mapped.getSqlSession();
        sqlSession.commit();

        ReqUpdateProduto req = new ReqUpdateProduto();
        req.setNome("my name");
        req.setCategoria(1);
        req.setFoto(null);
        req.setIdProduto(1);
        req.setPrecoUnitario(new BigDecimal("10.0"));
        produtoDao.updateProduto(req);

        sqlSession.close();
    }

    @Test
    public void testProduto() {
        ReqAddProduto reqAddProduto = new ReqAddProduto();
        reqAddProduto.setCategoria(1);
        reqAddProduto.setFoto(null);
        reqAddProduto.setNome("my product test");
        reqAddProduto.setPrecoUnitario(new BigDecimal("12.00"));

        ProdutoTests produtoTestes = new ProdutoTests();
        produtoTestes.createProduto(reqAddProduto);


        int id = reqAddProduto.getId();
        ProdutoXml produto = produtoTestes.getProduto(id);

        assertNotNull(produto);


        produtoTestes.deleteProduto(id);

        produto = produtoTestes.getProduto(id);
        assertNull(produto);

    }

    @Test
    public void testCliente() {
        SqlSession session = SqlUtils.SQL_SESSION_FACTORY.openSession();
        UserClienteCreationRequest req = new UserClienteCreationRequest();
        req.setContribuinte(1234567L);
        req.setNome("Nome test");
        req.setMorada("Minha morada");
        req.setContacto("123456");
        req.setPorta("2ºEsq");
        req.setDataNascimento(new GregorianCalendar(1979, Calendar.MAY, 25).getTime());
        req.setEmail("meuMail@sapo.pt");
        req.setLocalidade(6300559);
        req.setLogin("xico");
        req.setPassword("mypassword");

        session.insert("addCliente", req);

        final List<Long> idsUtilizadores = session.selectList("selectUser", "xico");

        Assert.assertEquals(1, idsUtilizadores.size());

        session.delete("deleteClients", new HashMap<String, Object>() {{
            put("ids", idsUtilizadores);
        }});
        session.delete("deleteUsers", new HashMap<String, Object>() {{
            put("ids", idsUtilizadores);
        }});

        List<Object> idsUtilizadoresAfterDelete = session.selectList("selectUser", "xico");

        Assert.assertEquals(0, idsUtilizadoresAfterDelete.size());

        session.commit();

        session.close();
    }


}
