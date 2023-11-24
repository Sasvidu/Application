package data;

public class Cleaning extends Treatment{

    //Private variable to store the unique instance
    private static Cleaning instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private Cleaning(String treatmentType, double fee, short timeInMinutes) {
        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;
    }

     //Public method to retrieve the unique instance
     protected static Cleaning getCleaning(String treatmentType, double fee, short timeInMinutes){
        if(instance == null){
            instance = new Cleaning(treatmentType, fee, timeInMinutes);
        }
        return instance;
    }

}

