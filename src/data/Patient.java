package data;

public class Patient implements Cloneable{

    private String name;
    private String address;
    private String telephoneNumber;

    protected Patient(String name, String address, String telephoneNumber){
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telNo) {
        this.telephoneNumber = telNo;
    }

    @Override
    public Patient clone() throws CloneNotSupportedException {
        Patient cloned = (Patient) super.clone();
        return cloned;
    }

}
