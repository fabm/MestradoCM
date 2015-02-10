package pt.ipg.mcm.xmodel;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.logging.Logger;

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
    private Retorno retorno;

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

    public Retorno getRetorno() {
        return retorno;
    }

    public void setRetorno(Retorno retorno) {
        this.retorno = retorno;
    }
}
