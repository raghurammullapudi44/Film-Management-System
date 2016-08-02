package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.fms.domain.Category;
import com.flp.fms.service.FilmServiceImpl;
import com.flp.fms.service.IFilmService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ViewAllCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFilmService actorService=new FilmServiceImpl();
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		
		List<Category> categories=actorService.getAllCategories();
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(Category.class, new CategoryAdapter()).create();
		
		String myJsonObj=gson.toJson(categories);
			
		out.println(myJsonObj);
	}
}
