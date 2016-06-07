package com.v2.lt.empMgmt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;

import com.v2.lt.emplmgmt.domain.Employee;

public class EmployeeConcurrentTest {
	
	/*@Test
	public void testConcurrentHashMapForEmployee() {
		
		ConcurrentMap<String, Employee> empMap = new ConcurrentHashMap<String, Employee>();
		
		for(int i= 0;i<100000;i++) {
			Employee employee = new Employee();
			employee.setEmpName("User"+i);
			empMap.putIfAbsent(employee.getEmpName(), employee);
		}
		
		System.out.println(empMap);
		
	}
	
	@Test
	public void testHashMapForEmployee() {
		
		//ConcurrentMap<String, Employee> empMap = new ConcurrentHashMap<String, Employee>();
		
		Map<String, Employee> empMap = new HashMap<String, Employee>();
		
		for(int i= 0;i<100000;i++) {
			Employee employee = new Employee();
			employee.setEmpName("User"+i);
			empMap.put(employee.getEmpName(), employee);
		}
		
		System.out.println(empMap);
		
	}*/
	
}
