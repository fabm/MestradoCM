package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.AddEncomendasInRest;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.ProdutoEncomendado;
import pt.ipg.mcm.xmodel.encomendas.AddEncomendasIn;
import pt.ipg.mcm.xmodel.encomendas.EncomendaIn;
import pt.ipg.mcm.xmodel.encomendas.ProdutoAEncomendar;

import java.util.ArrayList;
import java.util.List;

public class AddEncomendasInFromRest extends AbstractConversor<AddEncomendasInRest,AddEncomendasIn>{

    public AddEncomendasInFromRest(AddEncomendasInRest source) {
        super(source);
    }

    @Override
    public AddEncomendasIn converted() {
        AddEncomendasIn addEncomendasIn = new AddEncomendasIn();

        List<EncomendaIn> encomendaInList = new ArrayList<>();
        for(EncomendaInRest encomendaInRest:source.getEncomendaInList()){
            encomendaInList.add(new EncomendaInFromRest(encomendaInRest).converted());
        }
        addEncomendasIn.setEncomendaInList(encomendaInList);

        return null;
    }

}
