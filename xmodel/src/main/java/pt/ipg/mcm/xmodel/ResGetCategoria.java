package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 09-02-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Res-get-categoria")
public class ResGetCategoria {

    @NotNull
    @XmlElement(required = true)
    private String nome;

    @NotNull
    @XmlElement(required = true)
    private String descricao;

    @XmlElement(required = false)
    private RetornoSoap retorno;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }
}
