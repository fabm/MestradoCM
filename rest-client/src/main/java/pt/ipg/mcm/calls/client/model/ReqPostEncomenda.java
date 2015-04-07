package pt.ipg.mcm.calls.client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pt.ipg.mcm.calls.client.DateHelper;

import java.util.Date;
import java.util.List;

public class ReqPostEncomenda {
  private Date date;
  private int clientId;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  private List<ProdutoEncomendado> prodEncomendados;

  public List<ProdutoEncomendado> getProdEncomendados() {
    return prodEncomendados;
  }

  public void setProdEncomendados(List<ProdutoEncomendado> prodEncomendados) {
    this.prodEncomendados = prodEncomendados;
  }

  public JsonObject toJson() {
    JsonArray jsonArray = new JsonArray();
    for (ProdutoEncomendado prodEncomendado : prodEncomendados) {
      jsonArray.add(prodEncomendado.toJson());
    }
    JsonObject produtoEncomendado = new JsonObject();
    produtoEncomendado.add("produto", jsonArray);
    JsonObject encomenda = new JsonObject();
    encomenda.addProperty("clientId", clientId);
    encomenda.addProperty("dataEntrega", new DateHelper(DateHelper.Format.COMPACT).toString(date));
    encomenda.add("produtos", produtoEncomendado);
    return encomenda;
  }
}
