package com.v2.lt.empMgmt;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.dozer.DozerBeanMapper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v2.lt.emplmgmt.domain.Department;
import com.v2.lt.emplmgmt.domain.Employee;
import com.v2.lt.emplmgmt.domain.Grade;
import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.Role;
import com.v2.lt.emplmgmt.form.TimeSheetDetailsForm;

public class MainTest {

	static String tsFactor1 = "departments:name.grade:grade.roles:role";	
	static String tsFactor2 = "departments:name.grade:grade";
	
	static Map<String, List<String>> mapps = new HashMap<String, List<String>>();
	static List<String> arrl = new ArrayList<String>();
	
	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper obj = new ObjectMapper();
		
		//Role role = obj.readValue("{role: \"dfgfdg\"}", Role.class);
		
		/*Grade grad = new Grade();
		grad.setGrade("sadas");
		String str = obj.writeValueAsString(grad);
		
		System.out.println(str);
		
		Grade role = obj.readValue(str, Grade.class);
		
		System.out.println(role);*/
		
		TimeSheetDetailsForm ts = obj.readValue("{\"dayOfWeek\":\"31.08.2015\",\"tsRecordForm\":[{\"selectFactor\":[\"BFS\",\"NETS\"],\"selectedActivity\":{\"id\":1},\"taskDescription\":\"\",\"activities\":[{\"id\":1,\"version\":0,\"description\":\"\",\"createdBy\":null,\"deprecatedBy\":null,\"lastModifiedBy\":null,\"createdDate\":1440960929000,\"deprecatedDate\":null,\"lastModifiedDate\":null,\"deprecated\":null,\"activityName\":\"Coding\",\"activityCode\":\"X222\",\"activityDescription\":null,\"$$hashKey\":\"object:16\"}],\"hoursMon\":\"5\",\"hoursTue\":null,\"hoursWed\":\"5\",\"hoursThur\":null,\"hoursFri\":null,\"hoursSat\":null,\"hoursSun\":null,\"$$hashKey\":\"object:7\"}]}", TimeSheetDetailsForm.class);
		
		System.out.println(ts);
		
		
		/*Organization org = new Organization();
		org.setOrgName("abc");
		org.setPrimaryEmail("abc@aa.com	");
		
		Organization org1 = new Organization();
		org1.setOrgName("www");
		org1.setPrimaryEmail("xyz1@aa.com	");
		
		System.out.println(org1);
		
		DozerBeanMapper mapper = new DozerBeanMapper();
		
		mapper.map(org, org1);
		
		System.out.println(org1);*/
		
		
		
