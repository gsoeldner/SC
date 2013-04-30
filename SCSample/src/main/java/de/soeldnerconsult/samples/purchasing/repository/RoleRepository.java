package de.soeldnerconsult.samples.purchasing.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import de.soeldnerconsult.samples.purchasing.model.Role;


/**
 * Repository class for <code>Role</code> domain objects. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Guido Soeldner

 */
@Transactional
public interface RoleRepository {
	
	/**
     * Retrieve <code>Role</code>s from the data store by last name, returning all users whose last name <i>starts</i>
     * with the given name.
     *
     * @param lastName Value to search for
     * @return a <code>Collection</code> of matching <code>Role</code>s (or an empty <code>Collection</code> if none
     *         found)
     */
	Collection<Role> findRoleByName(String roleName) throws DataAccessException;
	
	/**
     * Retrieve an <code>Role</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Role</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
	Role findRoleById(String id) throws DataAccessException;
	
	/**
     * Save an <code>Role</code> to the data store, either inserting or updating it.
     *
     * @param  the <code>Role</code> to save
     * 
     */
	void saveRole(Role role) throws DataAccessException;

}
