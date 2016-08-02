package com.flp.fms.controller;

import com.flp.fms.domain.Language;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LangugaeAdapter implements JsonSerializer<Language>  {
	@Override
	public JsonElement serialize(Language language, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
		 JsonObject jsonObject = new JsonObject();
	     jsonObject.addProperty("languageId", language.getLanguageId());
	     jsonObject.addProperty("languageName", language.getLanguageName());
	     return jsonObject;    
	}
}
