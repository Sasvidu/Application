package forms;

import com.sasvidu.HomeManager;
import data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class HomeFrame extends JFrame implements ActionListener, Observer {

    //Private variable for storing the singular instance
    private static HomeFrame instance;

    //Other objects that the class interacts with
    private final HomeManager homeManager = HomeManager.getHomeManager();
    private LinkedList<Observable> observables = new LinkedList<>();

    //Table
    private TableModel appointmentsTableModel;
    private JTable appointmentsTable;
    private JScrollPane appointmentsTableScrollPane;

    //Buttons
    private JButton searchButton;
    private JButton viewButton;
    private JButton insertButton;
    private JButton payButton;
    private JButton editButton;
    private JButton invoiceButton;
    private JButton undoButton;
    private JButton redoButton;

    // Fields
    private JTextField appointmentIdFieldForSearch;
    private JTextField patientNameFieldForSearch;
    private JTextField appointmentIdField;
    private JTextField patientNameField;
    private JTextField patientAddressField;
    private JTextField patientPhoneNumberField;
    private JComboBox appointmentTreatmentField;
    private JRadioButton appointmentYesPaidButton;
    private JRadioButton appointmentNoPaidButton;
    private ButtonGroup appointmentIsPaidButtonGroup;

    //Table data
    private String[][] data = getData();
    private String column[] = {"Date", "Time", "App. Id", "Name", "Address", "Tel. No", "Treatment", "Paid"};

    //Declare HomeFrame Dimensions
    static final int width = 1550;
    static final int height = 850;
    static final int panelWidth = 600;
    static final int searchPanelHeight = 200;

    // Private Constructor
    private HomeFrame() {

        //Register the collection of schedules as an observable
        addObservable(ScheduleCollection.getScheduleCollection());

        //Theme colors
        Color homeOrange = new Color(0xB76D68);

        //Dimensions of components
        int labelWidth = 200;
        int labelHeight = 20;
        int labelX = 70;

        int textFieldWidth = 200;
        int textFieldHeight = 25;
        int textFieldX = 300;

        int searchButtonWidth = 160;
        int searchButtonHeight = 30;
        int formButtonWidth = 100;
        int formButtonHeight = 30;

        int formButtonMargin = 20;

        // Panels
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xFF8F73));
        searchPanel.setBounds(0, 0, panelWidth, searchPanelHeight);
        searchPanel.setLayout(null);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(homeOrange);
        formPanel.setBounds(0, searchPanelHeight, panelWidth, height - searchPanelHeight);
        formPanel.setLayout(null);

        // Table
        appointmentsTableModel = new DefaultTableModel(data, column);
        appointmentsTable = new JTable(appointmentsTableModel);
        appointmentsTable.setRowHeight(30);
        appointmentsTable.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 18));
        appointmentsTable.setFont(new Font("Montserrat", Font.PLAIN, 16));
        appointmentsTableScrollPane = new JScrollPane(appointmentsTable);
        appointmentsTableScrollPane.setBounds(panelWidth, 0, width - panelWidth, height);

        // Labels
        JLabel appointmentIdLabelForSearch = new JLabel("Appointment ID:");
        JLabel patientNameLabelForSearch = new JLabel("Patient Name:");
        JLabel appointmentIdLabel = new JLabel("Appointment ID:");
        JLabel patientNameLabel = new JLabel("Patient Name:");
        JLabel patientAddressLabel = new JLabel("Patient Address:");
        JLabel patientPhoneNumberLabel = new JLabel("Patient Phone Number:");
        JLabel appointmentTreatmentLabel = new JLabel("Treatment Type:");
        JLabel appointmentIsPaidLabel = new JLabel("Payment Made:");

        appointmentIdLabelForSearch.setVerticalAlignment(JLabel.CENTER);
        appointmentIdLabelForSearch.setHorizontalAlignment(JLabel.CENTER);

        // Set bounds for labels
        appointmentIdLabelForSearch.setBounds((((panelWidth - searchButtonWidth) / 2) - searchButtonWidth), (searchPanelHeight * 2 / 10), searchButtonWidth, searchButtonHeight);
        patientNameLabelForSearch.setBounds((((panelWidth - searchButtonWidth) / 2) + searchButtonWidth), (searchPanelHeight * 2 / 10), searchButtonWidth, searchButtonHeight);
        appointmentIdLabel.setBounds(labelX, 50, labelWidth, labelHeight);
        patientNameLabel.setBounds(labelX, 110, labelWidth, labelHeight);
        patientAddressLabel.setBounds(labelX, 170, labelWidth, labelHeight);
        patientPhoneNumberLabel.setBounds(labelX, 230, labelWidth, labelHeight);
        appointmentTreatmentLabel.setBounds(labelX, 290, labelWidth, labelHeight);
        appointmentIsPaidLabel.setBounds(labelX, 350, labelWidth, labelHeight);

        // Text fields
        String[] treatmentOptions = {"Cleaning" , "Whitening", "Filling", "Nerve Filling", "Root Canal Therapy"};

        appointmentIdFieldForSearch = new JTextField();
        patientNameFieldForSearch = new JTextField();
        appointmentIdField = new JTextField();
        patientNameField = new JTextField();
        patientAddressField = new JTextField();
        patientPhoneNumberField = new JTextField();
        appointmentTreatmentField = new JComboBox<>(treatmentOptions);
        appointmentYesPaidButton = new JRadioButton("Yes");
        appointmentNoPaidButton = new JRadioButton("No");

        appointmentIdField.setEditable(false);
        appointmentYesPaidButton.setBackground(homeOrange);
        appointmentNoPaidButton.setBackground(homeOrange);

        appointmentIsPaidButtonGroup = new ButtonGroup();
        appointmentIsPaidButtonGroup.add(appointmentYesPaidButton);
        appointmentIsPaidButtonGroup.add(appointmentNoPaidButton);

        // Set bounds for text fields
        appointmentIdFieldForSearch.setBounds((((panelWidth - searchButtonWidth) / 2) - searchButtonWidth), (searchPanelHeight * 5 / 10), searchButtonWidth, searchButtonHeight);
        patientNameFieldForSearch.setBounds((((panelWidth - searchButtonWidth) / 2) + searchButtonWidth - 50), (searchPanelHeight * 5 / 10), searchButtonWidth, searchButtonHeight);
        appointmentIdField.setBounds(textFieldX, 45, textFieldWidth, textFieldHeight);
        patientNameField.setBounds(textFieldX, 105, textFieldWidth, textFieldHeight);
        patientAddressField.setBounds(textFieldX, 165, textFieldWidth, textFieldHeight);
        patientPhoneNumberField.setBounds(textFieldX, 225, textFieldWidth, textFieldHeight);
        appointmentTreatmentField.setBounds(textFieldX, 285, textFieldWidth, textFieldHeight);
        appointmentYesPaidButton.setBounds(textFieldX, 355, textFieldWidth / 2, textFieldHeight);
        appointmentNoPaidButton.setBounds(textFieldX + (textFieldWidth / 2), 355, textFieldWidth / 2, textFieldHeight);

        // Buttons
        searchButton = new JButton();
        searchButton.setText("SEARCH");
        searchButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        searchButton.setVerticalAlignment(JButton.CENTER);
        searchButton.setHorizontalAlignment(JButton.CENTER);
        searchButton.setFocusable(false);
        searchButton.setBackground(Color.lightGray);
        searchButton.setBounds((((panelWidth - searchButtonWidth) / 2) - searchButtonWidth), (searchPanelHeight * 7 / 10), searchButtonWidth, searchButtonHeight);
        searchButton.addActionListener(this);

        viewButton = new JButton();
        viewButton.setText("APPOINTMENTS");
        viewButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        viewButton.setVerticalAlignment(JButton.CENTER);
        viewButton.setHorizontalAlignment(JButton.CENTER);
        viewButton.setFocusable(false);
        viewButton.setBackground(Color.lightGray);
        viewButton.setBounds((((panelWidth - searchButtonWidth) / 2) + searchButtonWidth - 50), (searchPanelHeight * 7 / 10), searchButtonWidth, searchButtonHeight);
        viewButton.addActionListener(this);

        insertButton = new JButton();
        insertButton.setText("INSERT");
        insertButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        insertButton.setVerticalAlignment(JButton.CENTER);
        insertButton.setHorizontalAlignment(JButton.CENTER);
        insertButton.setFocusable(false);
        insertButton.setBackground(Color.lightGray);
        insertButton.setBounds(labelX, 450, formButtonWidth, formButtonHeight);
        insertButton.addActionListener(this);

        editButton = new JButton();
        editButton.setText("UPDATE");
        editButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        editButton.setVerticalAlignment(JButton.CENTER);
        editButton.setHorizontalAlignment(JButton.CENTER);
        editButton.setFocusable(false);
        editButton.setBackground(Color.lightGray);
        editButton.setBounds(labelX + (formButtonWidth + formButtonMargin), 450, formButtonWidth, formButtonHeight);
        editButton.addActionListener(this);

        payButton = new JButton();
        payButton.setText("PAY");
        payButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        payButton.setVerticalAlignment(JButton.CENTER);
        payButton.setHorizontalAlignment(JButton.CENTER);
        payButton.setFocusable(false);
        payButton.setBackground(Color.lightGray);
        payButton.setBounds(labelX + (2 * (formButtonWidth + formButtonMargin)), 450, formButtonWidth, formButtonHeight);
        payButton.addActionListener(this);

        invoiceButton = new JButton();
        invoiceButton.setText("INVOICE");
        invoiceButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        invoiceButton.setVerticalAlignment(JButton.CENTER);
        invoiceButton.setHorizontalAlignment(JButton.CENTER);
        invoiceButton.setFocusable(false);
        invoiceButton.setBackground(Color.lightGray);
        invoiceButton.setBounds(labelX + (3 * (formButtonWidth + formButtonMargin)), 450, formButtonWidth, formButtonHeight);
        invoiceButton.addActionListener(this);

        undoButton = new JButton();
        undoButton.setText("UNDO");
        undoButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        undoButton.setVerticalAlignment(JButton.CENTER);
        undoButton.setHorizontalAlignment(JButton.CENTER);
        undoButton.setFocusable(false);
        undoButton.setBackground(Color.lightGray);
        undoButton.setBounds(((panelWidth / 2) - 110), 490, formButtonWidth, formButtonHeight);
        undoButton.addActionListener(this);

        redoButton = new JButton();
        redoButton.setText("REDO");
        redoButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        redoButton.setVerticalAlignment(JButton.CENTER);
        redoButton.setHorizontalAlignment(JButton.CENTER);
        redoButton.setFocusable(false);
        redoButton.setBackground(Color.lightGray);
        redoButton.setBounds(((panelWidth / 2) + 10), 490, formButtonWidth, formButtonHeight);
        redoButton.addActionListener(this);

        // Window
        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0x403F4C));

        this.add(searchPanel);
        this.add(formPanel);
        this.add(appointmentsTableScrollPane);

        searchPanel.add(appointmentIdLabelForSearch);
        searchPanel.add(appointmentIdFieldForSearch);
        searchPanel.add(searchButton);
        searchPanel.add(patientNameLabelForSearch);
        searchPanel.add(patientNameFieldForSearch);
        searchPanel.add(viewButton);

        formPanel.add(appointmentIdLabel);
        formPanel.add(patientNameLabel);
        formPanel.add(patientAddressLabel);
        formPanel.add(patientPhoneNumberLabel);
        formPanel.add(appointmentTreatmentLabel);
        formPanel.add(appointmentIsPaidLabel);
        formPanel.add(appointmentIdField);
        formPanel.add(patientNameField);
        formPanel.add(patientAddressField);
        formPanel.add(patientPhoneNumberField);
        formPanel.add(appointmentTreatmentField);
        formPanel.add(appointmentYesPaidButton);
        formPanel.add(appointmentNoPaidButton);
        formPanel.add(insertButton);
        formPanel.add(payButton);
        formPanel.add(editButton);
        formPanel.add(invoiceButton);
        formPanel.add(undoButton);
        formPanel.add(redoButton);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == insertButton){

            //Handle Appointment Insert
            String patientName = patientNameField.getText();
            String patientAddress = patientAddressField.getText();
            String patientTelephoneNumber = patientPhoneNumberField.getText();
            String treatmentType = appointmentTreatmentField.getSelectedItem().toString();
            homeManager.insert(patientName, patientAddress, patientTelephoneNumber, treatmentType);

        }else if(e.getSource() == searchButton){

            //Handle Appointment Search by Id
            String appointmentId = appointmentIdFieldForSearch.getText();
            Command<String[]> searchCommand = new SearchAppointmentCommand(appointmentId);
            homeManager.setCommandWithoutRecording(searchCommand);
            homeManager.executeCommand();
            String[] attributes = searchCommand.getResult();
            if(attributes != null) {
                System.out.println(attributes);
                this.appointmentIdField.setText(String.valueOf(attributes[0]));
                this.patientNameField.setText(String.valueOf(attributes[1]));
                this.patientAddressField.setText(String.valueOf(attributes[2]));
                this.patientPhoneNumberField.setText(String.valueOf(attributes[3]));
                this.appointmentTreatmentField.setSelectedItem(String.valueOf(attributes[4]));
                boolean isPaid = Boolean.parseBoolean(attributes[5]);
                if (isPaid) {
                    appointmentYesPaidButton.setSelected(true);
                } else {
                    appointmentNoPaidButton.setSelected(true);
                }
            }

        }else if(e.getSource() == viewButton){

            //Handle Appointment Search by Patient Name
            String patientName = patientNameFieldForSearch.getText();
            homeManager.view(patientName);

        }else if(e.getSource() == editButton){

            //Handle Appointment Edit
            String appointmentId = appointmentIdField.getText();
            String patientName = patientNameField.getText();
            String patientAddress = patientAddressField.getText();
            String patientTelephoneNumber = patientPhoneNumberField.getText();
            String treatmentType = appointmentTreatmentField.getSelectedItem().toString();
            Command editCommand = new EditAppointmentCommand(appointmentId, patientName, patientAddress, patientTelephoneNumber, treatmentType);
            homeManager.setCommand(editCommand);
            homeManager.executeCommand();

        }else if(e.getSource() == payButton){

            //Handle Appointment Payment
            String appointmentId = appointmentIdField.getText();
            Command payCommand = new PayAppointmentCommand(appointmentId);
            homeManager.setCommand(payCommand);
            homeManager.executeCommand();

        }else if(e.getSource() == invoiceButton){

            //Handle Appointment Invoice Retrieval
            String appointmentId = appointmentIdField.getText();
            Command viewCommand = new ViewInvoiceCommand(appointmentId);
            homeManager.setCommandWithoutRecording(viewCommand);
            homeManager.executeCommand();

        }else if(e.getSource() == undoButton){

            //Handle Undo Event
            homeManager.undo();

        }else if(e.getSource() == redoButton){

            //Handle Redo Event
            homeManager.redo();

        }

    }

    // Public method to retrieve the single instance
    public static HomeFrame getHomeFrame() {
        if (instance == null) {
            instance = new HomeFrame();
        }
        return instance;
    }

    //Method to see if the yes option for payment is selected
    private boolean isPaid(){
        if(appointmentYesPaidButton.isSelected()){
            return true;
        }
        return false;
    }

    //Method to add an observable to the Home Frame
    public void addObservable(Observable observable){
        observables.add(observable);
        observable.addObserver(this);
    }

    //Update method of an observer, that refreshes the UI
    @Override
    public void update(Observable observable) {
        String[][] newData = getData();
        updateTable(newData);

        appointmentIdField.setText("");
        patientNameField.setText("");
        patientAddressField.setText("");
        patientPhoneNumberField.setText("");
        appointmentTreatmentField.setSelectedItem("Cleaning");
        appointmentYesPaidButton.setSelected(false);
        appointmentNoPaidButton.setSelected(false);
        appointmentIsPaidButtonGroup.clearSelection();

        appointmentIdField.repaint();
        appointmentsTable.repaint();
        patientNameField.repaint();
        patientAddressField.repaint();
        patientPhoneNumberField.repaint();
        appointmentTreatmentField.repaint();
        appointmentYesPaidButton.repaint();
        appointmentNoPaidButton.repaint();
    }

    //Method to retrieve data for the table
    private String[][] getData(){
        Command<String[][]> readDataCommand = new ReadAppointmentsCommand();
        homeManager.setCommandWithoutRecording(readDataCommand);
        homeManager.executeCommand();
        String[][] data = readDataCommand.getResult();
        return data;
    }

    //Method to update the table data
    private void updateTable(String[][] newData) {
        DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();
        model.setDataVector(newData, column);
    }


}