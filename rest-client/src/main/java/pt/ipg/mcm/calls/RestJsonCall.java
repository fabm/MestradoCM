package pt.ipg.mcm.calls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import okio.ByteString;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public abstract class RestJsonCall {
  private Request request;

  public RestJsonCall(Request request) {
    this.request = request;
  }

  public <T> T getResponse(Class<T> jeClass) throws WebserviceException {
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

    try {
      return getObjectMapper().readValue(new InputStreamReader(response.body().byteStream()), jeClass);
    } catch (IOException e) {
      throw new IllegalStateException("Impossivel fazer parsing", e);
    }
  }

  public abstract ObjectMapper getObjectMapper();

  public static abstract class Builder<T extends RestJsonCall> {
    private ObjectMapper objectMapper = new ObjectMapper();
    private AuthBasicUtf8 authBasicUtf8;
    private Request.Builder requestBuilder;
    private String serverUrl;
    private static MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    public Builder() {
      requestBuilder = new Request.Builder();
    }

    public Builder<T> serverUrl(String serverUrl) {
      this.serverUrl = serverUrl;
      return this;
    }

    public Builder<T> path(String path) {
      requestBuilder.url(serverUrl + path);
      return this;
    }

    public Builder<T> auth(AuthBasicUtf8 authBasicUtf8) {
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


    public Builder<T> post(Object object) {
      try {
        RequestBody requestBody = RequestBody.create(jsonType, objectMapper.writeValueAsString(object));
        requestBuilder.post(requestBody);
      } catch (JsonProcessingException e) {
        throw new ParsingException(ParsingException.SERIALIZING_EXCEPTION, e);
      }
      return this;
    }

    public Builder<T> get() {
      requestBuilder.get();
      return this;
    }


    protected abstract T abstractBuild(Request request);

    public T build() {
      if (this.authBasicUtf8 != null) {
        requestBuilder.header("Authorization", basicAthenticationValue(authBasicUtf8.getLogin(), authBasicUtf8.getPassword()));
      }
      return abstractBuild(requestBuilder.build());
    }

  }
}
