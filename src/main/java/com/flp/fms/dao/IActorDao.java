package com.flp.fms.dao;

import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Actor;
import com.flp.fms.exceptions.NegativeInputException;

public interface IActorDao
{
	boolean AddActor(Actor actor);
	boolean ModifyActor(Actor actor);
	boolean RemoveActor(Actor actor);
	Actor SearchActor(Map searchParameters) throws NegativeInputException;
	List<Actor> getAllActor();
	Actor findActorByName(String first_name,String last_name);
	Actor SearchActorById(int actorId);
}

