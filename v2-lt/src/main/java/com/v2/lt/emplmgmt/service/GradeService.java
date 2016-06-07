package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.Grade;

public interface GradeService extends EmpMgmtGenericService {

	public Grade saveOrUpdate(Grade grade) throws EmpMgmtGenericException;
	
	public Grade findGrade(String grade);

	Grade findGradeForOrg(String grade, Long orgId);
	
}
