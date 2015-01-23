package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by BrrF on 22-01-2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Req-get-padeiro")
public class ReqGetPadeiro {
    @NotNull
    @XmlElement(required = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
