package de.soeldnerconsult.samples.purchasing.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import de.soeldnerconsult.samples.purchasing.model.Permission;
import de.soeldnerconsult.samples.purchasing.repository.PermissionRepository;

@Repository
public class JpaPermissionRepository implements PermissionRepository {


	EntityManager  em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;		
	}
	
	@Override
	public Collection<Permission> findPermissionByName(String name)
			throws DataAccessException {
		Query query = em.createQuery("from Permission perm where perm.name LIKE :name");
		query.setParameter("name", name);
		return query.getResultList();
		
	}

	@Override
	public Permission findPermissionById(String id) throws DataAccessException {
		Query query = em.createQuery("from Permission perm where perm.id = :id");
		query.setParameter("id", id);
		return (Permission)query.getSingleResult();
	}

	@Override
	public void savePermission(Permission permission)
			throws DataAccessException {
		em.merge(permission);

	}

}
