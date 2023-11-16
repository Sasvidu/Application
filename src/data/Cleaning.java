package data;

public class Cleaning extends Treatment{

    private static Cleaning instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private Cleaning(String treatmentType, float fee, short timeInMinutes) {

        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;

    }

    public Cleaning getCleaning(String treatmentType, float fee, short timeInMinutes){

        if(instance == null){
            instance = new Cleaning(treatmentType, fee, timeInMinutes);
        }
        return instance;

    }


}

