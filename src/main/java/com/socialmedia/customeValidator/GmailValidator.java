package com.socialmedia.customeValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.socialmedia.annotations.ValidGmail;
import com.socialmedia.common.SocialMediaConstants;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<ValidGmail, String> {

//	@Override
//	public void initialize(ValidEmail constraintAnnotation) {
//		ConstraintValidator.super.initialize(constraintAnnotation);
//	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile(SocialMediaConstants.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		System.out.println(matcher);
		return matcher.matches() && (4 <= email.length() && email.length() <= 64);
	}

}
