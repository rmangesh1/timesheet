package com.v2.lt.emplmgmt.domain;

import javax.persistence.Entity;

@Entity
public class TSFactor extends Base implements Comparable<TSFactor>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String empTSFactor;
	
	private String amTSFactor;
	
	private Integer factorOrderIndex;
	
	private String factorDescription;
	
	private String empToempTSFactorMultiplicity;
	
	private String orgToempTSFactorMultiplicity;
	
	public Integer getFactorOrderIndex() {
		return factorOrderIndex;
	}

	public void setFactorOrderIndex(Integer factorOrderIndex) {
		this.factorOrderIndex = factorOrderIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpTSFactor() {
		return empTSFactor;
	}

	public void setEmpTSFactor(String empTSFactor) {
		this.empTSFactor = empTSFactor;
	}

	public String getAmTSFactor() {
		return amTSFactor;
	}

	public void setAmTSFactor(String amTSFactor) {
		this.amTSFactor = amTSFactor;
	}
	
	public String getFactorDescription() {
		return factorDescription;
	}

	public void setFactorDescription(String factorDescription) {
		this.factorDescription = factorDescription;
	}
	
	public String getEmpToempTSFactorMultiplicity() {
		return empToempTSFactorMultiplicity;
	}

	public void setEmpToempTSFactorMultiplicity(String empToempTSFactorMultiplicity) {
		this.empToempTSFactorMultiplicity = empToempTSFactorMultiplicity;
	}
	
	public String getOrgToempTSFactorMultiplicity() {
		return orgToempTSFactorMultiplicity;
	}

	public void setOrgToempTSFactorMultiplicity(String orgToempTSFactorMultiplicity) {
		this.orgToempTSFactorMultiplicity = orgToempTSFactorMultiplicity;
	}

	@Override
	public int compareTo(TSFactor o) {
		return this.factorOrderIndex.compareTo(o.getFactorOrderIndex());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((amTSFactor == null) ? 0 : amTSFactor.hashCode());
		result = prime * result + ((empTSFactor == null) ? 0 : empTSFactor.hashCode());
		result = prime * result + ((factorOrderIndex == null) ? 0 : factorOrderIndex.hashCode());
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
		TSFactor other = (TSFactor) obj;
		if (amTSFactor == null) {
			if (other.amTSFactor != null)
				return false;
		} else if (!amTSFactor.equals(other.amTSFactor))
			return false;
		if (empTSFactor == null) {
			if (other.empTSFactor != null)
				return false;
		} else if (!empTSFactor.equals(other.empTSFactor))
			return false;
		if (factorOrderIndex == null) {
			if (other.factorOrderIndex != null)
				return false;
		} else if (!factorOrderIndex.equals(other.factorOrderIndex))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TSFactor [name=" + name + ", empTSFactor=" + empTSFactor + ", amTSFactor=" + amTSFactor
				+ ", factorOrderIndex=" + factorOrderIndex + "]";
	}
	
	
	
}
