package com.v2.lt.emplmgmt.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@NamedQueries({
@NamedQuery(name="Role.findRole", query="SELECT role FROM Role role WHERE role.role = :role"),
@NamedQuery(name="Role.findRoleForOrg", query="SELECT role FROM Role role WHERE role.role = :role and role.organization.id = :orgId"),
})
public class Role extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String role;
	
	private String roleDescription;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="organization_id")
	protected Organization organization;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Role other = (Role) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [role=");
		builder.append(role);
		builder.append(", organization=");
		builder.append(organization.getOrgName());
		builder.append("]");
		return builder.toString();
	}
	
}
