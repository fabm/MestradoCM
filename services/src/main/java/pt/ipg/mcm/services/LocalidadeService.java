package pt.ipg.mcm.services;

import pt.ipg.mcm.controller.LocalidadeDao;
import pt.ipg.mcm.entities.LocalidadeEntity;
import pt.ipg.mcm.errors.MestradoException;
import pt.ipg.mcm.xmodel.*;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(serviceName = "Localidade", portName = "LocalidadePort")
public class LocalidadeService {


    @EJB
    private LocalidadeDao localidadeDao;


    @WebResult(name = "response")
    @WebMethod(operationName = "get-all-localidades")
    @ResponseWrapper(localName = "get-all-localidades-response")
    public ResGetAllLocalidades getAllLocalidades(){
        ResGetAllLocalidades resGetAllLocalidades = new ResGetAllLocalidades();
        List<LocalidadeEntity> allLocalidades;

        try {
            allLocalidades = localidadeDao.getAll();
            List<Localidade> resCategorias = resGetAllLocalidades.getLocalidades();

            for (LocalidadeEntity localidadeEntity : allLocalidades) {
                Localidade localidade = new Localidade();
                localidade.setId(localidadeEntity.getIdLocalidade());
                localidade.setLocalidade(localidadeEntity.getLocalidade());
                resCategorias.add(localidade);
            }
            return resGetAllLocalidades;
        } catch (MestradoException e) {
            resGetAllLocalidades.setRetorno(new Retorno(e));
            return resGetAllLocalidades;
        }
    }


}
