package com.flp.fms.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;


public interface IFilmService 
{
	boolean AddFilm(Map filmDetails) throws ParseException, FieldEmptyException, NegativeInputException, DuplicateRecordFoundException;
	boolean ModifyFilm(Map modifyParameters, Film existingFilm) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException;
	boolean RemoveFilm(Map removeParameters) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException;
	List<Film> SearchFilm(Map searchParameters) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException;
	List<Film> getAllFilm();
	List<Language> getAllLanguages();
	List<Category> getAllCategories();
}
