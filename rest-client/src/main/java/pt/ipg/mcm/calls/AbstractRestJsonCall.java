package pt.ipg.mcm.calls;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import okio.ByteString;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class AbstractRestJsonCall {
  private Request request;

  public AbstractRestJsonCall(Request request) {
    this.request = request;
  }

  protected abstract Gson getGson();

  public <T extends JsonElement> T getResponse(Class<T> jeClass) throws WebserviceException {
    OkHttpClient okHttpClient = new OkHttpClient();
    Response response;
    try {
      response = okHttpClient.newCall(request).execute();
    } catch (IOException e) {
      throw new WebserviceException(null);
    }
    if (!response.isSuccessful()) {
      throw new WebserviceException(response);
    }

    getGson();
    return getGson().fromJson(new InputStreamReader(response.body().byteStream()), jeClass);
  }

  public static abstract class Builder {
    private Gson gson = new Gson();
    private AuthBasicUtf8 authBasicUtf8;
    private Request.Builder requestBuilder;
    private String serverUrl;
    private static MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    public Builder() {
      requestBuilder = new Request.Builder();
    }

    public Builder serverUrl(String serverUrl) {
      this.serverUrl = serverUrl;
      return this;
    }

    public Builder path(String path) {
      requestBuilder.url(serverUrl + path);
      return this;
    }

    public Builder auth(AuthBasicUtf8 authBasicUtf8) {
      this.authBasicUtf8 = authBasicUtf8;
      return this;
    }

    private String basicAthenticationValue(String userName, String password) {
      String usernameAndPassword = userName + ":" + password;
      byte[] bytes = new byte[0];
      try {
        bytes = usernameAndPassword.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      String encoded = ByteString.of(bytes).base64();
      return "Basic " + encoded;
    }


    public Builder post(JsonElement jsonElementRequest) {
      RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(jsonElementRequest));
      requestBuilder.post(requestBody);
      return this;
    }

    public Builder put(JsonElement jsonElementRequest) {
      RequestBody requestBody = RequestBody.create(jsonType, gson.toJson(jsonElementRequest));
      requestBuilder.put(requestBody);
      return this;
    }

    public Builder get() {
      requestBuilder.get();
      return this;
    }


    protected abstract AbstractRestJsonCall abstractBuild(Request request);

    public AbstractRestJsonCall build() {
      if (this.authBasicUtf8 != null) {
        requestBuilder.header("Authorization", basicAthenticationValue(authBasicUtf8.getLogin(), authBasicUtf8.getPassword()));
      }
      return abstractBuild(requestBuilder.build());
    }

  }
}
