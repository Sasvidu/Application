package data;

import forms.Invoice;

import java.util.HashMap;

public class InvoiceCollection {

    //Private class variable to store the unique instance
    private static InvoiceCollection instance;
    //Data Structure for storing invoices
    private HashMap<Integer, Invoice> invoices = new HashMap<>();

    //Private constructor
    private InvoiceCollection(){}

    //Public method to retrieve the instance
    public static InvoiceCollection getInvoiceCollection(){
        if(instance == null){
            instance = new InvoiceCollection();
        }
        return instance;
    }

    //Method to an invoice to the collection
    public void addInvoice(int id, Invoice invoice){
        invoices.put(id, invoice);
    }

    //Method to get an invoice
    public Invoice getInvoice(int id){
        Invoice invoice = invoices.get(id);
        invoice.setVisible(true);
        return invoice;
    }

}
