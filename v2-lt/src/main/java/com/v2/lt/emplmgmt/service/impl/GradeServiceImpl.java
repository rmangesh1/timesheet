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
import com.v2.lt.emplmgmt.dao.GradeDao;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.service.GradeService;

@Service("gradeService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class GradeServiceImpl extends EmpMgmtGenericServiceImpl<Long, Grade> implements GradeService {

	@Autowired
    protected GradeDao gradeDAO;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) gradeDAO);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	gradeDAO.setEntityManager(entityManager);
    }
	
	@Override
	public Grade saveOrUpdate(Grade grade) throws EmpMgmtGenericException {
		Grade grade2 = super.saveOrUpdate(grade);
		return grade2;
	}

	@Override
	public Grade findGrade(String grade) {

		Map<String, String> params = new HashMap<String, String>();
    	params.put("grade", grade);
    	String query = "Grade.findGrade";
    	List<Grade> grades = super.findByNamedQueryAndNamedParams(query, params);
    	if(grades.size() == 0){
    		return null;
    	}
    	
    	if(grades.size() > 1){
    		throw new EmpMgmtGenericException("Multiple grade of same name exists");
    	}
    	
    	return grades.get(0);
	
	}
	
	@Override
	public Grade findGradeForOrg(String grade, Long orgId) {

		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("grade", grade);
    	params.put("orgId", orgId);
    	String query = "Grade.findGradeForOrg";
    	List<Grade> grades = super.findByNamedQueryAndNamedParams(query, params);
    	if(grades.size() == 0){
    		return null;
    	}
    	
    	if(grades.size() > 1){
    		throw new EmpMgmtGenericException("Multiple grade of same name exists");
    	}
    	
    	return grades.get(0);
	
	}

}
