package com.nhlstenden.appstore.validation;

import org.junit.jupiter.api.Test;
import com.nhlstenden.appstore.validation.EmailValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmailValidatorTest
{

	@Test
	void validate_validEmail_returnsSameEmail()
	{
		String email = "john@student.nhlstenden.com";
		assertEquals(email, EmailValidator.validate(email));
	}

	@Test
	void validate_invalidEmail_returnsNull()
	{
		assertNull(EmailValidator.validate("not-an-email"));
	}

	@Test
	void validate_missingAtSymbol_returnsNull()
	{
		assertNull(EmailValidator.validate("johnexample.com"));
	}

	@Test
	void validate_nullEmail_returnsNull()
	{
		assertNull(EmailValidator.validate(null));
	}
}