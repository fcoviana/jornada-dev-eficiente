package br.com.fcoviana.apilojalivrosvirtual.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {
    String message() default "Documento inválido. Deve ser um CPF ou CNPJ válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}