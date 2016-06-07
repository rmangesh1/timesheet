package com.v2.lt.emplmgmt.util;

import java.util.Comparator;

import com.v2.lt.emplmgmt.domain.Employee;

public class EmployeeEmailComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getPrimaryEmail().compareTo(o2.getPrimaryEmail());
	}

}
