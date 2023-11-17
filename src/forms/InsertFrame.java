package forms;

import com.sasvidu.InsertManager;
import com.toedter.calendar.JDateChooser;

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
    private boolean isPaid;

    private JDateChooser dateField;
    private JButton checkDateButton;
    private JButton insertButton;

    public InsertFrame(int width, int height, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, boolean isPaid) {

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
        this.isPaid = isPaid;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == checkDateButton){
            var insertManager = InsertManager.getInsertManager();
            Date selectedDate = dateField.getDate();
            String response = insertManager.checkDate(selectedDate);
            switch (response){
                case "Null Error" : JOptionPane.showMessageDialog(null, "Please select a date to check availability.", "Warning", JOptionPane.WARNING_MESSAGE); break;
                case "Unavailable Error" : JOptionPane.showMessageDialog(null, "Appointments can only be made for Monday, Wednesday, Saturday and Sunday. The selected date is not one of these days.", "Warning", JOptionPane.WARNING_MESSAGE); break;
                case "Retrieval Error" : JOptionPane.showMessageDialog(null, "There was an error reading the available time for the day, please contact your troubleshooting manager to solve this issue.", "Error", JOptionPane.ERROR_MESSAGE); break;
                default : JOptionPane.showMessageDialog(null, "An appointment can be reserved at : " + response, "Available TIme", JOptionPane.INFORMATION_MESSAGE); break;
            }
        }

    }

}
