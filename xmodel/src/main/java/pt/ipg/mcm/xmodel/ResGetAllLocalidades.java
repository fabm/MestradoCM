package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BrrF on 30-01-2015.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-all-localidades")
public class ResGetAllLocalidades {

    @XmlElementWrapper(name = "localidades",required = true)
    @XmlElement(name = "localidade",required = false)
    private List<Localidade> localidades;

    @XmlElement(required = true)
    private Retorno retorno;

    public List<Localidade> getLocalidades() {
        if (localidades == null){
            localidades = new ArrayList<Localidade>();
        }
        return localidades;
    }


    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
}
