package com.v2.lt.empMgmt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.Constants;
import com.v2.lt.emplmgmt.domain.Activity;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Project;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.domain.UserRole;
import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.ActivityService;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.GradeService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.ProjectService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.service.TSFactorService;
import com.v2.lt.emplmgmt.service.TimesheetService;
import com.v2.lt.emplmgmt.service.UserRoleService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class EMSDemoTest {
	
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
	
	@Autowired
	TSFactorService tsFactorService;
	
	@Test
	@Rollback(value=false)
	public void testSaveOrganization(){
		
		Organization org = new Organization();
		org.setOrgName("Alaska Enterprises");
		org.setPrimaryPhone("1000 99 888");
		org.setPrimaryEmail("alaska@satkon.com");
		//org.setTsFactors(tsFactors);
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Alaska Enterprises")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveOrganization2(){
		Organization org = new Organization();
		org.setOrgName("Lnt Infotech");
		org.setPrimaryPhone("1000 99 888");
		org.setPrimaryEmail("lnt@satkon.com");
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Lnt Infotech")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
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
	
	
	/**
	 *  For organization 1 
	 */
	
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
		role2.setRole("IT Support");
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
	
	/**
	 *  For organization 2 
	 */
	
	@Test
	@Rollback(value=false)
	public void testSaveGrade2() {
		Organization org = organizationService.findOrganizationByName("Lnt Infotech");
		
		Grade grade = new Grade();
		grade.setGrade("G4");
		grade.setOrganization(org);
		gradeService.saveOrUpdate(grade);
		
		Grade grade1 = new Grade();
		grade1.setGrade("G5");
		grade1.setOrganization(org);
		gradeService.saveOrUpdate(grade1);
		
		Grade grade2 = new Grade();
		grade2.setGrade("G6");
		grade2.setOrganization(org);
		gradeService.saveOrUpdate(grade2);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveRoles2() {
		Organization org = organizationService.findOrganizationByName("Lnt Infotech");
		
		Role role = new Role();
		role.setRole("Java Dev");
		role.setOrganization(org);
		roleService.saveOrUpdate(role);
		
		Role role1 = new Role();
		role1.setRole("Test Application Engineer");
		role1.setOrganization(org);
		roleService.saveOrUpdate(role1);
		
		Role role2 = new Role();
		role2.setRole("IT Support");
		role2.setOrganization(org);
		roleService.saveOrUpdate(role2);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveActivity2() {
		Organization org = organizationService.findOrganizationByName("Lnt Infotech");
		Activity activityCoding = new Activity();
		activityCoding.setActivityCode("LT100");
		activityCoding.setActivityName("Coding");
		activityCoding.setOrganization(org);
		activityService.saveOrUpdate(activityCoding);
		
		Activity activityDesigning = new Activity();
		activityDesigning.setActivityCode("LT101");
		activityDesigning.setActivityName("Testing");
		activityDesigning.setOrganization(org);
		activityService.saveOrUpdate(activityDesigning);
	}
	
	
	/**
	 * Admin for org 1
	 */
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee() {
		
		
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		try {
		Grade grade = gradeService.findGradeForOrg("A-1-2",organization.getId() );
		Role role = roleService.findRoleForOrg("IT Support",organization.getId());
		UserRole userRole = userRoleService.findUserRoleByName("ROLE_ADMIN");
		
		Department department = new Department();
		department.setName("Admin");
		Project project = new Project();
		project.setProjectName("Admin Support");
		department.addProject(project);
		department.setOrganization(organization);
		department.setEmployees(new HashSet<Employee>());
		department.setOrganization(organization);
		project.setDepartment(department);
		
		Employee employee = new Employee();
		employee.setFirstName("Mangesh");
		employee.setLastName("Rananavare");
		employee.setPrimaryEmail("mrana@alaska.com");
		employee.setAddress("Thane");
		employee.setPrimaryPhone("56476575");
		employee.setGrade(grade);
		employee.setPassword("newuser123");
		employee.setOrganization(organization);
		
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
		employee.setUserRole(userRole);
		
		
		department.addEmployee(employee);
		
		
		//department = departmentService.saveOrUpdate(department);
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(organization);
		
	}
	
	/**
	 * Admin for org 2
	 */
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee2() {
		Organization organization = organizationService.findOrganizationByName("Lnt Infotech");
		Grade grade = gradeService.findGradeForOrg("G4",organization.getId() );
		Role role = roleService.findRoleForOrg("IT Support",organization.getId());
		UserRole userRole = userRoleService.findUserRoleByName("ROLE_ADMIN");
		
		Department department = new Department();
		department.setName("Admin");
		Project project = new Project();
		project.setProjectName("Admin Support");
		department.addProject(project);
		department.setOrganization(organization);
		department.setEmployees(new HashSet<Employee>());
		department.setOrganization(organization);
		project.setDepartment(department);
		
		Employee employee = new Employee();
		employee.setFirstName("Deepmala");
		employee.setLastName("Phatak");
		employee.setPrimaryEmail("deep@lnt.com");
		employee.setAddress("Thane");
		employee.setPrimaryPhone("56476575");
		employee.setGrade(grade);
		employee.setPassword("newuser123");
		employee.setOrganization(organization);
		
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
		employee.setUserRole(userRole);
		
		
		department.addEmployee(employee);
		
		
		//department = departmentService.saveOrUpdate(department);
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
		
		
		System.out.println(organization);
		
	}
	
	/**
	 * Add new TS factor
	 */
	
	@Test
	@Rollback(value=false)
	public void testAddTSFactor() {
		TSFactor tsFactor = new TSFactor();
		tsFactor.setAmTSFactor("employees:firstName");
		tsFactor.setEmpTSFactor("firstName");
		tsFactor.setFactorOrderIndex(5);
		tsFactor.setName("EMPLOYEE-FN");
		tsFactor.setEmpToempTSFactorMultiplicity(Constants.SINGLE);
		tsFactor.setOrgToempTSFactorMultiplicity(Constants.MULTIPLE);
		tsFactor.setFactorDescription("Depends on employee firstname.");
		
		tsFactorService.saveOrUpdate(tsFactor);
	}
	
	
	/**
	 *  Org 3
	 */
	@Test
	@Rollback(value=false)
	public void testSaveOrganization3(){
		
		Organization org = new Organization();
		org.setOrgName("Apple");
		org.setPrimaryPhone("3300 99 888");
		org.setPrimaryEmail("apple@app.com");
		//org.setTsFactors(tsFactors);
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Apple")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	/**
	 *  For organization 1 
	 */
	
	@Test
	@Rollback(value=false)
	public void testSaveGrade3() {
		Organization org = organizationService.findOrganizationByName("Apple");
		
		Grade grade = new Grade();
		grade.setGrade("AX1");
		grade.setOrganization(org);
		gradeService.saveOrUpdate(grade);
		
		Grade grade1 = new Grade();
		grade1.setGrade("AX2");
		grade1.setOrganization(org);
		gradeService.saveOrUpdate(grade1);
		
		Grade grade2 = new Grade();
		grade2.setGrade("AX3");
		grade2.setOrganization(org);
		gradeService.saveOrUpdate(grade2);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveRoles3() {
		Organization org = organizationService.findOrganizationByName("Apple");
		
		Role role = new Role();
		role.setRole("Java App Developer");
		role.setOrganization(org);
		roleService.saveOrUpdate(role);
		
		Role role2 = new Role();
		role2.setRole("IT Support");
		role2.setOrganization(org);
		roleService.saveOrUpdate(role2);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveActivity3() {
		Organization org = organizationService.findOrganizationByName("Apple");
		Activity activityCoding = new Activity();
		activityCoding.setActivityCode("A100");
		activityCoding.setActivityName("iOS Coding");
		activityCoding.setOrganization(org);
		activityService.saveOrUpdate(activityCoding);
		
		Activity activityDesigning = new Activity();
		activityDesigning.setActivityCode("A101");
		activityDesigning.setActivityName("iOS Designing");
		activityDesigning.setOrganization(org);
		activityService.saveOrUpdate(activityDesigning);
	}
	

	/**
	 * Admin for org 3
	 */
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartmentAndAddEmployee3() {
		
		
		Organization organization = organizationService.findOrganizationByName("Apple");
		try {
		Grade grade = gradeService.findGradeForOrg("AX2",organization.getId() );
		Role role = roleService.findRoleForOrg("IT Support",organization.getId());
		UserRole userRole = userRoleService.findUserRoleByName("ROLE_ADMIN");
		
		Department department = new Department();
		department.setName("Admin");
		Project project = new Project();
		project.setProjectName("Admin Support");
		department.addProject(project);
		department.setOrganization(organization);
		department.setEmployees(new HashSet<Employee>());
		department.setOrganization(organization);
		project.setDepartment(department);
		
		Employee employee = new Employee();
		employee.setFirstName("Ramesh");
		employee.setLastName("Jain");
		employee.setPrimaryEmail("ram@apple.com");
		employee.setAddress("Thane");
		employee.setPrimaryPhone("2356476575");
		employee.setGrade(grade);
		employee.setPassword("newuser123");
		employee.setOrganization(organization);
		
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
		employee.setUserRole(userRole);
		
		
		department.addEmployee(employee);
		
		
		//department = departmentService.saveOrUpdate(department);
		organization.addDepartment(department);
		organization = organizationService.saveOrUpdate(organization);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(organization);
		
	}
	
}
