package pt.ipg.mcm.myBatis.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ejb.injectors.EJBModule;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pt.ipg.mcm.batis.MappedSql;
import pt.ipg.mcm.controller.ProdutoDao;
import pt.ipg.mcm.controller.imp.UtilizadorDao;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.myBatis.test.sql.ProdutoTests;
import pt.ipg.mcm.xmodel.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestDatabase {

    @Test
    @Ignore
    public void testProdutoCategoria() {
        Injector injector = Guice.createInjector(new EJBModule());

        ProdutoDao produtoDao = injector.getInstance(ProdutoDao.class);
        MappedSql mapped = injector.getInstance(MappedSql.class);

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
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    @Ignore
    public void testAddUtilizador() {
        Injector injector = Guice.createInjector(new EJBModule());

        UtilizadorDao utilizadorDao = injector.getInstance(UtilizadorDao.class);
        MappedSql mapped = injector.getInstance(MappedSql.class);
        SqlSession sqlSession = mapped.getSqlSession();
        UserClienteCreationRequest req = new UserClienteCreationRequest();
        req.setContribuinte(123123L);
        req.setNome("kikokk");
        req.setMorada("zxcxczxc");
        req.setPorta("1ºesq");
        req.setDataNascimento(new Date());
        req.setEmail("asdasd@sdasd.pt");
        req.setContacto("123123");
        req.setLocalidade(6300);
        req.setLogin("wwwww");
        req.setPassword("mypass");

        try {
            utilizadorDao.createUserCliente(req);
            sqlSession.commit();
        } catch (MestradoException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }


    @Test
    @Ignore
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

}
