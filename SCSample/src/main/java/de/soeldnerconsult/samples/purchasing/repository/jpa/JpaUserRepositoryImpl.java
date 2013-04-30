package de.soeldnerconsult.samples.purchasing.repository.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.repository.UserRepository;

@Repository
public class JpaUserRepositoryImpl implements UserRepository {


	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.em = em;		
	}
	
	@Override
	public Collection<User> findUserByLastName(String lastName)
			throws DataAccessException {
		
		Query query = this.em.createQuery("from User user where user.lastName LIKE :lastName");
		query.setParameter("lastName", lastName + "%");
		
		return query.getResultList();
	}

	@Override
	public User findUserById(String id) throws DataAccessException {
		Query query = this.em.createQuery("from User user where user.id = :id");
		query.setParameter("id", id);
		return (User)query.getSingleResult();
	}

	@Override
	public void saveUser(User user) throws DataAccessException {
		this.em.merge(user);

	}

}
