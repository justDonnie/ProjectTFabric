package tisobay.projecttiso.validation.valid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tisobay.projecttiso.validation.PasswordValid;

public class PasswordValidation implements ConstraintValidator<PasswordValid,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() >= 4 && s.matches(".*[a-zA-Z].*\\d.*|.*\\d.*[a-zA-Z].*");
    }
}