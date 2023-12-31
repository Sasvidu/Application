package data;

public class NerveFilling extends Treatment{

    //Private variable to store the unique instance
    private static NerveFilling instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private NerveFilling(String treatmentType, double fee, short timeInMinutes) {
        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;
    }

    //Public method to retrieve the unique instance
    protected static NerveFilling getNerveFilling(String treatmentType, double fee, short timeInMinutes){
        if(instance == null){
            instance = new NerveFilling(treatmentType, fee, timeInMinutes);
        }
        return instance;
    }

}
