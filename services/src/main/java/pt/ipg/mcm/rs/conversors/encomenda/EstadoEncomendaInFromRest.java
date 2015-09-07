package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EstadoEncomendaInRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EstadoEncomendaIn;

public class EstadoEncomendaInFromRest extends AbstractConversor<EstadoEncomendaInRest,EstadoEncomendaIn>{
    public EstadoEncomendaInFromRest(EstadoEncomendaInRest source) {
        super(source);
    }

    @Override
    public EstadoEncomendaIn converted() {
        EstadoEncomendaIn estadoEncomendaIn = new EstadoEncomendaIn();
        estadoEncomendaIn.setEstado(source.getEstado());
        estadoEncomendaIn.setIdEncomenda(source.getIdEncomenda());
        return estadoEncomendaIn;
    }
}
