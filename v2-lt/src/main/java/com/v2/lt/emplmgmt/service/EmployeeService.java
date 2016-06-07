package com.v2.lt.emplmgmt.service;

import java.util.List;
import java.util.Map;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.TSFactor;

public interface EmployeeService extends EmpMgmtGenericService {
	
	public Employee saveOrUpdate(Employee employee) throws EmpMgmtGenericException;
	
	public Employee findEmployeeByName(String name);
	
	public Employee findEmployeeById(Long id);
	
	public Employee populateEmployeeActivities(Employee employee) throws NoSuchFieldException, IllegalAccessException;
	
	public Employee findEmployeeByEmail(String email);
	
	public List<Employee> fetchSubordinates(Employee employee);
	
	public List<Employee> searchEmployeesOnCriteria(Map<String, String> andSearchCriterias, List<String> orSearchCriterias, String searchValue)throws EmpMgmtGenericException;

	public Map<TSFactor, List<String>> populateEmpAndGetTSFactorsMap(Employee employee) throws NoSuchFieldException, IllegalAccessException;
	
}
