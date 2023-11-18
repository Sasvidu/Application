package com.sasvidu;

import data.AppointmentIdCollection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {

        var myApp = ProgramManager.getProgramManager();
        myApp.startApp();

    }

}
