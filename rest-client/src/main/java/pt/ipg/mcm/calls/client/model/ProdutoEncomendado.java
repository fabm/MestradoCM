package pt.ipg.mcm.calls.client.model;

import com.google.gson.JsonObject;

public class ProdutoEncomendado {
  private int quandidade;
  private long idProduto;

  public JsonObject toJson() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("quantidade",quandidade);
    jsonObject.addProperty("idProduto",idProduto);
    return jsonObject;
  }

  public int getQuandidade() {
    return quandidade;
  }

  public void setQuandidade(int quandidade) {
    this.quandidade = quandidade;
  }

  public long getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(long idProduto) {
    this.idProduto = idProduto;
  }
}
