package com.v2.lt.emplmgmt.factory;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.service.EmpMgmtGenericService;

public class EMSSearch {

	private EmpMgmtGenericService empMgmtGenericService;
	
	private List<String> orCriterias = new ArrayList<String>();

	public EmpMgmtGenericService getEmpMgmtGenericService() {
		return empMgmtGenericService;
	}

	public void setEmpMgmtGenericService(EmpMgmtGenericService empMgmtGenericService) {
		this.empMgmtGenericService = empMgmtGenericService;
	}

	public List<String> getOrCriterias() {
		return orCriterias;
	}

	public void setOrCriterias(List<String> orCriterias) {
		this.orCriterias = orCriterias;
	}
	
}
