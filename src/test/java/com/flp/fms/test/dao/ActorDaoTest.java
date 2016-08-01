package com.flp.fms.test.dao;

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

public class ActorDaoTest {
	@Mock private ActorDaoImplForDB actorDao;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToAddActor()
	{
		assertEquals(false,actorDao.AddActor(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullActorWasPassedToAddActor()
	{
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		assertEquals(true,actorDao.AddActor(actor));
	}
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToModifyActor()
	{
		assertEquals(false,actorDao.ModifyActor(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullActorWasPassedToModifyActor()
	{
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		
		Mockito.when(actorDao.ModifyActor(actor)).thenReturn(true);
		assertEquals(true,actorDao.ModifyActor(actor));
	}
	
	@Test
	public void shouldReturnFalseIfNullWasPassedToRemoveActor()
	{
		assertEquals(false,actorDao.RemoveActor(null));
	}
	
	@Test
	public void shouldReturnTrueIfNotNullActorWasPassedToRemoveActor()
	{
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		
		Mockito.when(actorDao.RemoveActor(actor)).thenReturn(true);
		assertEquals(true,actorDao.RemoveActor(actor));
	}
	
	@Test
	public void actorDetailsShouldBeReturnedIfActorRecordExitsForSearchActor()
	{
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		actorDao.AddActor(actor);
		
		Map<String, String> serachParameters=new HashMap<String, String>();
		serachParameters.put("firstName", "ntr");
		serachParameters.put("lastName","jr");
		
		Mockito.when(actorDao.SearchActor(serachParameters)).thenReturn(null);
		assertEquals(null,actorDao.SearchActor(serachParameters));
	}
	
	@Test
	public void actorDetailsShouldNotBeReturnedIfActorRecordDoesNotExitsForSearchActor()
	{
		Actor actor=new Actor();
		actor.setFirstName("ntr");
		actor.setLastName("jr");
		Mockito.when(actorDao.AddActor(actor)).thenReturn(true);
		actorDao.AddActor(actor);
		
		Map<String, String> serachParameters=new HashMap<String, String>();
		serachParameters.put("firstName", "raghu");
		serachParameters.put("lastName","ram");
		
		Mockito.when(actorDao.SearchActor(serachParameters)).thenReturn(actor);
		assertEquals(actor,actorDao.SearchActor(serachParameters));
	}
	
	@Test
	public void nullValueShouldBeReturnedIfNoActorDetailsExistsForGetAllActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException {
		Mockito.when(actorDao.getAllActor()).thenReturn(null);
		assertEquals(null,actorDao.getAllActor());
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
		assertEquals(allActors,actorDao.getAllActor());
	}
}
