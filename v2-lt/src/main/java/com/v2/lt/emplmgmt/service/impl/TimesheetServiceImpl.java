package com.v2.lt.emplmgmt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.dao.TimesheetDAO;
import com.v2.lt.emplmgmt.domain.TimeSheet;
import com.v2.lt.emplmgmt.service.TimesheetService;
import com.v2.lt.mocking.scenario.EmailService;
import com.v2.lt.mocking.scenario.Invoice;
import com.v2.lt.mocking.scenario.LegacyEmailService;


@Service("timesheetService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class TimesheetServiceImpl  extends EmpMgmtGenericServiceImpl<Long,  TimeSheet> implements TimesheetService{
	@Autowired
    protected TimesheetDAO timesheetDAO;
	

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) timesheetDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	timesheetDAO.setEntityManager(entityManager);
    }

	@Override
	public TimeSheet saveOrUpdate(TimeSheet timesheet)
			throws EmpMgmtGenericException {
		EmailService emailService = new LegacyEmailService();
		TimeSheet timesheet1 = findTimesheetById(timesheet.getId());
		if(timesheet1 == null){
			//create mode
			timesheet.setCreatedDate(new Date());
			timesheet1 = super.saveOrUpdate(timesheet);
			emailService.sendInvoice(new Invoice(), "timesheet filled");
		}
		return timesheet1;
	
	}

	@Override
	public TimeSheet findTimesheetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSheet findTimeSheetByWeek(String weekNum,  String year, Long empId) {
		Map<String, Object> params = new HashMap<String, Object	>();
		params.put("weekNum", weekNum);
		params.put("id", empId);
		params.put("year", year);
		String query = "TimeSheet.findTimeSheetByWeek";
		List<TimeSheet> timeSheets = super.findByNamedQueryAndNamedParams(query, params);
		
		if(timeSheets.size() == 0){
    		return null;
    	}
    	
    	if(timeSheets.size() > 1){
    		throw new EmpMgmtGenericException("Multiple timeSheets of same week exists");
    	}
    	
    	return timeSheets.get(0);
	}

	/*@Override
	public List<TimeSheet> findTimesheetByDate(Date fromDate, Date toDate,
			Employee employee) {
		Map<String, Date> params = new HashMap<String, Date>();
		List<TimeSheet> filteredTimeSheets = new ArrayList<>();
		params.put("date1", fromDate);
		params.put("date2", toDate);
		String query = "TimeSheet.findTimeSheetByDate";
		List<TimeSheet> timeSheets = super.findByNamedQueryAndNamedParams(query, params);
		System.out.println("timeSheets.size() " + timeSheets.size());
		if (timeSheets.size() > 0) {
			for (TimeSheet timesheet : timeSheets) {
				if (timesheet.getEmployee().getId() == employee.getId()) {
					filteredTimeSheets.add(timesheet);
				}
			}
		} 
		else {

			return null;
		}
		System.out.println("filteredTimeSheets.size() "+filteredTimeSheets.size());
		return filteredTimeSheets;
	}

	@Override
	public List<TimeSheet> findTimeSheetByDateForEmployee(Date fromDate, Date toDate, Long empId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date1", fromDate);
		params.put("date2", toDate);
		params.put("id", empId);
		String query = "TimeSheet.findTimeSheetByDateForEmployee";
		List<TimeSheet> timeSheets = super.findByNamedQueryAndNamedParams(query, params);
		System.out.println("timeSheets.size() " + timeSheets.size());
		return timeSheets;
	}*/

	
	

	
	
}
    