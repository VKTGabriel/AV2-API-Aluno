package br.dev.vktgabriel.av2.API_Aluno.validation.constrain;

import br.dev.vktgabriel.av2.API_Aluno.validation.DataPassadaValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DataPassadaValidation.class)
public @interface DataPassada {

    String message() default "Data inválida: Insira uma data no padrão [dd/mm/aaaa] anterior ao dia de hoje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
