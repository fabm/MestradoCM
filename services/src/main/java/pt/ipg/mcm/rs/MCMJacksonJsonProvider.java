package pt.ipg.mcm.rs;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MCMJacksonJsonProvider implements ContextResolver<ObjectMapper> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.configure(MapperFeature.USE_GETTERS_AS_SETTERS,false);
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return MAPPER;
  }


}
