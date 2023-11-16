package data;

public class NerveFilling extends Treatment{

    private static NerveFilling instance;

    //Parameterized Constructor to initialize the attributes upon instantiation:
    private NerveFilling(String treatmentType, float fee, short timeInMinutes) {

        this.treatmentType = treatmentType;
        this.fee = fee;
        this.timeInMinutes = timeInMinutes;

    }

    public NerveFilling getNerveFilling(String treatmentType, float fee, short timeInMinutes){

        if(instance == null){
            instance = new NerveFilling(treatmentType, fee, timeInMinutes);
        }
        return instance;

    }

}
