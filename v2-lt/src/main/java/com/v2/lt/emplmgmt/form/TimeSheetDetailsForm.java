package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

public class TimeSheetDetailsForm {

	private String dayOfWeek = "";
	
	private String weekNum = "";
	
	private List<TimeSheetRecordForm> tsRecordForm = new ArrayList<TimeSheetRecordForm>();

	public String getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(String weekNum) {
		this.weekNum = weekNum;
	}

	public List<TimeSheetRecordForm> getTsRecordForm() {
		return tsRecordForm;
	}

	public void setTsRecordForm(List<TimeSheetRecordForm> tsRecordForm) {
		this.tsRecordForm = tsRecordForm;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
}
