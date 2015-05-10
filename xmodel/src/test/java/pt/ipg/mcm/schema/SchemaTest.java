package pt.ipg.mcm.schema;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.junit.Test;
import org.w3c.dom.Document;
import pt.ipg.mcm.xmodel.ResGetProdutosCategorias;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchemaTest {
  @Test
  public void schemaTest() throws JAXBException, IOException {
      // grab the context
      JAXBContext context = JAXBContext.newInstance(ResGetProdutosCategorias.class);

      final List results = new ArrayList();

      // generate the schema
      context.generateSchema(
          // need to define a SchemaOutputResolver to store to
          new SchemaOutputResolver()
          {
            @Override
            public Result createOutput( String ns, String file )
                throws IOException
            {
              // save the schema to the list
              DOMResult result = new DOMResult();
              result.setSystemId( file );
              results.add( result );
              return result;
            }
          } );

      // output schema via System.out
      DOMResult domResult = (DOMResult) results.get( 0 );
      Document doc = (Document) domResult.getNode();
      OutputFormat format = new OutputFormat( doc );
      format.setIndenting( true );
      XMLSerializer serializer = new XMLSerializer( System.out, format );
      serializer.serialize( doc );
  }

}
