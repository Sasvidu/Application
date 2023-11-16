package data;

public abstract class Treatment {

    //Define Common attributes for a treatment:
    protected String treatmentType;
    protected double fee;
    protected short timeInMinutes;

    //Define Common methods for a treatment (getters):
    public String getTreatmentType() {
        return treatmentType;
    }

    public double getFee() {
        return fee;
    }

    public short getTimeInMinutes() {
        return timeInMinutes;
    }

}
