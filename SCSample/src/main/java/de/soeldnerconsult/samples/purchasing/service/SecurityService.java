package de.soeldnerconsult.samples.purchasing.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import de.soeldnerconsult.samples.purchasing.model.Permission;
import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.model.User;
@Transactional
public interface SecurityService {
	
	Collection<User> findUserByLastName(String lastName) throws DataAccessException;
	
	User findUserById(String id) throws DataAccessException;
	
	void saveUser(User user) throws DataAccessException;
	
	void deleteUser(String id) throws DataAccessException;
	
	Collection<Role> findRoleByName(String roleName) throws DataAccessException;
	
	Role findRoleById(String id) throws DataAccessException;
	
	void saveRole(Role role) throws DataAccessException;
	
	Collection<Permission> findPermissionByName(String name) throws DataAccessException;
	
	Permission findPermissionById(String id) throws DataAccessException;
	
	void savePermission(Permission permission) throws DataAccessException;

}
