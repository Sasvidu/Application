package data;

public class Filling extends Treatment{

    private static Filling instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private Filling(String treatmentType, float fee, short timeInMinutes) {

        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;

    }

    protected static Filling getFilling(String treatmentType, float fee, short timeInMinutes){

        if(instance == null){
            instance = new Filling(treatmentType, fee, timeInMinutes);
        }
        return instance;

    }

}
