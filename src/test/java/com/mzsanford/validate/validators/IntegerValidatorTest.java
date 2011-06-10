package com.mzsanford.validate.validators;

import junit.framework.TestCase;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;
import com.mzsanford.validate.exceptions.ValidationException.NumberMustEqualException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooHighException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooLowException;

public class IntegerValidatorTest extends TestCase {

	public void testIntegerValidator() {
		IntegerValidator validator = new IntegerValidator(123);
		assertTrue("Field value should be set correctly", 123 == validator.getField());
	}

	public void testMax() throws ValidationException {
		IntegerValidator validator = new IntegerValidator(123);

		assertNotNull("Invalid max should be ignored", validator.max(null));
		assertNotNull(validator.max(124));
		assertNotNull(validator.max(123));
		try {
			validator.max(122);
			fail("Value over max should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooHighException", (e instanceof NumberTooHighException));
		}
	}
	
	public void testMin() throws ValidationException {
		IntegerValidator validator = new IntegerValidator(123);

		assertNotNull("Invalid min should be ignored", validator.min(null));
		assertNotNull(validator.min(122));
		assertNotNull(validator.min(123));
		try {
			validator.min(124);
			fail("Value under min should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooLowException", (e instanceof NumberTooLowException));
		}
	}
	
	public void testMustEqual() throws ValidationException {
		IntegerValidator validator = new IntegerValidator(123);

		assertNotNull("Invalid value should be ignored", validator.mustEqual(null));
		assertNotNull(validator.mustEqual(123));
		try {
			validator.mustEqual(124);
			fail("Value not equal should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberMustEqualException", (e instanceof NumberMustEqualException));
		}
	}

	public void testIsNotNull() throws ValidationException {
		IntegerValidator notNullValue = new IntegerValidator(1);
		IntegerValidator nullValue = new IntegerValidator(null);
		
		assertNotNull(notNullValue.isNotNull());
		try {
			nullValue.isNotNull();
			fail("Null value should fail isNotNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NullNotAllowedException", (e instanceof NullNotAllowedException));
		}
	}

	public void testMustBeNull() throws ValidationException {
		IntegerValidator notNullValue = new IntegerValidator(1);
		IntegerValidator nullValue = new IntegerValidator(null);
		
		assertNotNull(nullValue.mustBeNull());
		try {
			notNullValue.mustBeNull();
			fail("Null value should fail mustBeNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be MustBeNullException", (e instanceof MustBeNullException));
		}
	}

}
