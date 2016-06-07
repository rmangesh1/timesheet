package com.v2.lt.emplmgmt.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.annotation.Logged;
import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.EmployeeDao;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.ActivityMapping;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.util.CommonUtil;
import com.v2.lt.emplmgmt.util.TimeSheetUtil;

@Service("employeeService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class EmployeeServiceImpl  extends EmpMgmtGenericServiceImpl<Long, Employee> implements EmployeeService{
	
	@Autowired
    protected EmployeeDao employeeDAO;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected DepartmentService departmentService;
	
	@Autowired
	protected ActivityMappingService activityMappingService;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) employeeDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	employeeDAO.setEntityManager(entityManager);
    }
	
	@Override
	@Transactional
	public Employee saveOrUpdate(Employee employee) throws EmpMgmtGenericException {
		// TODO Auto-generated method stub
		
		Employee resultedEmployee = null;
		
		Employee employee2 = null;
		
		if(employee.getFirstName() == null){
			throw new EmpMgmtGenericException("Emp Name can not be null");
		}
		
		if(employee.getGrade() == null){
			throw new EmpMgmtGenericException("Emp grade can not be null");
		}
		
		if(employee.getId() != null)
		employee2 = findEmployeeById(employee.getId());
			if(employee2 == null){
				//create mode
				employee.setCreatedDate(new Date());
				resultedEmployee = super.saveOrUpdate(employee);
			}
			else{
				resultedEmployee = super.saveOrUpdate(employee);
			}
		return resultedEmployee;
	}
	

	@Override
	@javax.transaction.Transactional(value=TxType.NOT_SUPPORTED)
	public Employee findEmployeeByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("empName", name);
    	String query = "Employee.findEmployeeByName";
    	List<Employee> employees = super.findByNamedQueryAndNamedParams(query, params);
    	if(employees.size() == 0){
    		return null;
    	}
    	
    	if(employees.size() > 1){
    		throw new EmpMgmtGenericException("Multiple organizations of same name exists");
    	}
    	
    	return employees.get(0);
	}

	@Override
	@javax.transaction.Transactional(value=TxType.NOT_SUPPORTED)
	public Employee findEmployeeById(Long id) {
		Map<String, Long> params = new HashMap<String, Long>();
    	params.put("id", id);
    	String query = "Employee.findEmployeeById";
    	List<Employee> employees = super.findByNamedQueryAndNamedParams(query, params);
    	if(employees.size() == 0){
    		return null;
    	}
    	
    	if(employees.size() > 1){
    		throw new EmpMgmtGenericException("Multiple employees of same id exists");
    	}
    	
    	return employees.get(0);
	}

	@Override
	@Logged
	public Employee populateEmployeeActivities(Employee employee) throws NoSuchFieldException, IllegalAccessException {
		
		Set<TSFactor> tsFactors = employee.getOrganization().getTsFactors();
		
		List<TSFactor> tsFactorsList = new ArrayList<TSFactor>(tsFactors);
		
		Collections.sort(tsFactorsList);
		
		String factors [] = TimeSheetUtil.getEmpTimeSheetFactors(tsFactorsList);
		
		Map<String, List<String>> factorValuesMap = CommonUtil.extractValuesOfDependingFactors(employee, factors);
		
		Map<TSFactor, List<String>> tsFactorValuesMap = TimeSheetUtil.getTSFactorValuesMap(factorValuesMap, tsFactorsList);
		
		List<List<String>> factorValues = TimeSheetUtil.getOrderedArrayOfFactorValues(tsFactorsList, tsFactorValuesMap);
		
		List<String> activityMappingConditions = TimeSheetUtil.makeCriterias(factorValues);
		
		List<ActivityMapping> activityMappings = activityMappingService.findActivityMappingsByConditions(activityMappingConditions);
		
		employee.setActivityMappings(activityMappings);
		
		return employee;
		
	}

	@Override
	public Employee findEmployeeByEmail(String email) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("primaryEmail", email);
    	String query = "Employee.findEmployeeByEmail";
    	List<Employee> employees = super.findByNamedQueryAndNamedParams(query, params);
    	if(employees.size() == 0){
    		return null;
    	}
    	
    	if(employees.size() > 1){
    		throw new EmpMgmtGenericException("Multiple employees of same email exists");
    	}
    	
    	return employees.get(0);
	}

	@Override
	public List<Employee> searchEmployeesOnCriteria(Map<String, String> andSearchCriterias,
			List<String> orSearchCriterias, String searchValue) throws EmpMgmtGenericException {
		List<Employee> emps = employeeDAO.searchEntityByCriteria(andSearchCriterias, orSearchCriterias, searchValue);
		return emps;
	}

	@Override
	public List<Employee> fetchSubordinates(Employee employee) {
		List<Employee> employees = employeeDAO.findAll();
		List<Employee> employeesWithManager = new ArrayList<>();
		for (Employee emp : employees) {
			if (emp.getManager() != null) {
				if (emp.getManager().getId() == employee.getId()) {
					employeesWithManager.add(emp);
				}
			}
		}
		return employeesWithManager;

	}

	@Override
	public Map<TSFactor, List<String>> populateEmpAndGetTSFactorsMap(Employee employee) throws NoSuchFieldException, IllegalAccessException {
		
		Set<TSFactor> tsFactors = employee.getOrganization().getTsFactors();
		
		List<TSFactor> tsFactorsList = new ArrayList<TSFactor>(tsFactors);
		
		Collections.sort(tsFactorsList);
		
		String factors [] = TimeSheetUtil.getEmpTimeSheetFactors(tsFactorsList);
		
		Map<String, List<String>> factorValuesMap = CommonUtil.extractValuesOfDependingFactors(employee, factors);
		
		Map<TSFactor, List<String>> tsFactorValuesMap = TimeSheetUtil.getTSFactorValuesMap(factorValuesMap, tsFactorsList);
		
		List<List<String>> factorValues = TimeSheetUtil.getOrderedArrayOfFactorValues(tsFactorsList, tsFactorValuesMap);
		
		List<String> activityMappingConditions = TimeSheetUtil.makeCriterias(factorValues, employee.getOrganization().getId());
		
		List<ActivityMapping> activityMappings = activityMappingService.findActivityMappingsByConditions(activityMappingConditions);
		
		employee.setActivityMappings(activityMappings);
		
		return tsFactorValuesMap;
		
	}

}
