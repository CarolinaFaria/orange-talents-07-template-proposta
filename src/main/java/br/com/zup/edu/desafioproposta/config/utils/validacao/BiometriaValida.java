package br.com.zup.edu.desafioproposta.config.utils.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {BiometriaValidator.class})
@Documented
public @interface BiometriaValida {

    String message() default "Biometria está incorreta";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
