package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.domain.Project;

public class DepartmentForm {
	
	private Long deptId;
	
	private String deptName;
	
	List<Project> projects = new ArrayList<Project>();

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
