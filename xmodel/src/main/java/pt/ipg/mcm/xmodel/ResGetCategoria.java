package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-categoria")
public class ResGetCategoria extends Categoria{

    @NotNull
    @XmlElement(required = true)
    private String nome;

    @NotNull
    @XmlElement(required = true)
    private String descricao;

    @XmlElement(required = false)
    private RetornoSoap retorno;

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
