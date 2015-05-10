package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 24-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-update-categoria")
public class ResUpdateCategoria {

    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private RetornoSoap retorno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
