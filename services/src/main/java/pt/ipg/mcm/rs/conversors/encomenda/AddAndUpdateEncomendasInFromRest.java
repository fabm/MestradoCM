package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.AddAndUpdateEncomendasInRest;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.EstadoEncomendaInRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.AddAndUpdateEncomendasIn;
import pt.ipg.mcm.xmodel.encomendas.EncomendaIn;
import pt.ipg.mcm.xmodel.encomendas.EstadoEncomendaIn;

import java.util.ArrayList;
import java.util.List;

public class AddAndUpdateEncomendasInFromRest extends AbstractConversor<AddAndUpdateEncomendasInRest,AddAndUpdateEncomendasIn>{

    public AddAndUpdateEncomendasInFromRest(AddAndUpdateEncomendasInRest source) {
        super(source);
    }

    @Override
    public AddAndUpdateEncomendasIn converted() {
        AddAndUpdateEncomendasIn addAndUpdateEncomendasIn = new AddAndUpdateEncomendasIn();

        List<EncomendaIn> encomendaInList = new ArrayList<>();
        for(EncomendaInRest encomendaInRest:source.getEncomendaInRestList()){
            encomendaInList.add(new EncomendaInFromRest(encomendaInRest).converted());
        }
        addAndUpdateEncomendasIn.setEncomendaInList(encomendaInList);


        List<EstadoEncomendaIn> estadoEncomendaInList = new ArrayList<>();
        for(EstadoEncomendaInRest estadoEncomendaInRest:source.getEstadoEncomendaInRestList()){
            estadoEncomendaInList.add(new EstadoEncomendaInFromRest(estadoEncomendaInRest).converted());
        }
        addAndUpdateEncomendasIn.setEstadoEncomendasList(estadoEncomendaInList);

        return addAndUpdateEncomendasIn;
    }
}
