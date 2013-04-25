package de.soeldnerconsult.samples.purchasing.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import de.soeldnerconsult.samples.purchasing.model.Permission;
import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.model.User;

public interface SecurityService {
	
	Collection<User> findUserByLastName(String lastName) throws DataAccessException;
	
	User findUserById(String id) throws DataAccessException;
	
	void saveUser(User user) throws DataAccessException;
	
	Collection<Role> findRolyByName(String roleName) throws DataAccessException;
	
	Role findRolyById(String id) throws DataAccessException;
	
	void saveRole(Role role) throws DataAccessException;
	
	Collection<Permission> findPermissionByName(String name) throws DataAccessException;
	
	Permission findPermissionById(String id) throws DataAccessException;
	
	void savePermission(Permission permission) throws DataAccessException;

}
