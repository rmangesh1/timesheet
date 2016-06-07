package com.v2.lt.empMgmt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v2.lt.emplmgmt.common.Constants;
import com.v2.lt.emplmgmt.domain.Activity;
import com.v2.lt.emplmgmt.domain.ActivityMapping;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Project;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.domain.TimeSheet;
import com.v2.lt.emplmgmt.domain.TimeSheetField;
import com.v2.lt.emplmgmt.domain.TimeSheetRecord;
import com.v2.lt.emplmgmt.domain.UserRole;
import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.ActivityService;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.GradeService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.ProjectService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.service.TimesheetService;
import com.v2.lt.emplmgmt.service.UserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class EMSTest {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	GradeService gradeService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	ActivityMappingService activityMappingService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TimesheetService timesheetService;
	
	@Test
	@Rollback(value=false)
	public void testSaveGrade() {
		Organization org = organizationService.findOrganizationByName("Alaska Enterprises");
		
		Grade grade = new Grade();
		grade.setGrade("A-1-2");
		grade.setOrganization(org);
		gradeService.saveOrUpdate(grade);
		
		Grade grade1 = new Grade();
		grade1.setGrade("A-1-3");
		grade1.setOrganization(org);
		gradeService.saveOrUpdate(grade1);
		
		Grade grade2 = new Grade();
		grade2.setGrade("B-1-1");
		grade2.setOrganization(org);
		gradeService.saveOrUpdate(grade2);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveRoles() {
		Organization org = organizationService.findOrganizationByName("Alaska Enterprises");
		
		Role role = new Role();
		role.setRole("Java Developer");
		role.setOrganization(org);
		roleService.saveOrUpdate(role);
		
		Role role1 = new Role();
		role1.setRole("Senior Test Engineer");
		role1.setOrganization(org);
		roleService.saveOrUpdate(role1);
		
		Role role2 = new Role();
		role2.setRole("Designer");
		role2.setOrganization(org);
		roleService.saveOrUpdate(role2);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveActivity() {
		Organization org = organizationService.findOrganizationByName("Alaska Enterprises");
		Activity activityCoding = new Activity();
		activityCoding.setActivityCode("X100");
		activityCoding.setActivityName("Coding");
		activityCoding.setOrganization(org);
		activityService.saveOrUpdate(activityCoding);
		
		Activity activityDesigning = new Activity();
		activityDesigning.setActivityCode("X101");
		activityDesigning.setActivityName("Designing");
		activityDesigning.setOrganization(org);
		activityService.saveOrUpdate(activityDesigning);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveUserRoles() {
		UserRole userRole = new UserRole();
		userRole.setUserRole("ROLE_ADMIN");
		userRoleService.saveOrUpdate(userRole);
		
		UserRole userRole1 = new UserRole();
		userRole1.setUserRole("ROLE_USER");
		userRoleService.saveOrUpdate(userRole1);
		
		UserRole userRole2 = new UserRole();
		userRole2.setUserRole("ROLE_MANAGER");
		userRoleService.saveOrUpdate(userRole2);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee() {
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		Grade grade = gradeService.findGrade("B-1-1");
		Role role = roleService.findRole("Java Developer");
		
		Department department = new Department();
		department.setName("BFS");
		Project project = new Project();
		project.setProjectName("NORDEA");
		department.addProject(project);
		department.setOrganization(organization);
		department.setEmployees(new HashSet<Employee>());
		
		Employee employee = new Employee();
		employee.setFirstName("Viral");
		employee.setLastName("Gada");
		employee.setPrimaryEmail("Viral.Gada@lntinfotech.com");
		employee.setAddress("Thane");
		employee.setPrimaryPhone("");
		employee.setGrade(grade);
		
		Set<Role> roles = new HashSet<Role>();
		Set<Department> departments = new HashSet<Department>();
		Set<Project> projects = new HashSet<Project>();
		projects.add(project);
		departments.add(department);
		roles.add(role);
		employee.setRoles(roles);
		employee.setDepartments(departments);
		employee.setType(EmployeeType.PERMANENT);
		employee.addProject(project);
		employee.setOrganization(organization);
		
		
		
		department.addEmployee(employee);
		
		
		//department = departmentService.saveOrUpdate(department);
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
		
		
		System.out.println(organization);
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUpdateEmployee3() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		
		employee.setLastName("Rananavare");
		employee.setPrimaryEmail("mangesh.rananavare@lntinfotech.com");
		employee.setAddress("Thane");
		employee.setPrimaryPhone("9920068896");
		
		//department = departmentService.saveOrUpdate(department);
		employee = employeeService.saveOrUpdate(employee);
		
		
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee3() {
		Organization organization = organizationService.findOrganizationByName("Lnt Infotech");
		Grade grade = gradeService.findGrade("A-1-3");
		Role role = roleService.findRole("Java Developer");
		
		Employee employee = new Employee();
		employee.setFirstName("Deepmala");
		employee.setGrade(grade);
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		employee.setRoles(roles);
		employee.setType(EmployeeType.PERMANENT);
		employee.setOrganization(organization);
		
		
		
		//department = departmentService.saveOrUpdate(department);
		employee = employeeService.saveOrUpdate(employee);
		
		
		System.out.println(organization);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee1() {
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		//Department department = departmentService.findDepartmentByName("BFS");
		Department department = new Department();
		department.setName("RMG");
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee2() {
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		Department department = departmentService.findDepartmentByName("BFS");
		
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
	}
	
	@Test
	@Rollback(value=false)
	public void testRemoveEmployeeDepartment() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		Department department = departmentService.findDepartmentByName("BFS");
		
		System.out.println(employee.getDepartments());
		
		employee.removeDepartment(department);
		employee = employeeService.saveOrUpdate(employee);
	}
	
	@Test
	@Rollback(value=false)
	public void testAddEmployeeDepartment() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		Department department = departmentService.findDepartmentByName("BFS");
		
		System.out.println(employee.getDepartments());
		
		employee.addDepartment(department);
		employee = employeeService.saveOrUpdate(employee);
	}
	
	/*@Test
	@Rollback(value=false)
	public void testUpdateDepartment() {
		Department department = departmentService.findDepartmentByName("TSL");
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		
		department.setOrganization(organization);
		department = departmentService.saveOrUpdate(department);
	}*/
	
	
	@Test
	@Rollback(value=false)
	public void testEmployeeFillTimeSheet() throws ParseException {
		Employee employee = employeeService.findEmployeeByName("Ram");
		Activity activityCoding = activityService.findActivityByName("Coding");
		Activity activityDesigning = activityService.findActivityByName("Designing");
		
		TimeSheetRecord tsr = new TimeSheetRecord();
		tsr.setActivity(activityCoding);
		tsr.setHoursMon(8);
		tsr.setTaskDescription("Coding e module");
		TimeSheetField tsf = new TimeSheetField();
		tsf.setOrderIndex(1);
		tsf.setName("Department");
		tsf.setValue("BFS");
		Set<TimeSheetField> tsfs = new HashSet<TimeSheetField>();
		tsfs.add(tsf);
		tsr.setTimeSheetFields(tsfs);
		
				
		List<TimeSheetRecord> timeSheetRecords = new ArrayList<TimeSheetRecord>();
		timeSheetRecords.add(tsr);
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy 00:00:00");
		TimeSheet ts = new TimeSheet();
		Date todayWithZeroTime =dateformat.parse(dateformat.format(new Date()));
		System.out.println(dateformat.format(new Date()));
		//ts.setDate(todayWithZeroTime);
		ts.setWeekNum("33");
		ts.setTimeSheetRecords(timeSheetRecords);
		ts.setYear("2015");
		ts.setEmployee(employee);
		List<TimeSheet> timeSheets = new ArrayList<TimeSheet>();
		timeSheets.add(ts);
		timesheetService.saveOrUpdate(ts);
		
		//employee.addTimeSheet(ts);
		
		//employee = employeeService.saveOrUpdate(employee);
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUpdateTimeSheet() throws ParseException {
		TimeSheet timeSheet = timesheetService.findTimesheetById(Long.valueOf(2));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		Date date = sdf.parse("02-09-2015 00:00:00");
		//timeSheet.setDate(date);
		timesheetService.saveOrUpdate(timeSheet);
	}
	
	@Test
	@Rollback(value=false)
	public void testFetchEmployee() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		
		//System.out.println(employee.getTimeSheets().get(0).getWorkedHours());
		
		for(TimeSheet ts : employee.getTimeSheets()) {
			System.out.println(ts+"\n-----------");
		}
	}
	
	
	@Test
	@Rollback(value=false)
	public void testActivityMapping() {
		Activity activityCoding = activityService.findActivityByName("Coding");
		Activity activityDesigning = activityService.findActivityByName("Designing");
		
		String condition = "BFS.NETS.Java Developer";
		
		ActivityMapping am = new ActivityMapping();
		am.setConditionForActivities(condition);
		Set<Activity> activities = new HashSet<Activity>();
		activities.add(activityDesigning);
		activities.add(activityCoding);
		
		am.setActivities(activities);
		
		
		activityMappingService.saveOrUpdate(am);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testFetchActivityMapping() {
		ActivityMapping am =  activityMappingService.findActivityMappingByCondition("BFS.NETS.Java Developer");
		
		System.out.println(am);
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testFetchEmployeeById() {
		Employee employee = employeeService.findEmployeeById(1L);
		
		System.out.println(employee);
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUserRole() {
		UserRole userRole = new UserRole();
		userRole.setUserRole("ROLE_ADMIN");
		
		userRoleService.saveOrUpdate(userRole);
		
	}
	
	@Test
	public void testUserRoleFetch() {
		
		System.out.println(userRoleService.findUserRoleByName("ROLE_ADMIN"));
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testAddProject() {
		Project project = new Project();
		project.setProjectName("NETS");
		Department department = departmentService.findDepartmentByName("BFS");
		
		department.addProject(project);
		
		departmentService.saveOrUpdate(department);
	}
	
	@Test
	@Rollback(value=false)
	public void testAddProjectToEmployee() {
		Employee employee = employeeService.findEmployeeByName("Deepmala");
		Project project = projectService.findProjectByName("NORDEA");
		
		employee.addProject(project);
		
		employeeService.saveOrUpdate(employee);
	}
	
	@Test
	public void testEmployeePopulate() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		try {
			employeeService.populateEmployeeActivities(employee);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAMPopulate() {
		
		try {
			activityMappingService.populateOrganizationActivityMapping(27L);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFetchActivity() throws JsonProcessingException {
		Activity activity = activityService.findActivityByName("Coding");
		try {
		ObjectMapper mapper = new ObjectMapper();
		
		String str = mapper.writeValueAsString(activity);
		System.out.println(str);
		System.out.println("yoomss22");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	@Rollback(value=false)
	public void testSearchEmployeesBy(){
		
		Map<String, String> andCriteriaMap = new HashMap<String, String>();
		andCriteriaMap.put(Constants.LUCENE_ORGANIZATION_FIELD, "1");
		
		List<String> orCriterias = new ArrayList<String>();
		orCriterias.add("firstName");
		orCriterias.add("lastName");
		orCriterias.add("primaryEmail");
		orCriterias.add("grade");
		
		String searchValue = "mangesh";
		
		List<Employee> emps = employeeService.searchEmployeesOnCriteria(andCriteriaMap, orCriterias, searchValue);
		System.out.println("size recs "+emps.size());
		System.out.println(emps);
		if(emps.size() > 0){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	
	/*@Test
	@Rollback(value=false)
	public void testfindTimesheetByDate() throws ParseException {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy 00:00:00");
		TimeSheet ts = new TimeSheet();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(new Date().getTime());
		while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
		    calendar.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
		}
		Date firstDayOfWeek = calendar.getTime();
		calendar.add(Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK-1);
		Date lastDayOfweek =calendar.getTime();
		Date firstDayWithZeroTime = dateformat.parse(dateformat.format(firstDayOfWeek));
		Date lastDayWithZeroTime = dateformat.parse(dateformat.format(lastDayOfweek));
		Employee employee = employeeService.findEmployeeById(Long.valueOf(5));
	    timesheetService.findTimesheetByDate(firstDayWithZeroTime, lastDayWithZeroTime,employee);
		
		
	}*/
	
	@Test
	@Rollback(value=false)
	public void testDeleteTimesheet() throws Exception {
		
		TimeSheet timesheet = timesheetService.findTimeSheetByWeek("36","2015", 3L);
		
		timesheetService.deleteIfExisting(timesheet.getId());
		
		System.out.println("deleted");
		
		
	}
	
}
