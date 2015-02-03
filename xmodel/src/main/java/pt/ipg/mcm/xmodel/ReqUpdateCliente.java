package pt.ipg.mcm.xmodel;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Req-update-cliente")
public class ReqUpdateCliente {
    @NotNull
    @XmlElement(required = true)
    private long idCliente;

    @NotNull
    @XmlElement(required = true)
    private long contribuinte;

    @NotNull
    @XmlElement(required = true)
    private String nome;


    @NotNull
    @XmlElement(required = true)
    private String morada;

    @NotNull
    @XmlElement(name = "data-nascimento", required = true)
    @XmlSchemaType(name = "date")
    private Date dataNascimento;

    @NotNull
    @XmlElement(required = true)
    private String email;

    @NotNull
    @XmlElement(required = true)
    private long contacto;

    @NotNull
    @XmlElement(required = true)
    private int localidade;

    @NotNull
    @XmlElement(required = true)
    private String pwd;

    @NotNull
    @XmlElement(required = true)
    private String nPorta;

    @NotNull
    @XmlElement(required = true)
    private long idUtilizador;


    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getContribuinte() {
        return contribuinte;
    }

    public void setContribuinte(long contribuinte) {
        this.contribuinte = contribuinte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContacto() {
        return contacto;
    }

    public void setContacto(long contacto) {
        this.contacto = contacto;
    }

    public int getLocalidade() {
        return localidade;
    }

    public void setLocalidade(int localidade) {
        this.localidade = localidade;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getnPorta() {
        return nPorta;
    }

    public void setnPorta(String nPorta) {
        this.nPorta = nPorta;
    }

    public long getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(long idUtilizador) {
        this.idUtilizador = idUtilizador;
    }
}
