package com.mzsanford.validate.validators;

public class IntegerValidator extends NumberValidator<Integer> {
	public IntegerValidator(Integer field) {
		super(field);
	}

	protected int compareTo(Integer value) {
		return field.compareTo(value);
	}
}
