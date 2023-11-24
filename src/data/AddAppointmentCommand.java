package data;

//Command to handle the click event of the add appointment button.
//It takes in parameters for appointment details and the date during which the appointment is to be placed. The time will be the time calculated by the system via the check date method.

import com.sasvidu.InsertManager;
import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class AddAppointmentCommand implements Command<String>{

    //Objects from classes that the command interacts with
    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private TreatmentFactory treatmentFactory = TreatmentFactory.getTreatmentFactory();
    private InsertManager insertManager = InsertManager.getInsertManager();

    //Mementos to be created
    private CombinedMemento beforeMemento;
    private CombinedMemento afterMemento;

    //Data of the appointment
    private Date selectedDate;
    private String patientName;
    private String patientAddress;
    private String patientTelephoneNumber;
    private String treatmentType;

    //Variable to store the return value
    private String result;

    //Error messages
    private final String retrievalError = "Retrieval Error";
    private final String nullError = "Null Error";
    private final String unavailableError = "Unavailable Error";
    private final String overflowError = "Overflow Error";
    private final String error = "Error";
    private final String success = "Success";

    //Parameterized constructor to initialize the variable
    public AddAppointmentCommand(Date selectedDate, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){
        this.selectedDate = selectedDate;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientTelephoneNumber = patientTelephoneNumber;
        this.treatmentType = treatmentType;
    }

    @Override
    public void execute() {
        try {
            //Capture state in a memento
            beforeMemento = MementoManager.getMementoManager().createMemento();
            //Call the checkDate function to see whether there is time for the appointment to be reserved for the requested date, and get the available time if there is
            Command<String> validateCommand = new CheckDateCommand(selectedDate, treatmentType);
            insertManager.setCheckCommand(validateCommand);
            insertManager.executeCommand();
            String availableTime = validateCommand.getResult();
            if (availableTime.matches(nullError) || availableTime.matches(unavailableError) || availableTime.matches(overflowError) || availableTime.matches(retrievalError)) {
                //If the checkDate functiom does not return an available time, forward the error message to the GUI component, to which it can react accordingly
                result = availableTime;
            } else {
                //Convert the selected date to a LocalDate object
                LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
                //See if a schedule has already been created for the date:
                if (!schedules.hasSchedule(date)) {
                    //If a schedule is not already available, create a new one
                    schedules.addSchedule(date);
                }
                //Insert the appointment into the schedule and the appointment list
                result = insertAppointment(patientName, patientAddress, patientTelephoneNumber, treatmentType, date);
                //Capture state in a memento
                afterMemento = MementoManager.getMementoManager().createMemento();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getResult() {
        return result;
    }

    //Private method to represent the common operations for inserting an appointment into the data structures, called by the addAppointment method
    private String insertAppointment(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, LocalDate date) throws CloneNotSupportedException {
        //Obtain the schedule for the date
        Schedule schedule = schedules.getSchedule(date);
        //Get the object representing the treatment required
        Treatment treatment = treatmentFactory.getTreatment(treatmentType);
        //Create a unique Id for the appointment
        int appointmentId = createId();
        //Create a new appointment object to encapsulate all the information
        Appointment appointment = new Appointment(appointmentId, patientName, patientAddress, patientTelephoneNumber, treatment);
        //Invoke operation for adding appointment to the schedule of the day, and store the result in a string variable
        String response = schedule.addAppointment(appointment);
        //See if the operation was successful
        if (response.matches(success)) {
            //Add the appointment to a hashmap of appointments if the appointment was added to the schedule
            appointments.addAppointment(appointmentId, appointment);
            //Return a success message
            return success;
        } else {
            //If addition is not successful, forward the error to the JFrame
            return response;
        }
    }

    //Private method that generates a random id for an appointment
    private int createId() {
        Random random = new Random();
        int id = 100;
        while (appointments.hasId(id)) {
            id = random.nextInt(1000);
        }
        return id;
    }

    //Methods for implementing Memento
    @Override
    public void undo(){
        MementoManager.getMementoManager().undo();
    }

    @Override
    public void redo(){
        MementoManager.getMementoManager().redo();
    }


}
