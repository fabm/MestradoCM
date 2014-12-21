package pt.ipg.mcm.errors.wct.utils.webservice;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class PathHelper {
  private InputStream inputStream;

  public PathHelper(InputStream inputStream) {
    this.inputStream = inputStream;
  }


  public XMLStreamReader getPath(NodeNav nodeNav) {

    NodeNav current = nodeNav;


    try {
    inputStream.reset();
      XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream);
      while (xmlStreamReader.hasNext()) {
        xmlStreamReader.next();
        if (xmlStreamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
          if (current.getName().equals(xmlStreamReader.getLocalName())) {
            if (current.getNext() == null) {
              return xmlStreamReader;
            } else {
              current = current.getNext();
            }
          }
        }
      }
    } catch (XMLStreamException e) {
      throw new IllegalStateException(e);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return null;
  }

}
