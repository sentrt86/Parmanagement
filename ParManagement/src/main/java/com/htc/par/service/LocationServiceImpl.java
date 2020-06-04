package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Location;
import com.htc.par.model.ResponseException;

@Service
public class LocationServiceImpl implements ILocationService{
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	

	@Override
	public List<Location> getallLocation() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/location/getLocations";
		try {
			System.out.println("Get list all location method in service");
			System.out.println("url:"+url);
			
			ResponseEntity<List<Location>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {});
			List<Location> allLocations = response.getBody();
			System.out.println(response.getBody());
			  for(Location location: allLocations) {
				  location.setLocationActive(location.getLocationActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allLocations;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
		
		
	}

	@Override
	public String createLocation(Location location) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/location/addLocation";
		location.setLocationActive(location.getLocationActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Location> request = new HttpEntity<>(location);
		System.out.println("Location objects in firt service"+location);
		System.out.println("request object in first service");
		System.out.println(request);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	@Override
	public String updateLocation(Location location) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/location/updateLocation";
		location.setLocationActive(location.getLocationActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Location> request = new HttpEntity<>(location);
		System.out.println("Location objects in update firt service"+location);
		System.out.println("request object if first service");
		System.out.println(request);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	@Override
	public String deleteLocation(int locationId) throws Exception {
		ResponseException responseException = null;
		System.out.println("delete method in first service method"+locationId);
		String url = parServiceApiUrl + "/location/deleteLocation/"+locationId ;
		HttpEntity<Integer> request = new HttpEntity<>(locationId);
		try {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});		
		return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
			System.out.println("error:"+ responseException.getMessage());
		}
		
		return responseException.getMessage();
	}

	@Override
	public int getNextLocationId() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/location/getNextLocationId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());	
		}
	}
	
}


