package pt.ipg.mcm;

import client.tests.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


public class TesteServices {

    private void authentication(Object port, String username, String password) {
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "francisco");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "francisco");
    }

    @Test
    @Ignore
    public void testGetProdutoCategoria() {
        ProdutoService produtoPort = new Produto().getProdutoPort();
        authentication(produtoPort, "francisco", "francisco");
        ResGetProdutosCategorias pc = produtoPort.getProdutosCategorias(1);
        Assert.assertThat(pc.getProdutoCategoria().size(), greaterThan(0));
    }

    @Test
    @Ignore
    public void testUpdateCategorias() {
        Categoria_Service categoriaService = new Categoria_Service();
        CategoriaService port = categoriaService.getCategoriaPort();
        authentication(port, "francisco", "francisco");
        ReqUpdateCategoria req = new ReqUpdateCategoria();
        req.setDescricao("teste descrição");
        req.setIdCategoria(1);
        req.setNome("nome atualizado");
        ResUpdateCategoria resUpdateCategoria = port.updateCategoria(req);
        Assert.assertThat(resUpdateCategoria.getRetorno().getCodigo(), equalTo(1));
    }

    @Test
    @Ignore
    public void testDeleteCategoria() {
        Categoria_Service categoriaService = new Categoria_Service();
        CategoriaService port = categoriaService.getCategoriaPort();
        authentication(port, "francisco", "francisco");
        port.deleteCategoria(321);
    }

    @Test
    @Ignore
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        ClienteService port = cliente.getClientePort();
        authentication(port, "francisco", "francisco");
        RetornoSoap response = port.deleteCliente(601);
        Assert.assertEquals(Integer.valueOf(1), response.getCodigo());
    }

    @Test
    @Ignore
    public void testGetCliente() throws LoginException_Exception {
        Cliente cliente = new Cliente();
        ClienteService port = cliente.getClientePort();
        authentication(port, "francisco", "francisco");
        ResGetCliente resGetCliente = port.getCliente(1L);
        Assert.assertNotNull(resGetCliente);
    }

    @Test
    @Ignore
    public void testAddUtilizadorCliente() throws LoginException_Exception {
        Utilizador utilizador = new Utilizador();
        UtilizadorService port = utilizador.getUtilizadorPort();
        UserClienteCreationRequest req = new UserClienteCreationRequest();
        req.setNome("meu nome");
        req.setLogin("kiko3");
        req.setLocalidade(6300);
        req.setPorta("3esq");
        req.setContacto("999999999");
        req.setContribuinte(123123123L);
        try {
            final XMLGregorianCalendar dataNascimento = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            dataNascimento.setDay(25);
            dataNascimento.setMonth(1);
            dataNascimento.setYear(2000);
            dataNascimento.setHour(1);
            dataNascimento.setMinute(1);
            dataNascimento.setSecond(1);
            req.setDataNascimento(dataNascimento);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        req.setEmail("x@x.pt");
        req.setPassword("kiko2");
        req.setMorada("A minha morada");
        ResCreationUserClient resCreationUserClient = port.createUserCliente(req);
        Assert.assertEquals(1,resCreationUserClient.getCodigo().intValue());
    }


}
