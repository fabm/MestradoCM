package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-delete-produto")
public class ResDeleteProduto {
    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private RetornoSoap retorno;

    public ResDeleteProduto() {
    }

    public ResDeleteProduto(RetornoSoap retornoSoap) {
        this.retorno = retornoSoap;
    }

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
