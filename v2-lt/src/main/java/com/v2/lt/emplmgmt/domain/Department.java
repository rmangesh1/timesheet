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
import javax.persistence.OneToMany;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
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
import com.sun.istack.NotNull;
import com.v2.lt.emplmgmt.search.bridges.DepartmentBridge;
import com.v2.lt.emplmgmt.search.bridges.EmployeeBridge;

@Entity
@NamedQueries({
@NamedQuery(name="Department.findDepartmentByName", query="SELECT dept FROM Department dept WHERE dept.name = :name"),
@NamedQuery(name="Department.findDepartmentById", query="SELECT dept FROM Department dept WHERE dept.id = :id"),
})
@Indexed
@AnalyzerDef(name = "deptcustomanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
@ClassBridge(impl=DepartmentBridge.class)
public class Department extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Analyzer(definition = "deptcustomanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	@NotNull
	protected String name;
	
	@Analyzer(definition = "deptcustomanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String departmentDescription;
	
	@JsonIgnore
	@ManyToMany(mappedBy="departments",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	protected Set<Employee> employees;
	
	@JsonIgnore
	@OneToMany(mappedBy="department",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	protected Set<Project> projects;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="organization_id")
	protected Organization organization;
	
	
	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department [name=");
		builder.append(name);
		builder.append(", employees=");
		builder.append(employees);
		builder.append(", projects=");
		builder.append(projects);
		builder.append(", organization=");
		builder.append(organization.getOrgName());
		builder.append("]");
		return builder.toString();
	}
	
	public void addProject(Project project) {
		if(this.projects == null) {
			this.projects = new HashSet<>();
			projects.add(project);
		} else {
			this.getProjects().add(project);
		}
		if(project != null) {
			project.setDepartment(this);
		}
	}
	
	public void addEmployee(Employee employee) {
		if(this.employees == null) {
			this.employees = new HashSet<>();
			employees.add(employee);
		} else {
			this.getEmployees().add(employee);
		}
		if(employee != null) {
			employee.getDepartments().add(this);
		}
	}
	
}
