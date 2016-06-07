package com.v2.lt.emplmgmt.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.v2.lt.emplmgmt.common.Constants;
import com.v2.lt.emplmgmt.domain.TSFactor;

public class TimeSheetUtil {

	public static List<String> makeCriterias(List<List<String>> factorValues) {
		
		StringBuilder criteria = null;
		int parentFactorValueIndex = 0;
		int childFactorValueIndex = 0;
		int size = factorValues.size();
		List<String> previousListCopy = null;
		List<String> newFormedCombinationList = new ArrayList<String>();
		if(size > 1) {
			while(parentFactorValueIndex<size-1) {
			++parentFactorValueIndex;
			childFactorValueIndex=0;
			if(previousListCopy == null) {
				previousListCopy = factorValues.get(parentFactorValueIndex-1);
			}
			while(childFactorValueIndex != factorValues.get(parentFactorValueIndex).size()) {
				for(String str : previousListCopy) {
					criteria = new StringBuilder();
					criteria.append(str+Constants.DOT);
					criteria.append(factorValues.get(parentFactorValueIndex).get(childFactorValueIndex));
					newFormedCombinationList.add(criteria.toString());
				}
				childFactorValueIndex++;
			}
			previousListCopy = new ArrayList<String>(newFormedCombinationList);
			newFormedCombinationList.clear();
			}
		} else {
			previousListCopy = factorValues.get(0);
		}
		return previousListCopy;
	}
	
	/*public static List<String> getFactorNames(String factors) {
		
		List<String> factorNames = new ArrayList<String>();
		
		String [] subfactors = factors.split("\\"+Constants.DOT);
		
		for(String factor : subfactors) {
			if(factor.contains(Constants.COMPOSITIONDELIMITER)) {
				factorNames.add(factor.substring(factor.lastIndexOf(Constants.COMPOSITIONDELIMITER)+1,factor.length()));
			} else {
				factorNames.add(factor);
			}
			
		}
		
		return factorNames;
	}*/
	
	/*public static List<List<String>> getOrderedArrayOfFactorValues(List<String> factorNames, Map<String, List<String>> factorValuesMap) {
		
		List<List<String>> factorValuesList = new ArrayList<List<String>>();
		List<String> factorValues = null;
		for(String factorName : factorNames) {
			factorValues = factorValuesMap.get(factorName);
			factorValuesList.add(factorValues);
		}
		
		return factorValuesList;
		
	}*/

	public static String [] getEmpTimeSheetFactors(List<TSFactor> tsFactorsList) {
		
		String factors [] = new String[tsFactorsList.size()];
		for(int i=0;i<tsFactorsList.size();i++) {
			factors[i] = tsFactorsList.get(i).getEmpTSFactor();
		}
		
		return factors;
	}
	
	public static String [] getAMTimeSheetFactors(List<TSFactor> tsFactorsList) {
		
		String factors [] = new String[tsFactorsList.size()];
		for(int i=0;i<tsFactorsList.size();i++) {
			factors[i] = tsFactorsList.get(i).getAmTSFactor();
		}
		
		return factors;
	}

	public static List<String> getFactorNames(List<TSFactor> tsFactorsList) {
		List<String> factorNames = new ArrayList<String>();
		for(TSFactor tsFactor : tsFactorsList) {
			factorNames.add(tsFactor.getName());
		}
		return factorNames;
	}

	public static Map<TSFactor, List<String>> getTSFactorValuesMap(Map<String, List<String>> factorValuesMap, List<TSFactor> tsFactorsList) {
		Map<TSFactor, List<String>> tsFactorValuesMap = new LinkedHashMap<TSFactor, List<String>>();
		List<List<String>> factorValueMapValues = new ArrayList<List<String>>(factorValuesMap.values());
		for(int i=0;i<factorValueMapValues.size();i++) {
			tsFactorValuesMap.put(tsFactorsList.get(i), factorValueMapValues.get(i));
		}
		
		return tsFactorValuesMap;
	}

	public static List<List<String>> getOrderedArrayOfFactorValues(List<TSFactor> tsFactorsList,Map<TSFactor, List<String>> tsFactorValuesMap) {
		List<List<String>> factorValuesList = new ArrayList<List<String>>();
		List<String> factorValues = null;
		for(TSFactor factorName : tsFactorsList) {
			factorValues = tsFactorValuesMap.get(factorName);
			factorValuesList.add(factorValues);
		}
		
		return factorValuesList;
	}

	public static List<String> makeCriterias(List<List<String>> factorValues, Long orgId) {
		List<String> plainCriterias = makeCriterias(factorValues);
		List<String> orgSpecificCriterias = new ArrayList<String>();
		for(String criteria : plainCriterias) {
			orgSpecificCriterias.add(""+orgId+Constants.DOT+criteria);
		}
		
		return orgSpecificCriterias;
	}

}
