package pt.ipg.mcm;

import client.tests.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.ws.BindingProvider;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


public class TesteServiceProduto {
    @Test
    @Ignore
    public void testGetProdutoCategoria(){
        ProdutoService produtoPort = new Produto().getProdutoPort();
        BindingProvider bp = (BindingProvider) produtoPort;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,"francisco");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,"francisco");
        ResGetProdutosCategorias pc = produtoPort.getProdutosCategorias(1);
        Assert.assertThat(pc.getProdutoCategoria().size(), greaterThan(0));
    }

    @Test
    @Ignore
    public void testUpdateCategorias(){
        Categoria_Service categoriaService = new Categoria_Service();
        CategoriaService port = categoriaService.getCategoriaPort();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,"francisco");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "francisco");
        ReqUpdateCategoria req = new ReqUpdateCategoria();
        req.setDescricao("teste descrição");
        req.setIdCategoria(1);
        req.setNome("nome atualizado");
        ResUpdateCategoria resUpdateCategoria = port.updateCategoria(req);
        Assert.assertThat(resUpdateCategoria.getRetorno().getCodigo(), equalTo(1));
    }
}
