package de.soeldnerconsult.samples.purchasing.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.repository.RoleRepository;

@Repository
public class JpaRoleRepositoryImpl implements RoleRepository {


	EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;		
	}
	
	@Override
	public Collection<Role> findRoleByName(String roleName)
			throws DataAccessException {
		Query query = em.createQuery("from Role role where role.name like :roleName");
		query.setParameter("roleName", roleName + "%");
		return query.getResultList();
	}

	@Override
	public Role findRoleById(String id) throws DataAccessException {
		Query query = em.createQuery("from Role role where role.id = :roleId");
		query.setParameter("roleId", id);
		return (Role)query.getSingleResult();
	}

	@Override
	public void saveRole(Role role) throws DataAccessException {
		em.merge(role);
	}

}
