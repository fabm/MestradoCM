
package pt.ipg.mcm.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pt.ipg.mcm.client package. 
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

    private final static QName _Get_QNAME = new QName("http://services.mcm.ipg.pt/", "get");
    private final static QName _GetCliente_QNAME = new QName("http://services.mcm.ipg.pt/", "get-cliente");
    private final static QName _AddCliente_QNAME = new QName("http://services.mcm.ipg.pt/", "add-cliente");
    private final static QName _Add_QNAME = new QName("http://services.mcm.ipg.pt/", "add");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pt.ipg.mcm.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link GetCliente }
     * 
     */
    public GetCliente createGetCliente() {
        return new GetCliente();
    }

    /**
     * Create an instance of {@link AddCliente }
     * 
     */
    public AddCliente createAddCliente() {
        return new AddCliente();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link ReqGetCliente }
     * 
     */
    public ReqGetCliente createReqGetCliente() {
        return new ReqGetCliente();
    }

    /**
     * Create an instance of {@link ResGetCliente }
     * 
     */
    public ResGetCliente createResGetCliente() {
        return new ResGetCliente();
    }

    /**
     * Create an instance of {@link ResAddCliente }
     * 
     */
    public ResAddCliente createResAddCliente() {
        return new ResAddCliente();
    }

    /**
     * Create an instance of {@link ReqAddCliente }
     * 
     */
    public ReqAddCliente createReqAddCliente() {
        return new ReqAddCliente();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "get-cliente")
    public JAXBElement<GetCliente> createGetCliente(GetCliente value) {
        return new JAXBElement<GetCliente>(_GetCliente_QNAME, GetCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "add-cliente")
    public JAXBElement<AddCliente> createAddCliente(AddCliente value) {
        return new JAXBElement<AddCliente>(_AddCliente_QNAME, AddCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.mcm.ipg.pt/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

}
