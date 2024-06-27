package org.brito.desafiojersey.annotations.validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.brito.desafiojersey.annotations.MinimoSeisCaracteres;

public class MinimoSeisCaracteresValidator implements ConstraintValidator<MinimoSeisCaracteres, String> {

    @Override
    public void initialize(MinimoSeisCaracteres constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.length() >= 6;
    }
}
