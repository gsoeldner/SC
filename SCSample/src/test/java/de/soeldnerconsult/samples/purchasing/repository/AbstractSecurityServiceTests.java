package de.soeldnerconsult.samples.purchasing.repository;

import org.hsqldb.lib.HashSet;
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
import java.util.Set;

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
		Role role = rep.findRoleById("1");
		assertEquals("manager", role.getName());
	}
	
	@Test
	@Transactional
	public void findRoles()
	{
		Collection<Role> roles = rep.findRoleByName("manager");
		assertEquals(1, roles.size());
	}
	
	@Test
	@Transactional
	public void saveRole()
	{
		Role role = new Role();
		role.setName("poweruser");		
		role.setVersion(1);
		rep.saveRole(role);
		Collection<Role> newRoles = rep.findRoleByName("");
		assertEquals(2, newRoles.size());
	}
	
	@Test
	@Transactional
	public void findPermission()
	{
		Permission permission = rep.findPermissionById("1");
		assertEquals("PERM_READ", permission.getName());
	}
	
	@Test
	@Transactional
	public void findPermissions()
	{
		Collection<Permission> permissions = rep.findPermissionByName("PERM_READ");
		assertEquals(1, permissions.size());
	}
	
	@Test
	@Transactional
	public void savePermission()
	{
		Permission permission = new Permission();
		permission.setName("PERM_WRITE");		
		permission.setVersion(1);
		rep.savePermission(permission);
		Collection<Permission> newPermissions = rep.findPermissionByName("");
		assertEquals(2, newPermissions.size());
	}
	
	@Test
	@Transactional
	public void saveRoleWithPermission()
	{
		Role role = new Role();
		role.setName("user");		
		role.setVersion(1);
		Set<Permission> permissions = new java.util.HashSet<Permission>();
		permissions.add(rep.findPermissionById("1"));
		role.setPermissions(permissions);
		rep.saveRole(role);
		for(Role newRole : rep.findRoleByName("user")){
			Collection<Permission> newPermissions =	newRole.getPermissions();
			assertEquals("user", newRole.getName());			
			assertEquals(permissions.size(), newPermissions.size());
		}
	}
	
	@Test
	@Transactional
	public void UserAddRole()
	{
		User user = rep.findUserById("2");
		user.addRole(rep.findRoleById("1"));		
		User newUser = rep.findUserById("2");			
		assertEquals("Jens", newUser.getFirstName());
		Role role = newUser.getRole("manager");
		assertEquals("manager", role.getName());		
	}
	
}
