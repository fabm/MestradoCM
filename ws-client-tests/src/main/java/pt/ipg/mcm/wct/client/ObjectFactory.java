
package pt.ipg.mcm.wct.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.ipg.mcm.wct.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddCliente_QNAME = new QName("http://services.mcm.ipg.pt/", "addCliente");
    private final static QName _AddClienteResponse_QNAME = new QName("http://services.mcm.ipg.pt/", "addClienteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.ipg.mcm.wct.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCliente }
     * 
     */
    public AddCliente createAddCliente() {
        return new AddCliente();
    }

    /**
     * Create an instance of {@link AddClienteResponse }
     * 
     */
    public AddClienteResponse createAddClienteResponse() {
        return new AddClienteResponse();
    }

    /**
     * Create an instance of {@link ClienteTypeResponse }
     * 
     */
    public ClienteTypeResponse createClienteTypeResponse() {
        return new ClienteTypeResponse();
    }

    /**
     * Create an instance of {@link ClienteTypeRequest }
     * 
     */
    public ClienteTypeRequest createClienteTypeRequest() {
        return new ClienteTypeRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "addCliente")
    public JAXBElement<AddCliente> createAddCliente(AddCliente value) {
        return new JAXBElement<AddCliente>(_AddCliente_QNAME, AddCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "addClienteResponse")
    public JAXBElement<AddClienteResponse> createAddClienteResponse(AddClienteResponse value) {
        return new JAXBElement<AddClienteResponse>(_AddClienteResponse_QNAME, AddClienteResponse.class, null, value);
    }

}
