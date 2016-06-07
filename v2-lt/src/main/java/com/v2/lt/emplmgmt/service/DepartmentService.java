package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Department;

public interface DepartmentService extends EmpMgmtGenericService{
	
	public Department saveOrUpdate(Department department) throws EmpMgmtGenericException;
	
	public Department findDepartmentByName(String name);

	public Department findDepartmentById(Long id);
	
}
