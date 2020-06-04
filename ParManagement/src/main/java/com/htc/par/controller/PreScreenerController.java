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
import com.htc.par.model.Area;
import com.htc.par.model.Prescreener;
import com.htc.par.service.PrescreenerServiceImpl;

@Controller
public class PreScreenerController {
		@Autowired PrescreenerServiceImpl prescreenerServiceImpl;
	
		@RequestMapping(value="/prescreener", method=RequestMethod.GET) 
		public ModelAndView area(Locale locale,Model model)  throws Exception{ 	
			System.out.println("main controller prescreener to get the prescreeener all");
		ModelAndView  modelView = new ModelAndView();
		List<Prescreener> prescreeners = prescreenerServiceImpl.getListAllPreScreener();
		System.out.println("first controller method");
		System.out.println(prescreeners);
		if( prescreeners==null)
		{
			modelView.setViewName("prescreener"); 
			return modelView;
		}
		System.out.println("model and view method");
		modelView.addObject("allPrescreenerList", prescreeners);
		modelView.setViewName("prescreener"); 
		return modelView;
	    }
	
	    @RequestMapping(value="/getNextPrescreenerId",method=RequestMethod.GET)
	    @Produces(MediaType.TEXT_PLAIN)
	    public @ResponseBody int getNextPrescreenerId()  throws Exception{	
	    	System.out.println("getNextPrescreenerId method calls");
	    	return prescreenerServiceImpl.getnextPrescreenerID();		
	    }
	
		
		//Request handler to create the prescreener
		
		@RequestMapping(value="/addPrescreener", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String addPrescreener(@RequestBody String json,HttpServletRequest request) throws Exception { 		
			
			String data = null;
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("add method of prescreener");
			System.out.println("JSON VALUE"+json);
			
			
			try {
				Prescreener prescreener = mapper.readValue(json,Prescreener.class);
				System.out.println("add method of prescreener controller:");
				
				data = prescreenerServiceImpl.addPrescreener(prescreener);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to update the prescreener
		
		@RequestMapping(value="/updatePrescreener", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String updatePrescreener(@RequestBody String json,HttpServletRequest request) throws Exception { 		
			
			String data = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				
				Prescreener prescreener = mapper.readValue(json,Prescreener.class);
				System.out.println("Update method of first controller");
				System.out.println(prescreener);
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				data = prescreenerServiceImpl.updatePrescreener(prescreener);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to delete the area
		
		@RequestMapping(value="/deletePrescreener/{preScreenerId}", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.TEXT_PLAIN)
		public @ResponseBody String deletePrescreener(@PathVariable("preScreenerId") String preScreenerId,HttpServletRequest request) throws NumberFormatException, Exception { 
			System.out.println("Delete method"+preScreenerId);
			String data = prescreenerServiceImpl.deletePrescreener(Integer.parseInt(preScreenerId));
			return data;
		}


}
