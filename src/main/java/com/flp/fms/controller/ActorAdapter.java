package com.flp.fms.controller;

import com.flp.fms.domain.Actor;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ActorAdapter implements JsonSerializer<Actor> {
	@Override
	public JsonElement serialize(Actor actor, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
		 JsonObject jsonObject = new JsonObject();
	     jsonObject.addProperty("actorId", actor.getActorId());
	     jsonObject.addProperty("firstName", actor.getFirstName());
	     jsonObject.addProperty("lastName", actor.getLastName());
	     //jsonObject.addProperty("lastUpdate", actor.getLastUpdate().toString());
	     return jsonObject;    
	}
}