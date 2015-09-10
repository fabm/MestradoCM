package pt.ipg.mcm.xmodel.encomendas;

import pt.ipg.mcm.calls.client.model.encomendas.AddAndUpdateEncomendasInRest;
import pt.ipg.mcm.calls.client.model.encomendas.EncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.EstadoEncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddAndUpdateEncomendasIn {

    @XmlElement(name = "encomenda")
    @XmlElementWrapper(name = "encomendas")
    private List<EncomendaIn> encomendaInList;

    @XmlElement(name = "estadoEncomendas")
    @XmlElementWrapper(name = "estadoEncomendas")
    private List<EstadoEncomendaIn> estadoEncomendasList;

    public static AddAndUpdateEncomendasIn convert(AddAndUpdateEncomendasInRest addAndUpdateEncomendasInRest) {
        AddAndUpdateEncomendasIn addAndUpdateEncomendasIn = new AddAndUpdateEncomendasIn();
        addAndUpdateEncomendasIn.estadoEncomendasList = new ArrayList<EstadoEncomendaIn>();
        for (EstadoEncomendaInRest estadoEncomendaInRest : addAndUpdateEncomendasInRest.getEstadoEncomendaInRestList()) {
            addAndUpdateEncomendasIn.estadoEncomendasList.add(EstadoEncomendaIn.convert(estadoEncomendaInRest));
        }

        addAndUpdateEncomendasIn.encomendaInList = new ArrayList<EncomendaIn>();
        for(EncomendaInRest encomendaInRest:addAndUpdateEncomendasInRest.getEncomendaInRestList()){
            EncomendaIn encomendaIn = new EncomendaIn();
            encomendaIn.setDataEntrega(encomendaInRest.getDataEntrega());

            List<ProdutoAEncomendar> produtoAEncomendarList = new ArrayList<ProdutoAEncomendar>();
            for(ProdutoEncomendadoRest produtoEncomendadoRest :encomendaInRest.getProdutoEncomendadoRestList()){
                ProdutoAEncomendar produtoAEncomendar = new ProdutoAEncomendar();
                produtoAEncomendar.convert(produtoEncomendadoRest);
                produtoAEncomendarList.add(produtoAEncomendar);
            }
            encomendaIn.setProdutoAEncomendarList(produtoAEncomendarList);

            addAndUpdateEncomendasIn.encomendaInList.add(encomendaIn);
        }
        return addAndUpdateEncomendasIn;
    }

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

    public AddAndUpdateEncomendasInRest convert() {
        AddAndUpdateEncomendasInRest addAndUpdateEncomendasInRest = new AddAndUpdateEncomendasInRest();

        List<EncomendaInRest> encomendaInRestList = new ArrayList<EncomendaInRest>();
        for (EncomendaIn encomendaIn : encomendaInList) {
            encomendaInRestList.add(encomendaIn.convert());
        }
        addAndUpdateEncomendasInRest.setEncomendaInRestList(encomendaInRestList);

        return addAndUpdateEncomendasInRest;
    }
}
