package br.com.exemplo.testai.config.enums.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValidation, String> {

    private String[] values;
    private String message;
    private boolean isRequired;

    @Override
    public void initialize(EnumValidation c) {
        ConstraintValidator.super.initialize(c);
        this.isRequired = c.isRequired();
        this.values = c.values();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext ctx) {
        if (!isRequired) {
            if (s == null || s.equals(""))
                return true;
        }

        return s != null && Arrays.asList(values).contains(s);
    }

}
