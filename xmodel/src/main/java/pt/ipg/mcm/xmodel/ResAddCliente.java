
package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-cliente")
public class ResAddCliente {

    @NotNull
    @XmlElement(required = true)
    private long id;

    @XmlElement(required = true)
    private Retorno retorno;

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
}
