package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Activity;

public interface ActivityService extends EmpMgmtGenericService{
	
	public Activity saveOrUpdate(Activity activity) throws EmpMgmtGenericException;
	
	public Activity findActivityByName(String name);
	
	public Activity findActivityById(Long id); 
	
}
