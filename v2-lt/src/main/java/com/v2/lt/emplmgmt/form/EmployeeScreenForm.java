package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.domain.UserRole;

public class EmployeeScreenForm {

  	List<Role> roles = new ArrayList<Role>();
    
    List<Grade> grades = new ArrayList<Grade>();
    
    List<DepartmentForm> departments = new ArrayList<DepartmentForm>();
    
    List<ProjectForm> projects = new ArrayList<ProjectForm>();
    
    List<EmployeeType> types = new ArrayList<EmployeeType>();
	
    List<ManagerForm> managers = new ArrayList<ManagerForm>();
    
    List<UserRole> userRoles = new ArrayList<UserRole>();

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public List<DepartmentForm> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentForm> departments) {
		this.departments = departments;
	}

	public List<ProjectForm> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectForm> projects) {
		this.projects = projects;
	}

	public List<EmployeeType> getTypes() {
		return types;
	}

	public void setTypes(List<EmployeeType> types) {
		this.types = types;
	}

	public List<ManagerForm> getManagers() {
		return managers;
	}

	public void setManagers(List<ManagerForm> managers) {
		this.managers = managers;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
    
}
