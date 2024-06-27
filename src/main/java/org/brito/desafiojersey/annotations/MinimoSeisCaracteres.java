package org.brito.desafiojersey.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.brito.desafiojersey.annotations.validator.MinimoSeisCaracteresValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinimoSeisCaracteresValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinimoSeisCaracteres {
    String message() default "{usuario.password.minimo.seis.caracteres}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
