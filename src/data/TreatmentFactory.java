package data;

public class TreatmentFactory {

    private static TreatmentFactory instance;

    private TreatmentFactory(){}

    public static TreatmentFactory getTreatmentFactory(){

        if(instance == null){
            instance = new TreatmentFactory();
        }
        return instance;

    }

    public Treatment getTreatment(String treatmentType, float fee, short timeInMinutes){

        if(treatmentType == null || treatmentType == ""){
            return null;
        }else if(treatmentType.matches("Cleaning")){
            return Cleaning.getCleaning(treatmentType, fee, timeInMinutes);
        }else if(treatmentType.matches("Whitening")){
            return Whitening.getWhitening(treatmentType, fee, timeInMinutes);
        }else if(treatmentType.matches("Filling")){
            return Filling.getFilling(treatmentType, fee, timeInMinutes);
        }else if(treatmentType.matches("Nerve Filling")){
            return NerveFilling.getNerveFilling(treatmentType, fee, timeInMinutes);
        }else if(treatmentType.matches("Root Canal Therapy")){
            return RootCanalTherapy.getRootCanalTherapy(treatmentType, fee, timeInMinutes);
        }
        return null;

    }

}
