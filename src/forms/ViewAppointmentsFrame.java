package forms;

import com.sasvidu.HomeManager;
import data.Command;
import data.ReadAppointmentsCommand;
import data.ReadPatientAppointmentsCommand;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ViewAppointmentsFrame extends JFrame {

    private HomeManager homeManager = HomeManager.getHomeManager();

    //Name of the patient
    private String patientName;

    //Data:
    private String column[] = {"Date", "Time", "App. Id", "Name", "Address", "Tel. No", "Treatment", "Paid"};

    //Table:
    private TableModel patientAppointmentsTableModel;
    private JTable patientAppointmentsTable;
    private JScrollPane patientAppointmentsTableScrollPane;

    //Declare Dimensions:
    static final int width = 1000;
    static final int height = 700;

    public ViewAppointmentsFrame(String patientName){

        this.patientName = patientName;

        String[][] data = getData();

        // Table:
        patientAppointmentsTableModel = new DefaultTableModel(data, column);
        patientAppointmentsTable = new JTable(patientAppointmentsTableModel);
        patientAppointmentsTable.setRowHeight(30);
        patientAppointmentsTable.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 18));
        patientAppointmentsTable.setFont(new Font("Montserrat", Font.PLAIN, 16));
        patientAppointmentsTableScrollPane = new JScrollPane(patientAppointmentsTable);
        patientAppointmentsTableScrollPane.setBounds(0, 0, width, height);

        this.setTitle("Patient : " + patientName);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(width, height);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.add(patientAppointmentsTableScrollPane);
        this.setVisible(true);

    }

    private String[][] getData(){

        Command<String[][]> readDataCommand = new ReadPatientAppointmentsCommand(patientName);
        homeManager.setCommandWithoutRecording(readDataCommand);
        homeManager.executeCommand();
        String[][] data = readDataCommand.getResult();
        return data;

    }

}
