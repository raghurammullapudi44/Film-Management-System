package com.flp.fms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.exceptions.RecordDoesNotExistsException;
import com.flp.fms.service.ActorServiceImpl;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IActorService;
import com.flp.fms.service.IFilmService;

public class RemoveFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");  
			IFilmService filmService=new FilmServiceImpl();
			Map<String,Object> searchParameters=new HashMap<String,Object>();
			searchParameters.put("title", request.getParameter("title"));
			searchParameters.put("rating", Integer.parseInt(request.getParameter("rating")));
			searchParameters.put("releaseDate", request.getParameter("releaseDate"));
			
			if(filmService.RemoveFilm(searchParameters))
			{
				response.sendRedirect(request.getContextPath() +"/htmlTemplates/statusMsg.html");
			}
		}
		catch (RecordDoesNotExistsException e) 
		{
			response.sendRedirect(request.getContextPath() +"/htmlTemplates/RecordDoesNotExistsException.html");
		}
		catch (Exception e) 
		{
			response.sendRedirect(request.getContextPath() +"/htmlTemplates/errorMsg.html");
		}
	}

}
