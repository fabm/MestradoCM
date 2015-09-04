package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-add-utilizador-Padeiro")

public class ResAddUtilizador {

    @XmlElement(required = false)
    private RetornoSoap retorno;

    public ResAddUtilizador() {
    }

    public ResAddUtilizador(RetornoSoap retorno) {
        this.retorno = retorno;
    }

    public ResAddUtilizador(MestradoException e) {
        retorno = new RetornoSoap(e);
    }


    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
