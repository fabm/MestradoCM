package pt.ipg.mcm.client.tests;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import pt.ipg.mcm.wct.utils.webservice.NodeNav;
import pt.ipg.mcm.wct.utils.webservice.PathHelper;
import pt.ipg.mcm.wct.utils.webservice.WsSoapClientDispatcherHelper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ClientTest {

  @Rule
  public ClientRule clientRule = new ClientRule();

  @Test
  public void testGetClient() throws Exception {
    String strPath = "/soap-messages/req1.xml";

    WsSoapClientDispatcherHelper wsSoapClientDispatcherHelper = new WsSoapClientDispatcherHelper();
    wsSoapClientDispatcherHelper.setWsdlUrl("http://localhost:8081/services/cliente?wsdl");
    wsSoapClientDispatcherHelper.setNs("http://services.mcm.ipg.pt/");
    wsSoapClientDispatcherHelper.setServiceName("cliente");
    wsSoapClientDispatcherHelper.setPortName("clientePort");
    wsSoapClientDispatcherHelper.createService();
    String resWs = wsSoapClientDispatcherHelper.dispatchRequest(getClass().getResourceAsStream(strPath));

    String pathToRes = "/soap-messages/res1.xml";

    String resFile = FileUtils.readFileToString(new File(getClass().getResource(pathToRes).toURI()));

    XMLUnit.setIgnoreWhitespace(true);
    XMLAssert.assertXMLEqual(resFile, resWs);

    PathHelper pathHelper = new PathHelper(new ByteArrayInputStream(resWs.getBytes()));


    NodeNav nodeNav = NodeNav.createNodeNavSoap()
        .setNext("get")
        .setNext("response")
        .setNext("contribuinte");

    XMLStreamReader xmlStreamReader = pathHelper.getPath(nodeNav);
    Assert.assertEquals(111, Integer.valueOf(xmlStreamReader.getElementText()).intValue());

    nodeNav = NodeNav.createNodeNavSoap()
        .setNext("get")
        .setNext("response")
        .setNext("nome");

    xmlStreamReader = pathHelper.getPath(nodeNav);
    Assert.assertEquals("usercliente", xmlStreamReader.getElementText());
  }
}
