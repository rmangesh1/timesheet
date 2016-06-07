package com.v2.lt.emplmgmt.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.ActivityService;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.GradeService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.ProjectService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.service.TSFactorService;

@Component
public class ServiceFactory {
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	GradeService gradeService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	ActivityMappingService activityMappingService;
	
	@Autowired
	TSFactorService tsFactorService;
	
	@Autowired
	ProjectService projectService;
	
	Map<String, EMSSearch> serviceMap = new HashMap<String, EMSSearch>();
	
	public EMSSearch getService(String serviceType) {
		loadEMSSearchServices();
		return serviceMap.get(serviceType);
	}

	private void loadEMSSearchServices() {
		
		serviceMap.put("Employee", loadEmployeeSearch());
		
		serviceMap.put("Department", loadDepartmentSearch());
		
		serviceMap.put("Project", loadProjectSearch());
	}

	private EMSSearch loadEmployeeSearch() {
		EMSSearch emsEmployeeSearch = new EMSSearch();
		List<String> orEmployeeCriterias = new ArrayList<String>();
		orEmployeeCriterias.add("firstName");
		orEmployeeCriterias.add("lastName");
		orEmployeeCriterias.add("primaryEmail");
		orEmployeeCriterias.add("grade");
		
		emsEmployeeSearch.setEmpMgmtGenericService(employeeService);
		emsEmployeeSearch.setOrCriterias(orEmployeeCriterias);
		return emsEmployeeSearch;
	}
	
	private EMSSearch loadDepartmentSearch() {
		EMSSearch emsDepartmentSearch = new EMSSearch();
		List<String> orDepartmentCriterias = new ArrayList<String>();
		orDepartmentCriterias.add("name");
		orDepartmentCriterias.add("departmentDescription");
		
		emsDepartmentSearch.setEmpMgmtGenericService(departmentService);
		emsDepartmentSearch.setOrCriterias(orDepartmentCriterias);
		return emsDepartmentSearch;
	}
	
	private EMSSearch loadProjectSearch() {
		EMSSearch emsProjectSearch = new EMSSearch();
		List<String> orProjectCriterias = new ArrayList<String>();
		orProjectCriterias.add("projectName");
		orProjectCriterias.add("projectCode");
		orProjectCriterias.add("location");
		
		emsProjectSearch.setEmpMgmtGenericService(projectService);
		emsProjectSearch.setOrCriterias(orProjectCriterias);
		return emsProjectSearch;
	}
	
}
