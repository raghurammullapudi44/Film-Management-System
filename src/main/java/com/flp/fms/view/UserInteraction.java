package com.flp.fms.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Film;
import com.flp.fms.exceptions.DuplicateRecordFoundException;
import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;
import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IActorService;
import com.flp.fms.service.IFilmService;
import com.flp.fms.util.DateValidation;

public class UserInteraction
{
	IFilmService filmService;
	IActorService actorService;
	Scanner sc=new Scanner(System.in);

	public UserInteraction()
	{
		filmService=new FilmServiceImpl();
		actorService=new ActorServiceImpl();
	}
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void AddFilm() throws ParseException, FieldEmptyException, NegativeInputException, DuplicateRecordFoundException
	{
		Map<String, Object> filmDetails=new HashMap<String, Object>();
		
		System.out.println("Enter title");
		filmDetails.put("title",sc.nextLine());
		
		System.out.println("Enter description");
		filmDetails.put("description",sc.nextLine());
		
		System.out.println("Enter release date in yyyy-mm-dd");
		filmDetails.put("releaseDate",dateFormat.parse(dateScanner()));
		
		System.out.println("Enter rental duration");
		filmDetails.put("rentalDuration",Integer.parseInt(sc.nextLine()));
		
		System.out.println("Enter rental rate");
		filmDetails.put("rentalRate",Integer.parseInt(sc.nextLine()));
		
		System.out.println("Enter length of the movie");
		filmDetails.put("length",Integer.parseInt(sc.nextLine()));
		
		System.out.println("Enter replacement cost");
		filmDetails.put("replacementCost",Integer.parseInt(sc.nextLine()));
		
		System.out.println("Enter rating");
		filmDetails.put("rating",Integer.parseInt(sc.nextLine()));
		
		System.out.println("Enter special features");
		filmDetails.put("specialFeatures",sc.nextLine());
				
		
		System.out.println("Enter the language Name");
		filmDetails.put("languageName",sc.nextLine());

		System.out.println("Enter the category Name");
		filmDetails.put("categoryName",sc.nextLine());
	
		System.out.println("Enter the Number of actors");
		int numberOfActors=Integer.parseInt(sc.nextLine());
		
		List actors=new ArrayList();
		for(int i=1;i <= numberOfActors;i++)
		{
			Map<String, String> actorDetails=new HashMap<String, String>();
			System.out.println("Enter the actor first name");
			actorDetails.put("firstName",sc.next());
			System.out.println("Enter the actor last name");
			actorDetails.put("lastName",sc.next());
			actors.add(actorDetails);
		}
		
		filmDetails.put("actors",actors);
		filmService.AddFilm(filmDetails);
		System.out.println("Film added Successfully");
	}
	
	public void ModifyFilm() throws ParseException, FieldEmptyException, NegativeInputException, RecordDoesNotExistsException
	{
		Map<String, Object> searchParameters=new HashMap<String, Object>();
		System.out.println("Please provide all the details to search and then modify film");
		
		System.out.println("enter the film title");
		searchParameters.put("title", sc.nextLine());
					
		System.out.println("enter the rating");
		searchParameters.put("rating", Integer.parseInt(sc.nextLine()));
					
		System.out.println("enter the release Date in yyyy-mm-dd");
		searchParameters.put("releaseDate", dateScanner());
		
		Film existingFilm=filmService.SearchFilm(searchParameters).get(0);
		System.out.println("existingFilm Details are :"+existingFilm);
		
		
		
		while(true)
		{
			System.out.println("Enter the choice of Field to Modify");
			System.out.println("-------------------------------------------------");
			System.out.println("1.title"+"\n"+"2.description"+"\n"+"3.rating"+"\n"+"4.rental rate"+"\n"+"5.replacement cost"+"\n"+"6.length"+"\n"+"7.special features"+"\n"+"8.rental duration"+"\n"+"9.Exit"+"\n");
			int choice=Integer.parseInt(sc.nextLine());
			Map<String, Object> modificationDetails=new HashMap<String, Object>();
			
			if(choice == 9)
			{
				break;
			}
			
			switch(choice)
			{
				case 1:System.out.println("enter the film title");
						modificationDetails.put("title", sc.nextLine());
						break;
				case 2:System.out.println("enter the Description");
						modificationDetails.put("description", sc.nextLine());
						break;
				case 3:System.out.println("enter the rating");
						modificationDetails.put("rating", Integer.parseInt(sc.nextLine()));
						break;
				case 4:System.out.println("enter the Rental Rate");
						modificationDetails.put("rentalRate", Integer.parseInt(sc.nextLine()));
						break;
				case 5:System.out.println("enter the Replacement Cost");
						modificationDetails.put("replacementCost", Integer.parseInt(sc.nextLine()));
						break;
				case 6:System.out.println("enter the length");
						modificationDetails.put("length", Integer.parseInt(sc.nextLine()));
						break;
				case 7:System.out.println("enter the special features");
						modificationDetails.put("spaecialFeatures", sc.nextLine());
						break;
				case 8:System.out.println("enter the Rental duration");
						modificationDetails.put("rentalDuration", Integer.parseInt(sc.nextLine()));
						break;
				case 9:System.out.println("exit");
						break;
				default:System.out.println("Invalid Search choice");
						break;
			}	
			System.out.println("Film Modification status is "+filmService.ModifyFilm(modificationDetails,existingFilm));
		}
		
		
	}
	
