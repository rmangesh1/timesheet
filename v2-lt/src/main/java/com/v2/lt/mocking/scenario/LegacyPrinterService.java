package com.v2.lt.mocking.scenario;

public class LegacyPrinterService implements PrintService{

	@Override
	public void sendInvoice(Invoice invoice, Customer customer) {
		// write code to send invoice to customer address
		System.out.println("Invoice Sent address");
	}

}
