package br.com.games101.sheet.validation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CenarioExisteValidator.class})
public @interface CenarioExistAnnotation {

    String message() default "Cenario não foi localizado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
