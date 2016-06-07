package com.v2.lt.test;

import org.junit.Before;
import org.junit.Test;

import com.v2.lt.mocking.scenario.Customer;
import com.v2.lt.mocking.scenario.Invoice;
import com.v2.lt.mocking.scenario.InvoiceStep;
import com.v2.lt.mocking.scenario.LegacyEmailService;
import com.v2.lt.mocking.scenario.LegacyPrinterService;

public class MockingScenarioTest {

	
	private InvoiceStep finalInvoiceStep = null;
	private Customer customer = null;
	private Invoice invoice = null;
 
	@Before
	public void beforeEachTest() {
		customer = new Customer();
		finalInvoiceStep = new InvoiceStep(new LegacyPrinterService(),     new LegacyEmailService());
		invoice = new Invoice();
 
	}
 
	@Test
	public void normalCustomer() {
		customer.setEmail("a@b.com");
		customer.setDispatchModeEmail(true);
		finalInvoiceStep.handleInvoice(invoice, customer);
 
	}
 
	@Test
	public void customerWithPrintedInvoice() {
                        customer.setDispatchModeEmail(false);
		finalInvoiceStep.handleInvoice(invoice, customer);
	}
}
