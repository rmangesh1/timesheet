package com.v2.lt.emplmgmt.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.v2.lt.emplmgmt.dao.ActivityMappingDao;
import com.v2.lt.emplmgmt.domain.ActivityMapping;

@Repository("activityMappingDao")
public class ActivityMappingDaoImpl extends JpaDAOImpl<Long, ActivityMapping> implements ActivityMappingDao {

	@Autowired
    EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext(unitName="EmpMgmt_PersistenceUnit")
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
		super.setEntityManager(entityManager);
		}
    
    @PostConstruct
    public void init() {
        super.setEntityManagerFactory(entityManagerFactory);
        super.setEntityManager(entityManager);
    }

    

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
