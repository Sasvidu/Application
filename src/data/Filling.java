package data;

public class Filling extends Treatment{

    //Private variable to store the unique instance
    private static Filling instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private Filling(String treatmentType, double fee, short timeInMinutes) {
        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;
    }

    //Public method to retrieve the unique instance
    protected static Filling getFilling(String treatmentType, double fee, short timeInMinutes){
        if(instance == null){
            instance = new Filling(treatmentType, fee, timeInMinutes);
        }
        return instance;
    }

}
