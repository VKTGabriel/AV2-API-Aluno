package br.dev.vktgabriel.av2.API_Aluno.validation;

import br.dev.vktgabriel.av2.API_Aluno.validation.constrain.Booleano;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BooleanoValidation implements ConstraintValidator<Booleano, String> {
    @Override
    public void initialize(Booleano constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {return true;}
        String minusculo = s.toLowerCase();
        return (minusculo.equals("true") || minusculo.equals("false"));
    }
}
