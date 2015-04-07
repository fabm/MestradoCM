package pt.ipg.mcm.calls.json.equals;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public abstract class JsonObjectEqualizer {
  private JsonObject jsonObject1;
  private JsonObject jsonObject2;

  public JsonObjectEqualizer(JsonObject jsonObject1, JsonObject jsonObject2) {
    this.jsonObject1 = jsonObject1;
    this.jsonObject2 = jsonObject2;
  }

  public boolean isEqual() {
    return isEqualJO(new Stack<String>(), jsonObject1, jsonObject2);
  }

  private boolean isEqualJO(Stack<String> st, JsonObject jo1, JsonObject jo2) {
    Set<Map.Entry<String, JsonElement>> es1 = jo1.entrySet();
    Set<Map.Entry<String, JsonElement>> es2 = jo2.entrySet();

    if (es1.size() != es2.size()) {
      return false;
    }
    Iterator<Map.Entry<String, JsonElement>> it1 = es1.iterator();
    if (es1.iterator().hasNext()) {
      Map.Entry<String, JsonElement> e1 = it1.next();
      String key = e1.getKey();
      st.add(key);
      if (!isEqual(st, e1.getValue(), jo2.get(key))) {
        return false;
      }

    }
    return true;
  }

  private boolean isEqual(Stack<String> st, JsonElement je1, JsonElement je2) {
    if (je1 instanceof JsonObject) {
      if (je2 instanceof JsonObject) {
        return isEqualJO(st, (JsonObject) je1, (JsonObject) je2);
      } else {
        return false;
      }
    }
    if (je1 instanceof JsonArray) {
      if (je2 instanceof JsonArray) {
        return isEqualJA(st, je1.getAsJsonArray(),je2.getAsJsonArray());
      } else {
        return false;
      }
    }

    return isElementsEqual(st, je1, je2);
  }

  protected abstract boolean isElementsEqual(Stack<String>st,JsonElement je1, JsonElement je2);

  private boolean isEqualJA(Stack<String> st, JsonArray ja1, JsonArray ja2) {
    Iterator<JsonElement> it1 = ja1.iterator();
    Iterator<JsonElement> it2 = ja2.iterator();

    while (it1.hasNext()){
      if(!isEqual(st,it1.next(),it2.next())){
        return false;
      }
    }

    return true;
  }

}
