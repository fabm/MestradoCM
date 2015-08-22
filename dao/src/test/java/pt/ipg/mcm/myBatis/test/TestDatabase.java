package pt.ipg.mcm.myBatis.test;

import org.junit.Test;
import pt.ipg.mcm.myBatis.test.sql.CategoriaTests;
import pt.ipg.mcm.myBatis.test.sql.ProdutoTests;
import pt.ipg.mcm.xmodel.ProdutoXml;
import pt.ipg.mcm.xmodel.ReqAddProduto;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestDatabase {

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
    public void testCategoria(){
        new CategoriaTests().getCategoriasDesync(0);
    }


}
