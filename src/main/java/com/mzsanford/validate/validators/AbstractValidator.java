package com.mzsanford.validate.validators;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;

public abstract class AbstractValidator<T> {
	protected T field;

	public AbstractValidator(T field) {
		this.field = field;
	}
	
	T getField() {
		return this.field;
	}
	
	public AbstractValidator<T> isNotNull() throws ValidationException {
		if (null == this.field) {
			throw new NullNotAllowedException();
		}
		return this;
	}
	
	public AbstractValidator<T> mustBeNull() throws ValidationException {
		if (null != this.field) {
			throw new MustBeNullException();
		}
		return this;
	}
}
