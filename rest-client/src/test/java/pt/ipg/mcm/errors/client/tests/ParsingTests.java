package pt.ipg.mcm.errors.client.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import pt.ipg.mcm.calls.client.model.categoria.CategoriaRest;
import pt.ipg.mcm.calls.client.model.categoria.GetCategoriaDesyncRest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class ParsingTests {
  @Test
  public void parsingBigDecimal() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String toJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new BigDecimal("1.5"));
      BigDecimal bd = objectMapper.readValue(toJson, BigDecimal.class);

      Assert.assertEquals(150, bd.multiply(new BigDecimal(100)).intValue());
    } catch (JsonProcessingException e) {
      Assert.fail("Problem with serialisation");
    } catch (IOException e) {
      Assert.fail("Problem with deserialisation");
    }

  }

  @Test
  public void parsingJacksonCategorias() throws IOException {
    GetCategoriaDesyncRest getCategoriaDesyncRest = new GetCategoriaDesyncRest();
    getCategoriaDesyncRest.setMaxVersao(123);
    List<CategoriaRest> catList = getCategoriaDesyncRest.createCategorias();
    CategoriaRest categoriaRest = new CategoriaRest();
    categoriaRest.setId(1L);
    categoriaRest.setDescricao("xpta");
    categoriaRest.setNome("xpto");
    catList.add(categoriaRest);

    ObjectMapper objectMapper = new ObjectMapper();
    String content = objectMapper.writeValueAsString(getCategoriaDesyncRest);

    GetCategoriaDesyncRest obj = objectMapper.readValue(content, GetCategoriaDesyncRest.class);
    Assert.assertEquals(1, obj.getCategorias().size());
    Assert.assertEquals(obj.getCategorias().get(0).getNome(), "xpto");

  }
}
