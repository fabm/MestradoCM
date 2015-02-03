package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 27-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Req-delete-categoria")
public class ReqDeleteCategoria {

    @XmlElement(required = true)
    private Integer idCategoria;


    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }




}
