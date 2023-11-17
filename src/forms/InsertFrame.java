package forms;

import com.sasvidu.InsertManager;
import com.toedter.calendar.JDateChooser;
import data.AppointmentIdCollection;
import data.ScheduleCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class InsertFrame extends JFrame implements ActionListener {

    private String patientName;
    private String patientAddress;
    private String patientTelephoneNumber;
    private String treatmentType;

    private JDateChooser dateField;
    private JButton checkDateButton;
    private JButton insertButton;

    private final String warningTxt = "Warning!";
    private final String errorTxt = "Error!";
    private final String nullErrorMessage = "Please select a date to check availability.";
    private final String unavailableErrorMessage = "Appointments can only be made for Monday, Wednesday, Saturday and Sunday. The selected date is not one of these days.";
    private final String overflowErrorMessage = "Sorry, there are no available time slots for the selected date as the schedule is already full";
    private final String retrievalErrorMessage = "There was an error reading a schedule, please contact your troubleshooting manager to solve this issue.";
    private final String uncalledErrorMessage = "There was an error invoking function related to the Check Date button, please contact your troubleshooting manager to solve this issue.";
    private final String addedAlreadyErrorMessage = "The appointment is already added to the date's schedule.";
    private final String addedElsewhereErrorMessage = "The appointment is already added to another date's schedule.";
    private final String errorMessage = "Something went wrong, please contact your troubleshooting manager for assistance";
    private final String successAddtionMessage = "The appointment has been successfully added, the registration fee is LKR. 1000.00/=";

    public InsertFrame(int width, int height, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType) {

        int dateLabelWidth = 70;
        int buttonWidth = 250;

        //Labels:
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds((width / 4), (height / 5), dateLabelWidth, 20);

        //Calendar:
        dateField = new JDateChooser();
        dateField.setBounds((width / 4) + dateLabelWidth, (height / 5), 250, 20);

        //Buttons:
        checkDateButton = new JButton();
        checkDateButton.setText("CHECK AVAILABILITY");
        checkDateButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        checkDateButton.setVerticalAlignment(JButton.CENTER);
        checkDateButton.setHorizontalAlignment(JButton.CENTER);
        checkDateButton.setFocusable(false);
        checkDateButton.setBackground(Color.white);
        checkDateButton.setBounds((width - buttonWidth) / 2, (height * 6 / 10) - 10, buttonWidth, 50);
        checkDateButton.addActionListener(this);

        insertButton = new JButton();
        insertButton.setText("ADD APPOINTMENT");
        insertButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        insertButton.setForeground(Color.white);
        insertButton.setVerticalAlignment(JButton.CENTER);
        insertButton.setHorizontalAlignment(JButton.CENTER);
        insertButton.setFocusable(false);
        insertButton.setBackground(Color.gray);
        insertButton.setBounds((width - buttonWidth) / 2, (height * 7 / 10), buttonWidth, 50);
        insertButton.addActionListener(this);

        //Window:
        this.setTitle("Insert Appointment");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.lightGray);

        this.add(dateLabel);
        this.add(dateField);
        this.add(checkDateButton);
        this.add(insertButton);

        this.setVisible(true);

        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientTelephoneNumber = patientTelephoneNumber;
        this.treatmentType = treatmentType;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == checkDateButton) {
            String response = callInsertManager(checkDateButton);
            switch (response) {
                case "Null Error":
                    JOptionPane.showMessageDialog(null, nullErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                    break;
                case "Unavailable Error":
                    JOptionPane.showMessageDialog(null, unavailableErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                    break;
                case "Overflow Error":
                    JOptionPane.showMessageDialog(null, overflowErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                case "Retrieval Error":
                    JOptionPane.showMessageDialog(null, retrievalErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                case "Uncalled Error":
                    JOptionPane.showMessageDialog(null, uncalledErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "An appointment can be reserved at : " + response, "Available TIme", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }

        if (e.getSource() == insertButton) {
            String response = callInsertManager(insertButton);
            switch (response) {
                case "Null Error":
                    JOptionPane.showMessageDialog(null, nullErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                    break;
                case "Unavailable Error":
                    JOptionPane.showMessageDialog(null, unavailableErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                    break;
                case "Overflow Error":
                    JOptionPane.showMessageDialog(null, overflowErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                case "Retrieval Error":
                    JOptionPane.showMessageDialog(null, retrievalErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                case "Uncalled Error":
                    JOptionPane.showMessageDialog(null, uncalledErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                case "Present Elsewhere Error":
                    JOptionPane.showMessageDialog(null, addedElsewhereErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                case "Already Added Error":
                    JOptionPane.showMessageDialog(null, addedAlreadyErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
                case "Success":
                    JOptionPane.showMessageDialog(null, successAddtionMessage, "Appointment Added!", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, errorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

    }

    public String callInsertManager(JButton button) {
        var insertManager = InsertManager.getInsertManager();
        Date selectedDate = dateField.getDate();
        if (button == checkDateButton) {
            return insertManager.checkDate(selectedDate, treatmentType);
        } else if (button == insertButton) {
            return insertManager.addAppointment(selectedDate, patientName, patientAddress, patientTelephoneNumber, treatmentType);
        }
        return "Uncalled Error";
    }

}
