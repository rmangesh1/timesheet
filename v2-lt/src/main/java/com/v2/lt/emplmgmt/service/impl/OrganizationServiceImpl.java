package com.v2.lt.emplmgmt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.transaction.Transactional.TxType;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.dao.OrganizationDAO;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.service.OrganizationService;

@Service("organizationService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class OrganizationServiceImpl  extends EmpMgmtGenericServiceImpl<Long, Organization> implements OrganizationService{
	@Autowired
    protected OrganizationDAO organizationDAO;
	
	
	
	 

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) organizationDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	organizationDAO.setEntityManager(entityManager);
    }
    
    @Override
	@javax.transaction.Transactional(value=TxType.NOT_SUPPORTED)
    public Organization findOrganizationByName(String name){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("orgName", name);
    	String query = "Organization.findOrganizationByName";
    	List<Organization> organizations = super.findByNamedQueryAndNamedParams(query, params);
    	if(organizations.size() == 0){
    		return null;
    	}
    	
    	if(organizations.size() > 1){
    		throw new EmpMgmtGenericException("Multiple organizations of same name exists");
    	}
    	
    	return organizations.get(0);
    }

	@Override
	@Transactional
	public Organization saveOrUpdate(Organization organization) throws EmpMgmtGenericException {
		// TODO Auto-generated method stub
		
		Organization organization2 = null;
		
		if(organization.getOrgName()  == null){
			throw new EmpMgmtGenericException("Org Name can not be null");
		}
		
		if(organization.getOrgName().trim().length() < 2){
			throw new EmpMgmtGenericException("Orga name too small.");
		}
		
		organization2 = findOrganizationByName(organization.getOrgName());
			if(organization2 == null){
				//create mode
				organization.setCreatedDate(new Date());
				organization2 = super.saveOrUpdate(organization);
			}
			else{
				//update mode
				organization2 = super.saveOrUpdate(organization);
			}
		return organization2;
	}

	@Override
	@javax.transaction.Transactional(value=TxType.NOT_SUPPORTED)
	public Organization findOrganizationById(Long orgId) {

    	Map<String, Long> params = new HashMap<String, Long>();
    	params.put("orgId", orgId);
    	String query = "Organization.findOrganizationById";
    	List<Organization> organizations = super.findByNamedQueryAndNamedParams(query, params);
    	if(organizations.size() == 0){
    		return null;
    	}
    	
    	if(organizations.size() > 1){
    		throw new EmpMgmtGenericException("Multiple organizations of same name exists");
    	}
    	
    	return organizations.get(0);
    
		
	}
	
	
 
}
