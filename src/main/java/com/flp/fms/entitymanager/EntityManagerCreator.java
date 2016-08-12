package com.flp.fms.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerCreator
{
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
	private static final EntityManager em=emf.createEntityManager();
	
	public EntityManager openEntityManger()
	{
		return this.em;
	}
	
	public void closeEntityManager()
	{
		this.em.close();
		this.emf.close();
	}
}
