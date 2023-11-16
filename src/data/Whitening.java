package data;

public class Whitening extends Treatment {

    private static Whitening instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private Whitening(String treatmentType, double fee, short timeInMinutes) {

        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;

    }

    protected static Whitening getWhitening(String treatmentType, double fee, short timeInMinutes){

        if(instance == null){
            instance = new Whitening(treatmentType, fee, timeInMinutes);
        }
        return instance;

    }


}
