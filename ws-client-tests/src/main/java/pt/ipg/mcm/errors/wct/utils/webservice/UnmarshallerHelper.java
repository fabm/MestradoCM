package pt.ipg.mcm.errors.wct.utils.webservice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class UnmarshallerHelper {
  private String entry;

  public UnmarshallerHelper(String entry) {
    this.entry = entry;
  }

  public <T>T unMarshallAfterTag(Class<T> cl, String tag) throws XMLStreamException, JAXBException {
    StringReader sr = new StringReader(entry);

    XMLStreamReader xsr = XMLInputFactory.newFactory().createXMLStreamReader(sr);

    xsr.nextTag();
    while (!xsr.getLocalName().equals(tag)) {
      xsr.nextTag();
    }

    JAXBContext jc = JAXBContext.newInstance(cl);
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<T> unmarshalled = unmarshaller.unmarshal(xsr, cl);

    return unmarshalled.getValue();
  }
}
