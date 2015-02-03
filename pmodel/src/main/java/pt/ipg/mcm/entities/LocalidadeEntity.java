package pt.ipg.mcm.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by BrrF on 01-02-2015.
 */
@Entity
@Table(name = "LOCALIDADE", schema = "BDA_1010136", catalog = "")
public class LocalidadeEntity {
    private String codigoPostal;
    private String localidade;
    private long idLocalidade;

    @Basic
    @Column(name = "CODIGO_POSTAL")
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Basic
    @Column(name = "LOCALIDADE")
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Id
    @Column(name = "ID_LOCALIDADE")
    public long getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(long idLocalidade) {
        this.idLocalidade = idLocalidade;
    }


}
