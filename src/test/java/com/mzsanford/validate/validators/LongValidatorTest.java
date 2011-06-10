package com.mzsanford.validate.validators;

import junit.framework.TestCase;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;
import com.mzsanford.validate.exceptions.ValidationException.NumberMustEqualException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooHighException;
import com.mzsanford.validate.exceptions.ValidationException.NumberTooLowException;

public class LongValidatorTest extends TestCase {

	public void testLongValidator() {
		LongValidator validator = new LongValidator(123L);
		assertTrue("Field value should be set correctly", 123L == validator.getField());
	}

	public void testMax() throws ValidationException {
		LongValidator validator = new LongValidator(123L);

		assertNotNull("Invalid max should be ignored", validator.max(null));
		assertNotNull(validator.max(124L));
		assertNotNull(validator.max(123L));
		try {
			validator.max(122L);
			fail("Value over max should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooHighException", (e instanceof NumberTooHighException));
		}
	}
	
	public void testMin() throws ValidationException {
		LongValidator validator = new LongValidator(123L);

		assertNotNull("Invalid min should be ignored", validator.min(null));
		assertNotNull(validator.min(122L));
		assertNotNull(validator.min(123L));
		try {
			validator.min(124L);
			fail("Value under min should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberTooLowException", (e instanceof NumberTooLowException));
		}
	}
	
	public void testMustEqual() throws ValidationException {
		LongValidator validator = new LongValidator(123L);

		assertNotNull("Invalid value should be ignored", validator.mustEqual(null));
		assertNotNull(validator.mustEqual(123L));
		try {
			validator.mustEqual(124L);
			fail("Value not equal should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NumberMustEqualException", (e instanceof NumberMustEqualException));
		}
	}

	public void testIsNotNull() throws ValidationException {
		LongValidator notNullValue = new LongValidator(1L);
		LongValidator nullValue = new LongValidator(null);
		
		assertNotNull(notNullValue.isNotNull());
		try {
			nullValue.isNotNull();
			fail("Null value should fail isNotNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NullNotAllowedException", (e instanceof NullNotAllowedException));
		}
	}

	public void testMustBeNull() throws ValidationException {
		LongValidator notNullValue = new LongValidator(1L);
		LongValidator nullValue = new LongValidator(null);
		
		assertNotNull(nullValue.mustBeNull());
		try {
			notNullValue.mustBeNull();
			fail("Null value should fail mustBeNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be MustBeNullException", (e instanceof MustBeNullException));
		}
	}

}
