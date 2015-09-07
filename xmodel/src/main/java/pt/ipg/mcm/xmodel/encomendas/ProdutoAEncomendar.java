package pt.ipg.mcm.xmodel.encomendas;


import pt.ipg.mcm.calls.client.model.encomendas.ProdutoEncomendadoRest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProdutoAEncomendar {
    private int quantidade;
    private long idProduto;
    @XmlTransient
    private long serverId;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }

    public ProdutoEncomendadoRest convert() {
        ProdutoEncomendadoRest produtoEncomendadoRest = new ProdutoEncomendadoRest();

        produtoEncomendadoRest.setIdProduto(idProduto);
        produtoEncomendadoRest.setQuandidade(quantidade);

        return produtoEncomendadoRest;
    }

    public void convert(ProdutoEncomendadoRest produtoEncomendadoRest) {
        quantidade = produtoEncomendadoRest.getQuandidade();
        idProduto = produtoEncomendadoRest.getIdProduto();
    }
}
