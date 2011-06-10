package com.mzsanford.validate.validators;

public class LongValidator extends NumberValidator<Long> {
	public LongValidator(Long field) {
		super(field);
	}

	protected int compareTo(Long value) {
		return field.compareTo(value);
	}
}
