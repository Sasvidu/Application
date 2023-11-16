package data;

public class RootCanalTherapy extends Treatment{

    private static RootCanalTherapy instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private RootCanalTherapy(String treatmentType, double fee, short timeInMinutes) {

        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;

    }

    protected static RootCanalTherapy getRootCanalTherapy(String treatmentType, double fee, short timeInMinutes){

        if(instance == null){
            instance = new RootCanalTherapy(treatmentType, fee, timeInMinutes);
        }
        return instance;

    }

}
