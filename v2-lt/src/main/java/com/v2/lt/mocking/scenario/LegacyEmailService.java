package com.v2.lt.mocking.scenario;

public class LegacyEmailService implements EmailService{

	@Override
	public void sendInvoice(Invoice invoice, String email) {
		// Code to send invoice
		System.out.println("Invoice Sent email");
	}
	
	

}
