package pt.ipg.mcm.calls;

import com.squareup.okhttp.Response;

public class WebserviceException extends Exception{
  private Response response;

  public WebserviceException(Response response) {
    this.response = response;
  }

  public Response getResponse() {
    return response;
  }

  public boolean isAuthenticationProblem(){
    return response != null && response.code() == 401;
  }

}
