package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-all-localidades")
public class ResGetAllLocalidades {

    @XmlElementWrapper(name = "localidades",required = true)
    @XmlElement(name = "localidade",required = false)
    private List<Localidade> localidades;

    @XmlElement(required = true)
    private RetornoSoap retorno;

    public ResGetAllLocalidades(List<Localidade> localidades) {
        this.localidades = localidades;
        retorno = new RetornoSoap(1,null);
    }

    public ResGetAllLocalidades() {
    }

    public List<Localidade> getLocalidades() {
        if (localidades == null){
            localidades = new ArrayList<Localidade>();
        }
        return localidades;
    }


    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
