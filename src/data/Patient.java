package data;

import java.util.Objects;

public class Patient implements Cloneable{

    //Data Attributes
    private String name;
    private String address;
    private String telephoneNumber;

    //Parameterized constructor for initializing the variables
    protected Patient(String name, String address, String telephoneNumber){
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telNo) {
        this.telephoneNumber = telNo;
    }

    //Methods for implementing Memento
    @Override
    public Patient clone() throws CloneNotSupportedException {
        Patient cloned = (Patient) super.clone();
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient patientObj = (Patient) obj;
        return  Objects.equals(name, patientObj.name) &&
                Objects.equals(address, patientObj.address) &&
                Objects.equals(telephoneNumber, patientObj.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, telephoneNumber);
    }

}
