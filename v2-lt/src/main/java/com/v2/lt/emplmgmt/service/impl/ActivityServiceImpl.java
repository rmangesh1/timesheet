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
import com.v2.lt.emplmgmt.dao.ActivityDao;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.Activity;
import com.v2.lt.emplmgmt.service.ActivityService;

@Service("activityService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class ActivityServiceImpl extends EmpMgmtGenericServiceImpl<Long, Activity>  implements ActivityService {

	@Autowired
    protected ActivityDao activityDao;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) activityDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	activityDao.setEntityManager(entityManager);
    }
	
	
	@Override
	public Activity saveOrUpdate(Activity activity)
			throws EmpMgmtGenericException {
		
		Activity activity2 = null;
		
		if(activity.getActivityName() == null){
			throw new EmpMgmtGenericException("Activity Name can not be null");
		}
		
		activity2 = findActivityByName(activity.getActivityName());
			if(activity2 == null){
				//create mode
				activity.setCreatedDate(new Date());
				activity2 = super.saveOrUpdate(activity);
			}
			else{
				//update mode
				activity2 = super.saveOrUpdate(activity);
			}
		return activity2;
	}

	@Override
	public Activity findActivityByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("activityName", name);
    	String query = "Activity.findActivityByName";
    	List<Activity> activities = super.findByNamedQueryAndNamedParams(query, params);
    	if(activities.size() == 0){
    		return null;
    	}
    	
    	if(activities.size() > 1){
    		throw new EmpMgmtGenericException("Multiple activities of same name exists");
    	}
    	
    	return activities.get(0);
	}

	@Override
	public Activity findActivityById(Long id) {
		Map<String, Long> params = new HashMap<String, Long>();
    	params.put("activityId", id);
    	String query = "Activity.findActivityById";
    	List<Activity> activities = super.findByNamedQueryAndNamedParams(query, params);
    	if(activities.size() == 0){
    		return null;
    	}
    	
    	if(activities.size() > 1){
    		throw new EmpMgmtGenericException("Multiple activities of same name exists");
    	}
    	
    	return activities.get(0);
	}
	
	
	
}
