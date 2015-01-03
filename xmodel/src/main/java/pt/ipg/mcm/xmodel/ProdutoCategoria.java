
package pt.ipg.mcm.xmodel;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for produto-categoria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="produto-categoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome-produto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nome-categoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preco-actual" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="foto" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "produto-categoria", propOrder = {
    "nomeProduto",
    "nomeCategoria",
    "precoActual",
    "descricao",
    "foto"
})
public class ProdutoCategoria {

    @XmlElement(name = "nome-produto", required = true)
    protected String nomeProduto;
    @XmlElement(name = "nome-categoria", required = true)
    protected String nomeCategoria;
    @XmlElement(name = "preco-actual", required = true)
    protected BigDecimal precoActual;
    @XmlElement(required = true)
    protected String descricao;
    @XmlElement(required = true)
    protected byte[] foto;

    /**
     * Gets the value of the nomeProduto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Sets the value of the nomeProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeProduto(String value) {
        this.nomeProduto = value;
    }

    /**
     * Gets the value of the nomeCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    /**
     * Sets the value of the nomeCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeCategoria(String value) {
        this.nomeCategoria = value;
    }

    /**
     * Gets the value of the precoActual property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecoActual() {
        return precoActual;
    }

    /**
     * Sets the value of the precoActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecoActual(BigDecimal value) {
        this.precoActual = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
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

}
