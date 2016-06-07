package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapping;

import com.v2.lt.emplmgmt.domain.EmployeeType;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.UserRole;

public class EmployeeDetailsForm {
	
	protected Long id;
	
	@Mapping
	protected String firstName = "";
	
	@Mapping
	protected String lastName = "";
    
	@Mapping
	protected String primaryEmail = "";
    
	@Mapping
    protected String primaryPhone = "";
    
	@Mapping
    protected String mobile = "";
    
	@Mapping
    protected String website = "";
    
	@Mapping
    protected String fax = "";
    
    protected Grade grade;
    
    protected List<String> selectedRoles = new ArrayList<String>();
    
    protected List<String> selectedDepartments = new ArrayList<String>();
    
    protected List<String> selectedProjects = new ArrayList<String>();
    
    @Mapping
    protected EmployeeType type;
    
    protected Long managerId;
    
    @Mapping
    protected UserRole userRole;
    
    @Mapping
    protected String password;
    
    @Mapping
    protected String address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<String> getSelectedDepartments() {
		return selectedDepartments;
	}

	public void setSelectedDepartments(List<String> selectedDepartments) {
		this.selectedDepartments = selectedDepartments;
	}

	public List<String> getSelectedProjects() {
		return selectedProjects;
	}

	public void setSelectedProjects(List<String> selectedProjects) {
		this.selectedProjects = selectedProjects;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
