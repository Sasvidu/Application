package data;

public abstract class Treatment {

    //Define Common attributes for a treatment:
    protected String treatmentType;
    protected float fee;
    protected short timeInMinutes;

    //Define Common methods for a treatment (getters):
    public String getTreatmentType() {
        return treatmentType;
    }

    public float getFee() {
        return fee;
    }

    public short getTimeInMinutes() {
        return timeInMinutes;
    }

}
