package com.flp.fms.controller;

import com.flp.fms.domain.Category;
import com.flp.fms.domain.Language;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CategoryAdapter implements JsonSerializer<Category> {
	@Override
	public JsonElement serialize(Category category, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
		 JsonObject jsonObject = new JsonObject();
	     jsonObject.addProperty("categoryId", category.getCategoryId());
	     jsonObject.addProperty("categoryName", category.getCategoryName());
	     return jsonObject;    
	}
}
