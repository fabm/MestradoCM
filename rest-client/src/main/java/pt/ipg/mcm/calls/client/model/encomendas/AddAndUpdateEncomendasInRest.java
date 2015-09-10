package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.List;

public class AddAndUpdateEncomendasInRest {
    List<EstadoEncomendaInRest> estadoEncomendaInRestList;
    List<EncomendaInRest> encomendaInRestList;

    public List<EstadoEncomendaInRest> getEstadoEncomendaInRestList() {
        return estadoEncomendaInRestList;
    }

    public void setEstadoEncomendaInRestList(List<EstadoEncomendaInRest> estadoEncomendaInRestList) {
        this.estadoEncomendaInRestList = estadoEncomendaInRestList;
    }

    public List<EncomendaInRest> getEncomendaInRestList() {
        return encomendaInRestList;
    }

    public void setEncomendaInRestList(List<EncomendaInRest> encomendaInRestList) {
        this.encomendaInRestList = encomendaInRestList;
    }

}
