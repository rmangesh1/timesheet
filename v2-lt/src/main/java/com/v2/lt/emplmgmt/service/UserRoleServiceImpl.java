package com.v2.lt.emplmgmt.service;

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
import com.v2.lt.emplmgmt.dao.UserRoleDao;
import com.v2.lt.emplmgmt.domain.UserRole;
import com.v2.lt.emplmgmt.service.impl.EmpMgmtGenericServiceImpl;

@Service("userRoleService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class UserRoleServiceImpl extends EmpMgmtGenericServiceImpl<Long, UserRole> implements UserRoleService{


	@Autowired
    protected UserRoleDao userRoleDao;

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) userRoleDao);
    }
    
	@PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	userRoleDao.setEntityManager(entityManager);
    }
	
	@Override
	public UserRole saveOrUpdate(UserRole userRole)
			throws EmpMgmtGenericException {
		
		UserRole userRole2 = null;
		
		if(userRole.getUserRole() == null){
			throw new EmpMgmtGenericException("userRole can not be null");
		}
		
		userRole2 = findUserRoleByName(userRole.getUserRole());
			if(userRole2 == null){
				//create mode
				userRole.setCreatedDate(new Date());
				userRole2 = super.saveOrUpdate(userRole);
			}
			else{
				//update mode
				userRole2 = super.saveOrUpdate(userRole);
			}
		return userRole2;
	}

	@Override
	public UserRole findUserRoleByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("userRole", name);
    	String query = "UserRole.findUserRoleByName";
    	List<UserRole> userRoles = super.findByNamedQueryAndNamedParams(query, params);
    	if(userRoles.size() == 0){
    		return null;
    	}
    	
    	if(userRoles.size() > 1){
    		throw new EmpMgmtGenericException("Multiple userroles of same name exists");
    	}
    	
    	return userRoles.get(0);
	}

}
