package com.v2.lt.emplmgmt.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.dozer.Mapping;

@Entity
public class TimeSheetRecord extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Set<TimeSheetField> timeSheetFields = new TreeSet<TimeSheetField>();
	
	@Mapping
	private String taskDescription;
	
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
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
	
	@Mapping
	private String conditionForActivitiesForRecord;


	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Set<TimeSheetField> getTimeSheetFields() {
		return timeSheetFields;
	}

	public void setTimeSheetFields(Set<TimeSheetField> timeSheetFields) {
		this.timeSheetFields = timeSheetFields;
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

	public String getConditionForActivitiesForRecord() {
		return conditionForActivitiesForRecord;
	}

	public void setConditionForActivitiesForRecord(String conditionForActivitiesForRecord) {
		this.conditionForActivitiesForRecord = conditionForActivitiesForRecord;
	}
	
}
