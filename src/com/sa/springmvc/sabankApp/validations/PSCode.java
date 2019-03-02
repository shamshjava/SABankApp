package com.sa.springmvc.sabankApp.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=PSCodeConstraintValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface PSCode {

	public String value() default  "PS";
	public String message() default  "PS Code should start with PS";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload> [] payload() default {};
}
