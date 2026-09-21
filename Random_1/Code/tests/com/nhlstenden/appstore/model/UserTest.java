package com.nhlstenden.appstore.model;

import com.nhlstenden.appstore.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest
{

	@Test
	void getEmail_validEmailProvided_returnsEmail()
	{
		User user = new User("John", "john@student.nhlstenden.com", LocalDate.of(2000, 1, 1));
		assertEquals("john@student.nhlstenden.com", user.getEmail());
	}

	@Test
	void getEmail_invalidEmailProvided_returnsNull()
	{
		User user = new User("John", "not-an-email", LocalDate.of(2000, 1, 1));
		assertNull(user.getEmail());
	}

	@Test
	void getAge_dateOfBirthTwentyYearsAgo_returnsTwenty()
	{
		LocalDate dateOfBirth = LocalDate.now().minusYears(20);
		User user = new User("John", "john@student.nhlstenden.com", dateOfBirth);
		assertEquals(20, user.getAge());
	}
}