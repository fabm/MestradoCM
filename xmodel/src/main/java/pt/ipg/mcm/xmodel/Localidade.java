package pt.ipg.mcm.xmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 29-01-2015.
 */
@XmlType(name = "Localidade")
@XmlAccessorType(XmlAccessType.FIELD)
public class Localidade {

    @XmlElement(name = "id")
    private long id ;

    @XmlElement(name = "localidade")
    private String localidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}
