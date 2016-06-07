package com.v2.lt.emplmgmt.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name="ActivityMapping.findActivityMappingByCondition", query="SELECT am FROM ActivityMapping am WHERE am.conditionForActivities = :condition"),
@NamedQuery(name="ActivityMapping.findActivityMappingsByConditions", query="SELECT am FROM ActivityMapping am WHERE am.conditionForActivities IN :conditions"),
})
public class ActivityMapping extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String conditionForActivities;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="CONDITION_ACTIVITY",
    joinColumns=@JoinColumn(name="activitymapping_id",referencedColumnName="id"),
    inverseJoinColumns=@JoinColumn(name="activity_id",referencedColumnName="id"))
	private Set<Activity> activities;


	public String getConditionForActivities() {
		return conditionForActivities;
	}

	public void setConditionForActivities(String conditionForActivities) {
		this.conditionForActivities = conditionForActivities;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityMapping [conditionForActivities=");
		builder.append(conditionForActivities);
		builder.append(", activities=");
		builder.append(activities);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((conditionForActivities == null) ? 0
						: conditionForActivities.hashCode());
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
		ActivityMapping other = (ActivityMapping) obj;
		if (conditionForActivities == null) {
			if (other.conditionForActivities != null)
				return false;
		} else if (!conditionForActivities.equals(other.conditionForActivities))
			return false;
		return true;
	}

}	
