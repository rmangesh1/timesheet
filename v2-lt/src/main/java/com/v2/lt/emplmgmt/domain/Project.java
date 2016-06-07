package com.v2.lt.emplmgmt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.dozer.Mapping;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.v2.lt.emplmgmt.search.bridges.EmployeeBridge;
import com.v2.lt.emplmgmt.search.bridges.ProjectBridge;

@Entity
@NamedQueries({
@NamedQuery(name="Project.findProjectByName", query="SELECT proj FROM Project proj WHERE proj.projectName = :projectName"),
})
@Indexed
@AnalyzerDef(name = "projcustomanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
@ClassBridge(impl=ProjectBridge.class)
public class Project extends Base implements Comparable<Project>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Mapping
	@Analyzer(definition = "projcustomanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String projectName;
	
	@Mapping
	@Analyzer(definition = "projcustomanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String projectCode;
	
	@Mapping
	@Analyzer(definition = "projcustomanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String location;
	
	@JsonIgnore
	@ManyToMany(mappedBy="projects",cascade = CascadeType.MERGE,targetEntity=Employee.class, fetch = FetchType.EAGER)
	protected Set<Employee> employees = new HashSet<Employee>();
	
	@ManyToOne
	@JoinColumn(name="department_id")
	protected Department department;

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Project [projectName=");
		builder.append(projectName);
		builder.append(", location=");
		builder.append(location);
		builder.append(", employees=");
		builder.append(employees);
		builder.append(", department=");
		builder.append((department != null) ? department.getName() : null);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Project o) {
		return this.projectName.compareTo(o.getProjectName());
	}
	
	public void changeDepartment(Department department) {
		this.department = department;
	}

}

