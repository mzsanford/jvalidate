package com.mzsanford.validate.validators;

import java.util.regex.Pattern;

import junit.framework.TestCase;

import com.mzsanford.validate.exceptions.ValidationException;
import com.mzsanford.validate.exceptions.ValidationException.MustBeNullException;
import com.mzsanford.validate.exceptions.ValidationException.NullNotAllowedException;
import com.mzsanford.validate.exceptions.ValidationException.StringEmptyException;
import com.mzsanford.validate.exceptions.ValidationException.StringFormatException;
import com.mzsanford.validate.exceptions.ValidationException.StringTooLongException;
import com.mzsanford.validate.exceptions.ValidationException.StringTooShortException;

public class StringValidatorTest extends TestCase {
	private StringValidator validator;

	public void testStringValidator() {
		validator = new StringValidator("test");
		assertEquals("Field value should be set correctly", "test", validator.getField());
	}

	public void testMaxLength() throws ValidationException {
		validator = new StringValidator("test");
		
		assertNotNull("Invalid length should be ignored", validator.maxLength(-1));
		assertNotNull(validator.maxLength(5));
		assertNotNull(validator.maxLength(4));
		try {
			validator.maxLength(3);
			fail("String longer than maxLength should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be StringTooLongException", (e instanceof StringTooLongException));
		}
	}

	public void testMinLength() throws ValidationException {
		validator = new StringValidator("test");
		
		assertNotNull("Invalid length should be ignored", validator.minLength(-1));
		assertNotNull(validator.minLength(3));
		assertNotNull(validator.minLength(4));
		try {
			validator.minLength(5);
			fail("String longer than minLength should fail validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be StringTooShortException", (e instanceof StringTooShortException));
		}
	}

	public void testIsNotEmpty() throws ValidationException {
		StringValidator notEmpty = new StringValidator("test");
		StringValidator empty = new StringValidator("");
		
		assertNotNull(notEmpty.isNotEmpty());
		try {
			empty.isNotEmpty();
			fail("Empty String should fail isNotEmpty validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be StringEmptyException", (e instanceof StringEmptyException));
		}
	}

	public void testIsNotNull() throws ValidationException {
		StringValidator notNullString = new StringValidator("");
		StringValidator nullString = new StringValidator(null);
		
		assertNotNull(notNullString.isNotNull());
		try {
			nullString.isNotNull();
			fail("Null String should fail isNotNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be NullNotAllowedException", (e instanceof NullNotAllowedException));
		}
	}

	public void testMustBeNull() throws ValidationException {
		StringValidator notNullString = new StringValidator("");
		StringValidator nullString = new StringValidator(null);
		
		assertNotNull(nullString.mustBeNull());
		try {
			notNullString.mustBeNull();
			fail("Null String should fail mustBeNull validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be MustBeNullException", (e instanceof MustBeNullException));
		}
	}

	public void testMatches() throws ValidationException {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");

		assertNotNull("Should ignore invalid pattern", new StringValidator("test").matches(null));
		assertNotNull(new StringValidator("matches").matches(pattern));
		try {
			new StringValidator("does not match").matches(pattern);
			fail("Unmatching String should fail matches validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be StirngFormatException", (e instanceof StringFormatException));
		}
	}

	public void testDoesNotMatch() throws ValidationException {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");

		assertNotNull("Should ignore invalid pattern", new StringValidator("test").doesNotMatch(null));
		assertNotNull(new StringValidator("no match").doesNotMatch(pattern));
		try {
			new StringValidator("match_unexpected").doesNotMatch(pattern);
			fail("Matching String should fail doesNotMatch validation");
		} catch (ValidationException e) {
			assertTrue("Validation error should be StirngFormatException", (e instanceof StringFormatException));
		}
	}


}
