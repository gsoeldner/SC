package de.soeldnerconsult.samples.purchasing.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import de.soeldnerconsult.samples.purchasing.model.Role;
import de.soeldnerconsult.samples.purchasing.repository.RoleRepository;

@Repository
public class JpaRoleRepositoryImpl implements RoleRepository {

	@Autowired
	EntityManager em;
	
	@Override
	public Collection<Role> findRolyByName(String roleName)
			throws DataAccessException {
		Query query = em.createQuery("from Role role where role.roleName like :roleName");
		query.setParameter("roleName", roleName + "%");
		return query.getResultList();
	}

	@Override
	public Role findRolyById(String id) throws DataAccessException {
		Query query = em.createQuery("from Role role where role.id = :roleId");
		query.setParameter("roleId", id);
		return (Role)query.getSingleResult();
	}

	@Override
	public void saveRole(Role role) throws DataAccessException {
		em.merge(role);
	}

}
