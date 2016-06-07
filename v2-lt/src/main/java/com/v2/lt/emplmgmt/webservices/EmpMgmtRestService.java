package com.v2.lt.emplmgmt.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.Constants;
import com.v2.lt.emplmgmt.domain.Activity;
import com.v2.lt.emplmgmt.domain.ActivityMapping;
import com.v2.lt.emplmgmt.domain.Base;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Project;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.domain.TimeSheet;
import com.v2.lt.emplmgmt.domain.TimeSheetField;
import com.v2.lt.emplmgmt.domain.TimeSheetRecord;
import com.v2.lt.emplmgmt.domain.UserRole;
import com.v2.lt.emplmgmt.factory.EMSSearch;
import com.v2.lt.emplmgmt.factory.ServiceFactory;
import com.v2.lt.emplmgmt.form.ActivityForm;
import com.v2.lt.emplmgmt.form.ActivityMappingForm;
import com.v2.lt.emplmgmt.form.DepartmentForm;
import com.v2.lt.emplmgmt.form.EmployeeDetailsForm;
import com.v2.lt.emplmgmt.form.EmployeeScreenForm;
import com.v2.lt.emplmgmt.form.LoginForm;
import com.v2.lt.emplmgmt.form.ManagerForm;
import com.v2.lt.emplmgmt.form.ProjectForm;
import com.v2.lt.emplmgmt.form.SearchForm;
import com.v2.lt.emplmgmt.form.TSFactorMasterForm;
import com.v2.lt.emplmgmt.form.TimeSheetDetailsForm;
import com.v2.lt.emplmgmt.form.TimeSheetRecordForm;
import com.v2.lt.emplmgmt.form.TimeSheetScreenForm;
import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.ActivityService;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmpMgmtGenericService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.GradeService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.ProjectService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.service.TSFactorService;
import com.v2.lt.emplmgmt.service.TimesheetService;
import com.v2.lt.emplmgmt.service.UserRoleService;

@Path("/empMgmtRestService")
@Transactional
public class EmpMgmtRestService {
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
	
	@Autowired
	ServiceFactory serviceFactory;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	TimesheetService timesheetService;
	
	@GET
	@Path("/getLastLoggedInTime/token/{token}/user/{user}/isMobile/{isMobile}")
	public List<Organization> getOrganizations(){
		List<Organization> organizations = organizationService.findAll();
		return organizations;
	}
	
	@GET
	@Path("/getAllEmployees/{orgId}")
	public List<EmployeeDetailsForm> getEmployees(@PathParam("orgId") Long orgId){
		Organization organization = organizationService.findOrganizationById(orgId);
		List<Employee> employees = new ArrayList<>(organization.getEmployees());
		
		List<EmployeeDetailsForm> empForms = populateEmployeeDetailsForm(employees);
		
		return empForms;
	}
	
	private List<EmployeeDetailsForm> populateEmployeeDetailsForm(List<Employee> employees) {
		EmployeeDetailsForm empForm = new EmployeeDetailsForm();
		List<EmployeeDetailsForm> empForms = new ArrayList<EmployeeDetailsForm>();
		List<String> selectedRoles = null;
		List<String> selectedDepartments = null;
		List<String> selectedProjects = null;
		for(Employee employee : employees) {
			empForm = new EmployeeDetailsForm();
			selectedRoles = new ArrayList<String>();
			selectedDepartments = new ArrayList<String>();
			selectedProjects = new ArrayList<String>();
			DozerBeanMapper mapper = new DozerBeanMapper();
			mapper.map(employee, empForm);
			empForm.setGrade(employee.getGrade());
			for(Role role : employee.getRoles()) {
				selectedRoles.add(role.getId().toString());
			}
			for(Department dept : employee.getDepartments()) {
				selectedDepartments.add(dept.getId().toString());
			}
			for(Project proj : employee.getProjects()) {
				selectedProjects.add(proj.getId().toString());
			}
			empForm.setSelectedRoles(selectedRoles);
			empForm.setSelectedDepartments(selectedDepartments);
			empForm.setSelectedProjects(selectedProjects);
			empForm.setUserRole(employee.getUserRole());
			if(employee.getManager() != null)
			empForm.setManagerId(employee.getManager().getId());
			empForm.setId(employee.getId());
			empForms.add(empForm);
		}
		return empForms;
	}

