package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.validacao.NotEmpty;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Req-add-utilizador")
public class ReqAddUtilizador {


    @NotEmpty
    @XmlElement(required = true)
    private String login;

    @NotEmpty
    @XmlElement(required = true)
    private String password;

    @NotEmpty
    @XmlElement(required = true)
    private String nome;

    @XmlTransient
    private Long id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
