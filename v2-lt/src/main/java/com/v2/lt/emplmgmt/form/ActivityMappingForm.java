package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.domain.TSFactor;

public class ActivityMappingForm {
	
	protected TSFactor tsFactor;
	
	protected List<String> values = new ArrayList<String>();

	public TSFactor getTsFactor() {
		return tsFactor;
	}

	public void setTsFactor(TSFactor tsFactor) {
		this.tsFactor = tsFactor;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
}
