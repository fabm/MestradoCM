package pt.ipg.mcm.xmodel;

import pt.ipg.mcm.errors.MestradoException;

public class ResCreationUserClient extends Retorno{
  public ResCreationUserClient(MestradoException e) {
    super(e);
  }

  public ResCreationUserClient() {
    super(1,"Registo feito com sucesso");
  }
}
