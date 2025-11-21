package br.dev.vktgabriel.av2.API_Aluno.validation;

import br.dev.vktgabriel.av2.API_Aluno.validation.constrain.NumericoPositivo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumericoPositivoValidation implements ConstraintValidator<NumericoPositivo, String> {
    @Override
    public void initialize(NumericoPositivo constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {return true;}
        String soNumero = s.replaceAll("[^0-9]", "").trim();
        if (s.length() != soNumero.length()){return false;}
        return Integer.parseInt(soNumero) > 0;
    }
}
