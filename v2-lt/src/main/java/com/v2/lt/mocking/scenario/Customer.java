package com.v2.lt.mocking.scenario;

public class Customer {
	
	private String email;
	
	private boolean dispatchModeEmail;
	
	

	public boolean isDispatchModeEmail() {
		return dispatchModeEmail;
	}

	public void setDispatchModeEmail(boolean dispatchModeEmail) {
		this.dispatchModeEmail = dispatchModeEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean prefersEmail(){
		return dispatchModeEmail;
	}

}
