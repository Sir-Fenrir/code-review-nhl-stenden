package com.nhlstenden.appstore.model;

import com.nhlstenden.appstore.validation.EmailValidator;

import java.time.LocalDate;
import java.time.Period;

public class User
{

	private final String name;
	private final String email;
	private final LocalDate dateOfBirth;

	public User(String name, String email, LocalDate dateOfBirth)
	{
		this.name = name;
		this.email = EmailValidator.validate(email);
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge()
	{
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	public String getEmail()
	{
		return email;
	}

	public String getName()
	{
		return name;
	}
}