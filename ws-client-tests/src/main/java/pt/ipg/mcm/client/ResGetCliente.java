
package pt.ipg.mcm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for res-get-cliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="res-get-cliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contribuinte" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="morada" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="porta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="data-nascimento" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localidade" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "res-get-cliente", propOrder = {
    "contribuinte",
    "nome",
    "role",
    "morada",
    "porta",
    "dataNascimento",
    "email",
    "contacto",
    "localidade"
})
public class ResGetCliente {

    protected long contribuinte;
    @XmlElement(required = true)
    protected String nome;
    protected int role;
    @XmlElement(required = true)
    protected String morada;
    protected int porta;
    @XmlElement(name = "data-nascimento", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascimento;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String contacto;
    protected int localidade;

    /**
     * Gets the value of the contribuinte property.
     * 
     */
    public long getContribuinte() {
        return contribuinte;
    }

    /**
     * Sets the value of the contribuinte property.
     * 
     */
    public void setContribuinte(long value) {
        this.contribuinte = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the role property.
     * 
     */
    public int getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     */
    public void setRole(int value) {
        this.role = value;
    }

    /**
     * Gets the value of the morada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Sets the value of the morada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMorada(String value) {
        this.morada = value;
    }

    /**
     * Gets the value of the porta property.
     * 
     */
    public int getPorta() {
        return porta;
    }

    /**
     * Sets the value of the porta property.
     * 
     */
    public void setPorta(int value) {
        this.porta = value;
    }

    /**
     * Gets the value of the dataNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Sets the value of the dataNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascimento(XMLGregorianCalendar value) {
        this.dataNascimento = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the contacto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Sets the value of the contacto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContacto(String value) {
        this.contacto = value;
    }

    /**
     * Gets the value of the localidade property.
     * 
     */
    public int getLocalidade() {
        return localidade;
    }

    /**
     * Sets the value of the localidade property.
     * 
     */
    public void setLocalidade(int value) {
        this.localidade = value;
    }

}
