package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 28-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Req-delete-produto")
public class ReqDeleteProduto {

    @XmlElement(required = true)
    private Integer idProduto;


    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }
}
