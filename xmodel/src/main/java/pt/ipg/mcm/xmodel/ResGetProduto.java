
package pt.ipg.mcm.xmodel;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for res-get-produto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="res-get-produto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preco-unitario" type="{}money"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="type-response" type="{}type-response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-produto", propOrder = {
    "nome",
    "precoUnitario",
    "categoria",
    "foto",
    "typeResponse"
})
public class ResGetProduto {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(name = "preco-unitario", required = true)
    protected BigDecimal precoUnitario;
    protected long categoria;
    @XmlElement(required = true)
    protected byte[] foto;
    @XmlElement(name = "type-response", required = true)
    protected TypeResponse typeResponse;

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
     * Gets the value of the precoUnitario property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    /**
     * Sets the value of the precoUnitario property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecoUnitario(BigDecimal value) {
        this.precoUnitario = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     */
    public long getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     */
    public void setCategoria(long value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the foto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * Sets the value of the foto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFoto(byte[] value) {
        this.foto = value;
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
