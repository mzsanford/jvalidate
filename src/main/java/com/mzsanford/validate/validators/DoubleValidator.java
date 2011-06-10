package com.mzsanford.validate.validators;

public class DoubleValidator extends NumberValidator<Double> {
	public DoubleValidator(Double field) {
		super(field);
	}

	protected int compareTo(Double value) {
		return field.compareTo(value);
	}
}
