package com.mzsanford.validate.validators;

import java.util.regex.Pattern;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.StringEmptyException;
import com.mzsanford.validate.exceptions.ValidationException.StringFormatException;
import com.mzsanford.validate.exceptions.ValidationException.StringTooLongException;
import com.mzsanford.validate.exceptions.ValidationException.StringTooShortException;

public class StringValidator extends AbstractValidator<String> {

	public StringValidator(String obj) {
		super(obj);
	}
	
	public StringValidator maxLength(int length) throws ValidationException {
		if (this.field != null && length >= 0 && this.field.length() > length) {
			throw new StringTooLongException();
		}
		return this;
	}
	
	public StringValidator minLength(int length) throws ValidationException {
		if (this.field != null && length >= 0 && this.field.length() < length) {
			throw new StringTooShortException();
		}
		return this;
	}
	
	public StringValidator isNotEmpty() throws ValidationException {
		if (this.field != null && this.field.isEmpty()) {
			throw new StringEmptyException();
		}
		return this;
	}
	
	public StringValidator matches(Pattern pattern) throws ValidationException {
		if (this.field != null && pattern != null && !pattern.matcher(field).matches()) {
			throw new StringFormatException();
		}
		return this;
	}
	
	public StringValidator doesNotMatch(Pattern pattern) throws ValidationException {
		if (this.field != null && pattern != null && pattern.matcher(field).matches()) {
			throw new StringFormatException();
		}
		return this;
	}
}
