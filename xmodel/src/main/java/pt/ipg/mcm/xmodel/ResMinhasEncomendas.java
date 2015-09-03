package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ResMinhasEncomendas extends RetornoSoap {

    public ResMinhasEncomendas() {

    }

    private List<MinhaEncomenda> minhasEncomendasList;

    public ResMinhasEncomendas(MestradoException e) {
        super(e);

    }

    public ResMinhasEncomendas(List<MinhaEncomenda> minhasEncomendasList) {
        super(1,"Encomendas devolvidas com sucesso");
        this.minhasEncomendasList = minhasEncomendasList;
    }

}
