package com.v2.lt.emplmgmt.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

import com.v2.lt.emplmgmt.common.Constants;

public class CommonUtil {
	
	public static Map<String, List<String>> extractValuesOfDependingFactors(Object object, String[] factors) throws NoSuchFieldException,	IllegalAccessException {
		
		Map<String, List<String>> factorValues = new LinkedHashMap<String, List<String>>();
		
		Class<? extends Object> objClass = object.getClass();
		
		List<String> factorValueList = null;
		int arrayIndex = 0;
		for(int i=0;i<factors.length;i++) {
			factorValueList = new ArrayList<String>();
			if(factors[i].contains(Constants.COMPOSITIONDELIMITER)) {
				String objectComposition [] = factors[i].split(Constants.COMPOSITIONDELIMITER);
				Object obj = getFieldValue(object, objClass, objectComposition[arrayIndex]);
				retreiveFactorValue(Arrays.copyOfRange(objectComposition, ++arrayIndex, objectComposition.length), arrayIndex ,  obj, factorValueList);
				arrayIndex=0;
			} else {
				Object obj = getFieldValue(object, objClass, factors[i]);
				factorValueList.add(obj.toString());
			}
			factorValues.put(factors[i].split(":")[factors[i].split(Constants.COMPOSITIONDELIMITER).length-1],factorValueList);
		}
		
		return factorValues;
	}

	private static void retreiveFactorValue(String[] objectComposition, int arrayIndex, Object obj, List<String> factorValueList) throws NoSuchFieldException, IllegalAccessException {
		if(obj instanceof Collection) {
			for(Object o : (Collection<?>)obj) {
				Class<? extends Object> objClass = o.getClass();
				Object extractObj = getFieldValue(o, objClass, objectComposition[0]);
				if(ClassUtils.isPrimitiveOrWrapper(extractObj.getClass()) || extractObj instanceof String) {
					System.out.println(objectComposition[0]+" "+extractObj);
					factorValueList.add(extractObj.toString());
				} else {
					arrayIndex--;
					retreiveFactorValue(Arrays.copyOfRange(objectComposition, ++arrayIndex, objectComposition.length), arrayIndex , extractObj, factorValueList);
				}
				
			}
		} else {
			Class<? extends Object> objClass = obj.getClass();
			Object extractObj = getFieldValue(obj, objClass, objectComposition[0]);
			if(ClassUtils.isPrimitiveOrWrapper(extractObj.getClass()) || extractObj instanceof String) {
				System.out.println(objectComposition[0]+" "+extractObj);
				factorValueList.add(extractObj.toString());
			} else {
				arrayIndex--;
				retreiveFactorValue(Arrays.copyOfRange(objectComposition, ++arrayIndex, objectComposition.length), arrayIndex , extractObj, factorValueList);
			}
			
		}
	}
	
	private static Object getFieldValue(Object object, Class<? extends Object> objClass, String factor) throws IllegalArgumentException, IllegalAccessException  {
		Field f = null;
		Object obj = null;
		try {
		f = objClass.getDeclaredField(factor);
		} catch (NoSuchFieldException e) {
			try {
				f = objClass.getSuperclass().getDeclaredField(factor);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		}
		if(f != null) {
			f.setAccessible(true);
			obj = f.get(object);
		} else {
			obj = "";
		}
		return obj;
	}
}
