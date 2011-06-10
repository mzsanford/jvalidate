package com.mzsanford.validate.validators;

import junit.framework.TestCase;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;
import com.mzsanford.validate.exceptions.ValidationException.NumberMustEqualException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooHighException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooLowException;

public class DoubleValidatorTest extends TestCase {

	public void testDoubleValidator() {
		DoubleValidator validator = new DoubleValidator(42.0d);
		assertTrue("Field value should be set correctly", 42.0d == validator.getField());
	}

	public void testMax() throws ValidationException {
		DoubleValidator validator = new DoubleValidator(42.0d);

		assertNotNull("Invalid max should be ignored", validator.max(null));
		assertNotNull(validator.max(43.0d));
		assertNotNull(validator.max(42.1d));
		assertNotNull(validator.max(42.0d));
		try {
			validator.max(41.99d);
			fail("Value over max should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooHighException", (e instanceof NumberTooHighException));
		}
	}
	
	public void testMin() throws ValidationException {
		DoubleValidator validator = new DoubleValidator(42.0d);

		assertNotNull("Invalid min should be ignored", validator.min(null));
		assertNotNull(validator.min(41.0d));
		assertNotNull(validator.min(41.99d));
		assertNotNull(validator.min(42.0d));
		try {
			validator.min(42.1d);
			fail("Value under min should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooLowException", (e instanceof NumberTooLowException));
		}
	}
	
	public void testMustEqual() throws ValidationException {
		DoubleValidator validator = new DoubleValidator(42.0d);

		assertNotNull("Invalid value should be ignored", validator.mustEqual(null));
		assertNotNull(validator.mustEqual(42.0d));
		try {
			validator.mustEqual(42.1d);
			fail("Value not equal should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberMustEqualException", (e instanceof NumberMustEqualException));
		}
	}

	public void testIsNotNull() throws ValidationException {
		DoubleValidator notNullValue = new DoubleValidator(42.0d);
		DoubleValidator nullValue = new DoubleValidator(null);
		
		assertNotNull(notNullValue.isNotNull());
		try {
			nullValue.isNotNull();
			fail("Null value should fail isNotNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NullNotAllowedException", (e instanceof NullNotAllowedException));
		}
	}

	public void testMustBeNull() throws ValidationException {
		DoubleValidator notNullValue = new DoubleValidator(42.0d);
		DoubleValidator nullValue = new DoubleValidator(null);
		
		assertNotNull(nullValue.mustBeNull());
		try {
			notNullValue.mustBeNull();
			fail("Null value should fail mustBeNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be MustBeNullException", (e instanceof MustBeNullException));
		}
	}

}
