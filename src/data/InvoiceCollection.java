package data;

import forms.Invoice;

import java.util.HashMap;

public class InvoiceCollection {

    private static InvoiceCollection instance;
    private HashMap<Integer, Invoice> invoices = new HashMap<>();

    private InvoiceCollection(){}

    public static InvoiceCollection getInvoiceCollection(){
        if(instance == null){
            instance = new InvoiceCollection();
        }
        return instance;
    }

    public void addInvoice(int id, Invoice invoice){
        invoices.put(id, invoice);
    }

    public Invoice getInvoice(int id){
        Invoice invoice = invoices.get(id);
        invoice.setVisible(true);
        return invoice;
    }

}
