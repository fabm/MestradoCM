package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.AddEncomendasOutRest;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaOutRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasOut;
import pt.ipg.mcm.xmodel.encomendas.EncomendaOut;

import java.util.ArrayList;
import java.util.List;

public class AddEncomendasOut2Rest extends AbstractConversor<AddEncomendasOut,AddEncomendasOutRest>{
    public AddEncomendasOut2Rest(AddEncomendasOut source) {
        super(source);
    }

    @Override
    public AddEncomendasOutRest converted() {
        AddEncomendasOutRest addEncomendasOutRest = new AddEncomendasOutRest();
        addEncomendasOutRest.setEncomendaOutRestList(convert(source.getEncomendaOutList()));
        return addEncomendasOutRest;
    }

    private List<EncomendaOutRest> convert(List<EncomendaOut> encomendaOutList) {
        List<EncomendaOutRest> encomendaOutRestList = new ArrayList<>();
        for(EncomendaOut encomendaOut:encomendaOutList){
            EncomendaOutRest encomendaOutRest = new EncomendaOutRest();
            encomendaOutRest.setClientId(encomendaOut.getClientId());
            encomendaOutRest.setServerId(encomendaOut.getServerId());
            encomendaOutRestList.add(encomendaOutRest);
        }
        return encomendaOutRestList;
    }
}
