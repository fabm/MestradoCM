package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaDetalheRest;
import pt.ipg.mcm.calls.client.model.encomendas.GetMinhasEncomendasRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
public class ResMinhasEncomendasDetalhe {
    private List<EncomendaDetalheXml> listaEncomendasDetalheXml;

    public ResMinhasEncomendasDetalhe() {
    }

    public ResMinhasEncomendasDetalhe(List<EncomendaDetalheXml> encomendaDetalheXmlList) {
        this.listaEncomendasDetalheXml = encomendaDetalheXmlList;
    }

    @XmlElement
    public List<EncomendaDetalheXml> getListaEncomendasDetalheXmls() {
        return listaEncomendasDetalheXml;
    }

    @XmlElement
    public long getMaxSync() {
        long syncMax = 0;
        for (EncomendaDetalheXml encomendaDetalheXml : listaEncomendasDetalheXml) {
            if (encomendaDetalheXml.getSync() > syncMax) {
                syncMax = encomendaDetalheXml.getSync();
            }
        }
        return syncMax;
    }

    public GetMinhasEncomendasRest convert() {
        GetMinhasEncomendasRest getMinhasEncomendasRest = new GetMinhasEncomendasRest();
        getMinhasEncomendasRest.setMaxSync(getMaxSync());
        List<EncomendaDetalheRest> encomendaDetalheRestList = getMinhasEncomendasRest.createEncomendaDetalheRestList();
        for(EncomendaDetalheXml encomendaDetalheXml:listaEncomendasDetalheXml){
            encomendaDetalheRestList.add(encomendaDetalheXml.convert());
        }
        return getMinhasEncomendasRest;
    }
}