		/*Grade grade = new Grade();
		grade.setGrade("A-1-2");
		
		Role role = new Role();
		role.setRole("Java Developer");
		
		Role role1 = new Role();
		role1.setRole("Senior Test Engineer");
		
		Department department = new Department();
		department.setName("BFS");
		
		Employee employee = new Employee();
		employee.setFirstName("Mangesh");
		employee.setGrade(grade);
		
		Set<Role> roles = new HashSet<Role>();
		Set<Department> departments = new HashSet<Department>();
		
		roles.add(role1);
		roles.add(role);
		
		employee.setRoles(roles);
		departments.add(department);
		department = new Department();
		department.setName("TSL");
		departments.add(department);
		employee.setDepartments(departments);
		
		Class eClass = employee.getClass();
		//String [] factors = tsFactor1.split("\\.");
		String [] factors = tsFactor2.split("\\.");
		
		factors(employee, eClass, factors);
		
		System.out.println(mapps);
		
				
		
		List<String> depts = mapps.get("name");
		List<String> grades = mapps.get("grade");
		//List<String> roless = mapps.get("role");
		
		String[] str1 = tsFactor1.split("\\.");
		
		System.out.println(tsFactor1.substring(tsFactor1.lastIndexOf(":")+1,tsFactor1.length()));
		
		System.out.println("abcc".lastIndexOf(":"));
	
		//makeCriterias(depts,grades);
		
		int j = 0;
		List<String> arrl1 = new ArrayList<String>();
		StringBuilder sb;
		while(j != grades.size()) {
			for(String dept : depts) {
				sb = new StringBuilder();
				sb.append(dept+".");
				
				sb.append(grades.get(j)+".");
				
				arrl.add(sb.toString());
			}
			j++;
		}
		
		j=0;
		while(j != roless.size()) {
			for(String com : arrl) {
				sb = new StringBuilder();
				sb.append(com);
				
				sb.append(roless.get(j));
				
				arrl1.add(sb.toString());
			}
			j++;
		}
		
		System.out.println(arrl1);*/
		
		
		
	}

	
	private static void makeCriterias(List<String>... factorValues) {
		StringBuilder sb;
		int i = 0;
		int j = 0;
		int size = factorValues.length;
		List<String> arrlS = null;
		if(size > 1) {
			while(i<size-1) {
			++i;
			j=0;
			if(arrlS == null) {
				arrlS = factorValues[i-1];
			}
			while(j != factorValues[i].size()) {
				for(String str : arrlS) {
					sb = new StringBuilder();
					sb.append(str+".");
					sb.append(factorValues[i].get(j));
					arrl.add(sb.toString());
				}
				j++;
			}
			arrlS = new ArrayList<String>(arrl);
			arrl.clear();
			}
		}
		System.out.println(arrlS);
	}
	
	/*private static void factors(Employee employee, Class eClass, String[] factors) throws NoSuchFieldException,	IllegalAccessException {
				for(String factor : factors) {
					if(factor.contains(":")) {
						String arr [] = factor.split(":");
						Field f = eClass.getDeclaredField(arr[0]);
						f.setAccessible(true);
						Object obj = f.get(employee);
						if(obj instanceof Collection) {
							for(Object o : (Collection)obj) {
								Class c = o.getClass();
								Field f2 = c.getDeclaredField(arr[1]);
								f2.setAccessible(true);
								Object oo = f2.get(o);
								System.out.println(arr[0]+">"+arr[1]+" "+oo);
							}
						} else {
							Class c = obj.getClass();
							Field f2 = c.getDeclaredField(arr[1]);
							f2.setAccessible(true);
							Object oo = f2.get(obj);
							System.out.println(arr[0]+">"+arr[1]+" "+oo);
						}
					}
				}
	}*/
	
	private static void factors(Employee employee, Class eClass, String[] factors) throws NoSuchFieldException,	IllegalAccessException {
		
		List<String> arrl = null;
		int i=0;
		for(String factor : factors) {
			arrl = new ArrayList<String>();
			if(factor.contains(":")) {
				String arr [] = factor.split(":");
				Field f = eClass.getDeclaredField(arr[i]);
				f.setAccessible(true);
				Object obj = f.get(employee);
				retreive(Arrays.copyOfRange(arr, ++i, arr.length), i ,  obj, arrl);
				i=0;
			}
			mapps.put(factor.split(":")[factor.split(":").length-1],arrl);
		}
}

	private static void retreive(String[] arr, int i, Object obj, List<String> arrl)
			throws NoSuchFieldException, IllegalAccessException {
		if(obj instanceof Collection) {
			for(Object o : (Collection)obj) {
				Class c = o.getClass();
				Field f2 = c.getDeclaredField(arr[0]);
				f2.setAccessible(true);
				Object oo = f2.get(o);
				if(ClassUtils.isPrimitiveOrWrapper(oo.getClass()) || oo instanceof String) {
					System.out.println(arr[0]+" "+oo);
					arrl.add(oo.toString());
				} else {
					retreive(Arrays.copyOfRange(arr, ++i, arr.length), i , oo, arrl);
				}
				
			}
		} else {
			Class c = obj.getClass();
			Field f2 = c.getDeclaredField(arr[0]);
			f2.setAccessible(true);
			Object oo = f2.get(obj);
			if(ClassUtils.isPrimitiveOrWrapper(oo.getClass()) || oo instanceof String) {
				System.out.println(arr[0]+" "+oo);
				arrl.add(oo.toString());
			} else {
				retreive(Arrays.copyOfRange(arr, ++i, arr.length), i , oo, arrl);
			}
			
		}
	}

}