	@POST
	@Path("/delete/employee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<Boolean> removeEmployeeByIdAndReturnEmployees(@PathParam("id") Long id){
		WSResponse<Boolean> wsResponse = new WSResponse<Boolean>();
		Boolean isDeleted = false;
		
		try {
			Employee employee = employeeService.findEmployeeById(id);
			employee.getDepartments().clear();
			employee.getRoles().clear();
			employee.getProjects().clear();
			employee.getSubordinates().clear();
			
			employeeService.saveOrUpdate(employee);
			employeeService.remove(employee.getId());
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		wsResponse.setObj(isDeleted);
		return wsResponse;
	}
	
	@GET
	@Path("/getAllActivities/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Activity> getAllActivites(@PathParam("orgId") Long orgId){
		Organization organization = organizationService.findOrganizationById(orgId);
		Set<Activity> activities = organization.getActivites();
		return activities;
	}
	
	
	@GET
	@Path("/getAllprojects/{deptId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Project> getAllprojects(@PathParam("deptId") Long deptId){
		Department department = departmentService.findDepartmentById(deptId);
		Set<Project> projects = department.getProjects();
		return projects;
	}
	
	@POST
	@Path("/save/project/{deptId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Project> saveOrUpdateProject(Project project, @PathParam("deptId") Long deptId){
		WSResponse<Project> wsResponse = new WSResponse<Project>();
		Project proj = null;
		if(project.getId() == null) {
			proj = projectService.findProjectByName(project.getProjectName());
			if(proj != null) {
				wsResponse.setErrorResponse("Project with same name already exists!");
			}
		}
		if(wsResponse.getErrorResponse() == null) {
			Department department = departmentService.findDepartmentById(deptId);
			project.setDepartment(department);
			proj = projectService.saveOrUpdate(project);
			wsResponse.setObj(proj);
		}
		return wsResponse;
	}
	
	@POST
	@Path("/save/activity/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Activity> saveOrUpdateActivityAndReturnActivity(Activity activity, @PathParam("orgId") Long orgId){
		WSResponse<Activity> wsResponse = new WSResponse<Activity>();
		Activity act = null;
		if(activity.getId() == null) {
			act = activityService.findActivityByName(activity.getActivityName());
			if(act != null) {
				wsResponse.setErrorResponse("Activity with same name already exists!");
			}
		}
		if(wsResponse.getErrorResponse() == null) {
			Organization organization = organizationService.findOrganizationById(orgId);
			activity.setOrganization(organization);
			act = activityService.saveOrUpdate(activity);
			wsResponse.setObj(act);
		}
		return wsResponse;
	}
	
	@POST
	@Path("/delete/activity/{activtiyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteActivity(@PathParam("activtiyId") Long activtiyId){
		//BooleanWrapper result = new BooleanWrapper();
		Boolean result = false;
		try {
			activityService.deleteIfExisting(activtiyId);
			//result.setResult(true);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Grade services
	 */
	
	@GET
	@Path("/getAllGrades/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Grade> getAllGrades(@PathParam("orgId") Long orgId){
		Organization organization = organizationService.findOrganizationById(orgId);
		Set<Grade> grades = organization.getGrades();
		return grades;
	}
	
	@POST
	@Path("/save/grade/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Grade> saveOrUpdateGradeAndReturnGrade(Grade grade, @PathParam("orgId") Long orgId){
		WSResponse<Grade> wsResponse = new WSResponse<Grade>();
		Grade grad = null;
		if(grade.getId() == null) {
			grad = gradeService.findGradeForOrg(grade.getGrade(),orgId);
			if(grad != null) {
				wsResponse.setErrorResponse("Grade with same name already exists!");
			}
		}
		if(wsResponse.getErrorResponse() == null) {
			Organization organization = organizationService.findOrganizationById(orgId);
			grade.setOrganization(organization);
			grad = gradeService.saveOrUpdate(grade);
			wsResponse.setObj(grad);
		}
		return wsResponse;
	}
	
	@POST
	@Path("/delete/grade/{gradeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteGrade(@PathParam("gradeId") Long gradeId){
		//BooleanWrapper result = new BooleanWrapper();
		Boolean result = false;
		try {
			gradeService.deleteIfExisting(gradeId);
			//result.setResult(true);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Role services
	 */
	
	@GET
	@Path("/getAllRoles/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Role> getAllRoles(@PathParam("orgId") Long orgId){
		Organization organization = organizationService.findOrganizationById(orgId);
		Set<Role> roles = organization.getRoles();
		return roles;
	}
	
	@POST
	@Path("/save/role/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Role> saveOrUpdateRoleAndReturnRole(Role role, @PathParam("orgId") Long orgId){
		WSResponse<Role> wsResponse = new WSResponse<Role>();
		Role empRole = null;
		if(role.getId() == null) {
			empRole = roleService.findRoleForOrg(role.getRole(),orgId);
			if(empRole != null) {
				wsResponse.setErrorResponse("Role with same name already exists!");
			}
		}
		if(wsResponse.getErrorResponse() == null) {
			Organization organization = organizationService.findOrganizationById(orgId);
			role.setOrganization(organization);
			empRole = roleService.saveOrUpdate(role);
			wsResponse.setObj(empRole);
		}
		return wsResponse;
	}
	
	@POST
	@Path("/delete/role/{roleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean deleteRole(@PathParam("roleId") Long roleId){
		//BooleanWrapper result = new BooleanWrapper();
		Boolean result = false;
		try {
			roleService.deleteIfExisting(roleId);
			//result.setResult(true);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * Department services
	 */
	
	@GET
	@Path("/getAllDepartments/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Department> getAllDepartments(@PathParam("orgId") Long orgId){
		Organization organization = organizationService.findOrganizationById(orgId);
		Set<Department> departments = organization.getDepartments();
		return departments;
	}
	
	@POST
	@Path("/save/department/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Department> saveOrUpdateDepartmentAndReturnDepartment(Department department, @PathParam("orgId") Long orgId){
		WSResponse<Department> wsResponse = new WSResponse<Department>();
		Department dept = null;
		if(department.getId() == null) {
			dept = departmentService.findDepartmentByName(department.getName());
			if(dept != null) {
				wsResponse.setErrorResponse("Department with same name already exists!");
			}
		}
		if(wsResponse.getErrorResponse() == null) {
			Organization organization = organizationService.findOrganizationById(orgId);
			department.setOrganization(organization);
			dept = departmentService.saveOrUpdate(department);
			wsResponse.setObj(dept);
		}
		return wsResponse;
	}
	
	@POST
	@Path("/delete/department/{deptId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<Long> deleteDepartment(@PathParam("deptId") Long deptId){
		
		WSResponse<Long> wsResponse = new WSResponse<Long>();
		
		Boolean result = false;
		try {
			departmentService.deleteIfExisting(deptId);
			//result.setResult(true);
			result = true;
			wsResponse.setObj(deptId);
		} catch (Exception e) {
			wsResponse.setErrorResponse("Error while deleting department");
			e.printStackTrace();
		}
		return wsResponse;
	}
	
	/**
	 * Activity Mapping services
	 */
	
	@GET
	@Path("/getAllActivityMappings/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<List<ActivityMappingForm>> getAllActivityMappings(@PathParam("orgId") Long orgId){
		WSResponse<List<ActivityMappingForm>> wsResponse = new WSResponse<List<ActivityMappingForm>>();
		ActivityMappingForm activityMappingForm = null;
		List<ActivityMappingForm> amList = new ArrayList<ActivityMappingForm>();
		try {
			Map<TSFactor, List<String>> orgActivityMapping = activityMappingService.populateOrganizationActivityMapping(orgId);
			for(Map.Entry<TSFactor, List<String>> entry : orgActivityMapping.entrySet()) {
				activityMappingForm = new ActivityMappingForm();
				activityMappingForm.setTsFactor(entry.getKey());
				activityMappingForm.setValues(entry.getValue());
				amList.add(activityMappingForm);
			}
			wsResponse.setObj(amList);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wsResponse;
	}
	
	@POST
	@Path("/getAllActivitiesForMapping/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<List<ActivityForm>> getAllActivityMappings(String condition, @PathParam("orgId") Long orgId){
		WSResponse<List<ActivityForm>> wsResponse = new WSResponse<List<ActivityForm>>();
		String finalCondition = orgId.toString()+Constants.DOT+condition;
		ActivityForm activityForm = null;
		List<ActivityForm> activityFormList = new ArrayList<ActivityForm>();
		Organization organization = organizationService.findOrganizationById(orgId);
		ActivityMapping activityMapping = activityMappingService.findActivityMappingByCondition(finalCondition);
		if(activityMapping == null) {
			for(Activity activity : organization.getActivites()) {
				activityForm = new ActivityForm();
				activityForm.setActivity(activity);
				activityFormList.add(activityForm);
			}
		} else {
			List<Activity> activitesAssigned = new ArrayList<Activity>(activityMapping.getActivities());
			List<Activity> activitiesNotAssigned = (List<Activity>) CollectionUtils.subtract(organization.getActivites(), activitesAssigned);
			for(Activity activity : activitesAssigned) {
				activityForm = new ActivityForm();
				activityForm.setActivity(activity);
				activityForm.setIsChecked(true);
				activityFormList.add(activityForm);
			}
			for(Activity activity : activitiesNotAssigned) {
				activityForm = new ActivityForm();
				activityForm.setActivity(activity);
				activityFormList.add(activityForm);
			}
		}
		wsResponse.setObj(activityFormList);	
		return wsResponse;
	}
	
	@POST
	@Path("/save/activitymapping/{orgId}/{condition}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<List<ActivityForm>> saveOrUpdateActivityMappingAndReturnActivityMapping(List<ActivityForm> activityFormList, @PathParam("orgId") Long orgId, @PathParam("condition") String condition){
		WSResponse<List<ActivityForm>> wsResponse = new WSResponse<List<ActivityForm>>();
		String finalCondition = orgId.toString()+Constants.DOT+condition;
		ActivityMapping activityMapping = activityMappingService.findActivityMappingByCondition(finalCondition);
		if(activityMapping == null) {
			activityMapping = new ActivityMapping();
			Set<Activity> activities = new HashSet<Activity>();
			activityMapping.setConditionForActivities(finalCondition);
			for(ActivityForm activityForm : activityFormList) {
				if(activityForm.getIsChecked())
				activities.add(activityForm.getActivity());
			}
			activityMapping.setActivities(activities);
		} else {
			activityMapping.getActivities().clear();
			for(ActivityForm activityForm : activityFormList) {
				if(activityForm.getIsChecked())
				activityMapping.getActivities().add(activityForm.getActivity());
			}
		}
		activityMappingService.saveOrUpdate(activityMapping);
		wsResponse.setObj(activityFormList);
		return wsResponse;
	}
	
	/**
	 * TSFactorMaster services
	 */
	
	
	@GET
	@Path("/getTSFactorMaster/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<List<TSFactorMasterForm>> getTSFactorMaster(@PathParam("orgId") Long orgId){
		WSResponse<List<TSFactorMasterForm>> wsResponse = new WSResponse<List<TSFactorMasterForm>>();
		List<TSFactorMasterForm> tsFactorFormList = new ArrayList<TSFactorMasterForm>();
		TSFactorMasterForm tsFactorMasterForm = null;

		Organization organization = organizationService.findOrganizationById(orgId);
		List<TSFactor> tsFactorsSelected = new ArrayList<TSFactor>(organization.getTsFactors());
		Collections.sort(tsFactorsSelected);
		List<TSFactor> allTSFactors = tsFactorService.findAll();
		Collections.sort(allTSFactors);
		
		if(tsFactorsSelected == null || tsFactorsSelected.isEmpty()) {
			for(TSFactor tsFactor : allTSFactors) {
				tsFactorMasterForm = new TSFactorMasterForm();
				tsFactorMasterForm.setTsFactor(tsFactor);
				tsFactorFormList.add(tsFactorMasterForm);
			}
		} else {
			List<TSFactor> tsFactorNotSelected = (List<TSFactor>) CollectionUtils.subtract(allTSFactors, tsFactorsSelected);
			Collections.sort(tsFactorNotSelected);
			for(TSFactor tsFactor : tsFactorNotSelected) {
				tsFactorMasterForm = new TSFactorMasterForm();
				tsFactorMasterForm.setTsFactor(tsFactor);
				tsFactorFormList.add(tsFactorMasterForm);
			}
			
			for(TSFactor tsFactor : tsFactorsSelected) {
				tsFactorMasterForm = new TSFactorMasterForm();
				tsFactorMasterForm.setTsFactor(tsFactor);
				tsFactorMasterForm.setIsChecked(true);
				tsFactorFormList.add(tsFactorMasterForm);
			}
			
		}
		
		Collections.sort(tsFactorFormList);
		wsResponse.setObj(tsFactorFormList);
		
		return wsResponse;
	}
	
	
	@POST
	@Path("/save/tsfactors/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<List<TSFactorMasterForm>> saveOrUpdateTSFactorsAndReturnTSFactors(List<TSFactorMasterForm> tsFactorFormList, @PathParam("orgId") Long orgId){
		WSResponse<List<TSFactorMasterForm>> wsResponse = new WSResponse<List<TSFactorMasterForm>>();
		
		Organization organization = organizationService.findOrganizationById(orgId);
		
		if(organization.getTsFactors() == null || organization.getTsFactors().isEmpty()) {
			Set<TSFactor> tsFactors = new HashSet<TSFactor>();
			for(TSFactorMasterForm tsFactorMasterForm : tsFactorFormList) {
				if(tsFactorMasterForm.getIsChecked()) {
					tsFactors.add(tsFactorMasterForm.getTsFactor());
				}
			}
			organization.setTsFactors(tsFactors);
		} else {
			organization.getTsFactors().clear();
			for(TSFactorMasterForm tsFactorMasterForm : tsFactorFormList) {
				if(tsFactorMasterForm.getIsChecked()) {
					organization.getTsFactors().add(tsFactorMasterForm.getTsFactor());
				}
			}
		}
		organizationService.saveOrUpdate(organization);
		wsResponse.setObj(tsFactorFormList);
		return wsResponse;
	}
	
	/**
	 * Organizaiton services
	 * @param orgId
	 * @return
	 */
	
	@GET
	@Path("/getOrgDetails/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<Organization> getOrgDetails(@PathParam("orgId") Long orgId){
		WSResponse<Organization> wsResponse = new WSResponse<Organization>();
		
		Organization organization = organizationService.findOrganizationById(orgId);
		
		wsResponse.setObj(organization);
		
		return wsResponse;
	}
	
	@POST
	@Path("/save/organization")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Organization> saveOrUpdateDepartmentAndReturnDepartment(Organization organization){
		WSResponse<Organization> wsResponse = new WSResponse<Organization>();
		
		Organization orgToUpdate = organizationService.findOrganizationById(organization.getId()); 
		
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.map(organization, orgToUpdate);
		
		organizationService.saveOrUpdate(orgToUpdate);
		
		wsResponse.setObj(orgToUpdate);
		return wsResponse;
	}
	
	@POST
	@Path("/findSearch/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<SearchForm> findSearch(SearchForm searchForm, @PathParam("orgId") Long orgId){
		WSResponse<SearchForm> wsResponse = new WSResponse<SearchForm>();
		
		Map<String, String> andCriteriaMap = new HashMap<String, String>();
		andCriteriaMap.put(Constants.LUCENE_ORGANIZATION_FIELD, orgId.toString());
		
	/*	List<String> orCriterias = new ArrayList<String>();
		orCriterias.add("firstName");
		orCriterias.add("lastName");
		orCriterias.add("primaryEmail");
		orCriterias.add("grade");*/
		
		EMSSearch emsSearchService = serviceFactory.getService(searchForm.getSearchFor());
		
		List<? extends Base> objs = emsSearchService.getEmpMgmtGenericService().searchEntityByCriteria(andCriteriaMap, emsSearchService.getOrCriterias(), searchForm.getValue());
		
		searchForm.setSearchResult(objs);
		
		wsResponse.setObj(searchForm);
		
		return wsResponse;
	}
	
	/**
	 * Project services
	 */
	
	@GET
	@Path("/getAllProjects/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<List<Project>> getAllProjects(@PathParam("orgId") Long orgId){
		
		WSResponse<List<Project>> wsResponse = new WSResponse<List<Project>>();
		
		Organization organization = organizationService.findOrganizationById(orgId);
		
		Set<Department> departments = organization.getDepartments();
		
		List<Project> projects = new ArrayList<Project>();
		for(Department dept : departments) {
			projects.addAll(dept.getProjects());
		}
		Collections.sort(projects);
		wsResponse.setObj(projects);
		
		
		return wsResponse;
	}
	
	@POST
	@Path("/save/project")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Project> saveOrUpdateDepartmentAndReturnDepartment(Project project){
		
		WSResponse<Project> wsResponse = new WSResponse<Project>();
		
		Department department = departmentService.findDepartmentById(project.getDepartment().getId());
		
		if(project.getId() == null) {
			department.addProject(project);
			departmentService.saveOrUpdate(department);
		} else {
			Project projToUpdate = (Project) projectService.find(project.getId());
			DozerBeanMapper mapper = new DozerBeanMapper();
			mapper.map(project, projToUpdate);
			if(projToUpdate.getDepartment().getId() != department.getId()) {
				projToUpdate.changeDepartment(department);
			}
			projectService.saveOrUpdate(projToUpdate);
		}
		
		wsResponse.setObj(project);
		return wsResponse;
	}
	
	@POST
	@Path("/delete/project/{projId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<Long> deleteProject(@PathParam("projId") Long projId){

		WSResponse<Long> wsResponse = new WSResponse<Long>();
		
		Boolean result = false;
		try {
			projectService.deleteIfExisting(projId);
			//result.setResult(true);
			result = true;
			wsResponse.setObj(projId);
		} catch (Exception e) {
			wsResponse.setErrorResponse("Error while deleting project");
			e.printStackTrace();
		}
		return wsResponse;
	}
	
	@GET
	@Path("/getEmployeeScreenDetails/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<EmployeeScreenForm> getEmployeeScreenDetails(@PathParam("orgId") Long orgId){
		WSResponse<EmployeeScreenForm> wsResponse = new WSResponse<EmployeeScreenForm>();
		EmployeeScreenForm empScreen = new EmployeeScreenForm();
		Organization organization = organizationService.findOrganizationById(orgId);
		
		DepartmentForm deptForm = null;
		ProjectForm projForm = null;
		ManagerForm managerForm = null;
		List<ProjectForm> projForms = new ArrayList<ProjectForm>();
		List<DepartmentForm> deptForms = new ArrayList<DepartmentForm>();
		List<ManagerForm> managerForms = new ArrayList<ManagerForm>();
		List<EmployeeType> types = new ArrayList<EmployeeType>();
		
		empScreen.setRoles(new ArrayList<Role>(organization.getRoles()));
		empScreen.setGrades(new ArrayList<Grade>(organization.getGrades()));
		
		for(Department dept : organization.getDepartments()) {
			deptForm = new DepartmentForm();
			deptForm.setDeptId(dept.getId());
			deptForm.setDeptName(dept.getName());
			deptForm.setProjects(new ArrayList<Project>(dept.getProjects()));
			deptForms.add(deptForm);
			for(Project proj : dept.getProjects()) {
				projForm = new ProjectForm();
				projForm.setId(proj.getId());
				projForm.setProjectName(proj.getProjectName());
				projForms.add(projForm);
			}
		}
		
		empScreen.setDepartments(deptForms);
		empScreen.setUserRoles(userRoleService.findAll());
		
		types.add(EmployeeType.PERMANENT);
		types.add(EmployeeType.CONSULTANT);
		empScreen.setTypes(types);
		
		for(Employee employee : organization.getEmployees()) {
			managerForm = new ManagerForm();
			managerForm.setManagerId(employee.getId());
			managerForm.setManagerName(employee.getFirstName()+" "+employee.getLastName());
			managerForms.add(managerForm);
		}
		
		empScreen.setManagers(managerForms);
		empScreen.setProjects(projForms);
		
		wsResponse.setObj(empScreen);
		
		return wsResponse;
	}
	
	
	@POST
	@Path("/save/employee/{orgId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<EmployeeDetailsForm> saveOrUpdateEmployeeAndReturnEmployees(EmployeeDetailsForm employeeDetailsForm,  @PathParam("orgId") Long orgId){
		WSResponse<EmployeeDetailsForm> wsResponse = new WSResponse<EmployeeDetailsForm>();

		Employee employee = null;
		Grade grade = null;
		
		Role role = null;
		Set<Role> roles = new HashSet<Role>();
		Department department = null;
		Set<Department> departments = new HashSet<Department>();
		Project project = null;
		Set<Project> projects = new HashSet<Project>();
		Employee manager = null;
		
		Organization organization = organizationService.findOrganizationById(orgId);
		
		if(employeeDetailsForm.getId() == null) { 
			employee = new Employee();
		} else {
			employee = employeeService.findEmployeeById(employeeDetailsForm.getId());
		}
			
		
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.map(employeeDetailsForm, employee);
		
		grade = (Grade) gradeService.find(employeeDetailsForm.getGrade().getId());
		
		for(String roleId : employeeDetailsForm.getSelectedRoles()) {
			role = (Role) roleService.find(Long.parseLong(roleId));
			roles.add(role);
		}
		
		for(String deptId : employeeDetailsForm.getSelectedDepartments()) {
			department = departmentService.findDepartmentById(Long.parseLong(deptId));
			departments.add(department);
		}
		
		for(String projId : employeeDetailsForm.getSelectedProjects()) {
			project = (Project) projectService.find(Long.parseLong(projId));
			projects.add(project);
		}
		
		if(employeeDetailsForm.getManagerId() != null) {
			manager = employeeService.findEmployeeById(employeeDetailsForm.getManagerId());
		}

		UserRole userRole = (UserRole) userRoleService.find(employeeDetailsForm.getUserRole().getId());
		
		employee.setRoles(roles);
		employee.setDepartments(departments);
		employee.setGrade(grade);
		employee.setProjects(projects);
		employee.setUserRole(userRole);
		employee.setOrganization(organization);
		employee.setManager(manager);
		
		
		employeeService.saveOrUpdate(employee);
		employeeDetailsForm.setId(employee.getId());
		
		
		wsResponse.setObj(employeeDetailsForm);
		
		return wsResponse;
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<Employee> login(LoginForm loginForm){
		WSResponse<Employee> wsResponse = new WSResponse<Employee>();
		
		Employee employee = employeeService.findEmployeeByEmail(loginForm.getUsername());
		
		if(employee == null || !employee.getPassword().equals(loginForm.getPassword())) {
			wsResponse.setErrorResponse("Invalid username/password!");
		} else {
			wsResponse.setObj(employee);
		}
		
		return wsResponse;
	}
	
	@GET
	@Path("/fetchSubordinates/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<List<Employee>> fetchSubordinates(@PathParam("empId") Long empId){
		WSResponse<List<Employee>> wsResponse = new WSResponse<List<Employee>>();
		Employee manager = employeeService.findEmployeeById(empId);
		//List<Employee> employees = employeeService.fetchSubordinates(manager);
		
		wsResponse.setObj(new ArrayList<Employee>(manager.getSubordinates()));
		return wsResponse;
	}
	
	
	/*@GET
	@Path("/viewTimesheet/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<List<TimeSheet>> viewTimesheet(@PathParam("empId") Long empId) throws ParseException{
		WSResponse<List<TimeSheet>> wsResponse = new WSResponse<List<TimeSheet>>();
		Employee employee = employeeService.findEmployeeById(empId);
		System.out.println("empId is "+empId);
		//TimeSheet ts = new TimeSheet();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(new Date().getTime());
		while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
		    calendar.add(Calendar.DATE, -1); 
		}
		Date firstDayOfWeek = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK-1);
		Date lastDayOfweek =calendar.getTime();
		Date firstDayWithZeroTime = dateformat.parse(dateformat.format(firstDayOfWeek));
		Date lastDayWithZeroTime = dateformat.parse(dateformat.format(lastDayOfweek));
		//Date todayWithZeroTime = dateformat.parse(dateformat.format(new Date()));
		
		System.out.println("inside"+firstDayWithZeroTime + lastDayWithZeroTime);
		List<TimeSheet> timeSheets = timesheetService.findTimesheetByDate(firstDayWithZeroTime,lastDayWithZeroTime,employee);
		wsResponse.setObj(timeSheets);
		return wsResponse;
	}*/
	
	
	@GET
	@Path("/getAllEmpTimeSheetFactors/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<TimeSheetScreenForm> getAllEmpTimeSheetFactors(@PathParam("empId") Long empId){
		WSResponse<TimeSheetScreenForm> wsResponse = new WSResponse<TimeSheetScreenForm>();
		Integer multipleMultiplicityCount = 0;
		Integer singleMultiplicityCount = 0;

		TimeSheetScreenForm tsScreenForm = new TimeSheetScreenForm();
		ActivityMappingForm activityMappingForm = null;
		List<ActivityMappingForm> amFormList = new ArrayList<ActivityMappingForm>();
		Employee employee = employeeService.findEmployeeById(empId);
		try {
			Map<TSFactor, List<String>> empTSFactorValueMap = employeeService.populateEmpAndGetTSFactorsMap(employee);
			for(Map.Entry<TSFactor, List<String>> entry : empTSFactorValueMap.entrySet()) {
				activityMappingForm = new ActivityMappingForm();
				activityMappingForm.setTsFactor(entry.getKey());
				activityMappingForm.setValues(entry.getValue());
				amFormList.add(activityMappingForm);
				if(entry.getKey().getEmpToempTSFactorMultiplicity().equalsIgnoreCase(Constants.MULTIPLE)) {
					multipleMultiplicityCount++;
				} else {
					singleMultiplicityCount++;
				}
			}
			tsScreenForm.setActivityMappings(employee.getActivityMappings());
			tsScreenForm.setTimeSheetForm(amFormList);
			tsScreenForm.setMultipleMultiplicityCount(multipleMultiplicityCount);
			tsScreenForm.setSingleMultiplicityCount(singleMultiplicityCount);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		wsResponse.setObj(tsScreenForm);
		return wsResponse;
	}
	
	
	
	@GET
	@Path("/getEmpTimeSheet/{empId}/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<TimeSheetDetailsForm> getEmpTimeSheet(@PathParam("empId") Long empId, @PathParam("date") String dayOfWeek) throws ParseException{
		WSResponse<TimeSheetDetailsForm> wsResponse = new WSResponse<TimeSheetDetailsForm>();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
		
		TimeSheetDetailsForm tsDetailsForm = new TimeSheetDetailsForm();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		Integer weekNum = 0;
		Integer year = 0;
		//Date firstDayWithZeroTime = null;
		Date selectedDate = null;
		
		selectedDate = dateformat.parse(dayOfWeek);
		calendar.setTimeInMillis(selectedDate.getTime());
		
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
		    calendar.add(Calendar.DATE, -1); 
		}
		//Date firstDayOfWeek = calendar.getTime();
		//calendar.add(Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK-1);
		//Date lastDayOfweek =calendar.getTime();
		//firstDayWithZeroTime = dateformat.parse(dateformat.format(firstDayOfWeek));
		//Date lastDayWithZeroTime = dateformat.parse(dateformat.format(lastDayOfweek));
		weekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		year = calendar.get(Calendar.YEAR);
		
		//System.out.println("inside"+firstDayWithZeroTime + lastDayWithZeroTime);
		TimeSheet timeSheet = timesheetService.findTimeSheetByWeek(weekNum.toString(), year.toString(), empId);
		tsDetailsForm.setWeekNum(weekNum.toString());
		populateTimeSheetDetailsForm(tsDetailsForm, timeSheet);
		String dateStr = dateformat.format(selectedDate);
		tsDetailsForm.setDayOfWeek(dateStr);
		
		wsResponse.setObj(tsDetailsForm);
		return wsResponse;
	}

	private void populateTimeSheetDetailsForm(TimeSheetDetailsForm tsDetailsForm, TimeSheet timeSheet) {
		TimeSheetRecordForm tsRecordForm = null;
		DozerBeanMapper mapper = new DozerBeanMapper();
		List<String> selectFactor = null;
		List<TimeSheetRecordForm> tsRecordFormList = new ArrayList<TimeSheetRecordForm>();
		
		
		if(timeSheet != null) {
			Organization org = organizationService.findOrganizationById(timeSheet.getEmployee().getOrganization().getId());
			List<TSFactor> tsFactors = new ArrayList<TSFactor>(org.getTsFactors());
			Collections.sort(tsFactors);
			for(TimeSheetRecord tsRecord : timeSheet.getTimeSheetRecords()) {
				tsRecordForm = new TimeSheetRecordForm();
				selectFactor = new ArrayList<String>();
				int x=0;
				mapper.map(tsRecord, tsRecordForm);
				tsRecordForm.setSelectedActivity(tsRecord.getActivity());
				//tsRecordForm.getActivities().add(tsRecord.getActivity());
				List<TimeSheetField> tsFieldsList = new ArrayList<TimeSheetField>(tsRecord.getTimeSheetFields());
				for(TSFactor tsFactor : tsFactors) {
					if(tsFactor.getEmpToempTSFactorMultiplicity().equals(Constants.SINGLE)) {
						selectFactor.add("");
					} else {
						if(x < tsFieldsList.size()) {
							selectFactor.add(tsFieldsList.get(x).getValue());
							x++;
						} else {
							selectFactor.add("");
						}
					}
				}
				/*for(TimeSheetField tsField : tsRecord.getTimeSheetFields()) {
					selectFactor.add(tsField.getValue());
				}*/
				setActivities(tsRecordForm, tsRecord);
				tsRecordForm.setSelectFactor(selectFactor);
				tsRecordFormList.add(tsRecordForm);
			}
			tsDetailsForm.setTsRecordForm(tsRecordFormList);
			
		} else {
			tsRecordForm = new TimeSheetRecordForm();
			List<TimeSheetRecordForm> tsRecords = new ArrayList<TimeSheetRecordForm>();
			tsRecords.add(tsRecordForm);
			tsDetailsForm.setTsRecordForm(tsRecords);
		}
		
	}
	
	private void setActivities(TimeSheetRecordForm tsRecordForm, TimeSheetRecord tsRecord) {
		ActivityMapping am = activityMappingService.findActivityMappingByCondition(tsRecord.getConditionForActivitiesForRecord());
		tsRecordForm.setActivities(new ArrayList<Activity>(am.getActivities()));
	}

	@POST
	@Path("/save/timesheet/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<TimeSheetDetailsForm> login(TimeSheetDetailsForm tsDetailsForm, @PathParam("empId") Long empId) throws Exception{
		WSResponse<TimeSheetDetailsForm> wsResponse = new WSResponse<TimeSheetDetailsForm>();
		
		TimeSheet timeSheet = null;
		List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
		TimeSheetRecord tsEntry = null;
		TimeSheetField tsField = null;
		List<TimeSheetField> tsFields = null;
		List<TimeSheetRecord> tsEntries = null;
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = dateformat.parse(tsDetailsForm.getDayOfWeek());
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(date.getTime());
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		Integer weekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		Integer year = calendar.get(Calendar.YEAR);
		
		DozerBeanMapper mapper = new DozerBeanMapper();
		
		Employee employee = employeeService.findEmployeeById(empId);
		/*timeSheet = timesheetService.findTimeSheetByWeek(weekNum.toString(), empId);
		
		if(timeSheet != null) {
			timesheetService.delete(timeSheet.getId());
			timeSheet = timesheetService.findTimeSheetByWeek(weekNum.toString(), empId);
		} */
		Organization org = organizationService.findOrganizationById(employee.getOrganization().getId());
		List<TSFactor> tsFactors = new ArrayList<TSFactor>(org.getTsFactors());
		Collections.sort(tsFactors);
		filterTimeSheetFactorsWithMultiplicityMultiple(tsFactors);
		
		tsEntries = new ArrayList<TimeSheetRecord>();
		
		for(TimeSheetRecordForm tsRecord : tsDetailsForm.getTsRecordForm()) {
			
			tsEntry = new TimeSheetRecord();
			tsFields = new ArrayList<TimeSheetField>();
				
			mapper.map(tsRecord, tsEntry);
			tsEntry.setActivity(activityService.findActivityById(tsRecord.getSelectedActivity().getId()));
			for(int i=0; i<tsRecord.getSelectFactor().size(); i++) {
				tsField = new TimeSheetField();
				tsField.setOrderIndex(tsFactors.get(i).getFactorOrderIndex());
				tsField.setName(tsFactors.get(i).getName());
				tsField.setValue(tsRecord.getSelectFactor().get(i));
				tsFields.add(tsField);
			}
			tsEntries.add(tsEntry);
			
			tsEntry.setTimeSheetFields(new TreeSet<TimeSheetField>(tsFields));
			
				
				
		}
		timeSheet = new TimeSheet();
		timeSheet.setWeekNum(weekNum.toString());
		timeSheet.setYear(year.toString());
		timeSheet.setTimeSheetRecords(tsEntries);
		timeSheet.setEmployee(employee);
		
		timesheetService.saveOrUpdate(timeSheet);
		
		wsResponse.setObj(tsDetailsForm);
		return wsResponse;
	}

	private void filterTimeSheetFactorsWithMultiplicityMultiple(List<TSFactor> tsFactors) {
		Iterator<TSFactor> it = tsFactors.listIterator();
		while(it.hasNext()) {
			if(it.next().getEmpToempTSFactorMultiplicity().equalsIgnoreCase(Constants.SINGLE)) {
				it.remove();
			}
		}
	}

	@GET
	@Path("/getTimeSheetBlankRecord")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<TimeSheetRecordForm> getTimeSheetBlankRecord() {
		WSResponse<TimeSheetRecordForm> wsResponse = new WSResponse<TimeSheetRecordForm>();
		
		TimeSheetRecordForm tsRecordForm = new TimeSheetRecordForm();
		
		wsResponse.setObj(tsRecordForm);
		return wsResponse;
	}
	
	@POST
	@Path("/delete/timesheet/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public WSResponse<TimeSheetDetailsForm> deleteTimeSheet(TimeSheetDetailsForm tsDetailsForm, @PathParam("empId") Long empId) throws Exception{
		WSResponse<TimeSheetDetailsForm> wsResponse = new WSResponse<TimeSheetDetailsForm>();
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
		Date date = dateformat.parse(tsDetailsForm.getDayOfWeek());
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(date.getTime());
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		Integer weekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		Integer year = calendar.get(Calendar.YEAR);
		
		TimeSheet timeSheet = timesheetService.findTimeSheetByWeek(weekNum.toString(), year.toString(), empId);
		
		if(timeSheet != null) {
			timesheetService.delete(timeSheet.getId());
		} 
		
		
		
		wsResponse.setObj(tsDetailsForm);
		return wsResponse;
	}
	
	@GET
	@Path("/getEmployee/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResponse<EmployeeDetailsForm> getEmployee(@PathParam("empId") Long empId){
		WSResponse<EmployeeDetailsForm> wsResponse = new WSResponse<EmployeeDetailsForm>();
		Employee employee = employeeService.findEmployeeById(empId);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		List<EmployeeDetailsForm> empForms = populateEmployeeDetailsForm(employees);
		wsResponse.setObj(empForms.get(0));
		return wsResponse;
	}
	
}
