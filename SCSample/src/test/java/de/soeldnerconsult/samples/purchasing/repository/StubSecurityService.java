package de.soeldnerconsult.samples.purchasing.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

import de.soeldnerconsult.samples.purchasing.model.Permission;
import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.model.UserStatus;
import de.soeldnerconsult.samples.purchasing.service.SecurityService;

public class StubSecurityService implements SecurityService {



	private List<User> userCol = new ArrayList<User>();
	
	public StubSecurityService()
	{		
		User user = new User();
		user.setId("1");
		user.setFirstName("Matthias");
		user.setLastName("Elbert");
		user.setUsername("melbert");
		user.setPassword("123");
		user.setStatus(UserStatus.ENABLED);
		user.setDateCreated(DateTime.now());
		user.setDateModified(DateTime.now());
		userCol.add(user);
	}
	
	@Override
	public Collection<User> findUserByLastName(String lastName) {
				
		return userCol;
	}

	@Override
	public User findUserById(String id) throws DataAccessException {
		User user = null;
		for (User item : userCol){
			if( item.getId() == id ) user = item;
		}
			
		return user;
	}

	@Override
	public void saveUser(User user) throws DataAccessException {
		userCol.add(user);
		
	}

	@Override
	public Collection<Role> findRoleByName(String roleName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findRoleById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveRole(Role role) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Permission> findPermissionByName(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission findPermissionById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePermission(Permission permission)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
