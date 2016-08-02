package com.flp.fms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.entitymanager.EntityManagerCreator;

public class FilmDaoImplForDB implements IFilmDao
{
	EntityManagerCreator creator=new EntityManagerCreator();
	EntityManager em=creator.openEntityManger();

	public boolean AddFilm(Film film) 
	{
		if(film != null)
		{
			em.getTransaction().begin();
			em.persist(film);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean ModifyFilm(Film film) {
		if(film != null)
		{
			em.getTransaction().begin();
			em.persist(film);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean RemoveFilm(Film film) {
		if(film != null)
		{
			em.getTransaction().begin();
			em.remove(film);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}

	public List<Film> SearchFilm(Map searchParameters) {
		List<String> key = new ArrayList<String>(searchParameters.keySet());
		TypedQuery<Film> query = null;
		
		if(key.contains("title") && key.contains("rating") && key.contains("releaseDate"))
		{
			query = em.createQuery("Select f from Film f where f.title='"+searchParameters.get("title")+"' and f.rating='"+searchParameters.get("rating")+"' and f.releaseYear='"+searchParameters.get("releaseDate")+"'",Film.class);
		}
		else if(key.contains("lastName") && key.contains("firstName"))
		{
			query = em.createQuery("select f from Film f join f.actors a where a.firstName = '"+searchParameters.get("firstName")+"' and a.lastName = '"+searchParameters.get("lastName")+"'",Film.class);
		}
		else if( key.get(0).equals("title"))
		{
			query = em.createQuery("Select f from Film f where f.title='"+searchParameters.get("title")+"'",Film.class);
		}
		else if(key.get(0).equals("rating"))
		{
			query = em.createQuery("Select f from Film f where f.rating='"+searchParameters.get("rating")+"'",Film.class);
		}
		else if(key.get(0).equals("languageId"))
		{
			query = em.createQuery("select f from Film f join f.language l where l.languageId ='"+searchParameters.get("languageId")+"'",Film.class);
		}
		else if(key.get(0).equals("actorId"))
		{
			query = em.createQuery("select f from Film f join f.actors a where a.actorId = '"+searchParameters.get("actorId")+"'",Film.class);
		}
		else if(key.get(0).equals("categoryId"))
		{
			query = em.createQuery("select f from Film f join f.category c where c.categoryId = "+searchParameters.get("categoryId")+"'",Film.class);
		}
		else if(key.get(0).equals("categoryName"))
		{
			query = em.createQuery("select f from Film f join f.category c where c.categoryName = '"+searchParameters.get("categoryName")+"'",Film.class);
		}
		else if(key.get(0).equals("languageName"))
		{
			query = em.createQuery("select f from Film f join f.language l where l.languageName ='"+searchParameters.get("languageName")+"'",Film.class);
		}
		
		if(query.getResultList().size() > 0)
		{
			return query.getResultList();
		}
		return null;
	}

	public List<Film> getAllFilm() {
		TypedQuery<Film> query = em.createQuery("Select f from Film f",Film.class);
		return query.getResultList();
		
	}
	
	public Language findLanguageByName(String languageName){
		TypedQuery<Language> query = em.createQuery("Select l from Language l where l.languageName='"+languageName+"' ",Language.class);
		
		if(query.getResultList().size() > 0)
		{
			return query.getSingleResult();
		}
		return null;
	}
	
	public Category findCategoryByName(String categoryName){
		TypedQuery<Category> query = em.createQuery("Select c from Category c where c.categoryName='"+categoryName+"'",Category.class);
		
		if(query.getResultList().size() > 0)
		{
			return query.getSingleResult();
		}
		return null;
	}
	
	public List<Language> getAllLanguages() {
		TypedQuery<Language> query = em.createQuery("Select l from Language l",Language.class);
		return query.getResultList();
	}
	
	public List<Category> getAllCategories() {
		TypedQuery<Category> query = em.createQuery("Select c from Category c",Category.class);
		return query.getResultList();
	}
}
