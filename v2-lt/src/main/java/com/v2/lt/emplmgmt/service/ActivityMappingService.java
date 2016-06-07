package com.v2.lt.emplmgmt.service;

import java.util.List;
import java.util.Map;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.ActivityMapping;
import com.v2.lt.emplmgmt.domain.TSFactor;

public interface ActivityMappingService extends EmpMgmtGenericService{
	
	public ActivityMapping saveOrUpdate(ActivityMapping activityMapping) throws EmpMgmtGenericException;
	
	public ActivityMapping findActivityMappingByCondition(String condition);

	public List<ActivityMapping> findActivityMappingsByConditions(List<String> activityMappingConditions);
	
	public Map<TSFactor, List<String>> populateOrganizationActivityMapping(Long orgId) throws NoSuchFieldException, IllegalAccessException;
}
