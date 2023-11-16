package data;

public class Patient {

    private String name;
    private String address;
    private String telNo;

    protected Patient(String name, String address, String telNo){
        this.name = name;
        this.address = address;
        this.telNo = telNo;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
