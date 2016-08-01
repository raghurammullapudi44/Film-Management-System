package com.flp.fms.service;

import java.util.List;
import java.util.Map;

import com.flp.fms.dao.ActorDaoImplForDB;
import com.flp.fms.dao.IActorDao;
import com.flp.fms.domain.Actor;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.util.InputDataValidation;

public class ActorServiceImpl implements IActorService
{
	IActorDao actorDao;
	InputDataValidation validate=new InputDataValidation();
	
	public ActorServiceImpl() {
		actorDao=new ActorDaoImplForDB();
	}

	public ActorServiceImpl(ActorDaoImplForDB actorDao) {
		this.actorDao=actorDao;
	}
	
	public boolean AddActor(Map actorDetails) throws FieldEmptyException, DuplicateRecordFoundException, NegativeInputException
	{
		validate.inputDataValidation(actorDetails);
		
		Actor actor=new Actor();
		actor.setFirstName((String) actorDetails.get("firstName"));
		actor.setLastName((String) actorDetails.get("lastName"));
		
		if(actorDao.getAllActor().contains(actor))
		{
			throw new DuplicateRecordFoundException();
		}
		return actorDao.AddActor(actor);
	}
	
	
	public boolean ModifyActor(Map actorDetails) throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		
		validate.inputDataValidation(actorDetails);
		
		Actor actor=actorDao.SearchActorById((Integer) actorDetails.get("actorId"));
		
		if(actor == null)
			throw new RecordDoesNotExistsException();
		
		Actor modifiedActor=new Actor();
			
		modifiedActor.setFirstName((String) actorDetails.get("firstName"));
		modifiedActor.setLastName((String) actorDetails.get("lastName"));
			
		if(actorDao.getAllActor().contains(modifiedActor))
		{
			throw new DuplicateRecordFoundException();
		}
		else
		{
			actor.setFirstName((String) actorDetails.get("firstName"));
			actor.setLastName((String) actorDetails.get("lastName"));
			return actorDao.ModifyActor(actor);
		}
	}

	
	public boolean RemoveActor(Map searchParameters) throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException{
		validate.inputDataValidation(searchParameters);
		
		Actor actor=actorDao.SearchActor(searchParameters);
		if(actor == null)
			throw new RecordDoesNotExistsException();
		
		return actorDao.RemoveActor(actor);
	}

	
	public Actor SearchActor(Map SearchParameters) throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException {
		validate.inputDataValidation(SearchParameters);
		
		Actor actor=actorDao.SearchActor(SearchParameters);
		if(actor == null)
			throw new RecordDoesNotExistsException();
		
		return actorDao.SearchActor(SearchParameters);
	}
	
	public Actor SearchActorById(int actorId) throws RecordDoesNotExistsException {
		Actor actor=actorDao.SearchActorById(actorId);
		if(actor == null)
			throw new RecordDoesNotExistsException();
		
		return actor;
	}

	
	public List<Actor> getAllActor() {
		return actorDao.getAllActor();
	}
}
