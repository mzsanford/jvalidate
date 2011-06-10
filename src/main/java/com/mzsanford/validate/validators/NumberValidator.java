package com.mzsanford.validate.validators;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.NumberMustEqualException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooHighException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooLowException;

public abstract class NumberValidator<T extends Number> extends AbstractValidator<T> {

	public NumberValidator(T field) {
		super(field);
	}
	
	/**
	 * Used to add type safe comparison in NumberValidator checks
	 * 
	 * @param value to compare to
	 * @return normal Comparable response
	 */
	protected abstract int compareTo(T value);

	public NumberValidator<T> max(T max) throws ValidationException {
		if (this.field != null && max != null && this.compareTo(max) > 0) {
			throw new NumberTooHighException();
		}
		return this;
	}
	
	public NumberValidator<T> min(T min) throws ValidationException {
		if (this.field != null && min != null && this.compareTo(min) < 0) {
			throw new NumberTooLowException();
		}
		return this;
	}
	
	public NumberValidator<T> mustEqual(T num) throws ValidationException {
		if (this.field != null && num != null && this.compareTo(num) != 0) {
			throw new NumberMustEqualException();
		}
		return this;
	}
	

}
