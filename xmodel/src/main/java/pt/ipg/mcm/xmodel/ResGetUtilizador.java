package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created by BrrF on 22-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ResGetUtilizador {
    private RetornoSoap retorno;

    private List<ResGetUtilizador> resGetUtilizadors;

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }

    public List<ResGetUtilizador> getResGetUtilizadors() {
        return resGetUtilizadors;
    }

    public void setResGetUtilizadors(List<ResGetUtilizador> resGetUtilizadors) {
        this.resGetUtilizadors = resGetUtilizadors;
    }
}
