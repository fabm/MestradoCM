package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.calls.client.model.encomendas.EstadoEncomendaInRest;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class EstadoEncomendaIn {

    private int estado;
    private long idEncomenda;

    public static EstadoEncomendaIn convert(EstadoEncomendaInRest estadoEncomendaInRest) {
        EstadoEncomendaIn estadoEncomendaIn = new EstadoEncomendaIn();
        estadoEncomendaIn.idEncomenda = estadoEncomendaInRest.getIdEncomenda();
        estadoEncomendaIn.estado = estadoEncomendaInRest.getEstado();
        return estadoEncomendaIn;
    }

    /**
     * Estado atual da encomenda
     * 1: A espera de confirmação de um padeiro
     * 2: Aguarda entrega
     * 3: Cancelado por incompatibilidade na data de entrega, ma nova encomenda pode ser criada com referênca à original que foi cancelada
     * 4: Cancelado pelo cliente
     * 5: Entregua confirmada pelo padeiro
     * 6: Entrega confirmada pelo cliente
     *
     * @return
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Estado atual da encomenda
     * 1: A espera de confirmação de um padeiro
     * 2: Aguarda entrega
     * 3: Cancelado por incompatibilidade na data de entrega, ma nova encomenda pode ser criada com referênca à original que foi cancelada
     * 4: Cancelado pelo cliente
     * 5: Entregua confirmada pelo padeiro
     * 6: Entrega confirmada pelo cliente
     *
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    public long getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(long idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
}
