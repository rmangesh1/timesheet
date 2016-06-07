package com.v2.lt.emplmgmt.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.EmployeeDao;
import com.v2.lt.emplmgmt.domain.Employee;

@Repository("empDAO")
public class EmployeeDaoImpl extends JpaDAOImpl<Long, Employee> implements EmployeeDao {
	
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
	

	/*@Override
	public List<Employee> searchEmployeesByGrade(String grade) {
		fullTextEntityManager =
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(getEntityManager());
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
			    .buildQueryBuilder().forEntity(entityClass).get();
		String flds = "";
		if(grade == null){
			throw new EmpMgmtGenericException("Grade name can not eb null");
		}
		
		if(grade.trim().length() == 0){
			throw new EmpMgmtGenericException("Grade name can not eb blank");
		}
		
//		String s[] = new String[fields.size()];
//		s = fields.toArray(s);
		
		
		org.apache.lucene.search.Query luceneQuery = qb
				  .keyword()
				  .onField("grade").ignoreFieldBridge()
				  .matching((grade)).createQuery();
		
		
		
		
		
		//System.out.println(luceneQuery.toString());
		//org.apache.lucene.search.Query luceneQuery = qb.keyword().onField("firstName").ignoreFieldBridge().matching(grade).createQuery();
		org.apache.lucene.search.Query luceneQuery1 = qb.keyword().onField("organization").ignoreFieldBridge().matching("Lnt Infotech").createQuery();
		//org.apache.lucene.search.Query luceneQuery2 = qb.keyword().onField("grade").ignoreFieldBridge().matching(grade).createQuery();
		org.apache.lucene.search.Query luceneQuery3 = qb.keyword().onFields("firstName","grade").ignoreFieldBridge().matching(grade).createQuery();
		BooleanQuery luceneQuery = new BooleanQuery();
		luceneQuery.add(luceneQuery1, Occur.MUST);
		//luceneQuery.add(luceneQuery2, Occur.SHOULD);
		luceneQuery.add(luceneQuery3, Occur.MUST);
		//org.apache.lucene.search.Query luceneQuery = new WildcardQuery(new Term("firstName", grade));
				  
		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery =
		    fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
		List<Employee> entities = null;
		entities = jpaQuery.getResultList();
//		if(startFrom ==-1 || maxResults == -1){
//			entities = jpaQuery.getResultList();
//		}
//		else{
//			jpaQuery.setFirstResult(startFrom);
//			jpaQuery.setMaxResults(maxResults +1);
//			entities = jpaQuery.getResultList();
//		}
		    
		
		return entities;
	}
	*/
}
