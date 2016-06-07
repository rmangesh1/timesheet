package com.v2.lt.emplmgmt.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
import com.sun.istack.NotNull;
import com.v2.lt.emplmgmt.search.bridges.EmployeeBridge;
@Entity
@NamedQueries({
@NamedQuery(name="Employee.findEmployeeByName", query="SELECT emp FROM Employee emp WHERE emp.firstName = :empName"),
@NamedQuery(name="Employee.findEmployeeById", query="SELECT emp FROM Employee emp WHERE emp.id = :id"),
@NamedQuery(name="Employee.findEmployeeByEmail", query="SELECT emp FROM Employee emp WHERE emp.primaryEmail = :primaryEmail"),
})
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
@ClassBridge(impl=EmployeeBridge.class)
public class Employee extends Base implements Comparable<Employee>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Mapping
	@Analyzer(definition = "customanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	@NotNull
	protected String firstName = "";
	
	@Mapping
	@Analyzer(definition = "customanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String lastName = "";
    
	@Mapping
	@Analyzer(definition = "customanalyzer")
	@Field(index=org.hibernate.search.annotations.Index.YES, analyze=Analyze.YES, store = Store.NO)
	protected String primaryEmail = "";
    
	@Mapping
    @NotNull
    protected String primaryPhone = "";
    
	@Mapping
    protected String mobile = "";
    
	@Mapping
    protected String website = "";
    
	@Mapping
    protected String fax = "";
    
    @NotNull
    @ManyToOne
    protected Grade grade;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_ROLE",
    joinColumns=@JoinColumn(name="employee_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id"))
    protected Set<Role> roles = new HashSet<Role>();;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_DEPARTMENT",
    joinColumns=@JoinColumn(name="employee_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="department_id",referencedColumnName="id"))
    protected Set<Department> departments = new HashSet<Department>();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="manager_id")
    protected Employee manager;

    @JsonIgnore
    @OneToMany(mappedBy="manager",fetch = FetchType.EAGER)
    protected Set<Employee> subordinates = new HashSet<Employee>();
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="EMPLOYEE_PROJECT",
    joinColumns=@JoinColumn(name="employee_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="project_id",referencedColumnName="id"))
    protected Set<Project> projects = new HashSet<Project>();
    
    @ManyToOne
    @JoinColumn(name="organization_id")
    protected Organization organization;
    
    @Mapping
    protected EmployeeType type;
    
    @Mapping
    protected String password;
    
    @Mapping
    protected String address;
    
    @ManyToOne
    @JoinColumn(name = "userrole_id")
    protected UserRole userRole;
    
    @JsonIgnore
    @OneToMany(mappedBy="employee", cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    protected Set<TimeSheet> timeSheets = new HashSet<TimeSheet>();
    
    protected Boolean enabled;
    
    @Transient
    protected List<ActivityMapping> activityMappings = new ArrayList<ActivityMapping>();
    
	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
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


	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public EmployeeType getType() {
		return type;
	}

	public void setType(EmployeeType type) {
		this.type = type;
	}
	
	public Set<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public Employee getManager() {
		return manager;
	}
	
	public void changeManager(Employee manager) {
		this.manager.getSubordinates().remove(this);
		this.manager = manager;
	}
    
	public void addDepartment(Department department) {
		this.departments.add(department);
		department.addEmployee(this);
	}
	
	public void removeDepartment(Department department) {
		if(this.departments.contains(department))
			this.departments.remove(department);
	}
	
	public String getEmployeeHierarchy() {
		return this.subordinates.toString();
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public int compareTo(Employee arg0) {
		return this.firstName.compareTo(arg0.getFirstName());
	}
	
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

	public Set<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(Set<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<ActivityMapping> getActivityMappings() {
		return activityMappings;
	}

	public void setActivityMappings(List<ActivityMapping> activityMappings) {
		this.activityMappings = activityMappings;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
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
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empName=");
		builder.append(firstName+" "+lastName);
		builder.append(", primaryEmail=");
		builder.append(primaryEmail);
		builder.append(", primaryPhone=");
		builder.append(primaryPhone);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", website=");
		builder.append(website);
		builder.append(", fax=");
		builder.append(fax);
		builder.append(", address=");
		builder.append(address);
		builder.append(", grade=");
		builder.append(grade);
		builder.append(", roles=");
		builder.append(roles);
		builder.append(", departments=");
		builder.append(displayDepartments());
		builder.append(", manager=");
		builder.append((manager != null) ? manager.getFirstName() : "No Manager");
		builder.append(", subordinates=");
		builder.append(subordinates);
		builder.append(", projects=");
		builder.append(displayProjects());
		builder.append(", organization=");
		builder.append(organization.getOrgName());
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

	public String displayDepartments() {
		StringBuilder sb = new StringBuilder();
		for(Department department : departments) {
			sb.append(department.getName()+",");
		}
		return sb.toString();
	}
	
	public String displayProjects() {
		StringBuilder sb = new StringBuilder();
		for(Project project : projects) {
			sb.append(project.getProjectName()+",");
		}
		return sb.toString();
	}

	public void addTimeSheet(TimeSheet ts) {
		this.timeSheets.add(ts);
	}
	
	public void addProject(Project project) {
		this.getProjects().add(project);
		project.getEmployees().add(this);
	}
	
}
