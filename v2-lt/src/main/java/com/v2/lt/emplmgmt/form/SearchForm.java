package com.v2.lt.emplmgmt.form;

import java.util.ArrayList;
import java.util.List;

import com.v2.lt.emplmgmt.domain.Base;

public class SearchForm {
	
	private String value;
	
	private String searchFor;
	
	private List<String> criterias;
	
	private List<? extends Base> searchResult = new ArrayList();

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSearchFor() {
		return searchFor;
	}

	public void setSearchFor(String searchFor) {
		this.searchFor = searchFor;
	}

	public List<String> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<String> criterias) {
		this.criterias = criterias;
	}

	public List<? extends Base> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<? extends Base> searchResult) {
		this.searchResult = searchResult;
	}
	
}
