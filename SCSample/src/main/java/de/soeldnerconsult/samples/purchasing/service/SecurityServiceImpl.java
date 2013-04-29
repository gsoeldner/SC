package de.soeldnerconsult.samples.purchasing.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import de.soeldnerconsult.samples.purchasing.model.Permission;
import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.repository.PermissionRepository;
import de.soeldnerconsult.samples.purchasing.repository.RoleRepository;
import de.soeldnerconsult.samples.purchasing.repository.UserRepository;


/**
 * Mostly used as a facade for all Security controllers
 *
 * @author Guido Soeldner
 */

@Service
public class SecurityServiceImpl implements SecurityService{

	UserRepository userRep;
	RoleRepository roleRep; 
	PermissionRepository permRep;
	
	@Autowired
	public SecurityServiceImpl(UserRepository userRep, RoleRepository roleRep, PermissionRepository permRep)
	{
		this.userRep = userRep;
		this.roleRep = roleRep;
		this.permRep = permRep;
	}
	
	@Override
	public Collection<User> findUserByLastName(String lastName)
			throws DataAccessException {
		return userRep.findUserByLastName(lastName);
	}

	@Override
	public User findUserById(String id) throws DataAccessException {
		return userRep.findUserById(id);
	}

	@Override
	public void saveUser(User user) throws DataAccessException {
		userRep.saveUser(user);
		
	}

	@Override
	public Collection<Role> findRoleByName(String roleName) throws DataAccessException {
		return roleRep.findRoleByName(roleName);
	}

	@Override
	public Role findRoleById(String id) throws DataAccessException {
		return roleRep.findRoleById(id);
	}

	@Override
	public void saveRole(Role role) throws DataAccessException {
		roleRep.saveRole(role);
		
	}

	@Override
	public Collection<Permission> findPermissionByName(String name)
			throws DataAccessException {
		return permRep.findPermissionByName(name);
	}

	@Override
	public Permission findPermissionById(String id) throws DataAccessException {
		return permRep.findPermissionById(id);
	}

	@Override
	public void savePermission(Permission permission)
			throws DataAccessException {
		permRep.savePermission(permission);
		
	}

}
