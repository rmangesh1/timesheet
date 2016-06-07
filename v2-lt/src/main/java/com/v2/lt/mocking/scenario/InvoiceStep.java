package com.v2.lt.mocking.scenario;

public class InvoiceStep {
	private PrintService printerService = null;
	private EmailService emailService = null;
 
	public InvoiceStep(PrintService printerService, EmailService emailService)
	{
		this.printerService = printerService;
		this.emailService = emailService;
	}
 
	public void handleInvoice(Invoice invoice, Customer customer)
	{
		if(customer.prefersEmail())
		{
			emailService.sendInvoice(invoice,customer.getEmail());
		}
		else
		{
			printerService.sendInvoice(invoice, customer);
		}
	}
}
