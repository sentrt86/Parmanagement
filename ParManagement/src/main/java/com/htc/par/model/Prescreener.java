package com.htc.par.model;

import java.io.Serializable;

public class Prescreener implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4483406158550233617L;
	private int preScreenerId;
	private String preScreenerName;
	private String preScreenerEmailId;
	private String preScreenercontactNo;
	private String preScreenerActive;
	
	
	public Prescreener(String preScreenerName, String preScreenerEmailId, String preScreenercontactNo,
			String preScreenerActive) {
		super();
		this.preScreenerName = preScreenerName;
		this.preScreenerEmailId = preScreenerEmailId;
		this.preScreenercontactNo = preScreenercontactNo;
		this.preScreenerActive = preScreenerActive;
	}

	public Prescreener() {
		// TODO Auto-generated constructor stub
	}

	public int getPreScreenerId() {
		return preScreenerId;
	}

	public void setPreScreenerId(int preScreenerId) {
		this.preScreenerId = preScreenerId;
	}

	public String getPreScreenerName() {
		return preScreenerName;
	}

	public void setPreScreenerName(String preScreenerName) {
		this.preScreenerName = preScreenerName;
	}

	public String getPreScreenerEmailId() {
		return preScreenerEmailId;
	}
	
	public void setPreScreenerEmailId(String preScreenerEmailId) {
		this.preScreenerEmailId = preScreenerEmailId;
	}

	public String getPreScreenercontactNo() {
		return preScreenercontactNo;
	}

	public void setPreScreenercontactNo(String preScreenercontactNo) {
		this.preScreenercontactNo = preScreenercontactNo;
	}

	public String getPreScreenerActive() {
		return preScreenerActive;
	}

	public void setPreScreenerActive(String preScreenerActive) {
		this.preScreenerActive = preScreenerActive;
	}

	@Override
	public String toString() {
		return "Prescreener [preScreenerId=" + preScreenerId + ", preScreenerName=" + preScreenerName
				+ ", preScreenerEmailId=" + preScreenerEmailId + ", preScreenercontactNo=" + preScreenercontactNo
				+ ", preScreenerActive=" + preScreenerActive + "]";
	}
	
	
	
	
	

}
