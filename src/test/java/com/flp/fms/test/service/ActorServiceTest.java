package com.flp.fms.test.service;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.flp.fms.dao.ActorDaoImplForDB;
import com.flp.fms.domain.Actor;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.IActorService;

public class ActorServiceTest {
	 private IActorService actorService;
	 @Mock private ActorDaoImplForDB actorDao;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		actorService=new ActorServiceImpl(actorDao);
	}

	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForAddActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName", "");
		actorDetails.put("lastName","rgy");
		actorService.AddActor(actorDetails);
	}
	
	@Test
	public void ifTheValidInfoIspassedActorRecordMustBeSavedSuccessFullyInDataBaseForAddActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Actor actor=new Actor();
		actor.setFirstName("raghu");
		actor.setLastName("ram");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName", "ntr");
		actorDetails.put("lastName","jr");
		Actor newActor=new Actor();
		newActor.setFirstName(actorDetails.get("firstName"));
		newActor.setLastName(actorDetails.get("lastName"));
		
		Mockito.when(actorDao.AddActor(newActor)).thenReturn(true);
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		assertEquals(true,actorService.AddActor(actorDetails));
	}
	
	@Test(expected=com.flp.fms.exceptions.DuplicateRecordFoundException.class)
	public void shouldNotAddDupliacteActorsForAddActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		//Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName", "ntr");
		actorDetails.put("lastName","jr");
		/*Actor newActor=new Actor();
		newActor.setFirstName(actorDetails.get("firstName"));
		newActor.setLastName(actorDetails.get("lastName"));*/
		
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		Mockito.when(actorService.AddActor(actorDetails));
	}


	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForModifyActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		Map<String, Object> actorDetails=new HashMap<String, Object>();
		actorDetails.put("actorId", 1);
		actorDetails.put("firstName", "");
		actorDetails.put("lastName","rgy");
		actorService.ModifyActor(actorDetails);
	}
	
	@Test(expected=NegativeInputException.class)
	public void inputActorIdShouldNotBeNegativeForModifyActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		Map<String, Object> actorModifiedDetails=new HashMap<String, Object>();
		actorModifiedDetails.put("actorId", -1);
		actorModifiedDetails.put("firstName", "ntr");
		actorModifiedDetails.put("lastName","nandamuri");
		
		actorService.ModifyActor(actorModifiedDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.DuplicateRecordFoundException.class)
	public void shouldNotModifyActorDetailsIfTheDetailsWereDuplicate() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		Actor actor=new Actor();
		actor.setActorId(1);
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Map<String, Object> modifiedActorDetails=new HashMap<String, Object>();
		modifiedActorDetails.put("actorId", 1);
		modifiedActorDetails.put("firstName", "ntr");
		modifiedActorDetails.put("lastName","jr");
		Mockito.when(actorDao.SearchActorById((Integer) modifiedActorDetails.get("actorId"))).thenReturn(actor);
		
		Actor modifiedActor=new Actor();
		modifiedActor.setFirstName((String) modifiedActorDetails.get("firstName"));
		modifiedActor.setLastName((String) modifiedActorDetails.get("lastName"));
		
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		actorService.ModifyActor(modifiedActorDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.RecordDoesNotExistsException.class)
	public void shouldNotModifyActorDetailsIfTheDetailsDoesNotExistsForModifyActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		Actor actor=new Actor();
		actor.setActorId(1);
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Map<String, Object> modifiedActorDetails=new HashMap<String, Object>();
		modifiedActorDetails.put("actorId", 2);
		modifiedActorDetails.put("firstName", "ntr");
		modifiedActorDetails.put("lastName","jr");
		Actor modifiedActor=new Actor();
		modifiedActor.setFirstName((String) modifiedActorDetails.get("firstName"));
		modifiedActor.setLastName((String) modifiedActorDetails.get("lastName"));
		
		Mockito.when(actorDao.SearchActorById((Integer) modifiedActorDetails.get("actorId"))).thenReturn(null);
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		actorService.ModifyActor(modifiedActorDetails);
	}
	
	@Test
	public void ShouldModifyActorDetailsIfTheDetailsWereNotDuplicateAndIfDetailsExistsForModifyActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException {
		Actor actor=new Actor();
		actor.setActorId(1);
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Map<String, Object> actorModifiedDetails=new HashMap<String, Object>();
		actorModifiedDetails.put("actorId", 1);
		actorModifiedDetails.put("firstName", "ntr");
		actorModifiedDetails.put("lastName","nandamuri");
		Mockito.when(actorDao.SearchActorById((Integer) actorModifiedDetails.get("actorId"))).thenReturn(actor);
		
		Actor modifiedActor=new Actor();
		modifiedActor.setFirstName((String) actorModifiedDetails.get("firstName"));
		modifiedActor.setLastName((String) actorModifiedDetails.get("lastName"));
		
		Mockito.when(actorDao.ModifyActor(modifiedActor)).thenReturn(true);
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		
		assertEquals(true,actorService.ModifyActor(actorModifiedDetails));
	}
	
	
	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForRemoveActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Map<String, String> actorDetails=new HashMap<String, String>();
		actorDetails.put("firstName", "");
		actorDetails.put("lastName","rgy");
		actorService.AddActor(actorDetails);
	}
	
	@Test(expected=com.flp.fms.exceptions.RecordDoesNotExistsException.class)
	public void actorShouldNotBeRemovedIfActorRecordDoesNotExitsForRemoveActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException  {
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		
		Map<String, String> removeSerachParameters=new HashMap<String, String>();
		removeSerachParameters.put("firstName", "raghu");
		removeSerachParameters.put("lastName","ram");
		
		Mockito.when(actorDao.SearchActor(removeSerachParameters)).thenReturn(null);
		actorService.RemoveActor(removeSerachParameters);
	}
	
	@Test
	public void actorShouldBeRemovedIfActorRecordExitsForRemoveActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException  {
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		
		Map<String, String> removeSerachParameters=new HashMap<String, String>();
		removeSerachParameters.put("firstName", "ntr");
		removeSerachParameters.put("lastName","jr");
		
		Mockito.when(actorDao.RemoveActor(actor)).thenReturn(true);
		Mockito.when(actorDao.SearchActor(removeSerachParameters)).thenReturn(actor);
		
		assertEquals(true,actorService.RemoveActor(removeSerachParameters));
	}
	
	@Test(expected=com.flp.fms.exceptions.FieldEmptyException.class)
	public void inputFiledsShouldNotBeNullAndEmptyForSeacrhActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Map<String, String> searchParameters=new HashMap<String, String>();
		searchParameters.put("firstName", "");
		searchParameters.put("lastName","rgv");
		actorService.AddActor(searchParameters);
	}
	
	@Test(expected=com.flp.fms.exceptions.RecordDoesNotExistsException.class)
	public void actorDetailsShouldNotBeReturnedIfActorRecordDoesNotExitsForSearchActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException  {
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		
		Map<String, String> serachParameters=new HashMap<String, String>();
		serachParameters.put("firstName", "raghu");
		serachParameters.put("lastName","ram");
		
		Mockito.when(actorDao.SearchActor(serachParameters)).thenReturn(null);
		actorService.SearchActor(serachParameters);
	}
	
	@Test
	public void actorDetailsShouldBeReturnedIfActorRecordExitsForSearchActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException  {
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		
		Map<String, String> searchParameters=new HashMap<String, String>();
		searchParameters.put("firstName", "ntr");
		searchParameters.put("lastName","jr");
		
		Mockito.when(actorDao.SearchActor(searchParameters)).thenReturn(actor);
		
		assertEquals(actor,actorService.SearchActor(searchParameters));
	}
	
	@Test
	public void nullValueShouldBeReturnedIfNoActorDetailsExistsForGetAllActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Mockito.when(actorDao.getAllActor()).thenReturn(null);
		assertEquals(null,actorService.getAllActor());
	}
	
	@Test
	public void actorsListShouldBeReturnedIfActorDetailsExistsForGetAllActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Actor actor=new Actor();
		actor.setActorId(1);
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		List<Actor> allActors = new ArrayList<Actor>();
		allActors.add(actor);
		
		Mockito.when(actorDao.getAllActor()).thenReturn(allActors);
		assertEquals(allActors,actorService.getAllActor());
	}
}
