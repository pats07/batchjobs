package com.batch.batchtest.domain;

import java.math.BigDecimal;

public class Vendor {

	private BigDecimal VENDORID;
	private String COMPANY;
	private String LAST_NAME;
	private String EMAIL;
	private String GENDER;
	private String IP_ADDRESS;

	public BigDecimal getVENDORID() {
		return VENDORID;
	}

	public void setVENDORID(BigDecimal vENDORID) {
		VENDORID = vENDORID;
	}

	public String getCOMPANY() {
		return COMPANY;
	}

	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public String getIP_ADDRESS() {
		return IP_ADDRESS;
	}

	public void setIP_ADDRESS(String iP_ADDRESS) {
		IP_ADDRESS = iP_ADDRESS;
	}

}
