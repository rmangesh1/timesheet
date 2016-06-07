package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.domain.ActivityMapping;

public class TimeSheetScreenForm {
	
	protected List<ActivityMappingForm> timeSheetForm = new ArrayList<ActivityMappingForm>(); 
	
	protected List<ActivityMapping> activityMappings = new ArrayList<ActivityMapping>();
	
	protected Integer multipleMultiplicityCount;
	
	protected Integer singleMultiplicityCount;

	public List<ActivityMapping> getActivityMappings() {
		return activityMappings;
	}

	public void setActivityMappings(List<ActivityMapping> activityMappings) {
		this.activityMappings = activityMappings;
	}

	public List<ActivityMappingForm> getTimeSheetForm() {
		return timeSheetForm;
	}

	public void setTimeSheetForm(List<ActivityMappingForm> timeSheetForm) {
		this.timeSheetForm = timeSheetForm;
	}

	public Integer getMultipleMultiplicityCount() {
		return multipleMultiplicityCount;
	}

	public void setMultipleMultiplicityCount(Integer multipleMultiplicityCount) {
		this.multipleMultiplicityCount = multipleMultiplicityCount;
	}

	public Integer getSingleMultiplicityCount() {
		return singleMultiplicityCount;
	}

	public void setSingleMultiplicityCount(Integer singleMultiplicityCount) {
		this.singleMultiplicityCount = singleMultiplicityCount;
	}
	
}
