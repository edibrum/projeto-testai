package br.com.exemplo.testai.config.enums.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidation {
    String message() default "Enum inválido";

    boolean isRequired();

    Class<?>[] groups() default {};

    String[] values();

    Class<? extends Payload>[] payload() default {};
}
