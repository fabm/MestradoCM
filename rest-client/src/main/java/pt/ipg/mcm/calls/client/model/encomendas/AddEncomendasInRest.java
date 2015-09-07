package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.List;

public class AddEncomendasInRest {
    private List<EncomendaInRest> encomendaInList;

    public List<EncomendaInRest> getEncomendaInList() {
        return encomendaInList;
    }

    public void setEncomendaInList(List<EncomendaInRest> encomendaInList) {
        this.encomendaInList = encomendaInList;
    }
}
