package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapping;

import com.v2.lt.emplmgmt.domain.Activity;

public class TimeSheetRecordForm {
	
	@Mapping
	private String conditionForActivitiesForRecord;
	
	private List<String> selectFactor = new ArrayList<String>();
	
	private Activity selectedActivity;
	
	@Mapping
	private String taskDescription = "";
	
	private List<Activity> activities = new ArrayList<Activity>();;
	
	@Mapping
	private Integer hoursMon;
	
	@Mapping
	private Integer hoursTue;
	
	@Mapping
	private Integer hoursWed;
	
	@Mapping
	private Integer hoursThur;
	
	@Mapping
	private Integer hoursFri;
	
	@Mapping
	private Integer hoursSat;
	
	@Mapping
	private Integer hoursSun;

	@Override
	public String toString() {
		return "TimeSheetRecordForm [selectFactor=" + selectFactor + ", selectedActivity=" + selectedActivity
				+ ", taskDescription=" + taskDescription + ", hoursMon=" + hoursMon + ", hoursTue=" + hoursTue
				+ ", hoursWed=" + hoursWed + ", hoursThur=" + hoursThur + ", hoursFri=" + hoursFri + ", hoursSat="
				+ hoursSat + ", hoursSun=" + hoursSun + "]";
	}
	
	public String getConditionForActivitiesForRecord() {
		return conditionForActivitiesForRecord;
	}



	public void setConditionForActivitiesForRecord(String conditionForActivitiesForRecord) {
		this.conditionForActivitiesForRecord = conditionForActivitiesForRecord;
	}



	public List<String> getSelectFactor() {
		return selectFactor;
	}

	public void setSelectFactor(List<String> selectFactor) {
		this.selectFactor = selectFactor;
	}

	public Activity getSelectedActivity() {
		return selectedActivity;
	}

	public void setSelectedActivity(Activity selectedActivity) {
		this.selectedActivity = selectedActivity;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Integer getHoursMon() {
		return hoursMon;
	}

	public void setHoursMon(Integer hoursMon) {
		this.hoursMon = hoursMon;
	}

	public Integer getHoursTue() {
		return hoursTue;
	}

	public void setHoursTue(Integer hoursTue) {
		this.hoursTue = hoursTue;
	}

	public Integer getHoursWed() {
		return hoursWed;
	}

	public void setHoursWed(Integer hoursWed) {
		this.hoursWed = hoursWed;
	}

	public Integer getHoursThur() {
		return hoursThur;
	}

	public void setHoursThur(Integer hoursThur) {
		this.hoursThur = hoursThur;
	}

	public Integer getHoursFri() {
		return hoursFri;
	}

	public void setHoursFri(Integer hoursFri) {
		this.hoursFri = hoursFri;
	}

	public Integer getHoursSat() {
		return hoursSat;
	}

	public void setHoursSat(Integer hoursSat) {
		this.hoursSat = hoursSat;
	}

	public Integer getHoursSun() {
		return hoursSun;
	}

	public void setHoursSun(Integer hoursSun) {
		this.hoursSun = hoursSun;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
}
