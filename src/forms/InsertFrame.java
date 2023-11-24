package forms;

import com.sasvidu.InsertManager;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class InsertFrame extends JFrame implements ActionListener {

    //Objects of other classes that the class interacts with
    private final InsertManager insertManager = InsertManager.getInsertManager();

    //Variables to store the data of the patient for whom the appointment is made
    private String patientName;
    private String patientAddress;
    private String patientTelephoneNumber;
    private String treatmentType;

    //Interactive Components
    private JDateChooser dateField;
    private JButton checkDateButton;
    private JButton insertButton;

    //Dimensions
    private final int width = 600;
    private final int height = 500;

    public InsertFrame(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType) {

        //Dimensions
        int dateLabelWidth = 70;
        int buttonWidth = 250;

        //Labels
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds((width / 4), (height / 5), dateLabelWidth, 20);

        //Calendar
        dateField = new JDateChooser();
        dateField.setBounds((width / 4) + dateLabelWidth, (height / 5), 250, 20);

        //Buttons
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

        //Window
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

        //Handle Check Date Event
        if (e.getSource() == checkDateButton) {
            Date selectedDate = dateField.getDate();
            insertManager.checkDate(selectedDate, treatmentType);
        }

        //Handle Insert Event
        if (e.getSource() == insertButton) {
            Date selectedDate = dateField.getDate();
            insertManager.addAppointment(selectedDate, patientName, patientAddress, patientTelephoneNumber, treatmentType, this);
        }

    }

}
