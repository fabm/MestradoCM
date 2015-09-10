package pt.ipg.mcm.errors.client.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Request;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pt.ipg.mcm.calls.AuthBasicUtf8;
import pt.ipg.mcm.calls.RestJsonCall;
import pt.ipg.mcm.calls.WebserviceException;
import pt.ipg.mcm.calls.client.model.delete.GetRegistosAApagarRest;
import pt.ipg.mcm.calls.client.model.encomendas.*;
import pt.ipg.mcm.calls.client.model.produtos.GetProdutoDesyncRest;
import pt.ipg.mcm.calls.client.model.produtos.ProdutoRest;

import java.util.ArrayList;
import java.util.List;

public class ClientTest {

    public static final String SERVER_URL = "http://localhost:8080";
    @Rule
    public ClientRule clientRule = new ClientRule();

    @Test
    public void encomendasDataEntregeTest() throws WebserviceException {
        RestJsonCall restJsonCall = getRestJsonCallBuilder()
                .get()
                .auth(new AuthBasicUtf8("bruno", "bruno"))
                .serverUrl(SERVER_URL)
                .path("/services/rest/encomenda/minhas/0")
                .build();


        GetMinhasEncomendasRest getMinhasEncomendasRest = restJsonCall.getResponse(GetMinhasEncomendasRest.class);

        for (EncomendaDetalheRest encomendaDetalheRest : getMinhasEncomendasRest.getEncomendaDetalheRestList()) {
            Assert.assertNotNull("a data de entrega da encomenda " + encomendaDetalheRest.getId() + " não pode ser vazia", encomendaDetalheRest.getDataEntrega());
        }
    }

    private RestJsonCall.Builder getRestJsonCallBuilder() {
        return new RestJsonCall.Builder() {

            @Override
            protected RestJsonCall abstractBuild(Request request) {
                return new RestJsonCall(request) {

                    @Override
                    public ObjectMapper getObjectMapper() {
                        return new ObjectMapper();
                    }
                };
            }
        };
    }

    @Test
    public void encomendasPrecoAtualTest() throws WebserviceException {
        RestJsonCall restJsonCall = getRestJsonCallBuilder()
                .get()
                .auth(new AuthBasicUtf8("bruno", "bruno"))
                .serverUrl(SERVER_URL)
                .path("/services/rest/produto/desync/0")
                .build();


        GetProdutoDesyncRest getProdutoDesyncRest = restJsonCall.getResponse(GetProdutoDesyncRest.class);

        for (ProdutoRest produtoRest : getProdutoDesyncRest.getProdutoRestList()) {
            System.out.println(produtoRest.getPrecoUnitario());
        }
    }

    @Test
    public void encomendasVaziasTest() throws WebserviceException {
        RestJsonCall restJsonCall = getRestJsonCallBuilder()
                .get()
                .auth(new AuthBasicUtf8("bruno", "bruno"))
                .serverUrl(SERVER_URL)
                .path("/services/rest/encomenda/minhas/0")
                .build();


        GetMinhasEncomendasRest getMinhasEncomendasRest = restJsonCall.getResponse(GetMinhasEncomendasRest.class);
        String enc = "";

        for (EncomendaDetalheRest encomendaDetalheRest : getMinhasEncomendasRest.getEncomendaDetalheRestList()) {
            List<ProdutoEncomendadoComPrecoRest> produtosEncomendados = encomendaDetalheRest.getProdutosEncomendados();
            if (produtosEncomendados == null || produtosEncomendados.isEmpty()) {
                if (!enc.isEmpty()) {
                    enc += ",";
                }
                enc += encomendaDetalheRest.getId();
            }
        }
        Assert.assertTrue("Apagar encomendas com os ids(" + enc + ")", enc.isEmpty());
    }

    @Test
    public void categoriasCall() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String url = SERVER_URL + "/services/rest/categoria/desync/0";
        String getCategoriaDesyncRest = restTemplate.getForObject(url, String.class);

        Assert.assertNotNull(getCategoriaDesyncRest);
    }

    @Test
    public void deleteDesync() throws WebserviceException {
        RestJsonCall restJsonCall = getRestJsonCallBuilder()
                .get()
                .serverUrl(SERVER_URL)
                .path("/services/rest/delete/desync/0")
                .build();

        GetRegistosAApagarRest getRegistosAApagarRest = restJsonCall.getResponse(GetRegistosAApagarRest.class);

        Assert.assertNotNull(getRegistosAApagarRest);
    }

    @Test
    public void addAndUpdateEncomendas() throws WebserviceException {

        AddAndUpdateEncomendasInRest addAndUpdateEncomendasInRest = new AddAndUpdateEncomendasInRest();
        List<EncomendaInRest> encomendaInRestList = new ArrayList<>();
        EncomendaInRest encomendaInRest = new EncomendaInRest();

        List<ProdutoEncomendadoRest> produtoEncomendadoRestList = new ArrayList<>();
        ProdutoEncomendadoRest produtoEncomendadoRest = new ProdutoEncomendadoRest();
        produtoEncomendadoRest.setIdProduto(1);
        produtoEncomendadoRest.setQuantidade(2);
        produtoEncomendadoRestList.add(produtoEncomendadoRest);

        encomendaInRest.setProdutoEncomendadoRestList(produtoEncomendadoRestList);
        encomendaInRestList.add(encomendaInRest);

        addAndUpdateEncomendasInRest.setEncomendaInRestList(encomendaInRestList);

        List<EstadoEncomendaInRest> estadoEncomendaInRestList = new ArrayList<>();
        EstadoEncomendaInRest estadoEncomendaInRest = new EstadoEncomendaInRest();

        estadoEncomendaInRest.setEstado(1);
        estadoEncomendaInRest.setIdEncomenda(49);
        estadoEncomendaInRestList.add(estadoEncomendaInRest);



        estadoEncomendaInRestList.add(estadoEncomendaInRest);
        addAndUpdateEncomendasInRest.setEstadoEncomendaInRestList(estadoEncomendaInRestList);

        RestJsonCall restJsonCall = getRestJsonCallBuilder()
                .serverUrl(SERVER_URL)
                .auth(new AuthBasicUtf8("bruno", "bruno"))
                .path("/services/rest/encomenda/update")
                .post(addAndUpdateEncomendasInRest)
                .build();



        AddEncomendasOutRest response = restJsonCall.getResponse(AddEncomendasOutRest.class);

        System.out.println(response.getEncomendaOutRestList());
    }


}
