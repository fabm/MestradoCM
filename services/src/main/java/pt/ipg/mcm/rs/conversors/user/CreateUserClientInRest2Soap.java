package pt.ipg.mcm.rs.conversors.user;

import pt.ipg.mcm.calls.client.DateHelper;
import pt.ipg.mcm.calls.client.model.user.CreateUserClientRestIn;
import pt.ipg.mcm.rs.conversors.AbstractConversor;
import pt.ipg.mcm.xmodel.UserClienteCreationRequest;

import java.text.ParseException;

public class CreateUserClientInRest2Soap extends AbstractConversor<CreateUserClientRestIn, UserClienteCreationRequest> {

  public CreateUserClientInRest2Soap(CreateUserClientRestIn source) {
    super(source);
  }

  @Override
  public UserClienteCreationRequest converted() {
    try {
      UserClienteCreationRequest userClienteCreationRequest = new UserClienteCreationRequest();
      userClienteCreationRequest.setContribuinte(source.getContribuinte());
      userClienteCreationRequest.setNome(source.getNome());
      userClienteCreationRequest.setMorada(source.getMorada());
      userClienteCreationRequest.setPorta(source.getPorta());
      userClienteCreationRequest.setDataNascimento(new DateHelper(DateHelper.Format.COMPACT).toDate(source.getDataNascimento()));
      userClienteCreationRequest.setEmail(source.getEmail());
      userClienteCreationRequest.setContacto(source.getContacto());
      userClienteCreationRequest.setLocalidade(source.getLocalidade());
      userClienteCreationRequest.setLogin(source.getLogin());
      userClienteCreationRequest.setPassword(source.getPassword());
      return userClienteCreationRequest;
    } catch (ParseException e) {
      throw new IllegalStateException(e);
    }
  }
}
