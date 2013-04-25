package de.soeldnerconsult.samples.purchasing.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.soeldnerconsult.samples.purchasing.model.*;
import de.soeldnerconsult.samples.purchasing.service.SecurityService;

import java.util.Collection;

import static org.junit.Assert.assertEquals;


public abstract class AbstractSecurityServiceTests {
	
	@Autowired
	SecurityService rep;
	
	@Test
	@Transactional
	public void findUsers()
	{
		Collection<User> users = rep.findUserByLastName("Soeldner");
		assertEquals(4, users.size());
	}

	@Test
	@Transactional
	public void findUserById()
	{
		User user = rep.findUserById("1");
		assertEquals("Guido", user.getFirstName());
	}
	
	@Test
	@Transactional
	public void saveUser()
	{
		User user = new User();
		user.setFirstName("Matthias");
		user.setLastName("Elbert");
		user.setUsername("melbert");
		user.setPassword("123");
		user.setStatus(UserStatus.ENABLED);
		user.setDateCreated(DateTime.now());
		user.setDateModified(DateTime.now());
		
		rep.saveUser(user);
		Collection<User> newUsers = rep.findUserByLastName("");
		assertEquals(5, newUsers.size());
	}
	
	@Test
	@Transactional
	public void findRole()
	{
		
	}
	
	@Test
	@Transactional
	public void findRoles()
	{
		
	}
}
