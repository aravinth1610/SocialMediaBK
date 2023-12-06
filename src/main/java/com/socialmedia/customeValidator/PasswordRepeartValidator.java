package com.socialmedia.customeValidator;

import java.lang.reflect.Field;

import com.socialmedia.annotations.PasswordRepeart;
import com.socialmedia.modal.SignUpModal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordRepeartValidator implements ConstraintValidator<PasswordRepeart, SignUpModal> {

	private String passwordField;
	private String confirmPasswordField;
	private String message;

	@Override
	public void initialize(PasswordRepeart constraintAnnotation) {
		passwordField = constraintAnnotation.passwordField();
		confirmPasswordField = constraintAnnotation.confirmPasswordField();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(SignUpModal value, ConstraintValidatorContext context) {

		boolean isValid = true;
		isValid = isPasswordConfirmPasswordMatched(value);

//		if(!isValid)
//		{
//			System.out.println("---");
//			 context.buildConstraintViolationWithTemplate(message)
//             .addPropertyNode(confirmPasswordField)
//             .addConstraintViolation()
//             .disableDefaultConstraintViolation();
//		}

		return isValid;
	}

	private Boolean isPasswordConfirmPasswordMatched(SignUpModal siginValue) {
		return (siginValue.getPassword() != null && siginValue.getPasswordRepeat() != null
				&& siginValue.getPassword().equals(siginValue.getPasswordRepeat()));
	}

}
