package com.v2.lt.mocking.scenario.manualmock;

import com.v2.lt.mocking.scenario.Customer;
import com.v2.lt.mocking.scenario.Invoice;
import com.v2.lt.mocking.scenario.PrintService;

public class DummyPrinterService implements PrintService{
	boolean isSent;

	@Override
	public void sendInvoice(Invoice invoice, Customer customer) {
		// ....
		System.out.println("send - physical invoice");
		isSent = true;
	}

	public boolean isSent(){
		return isSent;
	}
}
