package com.v2.lt.emplmgmt.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
@NamedQuery(name="Activity.findActivityByName", query="SELECT act FROM Activity act WHERE act.activityName = :activityName"),
@NamedQuery(name="Activity.findActivityById", query="SELECT act FROM Activity act WHERE act.id = :activityId"),
})
public class Activity extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String activityName;
	
	private String activityCode;
	
	private String activityDescription;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="organization_id")
	protected Organization organization;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	
	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Activity [activityName=");
		builder.append(activityName);
		builder.append(", activityCode=");
		builder.append(activityCode);
		builder.append(", activityDescription=");
		builder.append(activityDescription);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((activityCode == null) ? 0 : activityCode.hashCode());
		result = prime * result
				+ ((activityName == null) ? 0 : activityName.hashCode());
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
		Activity other = (Activity) obj;
		if (activityCode == null) {
			if (other.activityCode != null)
				return false;
		} else if (!activityCode.equals(other.activityCode))
			return false;
		if (activityName == null) {
			if (other.activityName != null)
				return false;
		} else if (!activityName.equals(other.activityName))
			return false;
		return true;
	}
	
	
	
}
