package pt.ipg.mcm.rs.conversors.encomenda;

import pt.ipg.mcm.calls.client.model.encomendas.EncomendaInRest;
import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.encomendas.EncomendaIn;
import pt.ipg.mcm.xmodel.encomendas.ProdutoAEncomendar;

import java.util.ArrayList;
import java.util.List;

public class EncomendaInFromRest extends AbstractConversor<EncomendaInRest,EncomendaIn>{
    public EncomendaInFromRest(EncomendaInRest source) {
        super(source);
    }

    @Override
    public EncomendaIn converted() {
        EncomendaIn encomendaIn = new EncomendaIn();

        encomendaIn.setDataEntrega(source.getDataEntrega());

        List<ProdutoAEncomendar> produtoAEncomendarList = new ArrayList<>();
        for(ProdutoEncomendadoRest produtoEncomendadoRest:source.getProdutoEncomendadoRestList()){
            ProdutoAEncomendar produtoAEncomendar = new ProdutoAEncomendar();
            produtoAEncomendar.setIdProduto(produtoEncomendadoRest.getIdProduto());
            produtoAEncomendar.setQuantidade(produtoAEncomendar.getQuantidade());
            produtoAEncomendarList.add(produtoAEncomendar);
        }
        encomendaIn.setProdutoAEncomendarList(produtoAEncomendarList);

        return encomendaIn;
    }
}
