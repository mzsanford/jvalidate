package com.mzsanford.validate.exceptions;

public class ValidationException extends Exception {
	// General exceptions
	public static class NullNotAllowedException extends ValidationException {}
	public static class MustBeNullException extends ValidationException {}

	// String exceptions
	public static class StringTooLongException extends ValidationException {}
	public static class StringTooShortException extends ValidationException {}
	public static class StringEmptyException extends ValidationException {}
	public static class StringFormatException extends ValidationException {}
	
	// Number exceptions
	public static class NumberTooLowException extends ValidationException {}
	public static class NumberTooHighException extends ValidationException {}
	public static class NumberMustEqualException extends ValidationException {}
}
