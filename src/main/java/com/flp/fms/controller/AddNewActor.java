package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.IActorService;


public class AddNewActor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");  
			IActorService actorService=new ActorServiceImpl();
			Map<String,String> actorDetails=new HashMap<String,String>();
			actorDetails.put("firstName", request.getParameter("firstName"));
			actorDetails.put("lastName", request.getParameter("lastName"));
			
			if(actorService.AddActor(actorDetails))
			{
				response.sendRedirect(request.getContextPath() +"/htmlTemplates/statusMsg.html");
			}
		}
		catch (Exception e) 
		{
			response.sendRedirect(request.getContextPath() +"/htmlTemplates/errorMsg.html");
		}
	}
}
