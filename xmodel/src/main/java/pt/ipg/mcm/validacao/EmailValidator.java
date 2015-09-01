package pt.ipg.mcm.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private static Pattern PATTERN;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static {
        PATTERN = Pattern.compile(EMAIL_PATTERN);
    }

    public void initialize(Email email) {

    }

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}