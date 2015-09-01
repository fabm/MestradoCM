package pt.ipg.mcm.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidator.class})
@Documented
public @interface Email {

  String message() default "{pt.ipg.mcm.Email.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}