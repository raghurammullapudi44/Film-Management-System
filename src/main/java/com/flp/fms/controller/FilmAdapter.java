package com.flp.fms.controller;

import java.lang.reflect.Type;

import com.flp.fms.domain.Film;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FilmAdapter implements JsonSerializer<Film> {
	@Override
	public JsonElement serialize(Film film, Type typeOfSrc, JsonSerializationContext context) {
		 JsonObject jsonObject = new JsonObject();
	     jsonObject.addProperty("filmId", film.getFilmId());
	     jsonObject.addProperty("title", film.getTitle());
	     jsonObject.addProperty("description", film.getDescription());
	     jsonObject.addProperty("rating", film.getRating());
	     jsonObject.addProperty("releaseYear", film.getReleaseYear().toString());
	     return jsonObject;    
	}

}