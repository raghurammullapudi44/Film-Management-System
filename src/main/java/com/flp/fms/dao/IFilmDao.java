package com.flp.fms.dao;

import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Category;
import com.flp.fms.domain.Film;
import com.flp.fms.domain.Language;

public interface IFilmDao
{
	boolean AddFilm(Film film);
	boolean ModifyFilm(Film film);
	boolean RemoveFilm(Film film);
	List<Film> SearchFilm(Map searchParameters);
	List<Film> getAllFilm();
	Language findLanguageByName(String language_name);
	Category findCategoryByName(String category_name);
	List<Language> getAllLanguages();
	List<Category> getAllCategories();
}
