package com.v2.lt.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.v2.lt.mocking.scenario.Customer;
import com.v2.lt.mocking.scenario.Invoice;
import com.v2.lt.mocking.scenario.InvoiceStep;
import com.v2.lt.mocking.scenario.manualmock.DummyEmailService;
import com.v2.lt.mocking.scenario.manualmock.DummyPrinterService;

public class MockingScenarioManualMockingTest {

	private InvoiceStep finalInvoiceStep = null;
	private Customer customer = null;
	private Invoice invoice = null;
	DummyPrinterService dummyPrintService;
	DummyEmailService dummyEmailService;
	@Before
	public void beforeEachTest() {
		customer = new Customer();
		dummyPrintService = new DummyPrinterService();
		dummyEmailService = new DummyEmailService();
		finalInvoiceStep = new InvoiceStep(dummyPrintService,     dummyEmailService);
		invoice = new Invoice();
 
	}
 
	@Test
	public void normalCustomer() {
		customer.setEmail("a@b.com");
		customer.setDispatchModeEmail(true);
		finalInvoiceStep.handleInvoice(invoice, customer);
		Assert.assertEquals(dummyEmailService.isEmailSent(), true);
 
	}
 
	@Test
	public void customerWithPrintedInvoice() {
        customer.setDispatchModeEmail(false);
		finalInvoiceStep.handleInvoice(invoice, customer);
		Assert.assertEquals(dummyPrintService.isSent(), true);
	}
}
