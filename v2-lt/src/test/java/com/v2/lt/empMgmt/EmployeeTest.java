package com.v2.lt.empMgmt;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.service.DepartmentService;
import com.v2.lt.emplmgmt.service.EmployeeService;
import com.v2.lt.emplmgmt.service.GradeService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.RoleService;
import com.v2.lt.emplmgmt.webservices.EmpMgmtRestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class EmployeeTest {
	
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
	EmpMgmtRestService empMgmtRestService;
	
	@Test
	@Rollback(value=false)
	public void testSaveGrade() {
		Grade grade = new Grade();
		grade.setGrade("A-1-2");
		gradeService.saveOrUpdate(grade);
		
		Grade grade1 = new Grade();
		grade1.setGrade("A-1-3");
		gradeService.saveOrUpdate(grade1);
		
		Grade grade2 = new Grade();
		grade2.setGrade("B-1-1");
		gradeService.saveOrUpdate(grade2);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveRoles() {
		Role role = new Role();
		role.setRole("Java Developer");
		roleService.saveOrUpdate(role);
		
		Role role1 = new Role();
		role1.setRole("Senior Test Engineer");
		roleService.saveOrUpdate(role1);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveDepartment() {
		Department department = new Department();
		department.setName("BFS");
		department.setEmployees(new HashSet<Employee>());
		
		Organization organization = new Organization();
		organization.setId(Long.valueOf(1));
		department.setOrganization(organization);
		department = departmentService.saveOrUpdate(department);
		System.out.println(department.getName());
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveEmployee(){
		
		Grade grade = gradeService.findGrade("A-1-2");
		Role role = roleService.findRole("Java Developer");
		Department department = departmentService.findDepartmentByName("BFS");
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		
		Employee employee = new Employee();
		createNewEmployee("Mangesh",grade, role, department, employee, organization);
		
		employee = employeeService.saveOrUpdate(employee);
		if(employee.getFirstName().equalsIgnoreCase("Mangesh")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}

	private void createNewEmployee(String name,Grade grade, Role role,
			Department department, Employee employee, Organization organization) {
		employee.setFirstName(name);
		employee.setGrade(grade);
		
		Set<Role> roles = new HashSet<Role>();
		Set<Department> departments = new HashSet<Department>();
		departments.add(department);
		roles.add(role);
		employee.setRoles(roles);
		employee.setDepartments(departments);
		employee.setType(EmployeeType.PERMANENT);
		employee.setOrganization(organization);
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUpdateDepartment(){
		Department department = departmentService.findDepartmentByName("BFS");
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		department.getEmployees().add(employee);
		departmentService.saveOrUpdate(department);
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUpdateEmployee() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		Grade grade = gradeService.findGrade("A-1-2");
		//Role role = roleService.findRole("Java Developer");
		employee.setGrade(grade);
		employee.setOrganization(organization);
		//employee.getRoles().add(role);
		employee = employeeService.saveOrUpdate(employee);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveManager() {
		
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		Grade grade = gradeService.findGrade("B-1-1");
		Role role = roleService.findRole("Java Developer");
		Department department = departmentService.findDepartmentByName("BFS");
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		
		Employee manager = new Employee();
		Set<Employee> employees = new HashSet<Employee>();
		employees.add(employee);
		
		manager.setSubordinates(employees);
		
		manager.setFirstName("Punit");
		manager.setGrade(grade);
		
		Set<Role> roles = new HashSet<Role>();
		Set<Department> departments = new HashSet<Department>();
		departments.add(department);
		roles.add(role);
		manager.setRoles(roles);
		manager.setDepartments(departments);
		manager.setType(EmployeeType.PERMANENT);
		manager.setOrganization(organization);
		
		manager = employeeService.saveOrUpdate(manager);
		if(manager.getFirstName().equalsIgnoreCase("Punit")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
		
	}
	
	@Test
	@Rollback(value=false)
	public void testUpdateEmployeeManager() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		Employee manager = employeeService.findEmployeeByName("Punit");
		employee.setManager(manager);
		employee = employeeService.saveOrUpdate(employee);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveEmployeeWithManager(){
		
		Grade grade = gradeService.findGrade("A-1-2");
		Role role = roleService.findRole("Java Developer");
		Department department = departmentService.findDepartmentByName("BFS");
		Organization organization = organizationService.findOrganizationByName("Alaska Enterprises");
		Employee manager = employeeService.findEmployeeByName("Punit");
		
		Employee employee = new Employee();
		createNewEmployee("ABC",grade, role, department, employee, organization);
		employee.setManager(manager);
		
		employee = employeeService.saveOrUpdate(employee);
		
		manager.getSubordinates().add(employee);
		employeeService.saveOrUpdate(manager);
		
		if(employee.getFirstName().equalsIgnoreCase("ABC")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	
	@Test
	public void testEmployeeManager() {
		Employee employee = employeeService.findEmployeeByName("Mangesh");
		System.out.println(employee.getManager());
	}
	
	@Test
	public void testManagerHierarchy() {
		Employee manager = employeeService.findEmployeeByName("Punit");
		System.out.println(manager);
		Assert.assertEquals(manager.getSubordinates().size(), 2);
	}
	
	@Test
	@Rollback(value=false)
	public void testAddProjectToDepartment() {
		empMgmtRestService.getAllDepartments(Long.valueOf(8)).size();

		
	}
	
	
	/*@Test
	public void testEmployeeSorting() {
		Employee employee1 = employeeService.findEmployeeByName("Mangesh");
		Employee employee2 = employeeService.findEmployeeByName("ABC");
		List<Employee> arrList = new ArrayList<Employee>();
		arrList.add(employee1);
		arrList.add(employee2);
		Collections.sort(arrList);
		System.out.println(arrList);
	}
	
	@Test
	@Rollback(value=false)
	public void testUpdateEmployeeEmail() {
		Employee employee1 = employeeService.findEmployeeByName("Mangesh");
		Employee employee2 = employeeService.findEmployeeByName("ABC");
		employee1.setPrimaryEmail("mang@gmail.com");
		employee2.setPrimaryEmail("xabc@gmail.com");
		employee1 = employeeService.updateEmployee(employee1);
		employee2 = employeeService.updateEmployee(employee2);
	}
	
	@Test
	public void testEmployeeSortingOnEmail() {
		Employee employee1 = employeeService.findEmployeeByName("Mangesh");
		Employee employee2 = employeeService.findEmployeeByName("ABC");
		List<Employee> arrList = new ArrayList<Employee>();
		arrList.add(employee1);
		arrList.add(employee2);
		Collections.sort(arrList,new EmployeeEmailComparator());
		System.out.println(arrList);
	}
	
	@Test
	public void testEmployeeGenericSortingString() {
		Employee employee1 = employeeService.findEmployeeByName("Mangesh");
		Employee employee2 = employeeService.findEmployeeByName("ABC");
		List<Employee> arrList = new ArrayList<Employee>();
		arrList.add(employee1);
		arrList.add(employee2);
		Collections.sort(arrList,new EmployeeComparator("empName"));
		System.out.println(arrList);
	}
	
	@Test
	public void testEmployeeGenericSortingLong() {
		Employee employee1 = employeeService.findEmployeeByName("Mangesh");
		Employee employee2 = employeeService.findEmployeeByName("ABC");
		List<Employee> arrList = new ArrayList<Employee>();
		employee1.setEmpId(10L);
		employee2.setEmpId(16L);
		arrList.add(employee1);
		arrList.add(employee2);
		Collections.sort(arrList,new EmployeeComparator("empId"));
		System.out.println(arrList);
	}
	*/
	
	
}
