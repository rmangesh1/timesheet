package com.v2.lt.emplmgmt.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@NamedQueries({
@NamedQuery(name="Grade.findGrade", query="SELECT grade FROM Grade grade WHERE grade.grade = :grade"),
@NamedQuery(name="Grade.findGradeForOrg", query="SELECT grade FROM Grade grade WHERE grade.grade = :grade and grade.organization.id = :orgId"),
})
public class Grade extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String grade;
	
	private String gradeDescription;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="organization_id")
	protected Organization organization;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public String getGradeDescription() {
		return gradeDescription;
	}

	public void setGradeDescription(String gradeDescription) {
		this.gradeDescription = gradeDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
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
		Grade other = (Grade) obj;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Grade [grade=");
		builder.append(grade);
		builder.append(", organization=");
		builder.append(organization.getOrgName());
		builder.append("]");
		return builder.toString();
	}

	
	
}
