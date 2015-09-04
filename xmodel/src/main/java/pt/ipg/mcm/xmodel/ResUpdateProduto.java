package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-update-produto")
public class ResUpdateProduto {

    public ResUpdateProduto() {
        new RetornoSoap(1, "Produto atualizado com sucesso");
    }

    public ResUpdateProduto(MestradoException e) {
        this.retorno = new RetornoSoap(e);
    }

    @XmlElement(required = true)
    private RetornoSoap retorno;

    public RetornoSoap getRetorno() {
        return retorno;
    }

}
