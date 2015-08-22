package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by rafa on 22/08/2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriaXml {


    @XmlTransient
    private long sync;

    @XmlElement(required = true)
    private String nome;

    @XmlElement(required = false)
    private String descricao;

    public long getSync() {
        return sync;
    }

    public void setSync(long sync) {
        this.sync = sync;
    }

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
}