	public void RemoveFilm() throws ParseException, FieldEmptyException, NegativeInputException, RecordDoesNotExistsException
	{
		Map<String, Object> removeParameters=new HashMap<String, Object>();
		
		System.out.println("Please provide all the details to search and then remove film");
	
		System.out.println("enter the film title");
		removeParameters.put("title", sc.nextLine());
					
		System.out.println("enter the rating");
		removeParameters.put("rating", Integer.parseInt(sc.nextLine()));
					
		System.out.println("enter the release Date in yyyy-mm-dd");
		removeParameters.put("releaseDate", dateScanner());
			
		System.out.println("Film Removal status is "+filmService.RemoveFilm(removeParameters));
	}
	
	public void SearchFilm() throws FieldEmptyException, NegativeInputException, RecordDoesNotExistsException
	{
		while(true)
		{
			Map<String, Object> searchParameters=new HashMap<String, Object>();
			System.out.println("Search Menu");
			System.out.println("-------------------------------------------------");
			System.out.println("1.By film Title"+"\n"+"2.By Actor Id"+"\n"+"3.By Rating"+"\n"+"4.By Language Id"+"\n"+"5.By Category Id"+"\n"+"6.By Actor Name"+"\n"+"7.By Language Name"+"\n"+"8.By Category Name"+"\n"+"9.Exit"+"\n");
			System.out.println("enter the choice of search");
			int choice=Integer.parseInt(sc.nextLine());
			
			if(choice == 9)
			{
				break;
			}
			
			switch(choice)
			{
				case 1:System.out.println("enter the film title");
						searchParameters.put("title", sc.nextLine());
						break;
				case 2:System.out.println("enter the Actor Id");
						searchParameters.put("actorId", Integer.parseInt(sc.nextLine()));
						break;
				case 3:System.out.println("enter the rating");
						searchParameters.put("rating", Integer.parseInt(sc.nextLine()));
						break;
				case 4:System.out.println("enter the Language Id");
						searchParameters.put("languageId", Integer.parseInt(sc.nextLine()));
						break;
				case 5:System.out.println("enter the Category Id");
						searchParameters.put("categoryId", Integer.parseInt(sc.nextLine()));
						break;
				case 6:System.out.println("enter the Actor First Name");
						searchParameters.put("firstName", sc.nextLine());
						System.out.println("enter the Actor last Name");
						searchParameters.put("lastName", sc.nextLine());
						break;
				case 7:System.out.println("enter the Language Name");
						searchParameters.put("languageName", sc.nextLine());
						break;
				case 8:System.out.println("enter the Category Name");
						searchParameters.put("categoryName", sc.nextLine());
						break;
				case 9:System.out.println("exit");
						break;
				default:System.out.println("Invalid Search choice");
						break;
			}
			System.out.println("Film Details are "+filmService.SearchFilm(searchParameters));
		}
	}
	
	public void getAllFilm()
	{
		List<Film> films=filmService.getAllFilm();
		System.out.println("All films details are "+films);
	}
	
	public void AddActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException
	{
		Actor actor=new Actor();
		Map<String, String> actorDetails=new HashMap<String, String>();
		System.out.println("Enter the actor first name");
		actorDetails.put("firstName",sc.nextLine());
		System.out.println("Enter the actor last name");
		actorDetails.put("lastName",sc.nextLine());
		actorService.AddActor(actorDetails);
		
		System.out.println("Actor added Successfully");
	}
	
	public void ModifyActor() throws FieldEmptyException, NegativeInputException, DuplicateRecordFoundException, RecordDoesNotExistsException
	{
		Map<Object, Object> actorDetails=new HashMap<Object, Object>();
		System.out.println("Enter the actor id to modify");
		actorDetails.put("actorId",Integer.parseInt(sc.nextLine()));
		System.out.println("Enter the actor modified first name");
		actorDetails.put("firstName",sc.nextLine());
		System.out.println("Enter the actor modified last name");
		actorDetails.put("lastName",sc.nextLine());
		System.out.println("Actor details modified status is "+actorService.ModifyActor(actorDetails));	
	}
	
	public void RemoveActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException
	{
		Actor actor=new Actor();
		Map<String, String> searchParameters=new HashMap<String, String>();
		System.out.println("Enter the actor first name");
		searchParameters.put("firstName",sc.nextLine());
		System.out.println("Enter the actor last name");
		searchParameters.put("lastName",sc.nextLine());
		
		System.out.println("Actor Removal status is "+actorService.RemoveActor(searchParameters));
	}
	
	public void SearchActor() throws NegativeInputException, RecordDoesNotExistsException, FieldEmptyException
	{
		Actor actor=new Actor();
		Map<String, String> searchParameters=new HashMap<String, String>();
		System.out.println("Enter the actor first name");
		searchParameters.put("firstName",sc.nextLine());
		System.out.println("Enter the actor last name");
		searchParameters.put("lastName",sc.nextLine());
		
		System.out.println("All actors details are "+actorService.SearchActor(searchParameters));
	}
	
	public void getAllActor()
	{
		List<Actor> actors= actorService.getAllActor();
		System.out.println("All actors details are "+actors);
	}
	
	private String dateScanner()
	{
		while(true)
		{
			String date=sc.nextLine();
			if(new DateValidation().isValidFormat(date))
			{
				return date;
			}
			else
			{
				System.out.println("Invalid Date Format, please enter the date in yyyy-mm-dd format");
			}
		}
	}
}
