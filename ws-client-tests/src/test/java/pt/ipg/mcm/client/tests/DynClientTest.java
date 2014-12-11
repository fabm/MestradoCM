package pt.ipg.mcm.client.tests;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import pt.ipg.mcm.wct.utils.webservice.UnmarshallerHelper;
import pt.ipg.mcm.wct.utils.webservice.WsSoapClientDispatcherHelper;
import pt.ipg.mcm.xmodel.ResGetCliente;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class DynClientTest {

  private URL wsdlUrl = null;

  public DynClientTest() throws MalformedURLException {
    wsdlUrl = new URL("http://192.168.1.104:8080/services/cliente?wsdl");
  }

  @Test
  public void testReadUrl() throws Exception {
    URLConnection connection = wsdlUrl.openConnection();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(
            connection.getInputStream()));

    StringBuilder response = new StringBuilder();
    String inputLine;

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }

    in.close();
  }

  @Test
  public void dynamicSoapTest() throws IOException, SOAPException, URISyntaxException, JAXBException, XMLStreamException, SAXException {
    String strPath = "/soap-messages/req1.xml";

    WsSoapClientDispatcherHelper wsSoapClientDispatcherHelper = new WsSoapClientDispatcherHelper();
    wsSoapClientDispatcherHelper.setWsdlUrl("http://192.168.1.104:8080/services/cliente?wsdl");
    wsSoapClientDispatcherHelper.setNs("http://services.mcm.ipg.pt/");
    wsSoapClientDispatcherHelper.setServiceName("cliente");
    wsSoapClientDispatcherHelper.setPortName("clientePort");
    wsSoapClientDispatcherHelper.createService();
    String resWs = wsSoapClientDispatcherHelper.dispatchRequest(getClass().getResourceAsStream(strPath));

    String pathToRes = "/soap-messages/res1.xml";

    String resFile = FileUtils.readFileToString(new File(getClass().getResource(pathToRes).toURI()));


    System.out.println("Xml carregado do cliente:");
    System.out.println(resWs);
    System.out.println("Xml carregado do ficheiro para comparação:");
    System.out.println(resFile);

    XMLUnit.setIgnoreWhitespace(true);
    XMLAssert.assertXMLEqual(resFile, resWs);

    System.out.println("Unmarshall da resposta para o xmodel:");
    UnmarshallerHelper unmarshallerHelper = new UnmarshallerHelper(resWs);
    ResGetCliente resGetCliente = unmarshallerHelper.unMarshallAfterTag(ResGetCliente.class, "response");


    Assert.assertEquals("usercliente", resGetCliente.getNome());


  }

}
