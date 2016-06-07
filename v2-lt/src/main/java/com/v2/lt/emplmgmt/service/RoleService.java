package com.v2.lt.emplmgmt.service;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.domain.Role;

public interface RoleService extends EmpMgmtGenericService {
	public Role saveOrUpdate(Role role) throws EmpMgmtGenericException;
	
	public Role findRole(String role);

	Role findRoleForOrg(String role, Long orgId);
}
