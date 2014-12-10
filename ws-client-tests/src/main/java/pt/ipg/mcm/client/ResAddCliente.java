
package pt.ipg.mcm.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for res-add-cliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="res-add-cliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="type-response" type="{http://services.mcm.ipg.pt/}type-response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "res-add-cliente", propOrder = {
    "id",
    "typeResponse"
})
public class ResAddCliente {

    protected long id;
    @XmlElement(name = "type-response", required = true)
    protected TypeResponse typeResponse;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the typeResponse property.
     * 
     * @return
     *     possible object is
     *     {@link TypeResponse }
     *     
     */
    public TypeResponse getTypeResponse() {
        return typeResponse;
    }

    /**
     * Sets the value of the typeResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeResponse }
     *     
     */
    public void setTypeResponse(TypeResponse value) {
        this.typeResponse = value;
    }

}
