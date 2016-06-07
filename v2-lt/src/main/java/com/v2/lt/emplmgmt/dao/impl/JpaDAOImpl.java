package com.v2.lt.emplmgmt.dao.impl;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.v2.lt.emplmgmt.common.EmpMgmtGenericException;
import com.v2.lt.emplmgmt.dao.JPADAO;
import com.v2.lt.emplmgmt.domain.Employee;
@SuppressWarnings("deprecation")
public class JpaDAOImpl <K, E> implements JPADAO<E, K>{ 
	
	Logger logger = LoggerFactory.getLogger(JpaDAOImpl.class);
    protected Class<E> entityClass; 
    
    EntityManagerFactory entityManagerFactory;
    
    EntityManager entityManager;
    
    FullTextEntityManager fullTextEntityManager;

    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@SuppressWarnings("unchecked") 
    public JpaDAOImpl() { 
        ParameterizedType genericSuperclass = (ParameterizedType) getClass() 
                .getGenericSuperclass(); 
        this.entityClass = (Class<E>) genericSuperclass 
                .getActualTypeArguments()[1]; 
    } 

    public void persist(E entity) { 
       // getJpaTemplate().persist(entity); 
    	getEntityManager().persist(entity);
    	getEntityManager().flush();
    } 

    public void remove(E entity) { 
       // getJpaTemplate().remove(entity); 
    	getEntityManager().remove(entity);
    	getEntityManager().flush();
    	
    } 
    
    
    public void remove(Long id){
    	E entity = getEntityManager().getReference(entityClass, id);
    	getEntityManager().remove(entity);
    	getEntityManager().flush();
    }
    
    public E merge(E entity) { 
      //  return getJpaTemplate().merge(entity); 
    	E e = getEntityManager().merge(entity);
    	getEntityManager().flush();
    	return e;
    } 
    
    public void refresh(E entity) { 
       // getJpaTemplate().refresh(entity); 
    	getEntityManager().refresh(entity);
    	getEntityManager().flush();
    } 

    public E findById(K id) { 
       // return getJpaTemplate().find(entityClass, id); 
    	return getEntityManager().find(entityClass, id);
    } 
    
    public E flush(E entity) { 
       // getJpaTemplate().flush(); 
    	getEntityManager().flush();
        return entity; 
    } 
    
    @SuppressWarnings("unchecked") 
    public List<E> findAll() { 
      //  Object res = getJpaTemplate().execute(new JpaCallback() { 
//    	Object res = getEntityManager().equals(new JpaCallback() { 
//            public Object doInJpa(EntityManager em) throws PersistenceException { 
//            	try{
//                Query q = em.createQuery("SELECT h FROM " + 
//                        entityClass.getName() + " h"); 
//                return q.getResultList(); 
//            	}
//            	catch(PersistenceException e){
//            		e.printStackTrace();
//            		throw e;
//            	}
//            } 
//            
//        }); 
    	return getEntityManager().createQuery("SELECT h FROM "+entityClass.getName() + " h").getResultList();
        
       // return (List<E>) res; 
    }
    
    
    
    @SuppressWarnings("unchecked") 
    public List<E> find(final int startFrom, final int maxResults) { 
        /*Object res = getEntityManager().equals((new JpaCallback() { 

            public Object doInJpa(EntityManager em) throws PersistenceException { 
            	try{
                Query q = em.createQuery("SELECT h FROM " + 
                        entityClass.getName() + " h");
                q.setMaxResults(maxResults);
                q.setFirstResult(startFrom);
                return q.getResultList(); 
            	}
            	catch(PersistenceException e){
            		e.printStackTrace();
            		throw e;
            	}
            } 
            
        })); */
        
        Query q = getEntityManager().createQuery("SELECT h FROM " + 
                entityClass.getName() + " h ORDER BY h.id DESC");
        q.setMaxResults(maxResults);
        q.setFirstResult(startFrom);        
        return (List<E>) q.getResultList(); 
    }

