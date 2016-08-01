package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.domain.Actor;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.IActorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class ViewAllActors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IActorService actorService=new ActorServiceImpl();
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		
		List<Actor> actors=actorService.getAllActor();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorAdapter()).create();
		
		String myJsonEmpObj=gson.toJson(actors);
			
		out.println(myJsonEmpObj);
	}
}
