package br.dev.vktgabriel.av2.API_Aluno.validation.constrain;

import br.dev.vktgabriel.av2.API_Aluno.validation.BooleanoValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BooleanoValidation.class)
public @interface Booleano {

    String message() default "Definição inválida: Demarque o campo com indicadores booleanos. [true || false]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
