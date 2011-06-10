package com.mzsanford.validate.validators;

public class FloatValidator extends NumberValidator<Float> {
	public FloatValidator(Float field) {
		super(field);
	}

	protected int compareTo(Float value) {
		return field.compareTo(value);
	}
}
