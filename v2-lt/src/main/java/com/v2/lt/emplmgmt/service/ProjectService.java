package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Project;

public interface ProjectService extends EmpMgmtGenericService {
	
	public Project saveOrUpdate(Project project) throws EmpMgmtGenericException;
	
	public Project findProjectByName(String name);
	
}
