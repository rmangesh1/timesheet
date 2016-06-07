package com.v2.lt.emplmgmt.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.Constants;
import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.ActivityMappingDao;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.ActivityMapping;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.service.ActivityMappingService;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.util.CommonUtil;
import com.v2.lt.emplmgmt.util.TimeSheetUtil;

@Service("activityMappingService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class ActivityMappingServiceImpl extends EmpMgmtGenericServiceImpl<Long, ActivityMapping> implements ActivityMappingService{

	@Autowired
    protected ActivityMappingDao activityMappingDao;
	
	@Autowired
	protected OrganizationService organizationService;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) activityMappingDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	activityMappingDao.setEntityManager(entityManager);
    }
	
	
	@Override
	public ActivityMapping saveOrUpdate(ActivityMapping activityMapping) throws EmpMgmtGenericException {
		
		ActivityMapping activityMapping2 = null;
		
		if(activityMapping.getConditionForActivities() == null){
			throw new EmpMgmtGenericException("activityMapping condition can not be null");
		}
		
		activityMapping2 = findActivityMappingByCondition(activityMapping.getConditionForActivities());
			if(activityMapping2 == null){
				//create mode
				activityMapping.setCreatedDate(new Date());
				activityMapping2 = super.saveOrUpdate(activityMapping);
			}
			else{
				//update mode
				activityMapping2 = super.saveOrUpdate(activityMapping);
			}
		return activityMapping2;
	}

	@Override
	public ActivityMapping findActivityMappingByCondition(String condition) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("condition", condition);
    	String query = "ActivityMapping.findActivityMappingByCondition";
    	List<ActivityMapping> activityMappings = super.findByNamedQueryAndNamedParams(query, params);
    	if(activityMappings.size() == 0){
    		return null;
    	}
    	
    	if(activityMappings.size() > 1){
    		throw new EmpMgmtGenericException("Multiple activityMappings of same condition exists");
    	}
    	
    	return activityMappings.get(0);
	}

	@Override
	public List<ActivityMapping> findActivityMappingsByConditions(List<String> activityMappingConditions) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
    	params.put("conditions", activityMappingConditions);
    	String query = "ActivityMapping.findActivityMappingsByConditions";
    	List<ActivityMapping> activityMappings = super.findByNamedQueryAndNamedParams(query, params);
    	
    	return activityMappings;
	}

	@Override
	public Map<TSFactor, List<String>> populateOrganizationActivityMapping(Long orgId) throws NoSuchFieldException, IllegalAccessException {
		
		Organization organization = organizationService.findOrganizationById(orgId);
		
		Set<TSFactor> tsFactors = organization.getTsFactors();
		
		List<TSFactor> tsFactorsList = new ArrayList<TSFactor>(tsFactors);
		
		Collections.sort(tsFactorsList);
		
		String factors [] = TimeSheetUtil.getAMTimeSheetFactors(tsFactorsList);
		
		Map<String, List<String>> factorValuesMap = CommonUtil.extractValuesOfDependingFactors(organization, factors);
		
		Map<TSFactor, List<String>> tsFactorValuesMap = TimeSheetUtil.getTSFactorValuesMap(factorValuesMap, tsFactorsList);
		
		System.out.println(tsFactorValuesMap);
		
		return tsFactorValuesMap;
	}

}
