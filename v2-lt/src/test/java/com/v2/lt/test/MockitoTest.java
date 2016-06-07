package com.v2.lt.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.v2.lt.mocking.scenario.Customer;
import com.v2.lt.mocking.scenario.EmailService;
import com.v2.lt.mocking.scenario.Invoice;
import com.v2.lt.mocking.scenario.InvoiceStep;
import com.v2.lt.mocking.scenario.PrintService;

public class MockitoTest {
	
	private InvoiceStep finalInvoiceStep = null;
	private Customer customer = null;
	private Invoice invoice = null;
	PrintService printerService;
	EmailService emailService;
	
	@Before
	public void beforeEachTest() {
		customer = new Customer();
		printerService = Mockito.mock(PrintService.class);
		emailService = Mockito.mock(EmailService.class);
		finalInvoiceStep = new InvoiceStep(printerService, emailService);
		invoice = new Invoice();
 
	}
 
	@Test
	public void normalCustomer() {
		customer.setEmail("a@b.com");
		customer.setDispatchModeEmail(true);
		
		finalInvoiceStep.handleInvoice(invoice, customer);
		Mockito.verifyZeroInteractions(printerService); // printerService should be invoked
		Mockito.verify(emailService).sendInvoice(invoice, customer.getEmail());//emailService.sendInvoice should be invoked
	}
 
	@Test
	public void customerWithPrintedInvoice() {
        customer.setDispatchModeEmail(false);
		finalInvoiceStep.handleInvoice(invoice, customer);
		Mockito.verifyZeroInteractions(emailService);
		Mockito.verify(printerService).sendInvoice(invoice, customer);
	}
}
