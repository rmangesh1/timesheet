package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.UserRole;

public interface UserRoleService extends EmpMgmtGenericService{
	
	public UserRole saveOrUpdate(UserRole userRole) throws EmpMgmtGenericException;
	
	public UserRole findUserRoleByName(String name);
	
}
