package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.domain.Actor;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.IActorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SearchActor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();
			IActorService actorService=new ActorServiceImpl();
			
			Actor actor=actorService.SearchActorById(Integer.parseInt(request.getParameter("actorId")));
			
			if(actor != null)
			{
				GsonBuilder gsonBuilder = new GsonBuilder();
				Gson gson = gsonBuilder.registerTypeAdapter(Actor.class, new ActorAdapter()).create();
				
				String myJsonEmpObj=gson.toJson(actor);
					
				out.println(myJsonEmpObj);
			}
		}
		catch (Exception e) 
		{
			response.sendRedirect(request.getContextPath() +"/htmlTemplates/errorMsg.html");
		}
	}
}
