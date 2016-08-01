package com.flp.fms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class statusMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=(String) request.getAttribute("message");
		PrintWriter out=response.getWriter();
		response.setContentType("application/json");
		
		Gson gson=new Gson();
		String myGsonMsg=gson.toJson(message);
		out.println(myGsonMsg);
	}
}
