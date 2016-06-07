package com.v2.lt.emplmgmt.domain;

import javax.persistence.Entity;

@Entity
public class TimeSheetField extends Base implements Comparable<TimeSheetField> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderIndex;
	
	private String name;
	
	private String value;
	
	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(TimeSheetField o) {
		return this.orderIndex.compareTo(o.getOrderIndex());
	}
	
	
}
