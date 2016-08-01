package com.flp.fms.test.dao;

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

import com.flp.fms.dao.FilmDaoImplForDB;
import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;

public class FilmDaoTest {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Mock private FilmDaoImplForDB filmDao;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToAddFilm()
	{
		assertEquals(false,filmDao.AddFilm(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullFilmWasPassedToAddFilm() throws ParseException
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
		assertEquals(true,filmDao.AddFilm(film));
	}
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToModifyFilm()
	{
		assertEquals(false,filmDao.ModifyFilm(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullFilmWasPassedToModifyFilm() throws ParseException
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
		
		Mockito.when(filmDao.ModifyFilm(film)).thenReturn(true);
		assertEquals(true,filmDao.ModifyFilm(film));
	}
	
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToRemoveFilm()
	{
		assertEquals(false,filmDao.RemoveFilm(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullFilmWasPassedToRemoveFilm() throws ParseException
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
		
		Mockito.when(filmDao.RemoveFilm(film)).thenReturn(true);
		assertEquals(true,filmDao.RemoveFilm(film));
	}
	
	@Test
	public void FilmDetailsShouldBeReturnedIfFilmRecordExitsForSearchFilm() throws ParseException
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
		
		List<Film> films=new ArrayList<Film>();
		films.add(film);
		
		Map<String,Object> searchParameters=new HashMap<String,Object>();
		searchParameters.put("title", "janatha garage");
		searchParameters.put("rating", "5");
		searchParameters.put("releaseDate", "2016-09-02");
		
		Mockito.when(filmDao.SearchFilm(searchParameters)).thenReturn(films);
		assertEquals(films,filmDao.SearchFilm(searchParameters));
	}
	
	@Test
	public void nullShouldBeReturnedIfFilmDetailsDoesNotExitsForSearchFilm() throws ParseException
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
		
		List<Film> films=new ArrayList<Film>();
		films.add(film);
		
		Map<String,Object> searchParameters=new HashMap<String,Object>();
		searchParameters.put("title", "simhadri");
		searchParameters.put("rating", "5");
		searchParameters.put("releaseDate", "2004-08-12");
		
		Mockito.when(filmDao.SearchFilm(searchParameters)).thenReturn(null);
		assertEquals(null,filmDao.SearchFilm(searchParameters));
	}
	
	@Test
	public void nullValueShouldBeReturnedIfNoFilmDetailsExistsForGetAllFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException 
	{
		Mockito.when(filmDao.getAllFilm()).thenReturn(null);
		assertEquals(null,filmDao.getAllFilm());
	}
	
	@Test
	public void FilmListShouldBeReturnedIfFilmDetailsExistsForGetAllFilm() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, ParseException 
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
		
		List<Film> films=new ArrayList<Film>();
		films.add(film);
		
		Mockito.when(filmDao.getAllFilm()).thenReturn(films);
		assertEquals(films,filmDao.getAllFilm());
	}

}
