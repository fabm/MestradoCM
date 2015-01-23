package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by BrrF on 19-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-utilizador-Padeiro")

public class ResAddUtilizador {

    @XmlElement(required = false)
    private Retorno retorno;

    public ResAddUtilizador() {
    }

    public ResAddUtilizador(Retorno retorno) {
        this.retorno = retorno;
    }

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
}
