package com.v2.lt.emplmgmt.common;

public class EmpMgmtGenericException extends RuntimeException{

	public EmpMgmtGenericException(){
		super();
	}
	
	public EmpMgmtGenericException(String message){
		super(message);
	}
	
	public EmpMgmtGenericException(String message, Throwable t){
		super(message, t);
	}

}

