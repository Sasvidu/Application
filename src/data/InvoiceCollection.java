package data;

import forms.Invoice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InvoiceCollection implements Cloneable{

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

    public HashMap<Integer, Invoice> getInvoices(){
        return invoices;
    }

    public CombinedMemento saveStateToMemento() throws CloneNotSupportedException {
        return MementoManager.getMementoManager().createMemento();
    }

    public void restoreStateFromMemento(CombinedMemento memento){
        this.invoices = memento.getInvoices().getInvoices();
    }

    @Override
    public InvoiceCollection clone() throws CloneNotSupportedException{
        InvoiceCollection cloned = (InvoiceCollection) super.clone();
        HashMap<Integer, Invoice> clonedInvoices = new HashMap<>();
        for (Map.Entry<Integer, Invoice> entry : invoices.entrySet()) {
            int id = entry.getKey();
            Invoice invoice = entry.getValue().clone();
            clonedInvoices.put(id, invoice);
        }
        cloned.invoices = clonedInvoices;
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        InvoiceCollection invoicesObj = (InvoiceCollection) obj;
        return deepEquals(this.invoices, invoicesObj.invoices);
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoices);
    }

}
