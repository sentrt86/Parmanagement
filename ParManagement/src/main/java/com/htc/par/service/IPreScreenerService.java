package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.Prescreener;

@Service
public interface IPreScreenerService {
	
	List<Prescreener> getListAllPreScreener() throws Exception;
	int getnextPrescreenerID() throws Exception;
	String addPrescreener(Prescreener prescreener)throws Exception;
	String updatePrescreener(Prescreener prescreener)throws Exception;
	String deletePrescreener(int prescreenerId)throws Exception;
	

}
