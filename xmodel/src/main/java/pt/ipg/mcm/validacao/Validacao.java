package pt.ipg.mcm.validacao;

import pt.ipg.mcm.errors.Erro;
import pt.ipg.mcm.errors.MestradoException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

public class Validacao {
  private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
  private Validator validator = validatorFactory.getValidator();
  private static Validacao validacao = new Validacao();

  public static Validacao getInstance() {
    if (validacao == null) {
      validacao = new Validacao();
    }
    return validacao;
  }

  private static String getAlias(String name, Map<String, String> aliasMap) {
    if (aliasMap == null) {
      return name;
    }
    String alias = aliasMap.get(name);
    if (alias == null) {
      return name;
    }
    return alias;
  }

  public void valida(Object object) throws MestradoException {
    valida(object,null);
  }

  public void valida(Object object, Map<String, String> aliasMap) throws MestradoException {
    Set<ConstraintViolation<Object>> cvs = validator.validate(object);
    if (cvs.size() > 0) {
      ConstraintViolation<Object> cv = cvs.iterator().next();
      Annotation ann = cv.getConstraintDescriptor().getAnnotation();

      if (ann.annotationType() == NotEmpty.class || ann.annotationType() == NotNull.class) {
        Path path = cv.getPropertyPath();
        String name = path.iterator().next().getName();
        name = getAlias(name, aliasMap);
        throw new MestradoException(Erro.CAMPO_VAZIO, name);
      }else if(ann.annotationType() == Email.class){
        Path path = cv.getPropertyPath();
        String name = path.iterator().next().getName();
        name = getAlias(name, aliasMap);
        throw new MestradoException(Erro.FORMATO_INVALIDO, name);
      }
    }
  }
}
