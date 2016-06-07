package com.v2.lt.emplmgmt.search.bridges;

import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.ParameterizedBridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.Role;

public class RoleBridge implements FieldBridge, ParameterizedBridge {
	Logger logger = LoggerFactory.getLogger(RoleBridge.class);

	@Override
	public void set(String name, Object value, Document document,
			LuceneOptions luceneOptions) {

		Employee emp = (Employee) value;
		Set<Role> roles = emp.getRoles(); 
		Role role = null;
		for(Role role1 : roles) {
			role = role1;
		}
		
		if(role != null){
			document.add(new Field("roleName", role.getRole(), org.apache.lucene.document.Field.Store.NO, org.apache.lucene.document.Field.Index.ANALYZED ));
		}
		
	} 

	@Override
	public void setParameterValues(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		
	}

}
