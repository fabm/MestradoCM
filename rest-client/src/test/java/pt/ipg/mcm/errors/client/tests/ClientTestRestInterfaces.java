package pt.ipg.mcm.errors.client.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Request;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pt.ipg.mcm.calls.AbstractRestJsonCall;
import pt.ipg.mcm.calls.AuthBasicUtf8;
import pt.ipg.mcm.calls.WebserviceException;
import pt.ipg.mcm.calls.client.model.PostEncomendaRes;
import pt.ipg.mcm.calls.client.model.ProdutoEncomendado;
import pt.ipg.mcm.calls.client.model.ReqPostEncomenda;
import pt.ipg.mcm.calls.client.model.ResPostEncomendas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClientTestRestInterfaces {

  public static final String SERVER_URL = "http://noteboorancisco.lan:8081";
  @Rule
  public ClientRule clientRule = new ClientRule();

  private AbstractRestJsonCall.Builder getBuilder() {
    final Gson gson = new Gson();
    return new AbstractRestJsonCall.Builder() {

      @Override
      protected AbstractRestJsonCall abstractBuild(Request request) {
        return new AbstractRestJsonCall(request) {
          @Override
          protected Gson getGson() {
            return gson;
          }
        };
      }
    };
  }

  @Test
  public void testResPostMinhasEncomendasClient() {
    AbstractRestJsonCall.Builder builder = getBuilder();

    AbstractRestJsonCall restJsonCall = builder
        .auth(new AuthBasicUtf8("francisco", "francisco"))
        .serverUrl(SERVER_URL)
        .path("/services/rest/itest/resPostMinhasEncomendas")
        .get()
        .build();

    try {
      JsonObject response = restJsonCall.getResponse(JsonObject.class);

      ResPostEncomendas resPostEncomendasFromJson = new ResPostEncomendas();
      resPostEncomendasFromJson.fromJson(response);

      ResPostEncomendas resPostEncomendasExpected = new ResPostEncomendas();
      List<PostEncomendaRes> postEcomendasRes = new ArrayList<PostEncomendaRes>();
      PostEncomendaRes postEncomenda = new PostEncomendaRes();
      postEncomenda.setClientId(1);
      postEncomenda.setServerId(1);
      postEcomendasRes.add(postEncomenda);
      resPostEncomendasExpected.setPostEncomendasRes(postEcomendasRes);

      Assert.assertEquals(resPostEncomendasExpected,resPostEncomendasFromJson);

    } catch (WebserviceException e) {
      Assert.fail();
    }

  }

  @Test
  public void testReqPostMinhasEncomendasClient() {
    AbstractRestJsonCall.Builder builder = getBuilder();

    ReqPostEncomenda reqPostEncomenda = new ReqPostEncomenda();
    reqPostEncomenda.setDate(new Date());
    reqPostEncomenda.setClientId(123);

    ProdutoEncomendado prodEncomendado = new ProdutoEncomendado();
    prodEncomendado.setIdProduto(11);
    prodEncomendado.setQuandidade(22);
    reqPostEncomenda.setProdEncomendados(Arrays.asList(prodEncomendado));

    AbstractRestJsonCall restJsonCall = builder
        .auth(new AuthBasicUtf8("francisco", "francisco"))
        .serverUrl(SERVER_URL)
        .path("/services/rest/itest/reqPostMinhasEncomendas")
        .post(reqPostEncomenda.toJson())
        .build();

    try {
      JsonObject response = restJsonCall.getResponse(JsonObject.class);
      Assert.assertEquals(reqPostEncomenda.toJson(),response);
    } catch (WebserviceException e) {
      Assert.fail("Webservice inaccessible");
    }

  }
}
