package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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

}
