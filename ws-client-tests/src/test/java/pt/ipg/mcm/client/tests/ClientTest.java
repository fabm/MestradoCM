package pt.ipg.mcm.client.tests;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.XMLAssert;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Rule;
import org.junit.Test;
import pt.ipg.mcm.wct.utils.webservice.WsSoapClientDispatcherHelper;

import java.io.File;

public class ClientTest {

  @Rule
  public ClientRule clientRule = new ClientRule();

  @Test
  public void testGetClient() throws Exception {
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

    XMLUnit.setIgnoreWhitespace(true);
    XMLAssert.assertXMLEqual(resFile, resWs);
  }
}
