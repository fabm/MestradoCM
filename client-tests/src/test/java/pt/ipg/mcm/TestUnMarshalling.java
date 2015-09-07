package pt.ipg.mcm;

import client.tests.UserClienteCreationRequest;
import org.junit.Test;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestUnMarshalling {
    @Test
    public void marshellingTestUserClienteCreationRequest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(UserClienteCreationRequest.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try {
            final XMLGregorianCalendar data = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            data.setDay(25);
            data.setMonth(1);
            data.setYear(2000);
            data.setHour(0);
            data.setMinute(0);
            data.setSecond(0);

            QName qName = new QName("root");
            JAXBElement<XMLGregorianCalendar> root =
                    new JAXBElement<XMLGregorianCalendar>(qName, XMLGregorianCalendar.class, data);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(root, stringWriter);

            Reader sr = new StringReader(stringWriter.toString());

            StreamSource streamSource = new StreamSource(sr);
            JAXBElement<String> rootUnmarshalled = unmarshaller.unmarshal(streamSource,String.class);

            assertEquals("2000-01-25T00:00:00", rootUnmarshalled.getValue());

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

    }
}
