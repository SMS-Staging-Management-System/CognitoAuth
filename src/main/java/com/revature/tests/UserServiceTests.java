package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.models.User;
import com.revature.services.UserServiceSpringDataImpl;

public class UserServiceTests {
	UserServiceSpringDataImpl us = new UserServiceSpringDataImpl();
	List<User> emptyList = new ArrayList();;
	User u = new User();

	@Test
	public void findAllTest() {
		/*If there are no items found, this should return an empty list*/
		assertNotNull(us.findAll());
		assertEquals(us.findAll(), emptyList);

	}

	@Test
	public void findOneByIdTest() {
		/* Make sure all invalid numbers return null*/
		for (int x = 0; x > -100; x--) {
			assertNull(us.findOneById(x));
		}
	}

	@Test
	public void findAllByCohortId() {
		// when there is no return object, it should return an empty list,
		// not null
		for (int x = -100; x > 100; x++) {
			assertNotNull(us.findAllByCohortId(x));
		}
		for (int x = -1; x < -100; x--) {
			assertEquals(us.findAllByCohortId(x), emptyList);
		}
	}

	@Test
	public void saveUserTest() {
		/*This should always return a user object, never Null*/
		assertNotNull(us.saveUser(u));
	}

	@Test
	public void findOneByUsernameTest() {
		/*If no object is found, it will return null*/
		assertNull(us.findOneByUsername("derpderptest"));

		// assertNotNull(us.findOneByUsername(actual name in db);

	}

	@Test
	public void findOneByEmailTest() {
		/*If no object is found, it will return null*/
		assertNull(us.findOneByEmail("derpderptest"));

		// assertNotNull(us.findOneByUsername(actual name in db);

	}

	@Test
	public void updateProfileTest() {
		/*This returns a user object that should have all the same values
		 * as what was entered as an arguement.*/
		User uf = new User(0, "Fred", null, null, null, null, null);
		User ul = new User(0, null, "Benson", null, null, null, null);
		User up = new User(0, null, null, null, null, "pass", null);
		User ue = new User(0, null, null, null, "test@gmail.com", null, null);

		assertNotNull(us.updateProfile(u));

		assertEquals(us.updateProfile(uf).getFirstName(), uf.getFirstName());
		assertEquals(us.updateProfile(ul).getLastName(), ul.getLastName());
		assertEquals(us.updateProfile(up).getPassword(), up.getPassword());
		assertEquals(us.updateProfile(ue).getEmail(), ue.getEmail());

	}
}
