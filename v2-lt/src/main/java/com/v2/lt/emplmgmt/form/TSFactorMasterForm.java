package com.v2.lt.emplmgmt.form;

import com.v2.lt.emplmgmt.domain.TSFactor;

public class TSFactorMasterForm implements Comparable<TSFactorMasterForm>{
	
	private TSFactor tsFactor;
	
	private Boolean isChecked = false;

	public TSFactor getTsFactor() {
		return tsFactor;
	}

	public void setTsFactor(TSFactor tsFactor) {
		this.tsFactor = tsFactor;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public int compareTo(TSFactorMasterForm o) {
		return tsFactor.compareTo(o.getTsFactor());
	}
	
}
