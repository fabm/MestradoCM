
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
    private Long id;

    @XmlElement(required = true)
    private RetornoSoap retorno;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
