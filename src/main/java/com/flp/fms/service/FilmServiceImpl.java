package com.flp.fms.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.flp.fms.dao.ActorDaoImplForDB;
import com.flp.fms.dao.FilmDaoImplForDB;
import com.flp.fms.dao.IActorDao;
import com.flp.fms.dao.IFilmDao;
import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.util.InputDataValidation;

public class FilmServiceImpl implements IFilmService
{
	IFilmDao filmDao;
	IActorDao actorDao;
	InputDataValidation validate=new InputDataValidation();
	
	public FilmServiceImpl() 
	{
		filmDao=new FilmDaoImplForDB();
		actorDao=new ActorDaoImplForDB();
	}
	
	
	public FilmServiceImpl(IFilmDao filmDao,IActorDao actorDao) 
	{
		this.filmDao=filmDao;
		this.actorDao=actorDao;
	}
	
	public boolean AddFilm(Map filmDetails) throws ParseException,FieldEmptyException, NegativeInputException, DuplicateRecordFoundException
	{
		validate.inputDataValidation(filmDetails);
			
		Film film=new Film();
		film.setTitle((String) filmDetails.get("title"));
		film.setRating((Integer) filmDetails.get("rating"));
		film.setReleaseYear((Date) filmDetails.get("releaseDate"));
		
		if(getAllFilm().contains(film))
		{
			throw new DuplicateRecordFoundException();
		}
		
		film.setDescription((String) filmDetails.get("description"));
		film.setRentalDuration( (Integer) filmDetails.get("rentalDuration"));
		film.setRentalRate((Integer) filmDetails.get("rentalRate"));
		film.setLength((Integer) filmDetails.get("length"));
		film.setReplacementCost((Integer) filmDetails.get("replacementCost"));
		
		film.setSpecialFeatures((String) filmDetails.get("specialFeatures"));
		
		Language lang=filmDao.findLanguageByName((String) filmDetails.get("languageName"));
		if(lang == null)
		{
			lang=new Language((String) filmDetails.get("languageName"));
		}
		film.setLanguage(lang);
		
		Category catg=filmDao.findCategoryByName((String) filmDetails.get("categoryName"));
		if(catg == null)
		{
			catg=new Category((String) filmDetails.get("categoryName"));
		}
		film.setCategory(catg);
		
		
		List<Actor> actors=actorDao.getAllActor();
		
		for(int i=0; i < ((List) filmDetails.get("actors")).size(); i++)
		{
			Actor actor=new Actor();
			actor.setFirstName((String) ((Map) ((List) filmDetails.get("actors")).get(i)).get("firstName"));
			actor.setLastName((String) ((Map) ((List) filmDetails.get("actors")).get(i)).get("lastName"));
			
			if(actors == null || !actors.contains(actor))
			{
				film.getActors().add(actor);
			}
			else
			{
				film.getActors().add(actorDao.findActorByName(actor.getFirstName(),actor.getLastName()));
			}
		}
		
		filmDao.AddFilm(film);
		return true;
	}
	
	public boolean ModifyFilm(Map modificationDetails,Film existingFilm) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException
	{
		validate.inputDataValidation(modificationDetails);
		
		List<String> key = new ArrayList<String>(modificationDetails.keySet());
		
		switch(key.get(0))
		{
			case "title":existingFilm.setTitle((String) modificationDetails.get("title"));
						break;
			case "description":existingFilm.setDescription((String) modificationDetails.get("description"));
						break;
			case "rating":existingFilm.setRating((int) modificationDetails.get("rating"));
						break;
			case "rentalRate":existingFilm.setRentalRate((int) modificationDetails.get("rentalRate"));
						break;
			case "length":existingFilm.setLength((int) modificationDetails.get("length"));
						break;
			case "rentalDuration":existingFilm.setRentalDuration((int) modificationDetails.get("rentalDuration"));
						break;
			case "specialFeatures":existingFilm.setSpecialFeatures((String) modificationDetails.get("specialFeatures"));
						break;
			case "replacementCost":existingFilm.setReplacementCost((int) modificationDetails.get("replacementCost"));
						break;
		}
		return filmDao.ModifyFilm(existingFilm);
	}

	public boolean RemoveFilm(Map removeParameters) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException
	{
		validate.inputDataValidation(removeParameters);
		
		List<Film> films=filmDao.SearchFilm(removeParameters);
		
		if(films != null)
			return filmDao.RemoveFilm(films.get(0));
		
		throw new RecordDoesNotExistsException();
	}
	
	public List<Film> SearchFilm(Map searchParameters) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException 
	{
		validate.inputDataValidation(searchParameters);
		
		List<Film> films=filmDao.SearchFilm(searchParameters);
		
		if(films != null)
		{
			return films;
		}
		throw new RecordDoesNotExistsException();
	}

	public List<Film> getAllFilm() 
	{
		return filmDao.getAllFilm();
	}
}
