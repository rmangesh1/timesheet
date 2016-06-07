package com.v2.lt.emplmgmt.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.dao.TSFactorDao;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.service.TSFactorService;

@Service("tsFactorService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class TSFactorServiceImpl extends EmpMgmtGenericServiceImpl<Long, TSFactor> implements TSFactorService {
	
	@Autowired
    protected TSFactorDao tsFactorDao;
	
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) tsFactorDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	tsFactorDao.setEntityManager(entityManager);
    }
	
}
