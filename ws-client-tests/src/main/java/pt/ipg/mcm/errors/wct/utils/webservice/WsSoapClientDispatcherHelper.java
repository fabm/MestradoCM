package pt.ipg.mcm.errors.wct.utils.webservice;

import org.apache.commons.io.IOUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class WsSoapClientDispatcherHelper {
  private String ns;
  private String wsdlUrl;
  private String serviceName;
  private String portName;

  private Service service;

  public String getWsdlUrl() {
    return wsdlUrl;
  }

  public void setWsdlUrl(String wsdlUrl) {
    this.wsdlUrl = wsdlUrl;
  }

  public String getNs() {
    return ns;
  }

  public void setNs(String ns) {
    this.ns = ns;
  }

  public void createService() throws MalformedURLException {
    URL url = new URL(wsdlUrl);
    QName qService = new QName(ns, serviceName);
    service = Service.create(url, qService);
  }

  public String dispatchRequest(InputStream inputStream) throws SOAPException, IOException {
    QName qPort = new QName(ns, portName);
    Dispatch<SOAPMessage> dispatch = service.createDispatch(qPort,
        SOAPMessage.class, Service.Mode.MESSAGE);

    MessageFactory factory = MessageFactory.newInstance();
    SOAPMessage msgObject = factory.createMessage(null, inputStream);

    Map<String, Object> rc = dispatch.getRequestContext();
    rc.put(BindingProvider.USERNAME_PROPERTY,"user");
    rc.put(BindingProvider.PASSWORD_PROPERTY,"user");

    SOAPMessage response = dispatch.invoke(msgObject);

    ByteArrayOutputStream bao = new ByteArrayOutputStream();
    response.writeTo(bao);
    InputStream bai = new ByteArrayInputStream(bao.toByteArray());
    StringWriter stringWriter = new StringWriter();
    IOUtils.copy(bai, stringWriter);

    return stringWriter.toString();
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public void setPortName(String portName) {
    this.portName = portName;
  }
}
