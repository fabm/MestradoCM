package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.List;

public class AddEncomendasOutRest {
    private List<EncomendaOutRest> encomendaOutRestList;

    public List<EncomendaOutRest> getEncomendaOutRestList() {
        return encomendaOutRestList;
    }

    public void setEncomendaOutRestList(List<EncomendaOutRest> encomendaOutRestList) {
        this.encomendaOutRestList = encomendaOutRestList;
    }
}
