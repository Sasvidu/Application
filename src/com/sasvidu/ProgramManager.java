package com.sasvidu;

import forms.*;
import data.*;

public class ProgramManager {

    private static ProgramManager instance;

    //Declare dimensions of JFrame windows:
    static final int loginFormWidth = 420;
    static final int loginFormHeight = 540;

//    static final int homeFrameWidth = 1550;
//    static final int homeFrameHeight = 850;
    static final int homeFrameWidth = 1550;
    static final int homeFrameHeight = 850;
    static final int homeFramePanelWidth = 600;
    static final int homeFrameSearchPanelHeight = 200;

    //Declare Treatment names:
    static final String cleaningCode = "Cleaning";
    static final String whiteningCode = "Whitening";
    static final String fillingCode = "Filling";
    static final String nerveFillingCode = "Nerve Filling";
    static final String RCTCode = "Root Canal Therapy";

    private ProgramManager(){}

    public static ProgramManager getProgramManager(){
        if (instance == null){
            instance = new ProgramManager();
        }
        return instance;
    }

    public void startApp(){

        //LoginForm.getLoginForm(loginFormWidth, loginFormHeight);
        HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight, homeFramePanelWidth, homeFrameSearchPanelHeight);
//        TreatmentFactory treatmentFactory = TreatmentFactory.getTreatmentFactory();
//        ScheduleFactory scheduleFactory = ScheduleFactory.getScheduleFactory();
//
//        Treatment cleaning = treatmentFactory.getTreatment(cleaningCode);
//        Treatment filling = treatmentFactory.getTreatment(fillingCode);
//        Treatment RCT = treatmentFactory.getTreatment(RCTCode);
//
//        Patient patient1 = new Patient("Azmaan", "Some Road", "077354499");
//        Patient patient2 = new Patient("Fathah", "Random Road", "071354499");
//
//        Appointment app1 = new Appointment(1, patient1, cleaning);
//        Appointment app2 = new Appointment(2, patient2, filling);
//        Appointment app3 = new Appointment(3, patient1, filling);
//        Appointment app4 = new Appointment(4, patient2, RCT);
//
//        Schedule schedule = scheduleFactory.getSchedule("2023-11-18");
//        System.out.println(schedule.getAvailableTime());
//        System.out.println(schedule.getListOfAppointments());
//        schedule.addAppointment(app1);
//        System.out.println(schedule.getAvailableTime());
//        System.out.println(schedule.getListOfAppointments());
//        schedule.addAppointment(app2);
//        System.out.println(schedule.getAvailableTime());
//        System.out.println(schedule.getListOfAppointments());
//        schedule.addAppointment(app3);
//        System.out.println(schedule.getAvailableTime());
//        System.out.println(schedule.getListOfAppointments());
//        schedule.addAppointment(app4);
//        System.out.println(schedule.getAvailableTime());
//        System.out.println(schedule.getListOfAppointments());
//
//        System.out.println(app2.getTreatment());
//        System.out.println(app3.getTreatment());

    }

}
