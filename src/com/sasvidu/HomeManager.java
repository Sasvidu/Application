package com.sasvidu;

import data.*;
import forms.InsertFrame;

public class HomeManager {

    private static HomeManager instance;
    private final int insertFrameWidth = 600;
    private final int insertFrameHeight = 500;

    private HomeManager(){}

    public static HomeManager getHomeManager(){

        if(instance == null){
            instance = new HomeManager();
        }
        return instance;

    }

    public void insert(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, boolean isPaid){

        InsertFrame insertFrame = new InsertFrame(insertFrameWidth, insertFrameHeight, patientName, patientAddress, patientTelephoneNumber, treatmentType, isPaid);

    }

}
