package com.v2.lt.emplmgmt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
//@NamedQuery(name="TimeSheet.findTimeSheetByDate", query="SELECT ts FROM TimeSheet ts WHERE ts.date between :date1 and :date2"),
@NamedQuery(name="TimeSheet.findTimeSheetById", query="SELECT ts FROM TimeSheet ts WHERE ts.id = :id"),
@NamedQuery(name="TimeSheet.findTimeSheetByWeek", query="SELECT ts FROM TimeSheet ts WHERE ts.weekNum = :weekNum and ts.year = :year and ts.employee.id = :id"),
//@NamedQuery(name="TimeSheet.findTimeSheetByDateForEmployee", query="SELECT ts FROM TimeSheet ts WHERE ts.date between :date1 and :date2 and ts.employee.id = :id"),
})
public class TimeSheet extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String weekNum;
	
	private String year;

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE},  fetch = FetchType.EAGER, orphanRemoval=true)
	private List<TimeSheetRecord> timeSheetRecords = new ArrayList<TimeSheetRecord>();
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="employee_id")
	private Employee employee;
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(String weekNum) {
		this.weekNum = weekNum;
	}

	public List<TimeSheetRecord> getTimeSheetRecords() {
		return timeSheetRecords;
	}

	public void setTimeSheetRecords(List<TimeSheetRecord> timeSheetRecords) {
		this.timeSheetRecords = timeSheetRecords;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "TimeSheet [weekNum=" + weekNum + ", year=" + year + ", timeSheetRecords=" + timeSheetRecords + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weekNum == null) ? 0 : weekNum.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		TimeSheet other = (TimeSheet) obj;
		if (weekNum == null) {
			if (other.weekNum != null)
				return false;
		} else if (!weekNum.equals(other.weekNum))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
}
