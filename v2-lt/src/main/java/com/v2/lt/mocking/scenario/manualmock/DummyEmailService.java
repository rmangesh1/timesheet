package com.v2.lt.mocking.scenario.manualmock;

import com.v2.lt.mocking.scenario.EmailService;
import com.v2.lt.mocking.scenario.Invoice;

public class DummyEmailService implements EmailService{
	
	boolean isEmailSent;

	@Override
	public void sendInvoice(Invoice invoice, String email) {
		// TODO Auto-generated method stub
		System.out.println("send -  invoice by email");
		isEmailSent = true;
	}
	
	public boolean isEmailSent(){
		return isEmailSent;
	}

}
