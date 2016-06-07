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
import com.v2.lt.emplmgmt.dao.DepartmentDao;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.service.DepartmentService;

@Service("departmentService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class DepartmentServiceImpl extends EmpMgmtGenericServiceImpl<Long, Department> implements DepartmentService {

	@Autowired
    protected DepartmentDao departmentDAO;
	
	
	
	 

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) departmentDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	departmentDAO.setEntityManager(entityManager);
    }
	
	@Override
	public Department saveOrUpdate(Department department) throws EmpMgmtGenericException {
		
		Department department2 = null;
		
		if(department.getName() == null){
			throw new EmpMgmtGenericException("Department Name can not be null");
		}
		
		if(department.getId() != null)
		department2 = findDepartmentById(department.getId());
			if(department2 == null){
				//create mode
				department.setCreatedDate(new Date());
				department2 = super.saveOrUpdate(department);
			}
			else{
				//update mode
				department2 = super.saveOrUpdate(department);
			}
		return department2;
	}

	@Override
	public Department findDepartmentByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("name", name);
    	String query = "Department.findDepartmentByName";
    	List<Department> departments = super.findByNamedQueryAndNamedParams(query, params);
    	if(departments.size() == 0){
    		return null;
    	}
    	
    	if(departments.size() > 1){
    		throw new EmpMgmtGenericException("Multiple Departments of same name exists");
    	}
    	
    	return departments.get(0);
	}
	
	@Override
	public Department findDepartmentById(Long id) {
		Map<String, Long> params = new HashMap<String, Long>();
    	params.put("id", id);
    	String query = "Department.findDepartmentById";
    	List<Department> departments = super.findByNamedQueryAndNamedParams(query, params);
    	if(departments.size() == 0){
    		return null;
    	}
    	
    	if(departments.size() > 1){
    		throw new EmpMgmtGenericException("Multiple Departments of same name exists");
    	}
    	
    	return departments.get(0);
	}


}
