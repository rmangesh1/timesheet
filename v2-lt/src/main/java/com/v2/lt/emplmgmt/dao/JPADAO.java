package com.v2.lt.emplmgmt.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
public interface JPADAO<E, K> {

	 public void persist(E entity);

	    public void remove(E entity);
	    
	    public void remove(Long id);
	    
	    public E merge(E entity) ;
	    
	    public void refresh(E entity);

	    public E findById(K id) ;
	    
	    public E flush(E entity);
	    
	   
	    public List<E> findAll() ;

           public List<E> find(int startFrom, int maxResults) ;
           
	    public Integer removeAll() ;
	    
	    public void setEntityManager(EntityManager entityManager);
	    
	    /**
	     * Find using a named query.
	     *
	     * @param queryName the name of the query
	     * @param params the query parameters
	     *
	     * @return the list of entities
	     */
	    public List<E> findByNamedQueryAndNamedParams(
	        final String queryName,
	        final Map<String, ?extends Object> params
	    );
	    
	    public List<E> findByNamedQueryAndNamedParams(
		        final String queryName,
		        final Map<String, ?extends Object> params,
		        int startFrom,int maxResults
		    );
	    
	    public int recordCount(final String name,final Map<String, ?extends Object> params);

		List<E> searchEntityByCriteria(Map<String, String> andSearchCriterias, List<String> orSearchCriterias,
				String searchValue);
	   
	    
}
