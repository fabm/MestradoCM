package pt.ipg.mcm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import org.junit.Assert;
import org.junit.Test;
import pt.ipg.mcm.xmodel.ReqAddEncomenda;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

public class ParsingTest {
  @Test
  public void parsingReqEncomendaTest() throws JsonProcessingException, DatatypeConfigurationException, JAXBException {
    ReqAddEncomenda reqAddEncomenda = new ReqAddEncomenda();
    reqAddEncomenda.setDataEntrega(new Date());

    ObjectMapper objectMapper = new ObjectMapper();
    AnnotationIntrospector annotationIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
    objectMapper.setAnnotationIntrospector(annotationIntrospector);
    System.out.println(objectMapper.writeValueAsString(reqAddEncomenda));

    StringWriter stringWriter = new StringWriter();

    JAXBContext jaxbContext = JAXBContext.newInstance(ReqAddEncomenda.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    // format the XML output
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    QName qName = new QName("root");
    JAXBElement<ReqAddEncomenda> root = new JAXBElement<ReqAddEncomenda>(qName, ReqAddEncomenda.class, reqAddEncomenda);


    jaxbMarshaller.marshal(root, stringWriter);

    String result = stringWriter.toString();
    System.out.println(result);

    String xml = "<root><dataEntrega>2015-01-25</dataEntrega></root>";

    Unmarshaller unmarshaller = JAXBContext.newInstance(ReqAddEncomenda.class).createUnmarshaller();

    JAXBElement<ReqAddEncomenda> unmarshalled = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), ReqAddEncomenda.class);

    Assert.assertNotNull(unmarshalled);

  }
}
