package pt.ipg.mcm.xmodel.encomendas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddAndUpdateEncomendasIn {

    @XmlElement(name = "encomenda")
    @XmlElementWrapper(name = "encomendas")
    private List<EncomendaIn> encomendaInList;

    @XmlElement(name = "estadoEncomendas")
    @XmlElementWrapper(name = "estadoEncomendas")
    private List<EstadoEncomendaIn> estadoEncomendasList;

    public List<EncomendaIn> getEncomendaInList() {
        return encomendaInList;
    }

    public List<EstadoEncomendaIn> getEstadoEncomendasList() {
        return estadoEncomendasList;
    }

    public void setEncomendaInList(List<EncomendaIn> encomendaInList) {
        this.encomendaInList = encomendaInList;
    }

    public void setEstadoEncomendasList(List<EstadoEncomendaIn> estadoEncomendasList) {
        this.estadoEncomendasList = estadoEncomendasList;
    }
}
