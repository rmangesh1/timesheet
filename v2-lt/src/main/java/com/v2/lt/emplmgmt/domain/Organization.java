package com.v2.lt.emplmgmt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.dozer.Mapping;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
@Entity
@NamedQueries({
@NamedQuery(name="Organization.findOrganizationByName", query="SELECT org FROM Organization org WHERE org.orgName = :orgName"),
@NamedQuery(name="Organization.findOrganizationById", query="SELECT org FROM Organization org WHERE org.id = :orgId"),
})
public class Organization extends Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Mapping
	protected String orgName = "";
    
	@Mapping
	protected String company = "";
    
	@Mapping
	protected String primaryEmail = "";
    
    @NotNull
    @Mapping
    protected String primaryPhone = "";
    
    @Mapping
    protected String mobile = "";
    
    @Mapping
    protected String website = "";
    
    @Mapping
    protected String fax = "";
    
    //protected String timeSheetFactors = "";
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ORGANIZATION_TSFACTOR",
    joinColumns=@JoinColumn(name="ORGANIZATION_ID",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="TSFACTORS_ID",referencedColumnName="id"))
    protected Set<TSFactor> tsFactors = new HashSet<TSFactor>();;
    
    @JsonIgnore
    @OneToMany(mappedBy="organization", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    protected Set<Department> departments = new HashSet<Department>();
    
    @JsonIgnore
    @OneToMany(mappedBy="organization", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected Set<Employee> employees = new HashSet<Employee>();
    
    @JsonIgnore
    @OneToMany(mappedBy="organization", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    protected Set<Role> roles = new HashSet<Role>();
    
    @JsonIgnore
    @OneToMany(mappedBy="organization", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    protected Set<Grade> grades = new HashSet<Grade>();
    
    @JsonIgnore
    @OneToMany(mappedBy="organization", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    protected Set<Activity> activites = new HashSet<Activity>();
    
    protected int numOfEmployers;


	public Set<Activity> getActivites() {
		return activites;
	}


	public void setActivites(Set<Activity> activites) {
		this.activites = activites;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
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


	public int getNumOfEmployers() {
		return numOfEmployers;
	}


	public void setNumOfEmployers(int numOfEmployers) {
		this.numOfEmployers = numOfEmployers;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


	public Set<Department> getDepartments() {
		return departments;
	}


	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}
	
	
	public Set<TSFactor> getTsFactors() {
		return tsFactors;
	}


	public void setTsFactors(Set<TSFactor> tsFactors) {
		this.tsFactors = tsFactors;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<Grade> getGrades() {
		return grades;
	}


	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organization [orgName=");
		builder.append(orgName);
		builder.append(", company=");
		builder.append(company);
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
		builder.append(", departments=");
		builder.append(departments);
		builder.append(", employees=");
		builder.append(employees);
		builder.append(", numOfEmployers=");
		builder.append(numOfEmployers);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
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
		Organization other = (Organization) obj;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		return true;
	}
	
	public void addEmployee(Employee employee) {
		if(this.employees == null) {
			this.employees = new HashSet<>();
			employees.add(employee);
		} else {
			this.getEmployees().add(employee);
		}
		if(employee != null) {
			employee.setOrganization(this);
		}
	}
	
	public void addDepartment(Department department) {
		if(this.departments == null) {
			this.departments = new HashSet<>();
			departments.add(department);
		} else {
			this.departments.add(department);
		}
		if(department != null) {
			department.setOrganization(this);
		}
	}
	
	public void addRole(Role role) {
		if(this.roles == null) {
			this.roles = new HashSet<>();
			roles.add(role);
		} else {
			this.roles.add(role);
		}
		if(role != null) {
			role.setOrganization(this);
		}
	}
	
	public void addGrade(Grade grade) {
		if(this.grades == null) {
			this.grades = new HashSet<>();
			grades.add(grade);
		} else {
			this.grades.add(grade);
		}
		if(grade != null) {
			grade.setOrganization(this);
		}
	}
	
	public void addActivity(Activity activity) {
		if(this.activites == null) {
			this.activites = new HashSet<>();
			activites.add(activity);
		} else {
			this.activites.add(activity);
		}
		if(activity != null) {
			activity.setOrganization(this);
		}
	}
	
	public void addTSFactor(TSFactor tsFactor) {
		if(this.tsFactors == null) {
			this.tsFactors = new HashSet<TSFactor>();
			tsFactors.add(tsFactor);
		} else {
			this.tsFactors.add(tsFactor);
		}
	}
    
}
