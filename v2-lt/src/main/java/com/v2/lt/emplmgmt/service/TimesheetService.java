
package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.TimeSheet;


public interface TimesheetService extends EmpMgmtGenericService{
			
	TimeSheet saveOrUpdate(TimeSheet timesheet) throws EmpMgmtGenericException;
	
	public TimeSheet findTimesheetById(Long id);
	
	//public List<TimeSheet> findTimesheetByDate(Date fromDate,Date toDate,Employee employee);

	//public List<TimeSheet> findTimeSheetByDateForEmployee(Date fromDate,Date toDate, Long empId);
	
	public TimeSheet findTimeSheetByWeek(String weekNum, String year, Long empId);
}

