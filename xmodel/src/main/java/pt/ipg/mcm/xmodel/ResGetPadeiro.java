package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created by BrrF on 22-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ResGetPadeiro {
    private Retorno retorno;

    private List<ResGetPadeiro> resGetPadeiros;

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }

    public List<ResGetPadeiro> getResGetPadeiros() {
        return resGetPadeiros;
    }

    public void setResGetPadeiros(List<ResGetPadeiro> resGetPadeiros) {
        this.resGetPadeiros = resGetPadeiros;
    }
}
