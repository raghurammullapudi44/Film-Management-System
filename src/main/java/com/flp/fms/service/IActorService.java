package com.flp.fms.service;

import java.util.List;
import java.util.Map;

import com.flp.fms.domain.Actor;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;

public interface IActorService {

	boolean AddActor(Map actorDetails) throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException;

	boolean ModifyActor(Map actorDetails) throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException;

	boolean RemoveActor(Map searchParameters) throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException;

	Actor SearchActor(Map searchParameters) throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException;

	List<Actor> getAllActor();

	Actor SearchActorById(int actorId) throws RecordDoesNotExistsException;
}