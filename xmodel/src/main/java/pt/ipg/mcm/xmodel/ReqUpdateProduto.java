package pt.ipg.mcm.xmodel;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Req-update-produto")
public class ReqUpdateProduto {

    @XmlElement(required = true)
    private  Integer idProduto;

    @XmlElement(required = true)
    private String nome;

    @XmlElement(name = "preco-unitario", required = true)
    private BigDecimal precoUnitario;

    @XmlElement(required = true)
    private long categoria;

    @XmlElement(required = true)
    protected byte[] foto;


    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public long getCategoria() {
        return categoria;
    }

    public void setCategoria(long categoria) {
        this.categoria = categoria;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
