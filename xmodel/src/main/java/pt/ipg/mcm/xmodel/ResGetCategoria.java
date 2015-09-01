package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

public class ResGetCategoria {
    private RetornoSoap retorno;
    private Long versaoMax;

    public ResGetCategoria (RetornoSoap retorno){this.retorno = retorno;}

    private List<CategoriaXml> resGetCategorias ;

    public ResGetCategoria(){

    }
    public ResGetCategoria(MestradoException e){
        retorno = new RetornoSoap(e);
    }

    public Long getVersaoMax() {
        return versaoMax;
    }

    public void setVersaoMax(Long maxVersion) {
        this.versaoMax = maxVersion;
    }

    public List<CategoriaXml> getResGetCategorias() {
        return resGetCategorias;
    }

    public void setResGetCategorias(List<CategoriaXml> categoriaXmls) {
        this.resGetCategorias= categoriaXmls;
    }

    public RetornoSoap getRetorno() {
        return retorno;
    }

    public void setRetorno(RetornoSoap retorno) {
        this.retorno = retorno;
    }



//
//    @NotNull
//    @XmlElement(required = true)
//    private String nome;
//
//    @NotNull
//    @XmlElement(required = true)
//    private String descricao;
//
//    @XmlElement(required = false)
//    private RetornoSoap retorno;
//
//    public RetornoSoap getRetorno() {
//        return retorno;
//    }
//
//    public void setRetorno(RetornoSoap retorno) {
//        this.retorno = retorno;
//    }
}