    @SuppressWarnings("unchecked") 
    public Integer removeAll() { 
        //return (Integer) getJpaTemplate().execute(new JpaCallback() { 
//    	return (Integer) getEntityManager().execute(new JpaCallback() { 
//            public Object doInJpa(EntityManager em) throws PersistenceException { 
//                Query q = em.createQuery("DELETE FROM " + 
//                        entityClass.getName() + " h"); 
//                return q.executeUpdate(); 
//            } 
//            
//        }); 
    	return 0;
    }
    
   
    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    public List<E> findByNamedQueryAndNamedParams(
        final String name,
        final Map<String, ?extends Object> params
    ){
    	javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		final List<E> result = (List<E>) query.getResultList();
		return result;
    }
    
    /**
     * Find using a named query.
     *
     * @param name the name
     * @param params the query parameters
     * @param startFrom the start from
     * @param maxResults the max results
     * @return the list of entities
     */
    public List<E> findByNamedQueryAndNamedParams(
        final String name,
        final Map<String, ?extends Object> params,
        int startFrom,int maxResults
    ){
    	javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		query.setMaxResults(maxResults);
		query.setFirstResult(startFrom); 

		final List<E> result = (List<E>) query.getResultList();
		return result;
    }
    
    public int recordCount(final String name,final Map<String, ?extends Object> params)
    {
    	javax.persistence.Query query = getEntityManager().createNamedQuery(name);	
		for (final Map.Entry<String, ? extends Object> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
    	return ((Long)query.getSingleResult()).intValue();	
    }
    
    
    @Override
	public List<E> searchEntityByCriteria(Map<String, String> andSearchCriterias, List<String> orSearchCriterias, String searchValue) {
		fullTextEntityManager =
			    org.hibernate.search.jpa.Search.getFullTextEntityManager(getEntityManager());
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
			    .buildQueryBuilder().forEntity(entityClass).get();
		String flds = "";
		if(searchValue == null){
			throw new EmpMgmtGenericException("searchValue name can not eb null");
		}
		
		if(searchValue.trim().length() == 0){
			throw new EmpMgmtGenericException("searchValue name can not eb blank");
		}
		
//		String s[] = new String[fields.size()];
//		s = fields.toArray(s);
		
		
		/*org.apache.lucene.search.Query luceneQuery = qb
				  .keyword()
				  .onField("grade").ignoreFieldBridge()
				  .matching((grade)).createQuery();*/
		BooleanQuery luceneQuery = new BooleanQuery();
		
		org.apache.lucene.search.Query luceneQueryAndCriterias = null;
		for(Map.Entry<String, String> andCriteriaEntry : andSearchCriterias.entrySet()) {
			luceneQueryAndCriterias = qb.keyword().onField(andCriteriaEntry.getKey()).ignoreFieldBridge().matching(andCriteriaEntry.getValue()).createQuery();
			luceneQuery.add(luceneQueryAndCriterias,Occur.MUST);
		}
		
		org.apache.lucene.search.Query luceneQueryOrCriterias = null;
		
		if(!orSearchCriterias.isEmpty()) {
			String [] arrCriterias = new String[orSearchCriterias.size()];
			orSearchCriterias.toArray(arrCriterias);
			
			luceneQueryOrCriterias = qb.keyword().onFields(arrCriterias).ignoreFieldBridge().matching(searchValue).createQuery();
			luceneQuery.add(luceneQueryOrCriterias,Occur.MUST);
		}
		
		
		//System.out.println(luceneQuery.toString());
		//org.apache.lucene.search.Query luceneQuery = qb.keyword().onField("firstName").ignoreFieldBridge().matching(grade).createQuery();
		/*org.apache.lucene.search.Query luceneQuery1 = qb.keyword().onField("organization").ignoreFieldBridge().matching("Lnt Infotech").createQuery();
		//org.apache.lucene.search.Query luceneQuery2 = qb.keyword().onField("grade").ignoreFieldBridge().matching(grade).createQuery();
		org.apache.lucene.search.Query luceneQuery3 = qb.keyword().onFields("firstName","grade").ignoreFieldBridge().matching(grade).createQuery();
		
		luceneQuery.add(luceneQuery1, Occur.MUST);
		//luceneQuery.add(luceneQuery2, Occur.SHOULD);
		luceneQuery.add(luceneQuery3, Occur.MUST);*/
		//org.apache.lucene.search.Query luceneQuery = new WildcardQuery(new Term("firstName", grade));
				  
		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery =
		    fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
		List<E> entities = null;
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
    
}
