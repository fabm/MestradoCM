
package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type-response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type-response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensagem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo-resposta" type="{}type-enum-response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type-response", propOrder = {
    "mensagem",
    "tipoResposta"
})
public class TypeResponse {

    @XmlElement(required = true)
    protected String mensagem;
    @XmlElement(name = "tipo-resposta", required = true)
    protected TypeEnumResponse tipoResposta;

    /**
     * Gets the value of the mensagem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Sets the value of the mensagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagem(String value) {
        this.mensagem = value;
    }

    /**
     * Gets the value of the tipoResposta property.
     * 
     * @return
     *     possible object is
     *     {@link TypeEnumResponse }
     *     
     */
    public TypeEnumResponse getTipoResposta() {
        return tipoResposta;
    }

    /**
     * Sets the value of the tipoResposta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeEnumResponse }
     *     
     */
    public void setTipoResposta(TypeEnumResponse value) {
        this.tipoResposta = value;
    }

}
