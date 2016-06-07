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
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.dao.ProjectDao;
import com.v2.lt.emplmgmt.domain.Project;
import com.v2.lt.emplmgmt.service.ProjectService;

@Service("projectService")
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=EmpMgmtGenericException.class)
public class ProjectServiceImpl extends EmpMgmtGenericServiceImpl<Long, Project> implements ProjectService{

	@Autowired
    protected ProjectDao projectDao;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) projectDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	projectDao.setEntityManager(entityManager);
    }
	
	@Override
	public Project saveOrUpdate(Project project) throws EmpMgmtGenericException {
		
		Project project2 = null;
		
		if(project.getProjectName() == null){
			throw new EmpMgmtGenericException("project Name can not be null");
		}
		
		project2 = findProjectByName(project.getProjectName());
			if(project2 == null){
				//create mode
				project.setCreatedDate(new Date());
				project2 = super.saveOrUpdate(project);
			}
			else{
				//update mode
				project2 = super.saveOrUpdate(project);
			}
		return project2;
	}

	@Override
	public Project findProjectByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
    	params.put("projectName", name);
    	String query = "Project.findProjectByName";
    	List<Project> projects = super.findByNamedQueryAndNamedParams(query, params);
    	if(projects.size() == 0){
    		return null;
    	}
    	
    	if(projects.size() > 1){
    		throw new EmpMgmtGenericException("Multiple projects of same name exists");
    	}
    	
    	return projects.get(0);
	}

}
