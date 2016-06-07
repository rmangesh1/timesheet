package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Organization;

public interface OrganizationService extends EmpMgmtGenericService{
	
	public Organization saveOrUpdate(Organization organization) throws EmpMgmtGenericException;
	
	public Organization findOrganizationByName(String name);
	
	public Organization findOrganizationById(Long orgId);

}

