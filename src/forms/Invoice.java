package forms;

import data.Appointment;
import data.InvoiceCollection;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Invoice extends JFrame {

    //Attributes
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private Appointment appointment;

    //Colors
    private final Color fontColor = new Color(0, 0, 0);
    private final Color backgroundColor = new Color(0xDFF2FF);
    private final Font font = new Font("Montserrat", Font.PLAIN, 20);

    //Dimensions
    private final int labelWidth = 200;
    private final int labelHeight = 50;
    private final int labelX = 50;
    private final int labelValueX = labelX + labelWidth + 50;
    static final int width = 540;
    static final int height = 740;

    public Invoice(Appointment appointment){

        //Initialization
        this.appointment = appointment;
        this.paymentDate = LocalDate.now();
        this.paymentTime = LocalTime.now();

        //Labels
        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setForeground(fontColor);
        dateLabel.setFont(font);
        dateLabel.setVerticalAlignment(JLabel.CENTER);
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setBounds(labelX, 90, labelWidth, labelHeight);

        JLabel timeLabel = new JLabel("Time: ");
        timeLabel.setForeground(fontColor);
        timeLabel.setFont(font);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setBounds(labelX, 90 + (labelHeight + 10), labelWidth, labelHeight);

        JLabel appointmentIdLabel = new JLabel("Appointment Id: ");
        appointmentIdLabel.setForeground(fontColor);
        appointmentIdLabel.setFont(font);
        appointmentIdLabel.setVerticalAlignment(JLabel.CENTER);
        appointmentIdLabel.setHorizontalAlignment(JLabel.CENTER);
        appointmentIdLabel.setBounds(labelX, 90 + (2 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientNameLabel = new JLabel("Patient Name: ");
        patientNameLabel.setForeground(fontColor);
        patientNameLabel.setFont(font);
        patientNameLabel.setVerticalAlignment(JLabel.CENTER);
        patientNameLabel.setHorizontalAlignment(JLabel.CENTER);
        patientNameLabel.setBounds(labelX, 90 + (3 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientAddressLabel = new JLabel("Patient Address: ");
        patientAddressLabel.setForeground(fontColor);
        patientAddressLabel.setFont(font);
        patientAddressLabel.setVerticalAlignment(JLabel.CENTER);
        patientAddressLabel.setHorizontalAlignment(JLabel.CENTER);
        patientAddressLabel.setBounds(labelX, 90 + (4 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientTelephoneNumberLabel = new JLabel("Patient Tel. No.: ");
        patientTelephoneNumberLabel.setForeground(fontColor);
        patientTelephoneNumberLabel.setFont(font);
        patientTelephoneNumberLabel.setVerticalAlignment(JLabel.CENTER);
        patientTelephoneNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        patientTelephoneNumberLabel.setBounds(labelX, 90 + (5 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel treatmentLabel = new JLabel("Treatment: ");
        treatmentLabel.setForeground(fontColor);
        treatmentLabel.setFont(font);
        treatmentLabel.setVerticalAlignment(JLabel.CENTER);
        treatmentLabel.setHorizontalAlignment(JLabel.CENTER);
        treatmentLabel.setBounds(labelX, 90 + (6 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel feeLabel = new JLabel("Fee: ");
        feeLabel.setForeground(fontColor);
        feeLabel.setFont(font);
        feeLabel.setVerticalAlignment(JLabel.CENTER);
        feeLabel.setHorizontalAlignment(JLabel.CENTER);
        feeLabel.setBounds(labelX, 90 + (7 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel dateValueLabel = new JLabel(this.paymentDate.toString());
        dateValueLabel.setForeground(fontColor);
        dateValueLabel.setFont(font);
        dateValueLabel.setVerticalAlignment(JLabel.CENTER);
        dateValueLabel.setHorizontalAlignment(JLabel.CENTER);
        dateValueLabel.setBounds(labelValueX, 90, labelWidth, labelHeight);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String time = this.paymentTime.format(formatter);
        JLabel timeValueLabel = new JLabel(time);
        timeValueLabel.setForeground(fontColor);
        timeValueLabel.setFont(font);
        timeValueLabel.setVerticalAlignment(JLabel.CENTER);
        timeValueLabel.setHorizontalAlignment(JLabel.CENTER);
        timeValueLabel.setBounds(labelValueX, 90 + (labelHeight + 10), labelWidth, labelHeight);

        JLabel appointmentIdValueLabel = new JLabel(String.valueOf(this.appointment.getAppointmentId()));
        appointmentIdValueLabel.setForeground(fontColor);
        appointmentIdValueLabel.setFont(font);
        appointmentIdValueLabel.setVerticalAlignment(JLabel.CENTER);
        appointmentIdValueLabel.setHorizontalAlignment(JLabel.CENTER);
        appointmentIdValueLabel.setBounds(labelValueX, 90 + (2 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientNameValueLabel = new JLabel(this.appointment.getPatient().getName());
        patientNameValueLabel.setForeground(fontColor);
        patientNameValueLabel.setFont(font);
        patientNameValueLabel.setVerticalAlignment(JLabel.CENTER);
        patientNameValueLabel.setHorizontalAlignment(JLabel.CENTER);
        patientNameValueLabel.setBounds(labelValueX, 90 + (3 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientAddressValueLabel = new JLabel(this.appointment.getPatient().getAddress());
        patientAddressValueLabel.setForeground(fontColor);
        patientAddressValueLabel.setFont(font);
        patientAddressValueLabel.setVerticalAlignment(JLabel.CENTER);
        patientAddressValueLabel.setHorizontalAlignment(JLabel.CENTER);
        patientAddressValueLabel.setBounds(labelValueX, 90 + (4 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel patientTelephoneNumberValueLabel = new JLabel(this.appointment.getPatient().getTelephoneNumber());
        patientTelephoneNumberValueLabel.setForeground(fontColor);
        patientTelephoneNumberValueLabel.setFont(font);
        patientTelephoneNumberValueLabel.setVerticalAlignment(JLabel.CENTER);
        patientTelephoneNumberValueLabel.setHorizontalAlignment(JLabel.CENTER);
        patientTelephoneNumberValueLabel.setBounds(labelValueX, 90 + (5 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel treatmentValueLabel = new JLabel(appointment.getTreatment().getTreatmentType());
        treatmentValueLabel.setForeground(fontColor);
        treatmentValueLabel.setFont(font);
        treatmentValueLabel.setVerticalAlignment(JLabel.CENTER);
        treatmentValueLabel.setHorizontalAlignment(JLabel.CENTER);
        treatmentValueLabel.setBounds(labelValueX, 90 + (6 * (labelHeight + 10)), labelWidth, labelHeight);

        JLabel feeValueLabel = new JLabel(String.valueOf(this.appointment.getTreatment().getFee()) + "0");
        feeValueLabel.setForeground(fontColor);
        feeValueLabel.setFont(font);
        feeValueLabel.setVerticalAlignment(JLabel.CENTER);
        feeValueLabel.setHorizontalAlignment(JLabel.CENTER);
        feeValueLabel.setBounds(labelValueX, 90 + (7 * (labelHeight + 10)), labelWidth, labelHeight);

        //Window
        this.setTitle("Invoice");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(backgroundColor);

        this.add(dateLabel);
        this.add(timeLabel);
        this.add(appointmentIdLabel);
        this.add(patientNameLabel);
        this.add(patientAddressLabel);
        this.add(patientTelephoneNumberLabel);
        this.add(treatmentLabel);
        this.add(feeLabel);

        this.add(dateValueLabel);
        this.add(timeValueLabel);
        this.add(appointmentIdValueLabel);
        this.add(patientNameValueLabel);
        this.add(patientAddressValueLabel);
        this.add(patientTelephoneNumberValueLabel);
        this.add(treatmentValueLabel);
        this.add(feeValueLabel);

        this.setVisible(true);

        //Add the invoice to the invoice collection
        int appointmentId = appointment.getAppointmentId();
        InvoiceCollection.getInvoiceCollection().addInvoice(appointmentId, this);

    }

    //Getter for the appointment
    public Appointment getAppointment(){
        return this.appointment;
    }

}
