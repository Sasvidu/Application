package data;

public class TreatmentFactory {

    //Treatment duration and fee data
    private final double cleaningFee = 4000.00;
    private final double whiteningFee = 5000.00;
    private final double fillingFee = 25000.00;
    private final double nerveFillingFee = 28000.00;
    private final double rootCanalTherapyFee = 35000.00;

    private final short cleaningTime = 30;
    private final short whiteningTime = 45;
    private final short fillingTime = 60;
    private final short nerveFillingTime = 60;
    private final short rootCanalTherapyTime = 75;

    //Private class variable for storing the unique instance
    private static TreatmentFactory instance;

    //Private constructor
    private TreatmentFactory(){}

    //Public method for retrieving the schedule factory
    public static TreatmentFactory getTreatmentFactory(){
        if(instance == null){
            instance = new TreatmentFactory();
        }
        return instance;
    }

    //Method for creation of the suitable treatment
    public Treatment getTreatment(String treatmentType){
        if(treatmentType == null || treatmentType == ""){
            return null;
        }else if(treatmentType.matches("Cleaning")){
            return Cleaning.getCleaning(treatmentType, cleaningFee, cleaningTime);
        }else if(treatmentType.matches("Whitening")){
            return Whitening.getWhitening(treatmentType, whiteningFee, whiteningTime);
        }else if(treatmentType.matches("Filling")){
            return Filling.getFilling(treatmentType, fillingFee, fillingTime);
        }else if(treatmentType.matches("Nerve Filling")){
            return NerveFilling.getNerveFilling(treatmentType, nerveFillingFee, nerveFillingTime);
        }else if(treatmentType.matches("Root Canal Therapy")){
            return RootCanalTherapy.getRootCanalTherapy(treatmentType, rootCanalTherapyFee, rootCanalTherapyTime);
        }
        return null;
    }

}
