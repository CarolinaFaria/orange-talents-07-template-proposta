package br.com.zup.edu.desafioproposta.validacao;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR) // utiliza a operação de OR no lugar da AND para as anotações de CPF e CNPJ
@ReportAsSingleViolation //
@Constraint(validatedBy = { }) // Não precisa de validator
@Documented
public @interface CPForCNPJ {

    String message() default "Documento está no formato incorreto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
