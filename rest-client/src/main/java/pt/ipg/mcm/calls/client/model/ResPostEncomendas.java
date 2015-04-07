package pt.ipg.mcm.calls.client.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResPostEncomendas {
  private List<PostEncomendaRes> postEncomendasRes;

  public void fromJson(JsonObject jsonObject) {
    Iterator<JsonElement> it = jsonObject.get("encomendas")
        .getAsJsonObject().get("encomenda")
        .getAsJsonArray().iterator();

    postEncomendasRes = new ArrayList<PostEncomendaRes>();

    while (it.hasNext()) {
      PostEncomendaRes postEncomendaRes = new PostEncomendaRes();
      postEncomendaRes.fromJson(it.next().getAsJsonObject());
      postEncomendasRes.add(postEncomendaRes);
    }
  }

  public List<PostEncomendaRes> getPostEncomendasRes() {
    return postEncomendasRes;
  }

  public void setPostEncomendasRes(List<PostEncomendaRes> postEncomendasRes) {
    this.postEncomendasRes = postEncomendasRes;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof ResPostEncomendas)) {
      return false;
    }
    ResPostEncomendas that = (ResPostEncomendas) obj;
    if (this.postEncomendasRes == null && that.postEncomendasRes == null) {
      return true;
    }
    if(this.postEncomendasRes.size()!= that.postEncomendasRes.size()){
      return false;
    }
    Iterator<PostEncomendaRes> itThis = this.postEncomendasRes.iterator();
    Iterator<PostEncomendaRes> itThat = that.postEncomendasRes.iterator();
    while (itThis.hasNext()){
      if(!itThis.next().equals(itThat.next())){
        return false;
      }
    }
    return true;
  }
}
