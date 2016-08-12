package com.flp.fms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import com.flp.fms.domain.Actor;
import com.flp.fms.entitymanager.EntityManagerCreator;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class ActorDaoImplForDB implements IActorDao{
	EntityManagerCreator creator=new EntityManagerCreator();
	EntityManager em=creator.openEntityManger();
	
	public boolean AddActor(Actor actor) {
		if(actor !=null )
		{
			em.getTransaction().begin();
			em.persist(actor);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean ModifyActor(Actor actor) {
		if(actor !=null )
		{
			em.getTransaction().begin();
			em.persist(actor);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean RemoveActor(Actor actor){
		if(actor != null)
		{
			em.getTransaction().begin();
			em.remove(actor);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public Actor SearchActor(Map searchParameters){
		List<String> key = new ArrayList<String>(searchParameters.keySet());
		TypedQuery<Actor> query = null;
		
		if(key.contains("firstName") && key.contains("lastName"))
		{
			query = em.createQuery("Select a from Actor a where a.firstName='"+searchParameters.get("firstName")+"' and a.lastName='"+searchParameters.get("lastName")+"'",Actor.class);
		}
		
		if(query.getResultList().size() > 0)
		{
			return query.getSingleResult();
		}
		return null;
	}
	
	public Actor SearchActorById(int actorId) {
		return em.find(Actor.class, actorId);
	}

	public List<Actor> getAllActor() {
		TypedQuery<Actor> query = em.createQuery("Select a from Actor a",Actor.class);
		if(query.getResultList().size() > 0)
		{
			return query.getResultList();
		}
		return null;
	}
	
	public Actor findActorByName(String firstName,String lastName){
		TypedQuery<Actor> query = em.createQuery("Select a from Actor a where a.firstName='"+firstName+"' and a.lastName='"+lastName+"'",Actor.class);
	
		if(query.getResultList().size() > 0)
		{
			return query.getSingleResult();
		}
		return null;
	}
}
	
	
