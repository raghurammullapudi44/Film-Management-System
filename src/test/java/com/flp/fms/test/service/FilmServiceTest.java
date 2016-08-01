package com.flp.fms.test.service;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.flp.fms.dao.ActorDaoImplForDB;
import com.flp.fms.dao.FilmDaoImplForDB;
import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IActorService;
import com.flp.fms.service.IFilmService;



public class FilmServiceTest {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private IFilmService filmService;
	@Mock private FilmDaoImplForDB filmDao;
	private IActorService actorService;
	@Mock private ActorDaoImplForDB actorDao;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		filmService = new FilmServiceImpl(filmDao,actorDao);
	}

	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForAddFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("description","");
		filmService.AddFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.NegativeInputException.class)
	public void inputFieldsShouldNotBeNegativeForAddFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("description","mass entertainer");
		filmDetails.put("rating",-5);
		filmService.AddFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.DuplicateRecordFoundException.class)
	public void shouldNotAddDupliacteFilmForAddFilm() throws ParseException, FieldEmptyException, NegativeInputException, DuplicateRecordFoundException
	{
		Film film=new Film();
		film.setTitle("janatha garage");
		film.setDescription("mass entertainer");
		film.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		film.setRentalDuration(1);
		film.setRentalRate(2);
		film.setLength(3);
		film.setReplacementCost(4);
		film.setRating(5);
		film.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		film.setActors(actors);
		film.setCategory(catg);
		film.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		filmDao.AddFilm(film);
		
		List<Film> allFilms=new ArrayList<Film>();
		allFilms.add(film);
		
		Map<String, Object> newFilmDetails=new HashMap<String, Object>();
		newFilmDetails.put("title", "janatha garage");
		newFilmDetails.put("description", "dtghdet");
		newFilmDetails.put("rating",5);
		newFilmDetails.put("length",2);
		newFilmDetails.put("replacementCost",6);
		newFilmDetails.put("rentalRate",4);
		newFilmDetails.put("rentalDuration",3);
		newFilmDetails.put("specialFeatures","tydtydf");
		newFilmDetails.put("releaseDate",dateFormat.parse("2016-09-02"));
		newFilmDetails.put("languageName","telugu");
		newFilmDetails.put("categoryName","mass");
		
		List newActors=new ArrayList();
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName","raghu");
		actorDetails.put("lastName","ram");
		newActors.add(actorDetails);
		newFilmDetails.put("actors",newActors);
		
		Mockito.when(filmDao.getAllFilm()).thenReturn(allFilms);
		filmService.AddFilm(newFilmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForModifyFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("description","");
		filmDetails.put("releaseDate","2016-09-02");
		filmService.ModifyFilm(filmDetails, null);
	}
	
	@Test(expected=com.flp.fms.exceptions.NegativeInputException.class)
	public void inputFieldsShouldNotBeNegativeForModifyFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("releaseDate","2016-09-02");
		filmDetails.put("rating",-5);
		filmService.ModifyFilm(filmDetails, null);
	}
	
	@Test
	public void ifValidInfoIsPassedTheDetailsShouldBeModifiedSucessfullyForModifyFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Film existingFilm=new Film();
		existingFilm.setTitle("janatha garage");
		existingFilm.setDescription("mass entertainer");
		existingFilm.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		existingFilm.setRentalDuration(1);
		existingFilm.setRentalRate(2);
		existingFilm.setLength(3);
		existingFilm.setReplacementCost(4);
		existingFilm.setRating(5);
		existingFilm.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		existingFilm.setActors(actors);
		existingFilm.setCategory(catg);
		existingFilm.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(existingFilm)).thenReturn(true);
		filmDao.AddFilm(existingFilm);
		
		Map<String, Object> modifiactionDetails=new HashMap<String, Object>();
		modifiactionDetails.put("title", "janatha garage");
		
		Mockito.when(filmDao.ModifyFilm(existingFilm)).thenReturn(true);
		assertEquals(true,filmService.ModifyFilm(modifiactionDetails, existingFilm));
	}
	
	@Test
	public void ifTheValidInfoIspassedFilmRecordMustBeSavedSuccessFullyInDataBaseForAddFilm() throws ParseException, FieldEmptyException, NegativeInputException, DuplicateRecordFoundException
	{
		Film film=new Film();
		film.setTitle("janatha garage");
		film.setDescription("mass entertainer");
		film.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		film.setRentalDuration(1);
		film.setRentalRate(2);
		film.setLength(3);
		film.setReplacementCost(4);
		film.setRating(5);
		film.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		film.setActors(actors);
		film.setCategory(catg);
		film.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		filmDao.AddFilm(film);
		
		List<Film> allFilms=new ArrayList<Film>();
		allFilms.add(film);
		
		
		Map<String, Object> newFilmDetails=new HashMap<String, Object>();
		newFilmDetails.put("title", "dhruva");
		newFilmDetails.put("description", "dtghdet");
		newFilmDetails.put("rating",5);
		newFilmDetails.put("length",2);
		newFilmDetails.put("replacementCost",6);
		newFilmDetails.put("rentalRate",4);
		newFilmDetails.put("rentalDuration",3);
		newFilmDetails.put("specialFeatures","tydtydf");
		newFilmDetails.put("releaseDate",dateFormat.parse("2016-09-02"));
		newFilmDetails.put("languageName","telugu");
		newFilmDetails.put("categoryName","mass");
		
		List newActors=new ArrayList();
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName","raghu");
		actorDetails.put("lastName","ram");
		newActors.add(actorDetails);
		newFilmDetails.put("actors",newActors);
		
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		Mockito.when(actorDao.getAllActor()).thenReturn(actors);
		Mockito.when(filmDao.getAllFilm()).thenReturn(allFilms);
		filmService.AddFilm(newFilmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForRemoveFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",5);
		filmDetails.put("releaseDate","");
		filmService.RemoveFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.NegativeInputException.class)
	public void ratingFieldShouldNotBeNegativeForRemoveFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",-5);
		filmDetails.put("releaseDate","2016-09-02");
		filmService.RemoveFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.RecordDoesNotExistsException.class)
	public void filmShouldNotBeRemovedIfFilmRecordDoesNotExitsForRemoveFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {		
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "dhruva");
		filmDetails.put("rating",1);
		filmDetails.put("releaseDate","2016-10-07");
		
		Mockito.when(filmDao.SearchFilm(filmDetails)).thenReturn(null);
		filmService.RemoveFilm(filmDetails);
	}

	
	@Test
	public void filmShouldBeRemovedIfFilmRecordExitsForRemoveFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Film film=new Film();
		film.setTitle("janatha garage");
		film.setDescription("mass entertainer");
		film.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		film.setRentalDuration(1);
		film.setRentalRate(2);
		film.setLength(3);
		film.setReplacementCost(4);
		film.setRating(5);
		film.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		film.setActors(actors);
		film.setCategory(catg);
		film.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		filmDao.AddFilm(film);
		List<Film> allFilms=new ArrayList<Film>();
		allFilms.add(film);
		
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",5);
		filmDetails.put("releaseDate","2016-09-02");
		
		Mockito.when(filmDao.RemoveFilm(allFilms.get(0))).thenReturn(true);
		Mockito.when(filmDao.SearchFilm(filmDetails)).thenReturn(allFilms);
		assertEquals(true,filmService.RemoveFilm(filmDetails));
	}

	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForSearchFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",5);
		filmDetails.put("releaseDate","");
		filmService.RemoveFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.NegativeInputException.class)
	public void ratingFieldShouldNotBeNegativeForSearchFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",-5);
		filmDetails.put("releaseDate","2016-09-02");
		filmService.RemoveFilm(filmDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.RecordDoesNotExistsException.class)
	public void filmDetailsShouldNotBeReturnedIfFilmRecordDoesNotExitsForSearchFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "dhruva");
		filmDetails.put("rating",1);
		filmDetails.put("releaseDate","2016-10-07");
		
		Mockito.when(filmDao.SearchFilm(filmDetails)).thenReturn(null);
		filmService.SearchFilm(filmDetails);
	}
	
	@Test
	public void filmDetailsShouldBeReturnedIfFilmRecordExitsForSearchFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException, RecordDoesNotExistsException {
		Film film=new Film();
		film.setTitle("janatha garage");
		film.setDescription("mass entertainer");
		film.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		film.setRentalDuration(1);
		film.setRentalRate(2);
		film.setLength(3);
		film.setReplacementCost(4);
		film.setRating(5);
		film.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		film.setCategory(catg);
		film.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		filmDao.AddFilm(film);
		List<Film> films=new ArrayList<Film>();
		films.add(film);
		
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		filmDetails.put("title", "janatha garage");
		filmDetails.put("rating",5);
		filmDetails.put("releaseDate","2016-09-02");
		
		Mockito.when(filmDao.SearchFilm(filmDetails)).thenReturn(films);
		assertEquals(films.get(0),filmService.SearchFilm(filmDetails).get(0));
	}
	
	@Test
	public void nullValueShouldBeReturnedIfNoFilmDetailsExistsForGetAllFilms() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Mockito.when(filmDao.getAllFilm()).thenReturn(null);
		assertEquals(null,filmService.getAllFilm());
	}
	
	@Test
	public void FilmsListShouldBeReturnedIfFilmDetailsExistsForGetAllFilms() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException {
		Film film=new Film();
		film.setTitle("janatha garage");
		film.setDescription("mass entertainer");
		film.setReleaseYear((Date) dateFormat.parse("2016-09-02"));
		film.setRentalDuration(1);
		film.setRentalRate(2);
		film.setLength(3);
		film.setReplacementCost(4);
		film.setRating(5);
		film.setSpecialFeatures("enteratainer");
		Category catg=new Category();
		Language lang=new Language();
		catg.setCategoryName("mass");
		lang.setLanguageName("telugu");
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		List<Actor> actors=new ArrayList<Actor>();
		actors.add(actor);
		film.setCategory(catg);
		film.setLanguage(lang);
		Mockito.when(filmDao.AddFilm(film)).thenReturn(true);
		filmDao.AddFilm(film);
		List<Film> films=new ArrayList<Film>();
		films.add(film);
		
		Mockito.when(filmDao.getAllFilm()).thenReturn(films);
		assertEquals(films,filmService.getAllFilm());
	}
	
}
