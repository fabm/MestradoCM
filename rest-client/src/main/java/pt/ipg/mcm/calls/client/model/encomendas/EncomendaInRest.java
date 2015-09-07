package pt.ipg.mcm.calls.client.model.encomendas;

import java.util.Date;
import java.util.List;

public class EncomendaInRest {
    private Date dataEntrega;
    private List<ProdutoEncomendadoRest> produtoEncomendadoRestList;

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<ProdutoEncomendadoRest> getProdutoEncomendadoRestList() {
        return produtoEncomendadoRestList;
    }

    public void setProdutoEncomendadoRestList(List<ProdutoEncomendadoRest> produtoEncomendadoRestList) {
        this.produtoEncomendadoRestList = produtoEncomendadoRestList;
    }
}
