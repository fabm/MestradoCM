
package pt.ipg.mcm.xmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for res-get-produtos-categorias complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="res-get-produtos-categorias">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="produto-categoria" type="{}produto-categoria" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "res-get-produtos-categorias", propOrder = {
    "produtoCategoriaList"
})
public class ResGetProdutosCategorias {

    @XmlElement(name = "produto-categoria", required = true)
    protected List<ProdutoCategoria> produtoCategoriaList;

    /**
     * Gets the value of the produtoCategoriaList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the produtoCategoriaList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProdutoCategoriaList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProdutoCategoria }
     * 
     * 
     */
    public List<ProdutoCategoria> getProdutoCategoriaList() {
        if (produtoCategoriaList == null) {
            produtoCategoriaList = new ArrayList<ProdutoCategoria>();
        }
        return this.produtoCategoriaList;
    }

}
