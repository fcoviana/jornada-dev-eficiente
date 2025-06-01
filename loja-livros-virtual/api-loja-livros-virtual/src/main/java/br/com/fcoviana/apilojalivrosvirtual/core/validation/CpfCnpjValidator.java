package br.com.fcoviana.apilojalivrosvirtual.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {
    private final CPFValidator cpfValidator = new CPFValidator();
    private final CNPJValidator cnpjValidator = new CNPJValidator();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        String documento = value.replaceAll("\\D", "");

        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, context) || cnpjValidator.isValid(documento, context);
    }
}
