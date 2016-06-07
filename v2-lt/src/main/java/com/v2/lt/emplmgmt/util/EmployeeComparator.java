package com.v2.lt.emplmgmt.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

import org.springframework.util.ReflectionUtils;

import com.v2.lt.emplmgmt.domain.Employee;

public class EmployeeComparator implements Comparator<Employee> {

	String argName = "";
	
	public EmployeeComparator(String argName) {
		super();
		this.argName = argName;
	}

	@Override
	public int compare(Employee o1, Employee o2) {
		
		Class employee = o1.getClass();

        try {

                Field field = employee.getDeclaredField(argName);
                Class fieldType = field.getType();
                ReflectionUtils.makeAccessible(field);

                Object value1 = field.get(o1);

                Object value2 = field.get(o2);

                Method method = fieldType.getDeclaredMethod("compareTo", fieldType); 
                Integer result = (Integer) method.invoke(value1, value2);

                return result;

                
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchFieldException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		
		return 0;
	}

}
