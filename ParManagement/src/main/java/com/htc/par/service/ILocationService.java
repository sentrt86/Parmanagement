package com.htc.par.service;

import java.util.List;

import com.htc.par.model.Location;

public interface ILocationService {
	
	List<Location> getallLocation()throws Exception;
	int getNextLocationId()throws Exception;
	String createLocation(Location location)throws Exception;
	String updateLocation(Location location)throws Exception;
	String deleteLocation(int locationId) throws Exception;
	

}
