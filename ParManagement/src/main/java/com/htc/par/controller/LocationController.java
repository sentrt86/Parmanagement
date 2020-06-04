package com.htc.par.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Location;
import com.htc.par.service.LocationServiceImpl;

@Controller
public class LocationController {
	
	@Autowired LocationServiceImpl locationServiceImpl;
	
	@RequestMapping(value="/location",method=RequestMethod.GET)
	public ModelAndView area(Locale locale,Model model)  throws Exception{ 	
		ModelAndView  modelView = new ModelAndView();
		List<Location> locations = locationServiceImpl.getallLocation();
		System.out.println("first controller method");
		if(locations==null)
		{
			modelView.setViewName("location"); 
			return modelView;
		}
		System.out.println("model and view method");
		modelView.addObject("alllocationList", locations);
		modelView.setViewName("location"); 
		return modelView;
		
	}

	@RequestMapping(value="/getNextLocationId",method=RequestMethod.GET)
    @Produces(MediaType.TEXT_PLAIN)
    public @ResponseBody int getNextLocationId()  throws Exception{	
    	System.out.println("getlocationId method calls");
    	return locationServiceImpl.getNextLocationId();	
    }

	
	//Request handler to create the prescreener
	
	@RequestMapping(value="/addLocation", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String addLocation(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		System.out.println("add location method calls in first controller");
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("add method of Location");
		System.out.println("JSON VALUE"+json);
		
		
		try {
			Location location = mapper.readValue(json,Location.class);
			System.out.println("add method of prescreener controller:");
			
			data = locationServiceImpl.createLocation(location);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to update the prescreener
	
	@RequestMapping(value="/updateLocation", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateLocation(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			Location location = mapper.readValue(json,Location.class);
			System.out.println("Update method of first controller");
			System.out.println(location);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			data = locationServiceImpl.updateLocation(location);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to delete the area
	
	@RequestMapping(value="/deleteLocation/{locationId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteLocation(@PathVariable("locationId") String locationId,HttpServletRequest request) throws NumberFormatException, Exception { 
		System.out.println("Delete method in first controller"+locationId);
		String data = locationServiceImpl.deleteLocation(Integer.parseInt(locationId));
		return data;
	}



}
