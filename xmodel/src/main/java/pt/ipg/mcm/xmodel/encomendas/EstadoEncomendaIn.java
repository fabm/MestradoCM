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
     * 4: Entrega parcial, o qual fará com que seja criada uma nova encomenda com com parte dos produtos encomendados na encomenda original e associada à encomenda original
     * 5: Cancelado pelo cliente
     * 6: Entregua confirmada pelo padeiro
     * 7: Entrega confirmada pelo cliente
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
     * 4: Entrega parcial, o qual fará com que seja criada uma nova encomenda com com parte dos produtos encomendados na encomenda original e associada à encomenda original
     * 5: Cancelado pelo cliente
     * 6: Entregua confirmada pelo padeiro
     * 7: Entrega confirmada pelo cliente
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
