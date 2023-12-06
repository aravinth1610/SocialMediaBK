package com.socialmedia.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.socialmedia.customeValidator.PasswordRepeartValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRepeartValidator.class)
public @interface PasswordRepeart {

	String message() default "Password missmatch Error";

	String passwordField();

	String confirmPasswordField();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
