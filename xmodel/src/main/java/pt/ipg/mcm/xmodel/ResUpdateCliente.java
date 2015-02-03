package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 26-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-update-cliente")
public class ResUpdateCliente {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private Retorno retorno;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
}
