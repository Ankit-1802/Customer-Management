package com.capg.annotations;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<isValidDob, LocalDate>
{

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		LocalDate now = LocalDate.now();
		if(now.compareTo(value)>0)return true;
		return false;
	}

}