package com.mzsanford.validate.validators;

import junit.framework.TestCase;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;
import com.mzsanford.validate.exceptions.ValidationException.NumberMustEqualException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooHighException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooLowException;

public class FloatValidatorTest extends TestCase {

	public void testFloatValidator() {
		FloatValidator validator = new FloatValidator(42.0f);
		assertTrue("Field value should be set correctly", 42.0f == validator.getField());
	}

	public void testMax() throws ValidationException {
		FloatValidator validator = new FloatValidator(42.0f);

		assertNotNull("Invalid max should be ignored", validator.max(null));
		assertNotNull(validator.max(43.0f));
		assertNotNull(validator.max(42.1f));
		assertNotNull(validator.max(42.0f));
		try {
			validator.max(41.99f);
			fail("Value over max should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooHighException", (e instanceof NumberTooHighException));
		}
	}
	
	public void testMin() throws ValidationException {
		FloatValidator validator = new FloatValidator(42.0f);

		assertNotNull("Invalid min should be ignored", validator.min(null));
		assertNotNull(validator.min(41.0f));
		assertNotNull(validator.min(41.99f));
		assertNotNull(validator.min(42.0f));
		try {
			validator.min(42.1f);
			fail("Value under min should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooLowException", (e instanceof NumberTooLowException));
		}
	}
	
	public void testMustEqual() throws ValidationException {
		FloatValidator validator = new FloatValidator(42.0f);

		assertNotNull("Invalid value should be ignored", validator.mustEqual(null));
		assertNotNull(validator.mustEqual(42.0f));
		try {
			validator.mustEqual(42.1f);
			fail("Value not equal should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberMustEqualException", (e instanceof NumberMustEqualException));
		}
	}

	public void testIsNotNull() throws ValidationException {
		FloatValidator notNullValue = new FloatValidator(42.0f);
		FloatValidator nullValue = new FloatValidator(null);
		
		assertNotNull(notNullValue.isNotNull());
		try {
			nullValue.isNotNull();
			fail("Null value should fail isNotNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NullNotAllowedException", (e instanceof NullNotAllowedException));
		}
	}

	public void testMustBeNull() throws ValidationException {
		FloatValidator notNullValue = new FloatValidator(42.0f);
		FloatValidator nullValue = new FloatValidator(null);
		
		assertNotNull(nullValue.mustBeNull());
		try {
			notNullValue.mustBeNull();
			fail("Null value should fail mustBeNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be MustBeNullException", (e instanceof MustBeNullException));
		}
	}

}
