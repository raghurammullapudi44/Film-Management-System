package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.domain.Actor;
import com.flp.fms.domain.Film;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IFilmService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ViewALLFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFilmService filmService=new FilmServiceImpl();
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		
		List<Film> films=filmService.getAllFilm();
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Film.class, new FilmAdapter()).create();
		 
		String myJsonEmpObj=gson.toJson(films);
			
		out.println(myJsonEmpObj);
		
	}

}
