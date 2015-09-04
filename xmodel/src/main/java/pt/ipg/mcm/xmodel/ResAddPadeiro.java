package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResAddPadeiro {

    private Long id;

    private RetornoSoap retorno;

    public ResAddPadeiro() {
    }


    public ResAddPadeiro(Long id) {
        this.id = id;
        retorno = new RetornoSoap(1, "Padeiro inserido com sucesso");
    }

    public ResAddPadeiro(MestradoException e) {
        this.retorno = new RetornoSoap(e);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
