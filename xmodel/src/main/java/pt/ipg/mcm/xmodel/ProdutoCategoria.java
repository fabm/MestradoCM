
package pt.ipg.mcm.xmodel;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Produto-categoria")
public class ProdutoCategoria {


    private Long idCategoria;
    @XmlElement(name = "id-produto",required = true)
    private Long idproduto;
    @XmlElement(name = "nome-produto", required = true)
    private String nomeProduto;
    @XmlElement(name = "nome-categoria", required = true)
    private String nomeCategoria;
    @XmlElement(name = "preco-actual", required = true)
    private BigDecimal precoActual;
    @XmlElement(required = true)
    private String descricao;
    @XmlElement(required = true)
    private byte[] foto;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdproduto() {   return idproduto;  }

    public void setIdproduto(Long idproduto) {    this.idproduto = idproduto;   }
    
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String value) {
        this.nomeProduto = value;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String value) {
        this.nomeCategoria = value;
    }

    public BigDecimal getPrecoActual() {
        return precoActual;
    }

    public void setPrecoActual(BigDecimal value) {
        this.precoActual = value;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String value) {
        this.descricao = value;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] value) {
        this.foto = value;
    }

}
