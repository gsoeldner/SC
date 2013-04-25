package de.soeldnerconsult.samples.purchasing.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import de.soeldnerconsult.samples.purchasing.model.Permission;


/**
 * Repository class for <code>Permission</code> domain objects. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Guido Soeldner

 */
public interface PermissionRepository {
	
	/**
     * Retrieve <code>Role</code>s from the data store by  name, returning all users whose last name <i>starts</i>
     * with the given name.
     *
     * @param Name Value to search for
     * @return a <code>Collection</code> of matching <code>Role</code>s (or an empty <code>Collection</code> if none
     *         found)
     */
	Collection<Permission> findPermissionByName(String name) throws DataAccessException;
	
	/**
	 * Retrieve the <code>Permission</code> from the data store by id
	 * @param the id for search for
	 * @return the <code>Permission</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
	 */
	Permission findPermissionById(String id) throws DataAccessException;
	
	/**
     * Save an <code>Permission</code> to the data store, either inserting or updating it.
     *
     * @param  the <code>Permission</code> to save
     * 
     */
	void savePermission(Permission permission) throws DataAccessException;
	

}
