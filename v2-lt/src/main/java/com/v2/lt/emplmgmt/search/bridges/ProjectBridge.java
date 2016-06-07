package com.v2.lt.emplmgmt.search.bridges;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.ParameterizedBridge;

import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Project;

public class ProjectBridge implements FieldBridge, ParameterizedBridge {

	@Override
	public void setParameterValues(Map<String, String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {

		System.out.println(name+" "+value);
		Project proj = (Project) value;
		Organization org = proj.getDepartment().getOrganization();
		
		if(org != null) {
			document.add(new Field("organization", org.getId().toString(), Field.Store.NO, Field.Index.ANALYZED ));
		}
		
	
	}

}
