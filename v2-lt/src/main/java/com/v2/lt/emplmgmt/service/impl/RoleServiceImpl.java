package com.v2.lt.emplmgmt.service.impl;

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
import com.v2.lt.emplmgmt.dao.RoleDao;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.service.RoleService;

@Service("roleService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class RoleServiceImpl extends EmpMgmtGenericServiceImpl<Long, Role> implements RoleService {

	@Autowired
    protected RoleDao roleDAO;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) roleDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	roleDAO.setEntityManager(entityManager);
    }
	
	@Override
	public Role saveOrUpdate(Role role) throws EmpMgmtGenericException {
		return super.saveOrUpdate(role);
	}

	@Override
	public Role findRole(String role) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("role", role);
    	String query = "Role.findRole";
    	List<Role> roles = super.findByNamedQueryAndNamedParams(query, params);
    	if(roles.size() == 0){
    		return null;
    	}
    	
    	if(roles.size() > 1){
    		throw new EmpMgmtGenericException("Multiple organizations of same name exists");
    	}
    	
    	return roles.get(0);
	}
	
	@Override
	public Role findRoleForOrg(String role, Long orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("role", role);
    	params.put("orgId", orgId);
    	String query = "Role.findRoleForOrg";
    	List<Role> roles = super.findByNamedQueryAndNamedParams(query, params);
    	if(roles.size() == 0){
    		return null;
    	}
    	
    	if(roles.size() > 1){
    		throw new EmpMgmtGenericException("Multiple roles of same name exists");
    	}
    	
    	return roles.get(0);
	}

}

