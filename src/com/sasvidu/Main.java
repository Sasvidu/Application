package com.sasvidu;

import forms.*;
import data.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    //Declare dimensions of JFrame windows:
    static final int loginFormWidth = 420;
    static final int loginFormHeight = 540;
    static final int homeFrameWidth = 1550;
    static final int homeFrameHeight = 850;
    static final int homeFrameFormPanelWidth = 600;
    static final int homeFrameFormPanelHeight = homeFrameHeight;
    static final int homeFrameSearchPanelWidth = homeFrameWidth - homeFrameFormPanelWidth;
    static final int homeFrameSearchPanelHeight = 300;

    //Declare the users of the system:
    static final UserCollection users = UserCollection.getUserCollection(new User[]{new User("Gayani", "t00thCAR3@2023"), new User("Ranasinghe", "123"), new User("Sasvidu", "2007")});

    //Declare Treatment names:
    static final String cleaningCode = "Cleaning";
    static final String whiteningCode = "Whitening";
    static final String fillingCode = "Filling";
    static final String nerveFillingCode = "Nerve Filling";
    static final String RCTCode = "Root Canal Therapy";


    public static void main(String[] args){

        LoginForm.getLoginForm(loginFormWidth, loginFormHeight);
        //HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight, homeFrameFormPanelWidth, homeFrameFormPanelHeight, homeFrameSearchPanelWidth, homeFrameSearchPanelHeight);
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


    public static boolean login(String username, String password){
        //TIme Complexity - O(n)

        Iterator userIterator = users.getIterator();

        while(userIterator.hasNext()){
            User currentUser = (User) userIterator.next();
            if(username.matches(currentUser.getUsername()) && password.matches(currentUser.getPassword())) {
                HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight, homeFrameFormPanelWidth, homeFrameFormPanelHeight, homeFrameSearchPanelWidth, homeFrameSearchPanelHeight);
                return true;
            }
        }

        return false;

    }

}
