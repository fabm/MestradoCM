package pt.ipg.mcm.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

  @Override
  public void initialize(NotEmpty notEmpty) {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return false;
    }
    if (s.isEmpty()) {
      return false;
    }
    return true;
  }
}
