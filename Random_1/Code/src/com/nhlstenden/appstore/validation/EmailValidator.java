package com.nhlstenden.appstore.validation;

public class EmailValidator
{

	private static final String EMAIL_PATTERN = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$";

	private EmailValidator()
	{
	}

	public static String validate(String email)
	{
		if (email != null && email.matches(EMAIL_PATTERN))
		{
			return email;
		}
		return null;
	}
}