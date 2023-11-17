package forms;

import com.sasvidu.HomeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {

    // Private variable for storing the singular instance
    private static HomeFrame instance;

    //Buttons:
    private JButton searchButton;
    private JButton insertButton;
    private JButton payButton;
    private JButton editButton;

    // Fields:
    private JTextField appointmentIdFieldForSearch;
    private JTextField appointmentIdField;
    private JTextField patientNameField;
    private JTextField patientAddressField;
    private JTextField patientPhoneNumberField;
    private JComboBox appointmentTreatmentField;
    private JRadioButton appointmentYesPaidButton;
    private JRadioButton appointmentNoPaidButton;


    // Private Constructor
    private HomeFrame(int width, int height, int panelWidth, int searchPanelHeight) {

        Color homeOrange = new Color(0xB76D68);

        int labelWidth = 200;
        int labelHeight = 20;
        int labelX = 70;

        int textFieldWidth = 200;
        int textFieldHeight = 25;
        int textFieldX = 300;

        int searchButtonWidth = 160;
        int searchButtonHeight = 30;
        int formButtonWidth = 130;
        int formButtonHeight = 30;

        int formButtonMargin = 20;

        // Panels:
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xFF8F73));
        searchPanel.setBounds(0, 0, panelWidth, searchPanelHeight);
        searchPanel.setLayout(null);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(homeOrange);
        formPanel.setBounds(0, searchPanelHeight, panelWidth, height - searchPanelHeight);
        formPanel.setLayout(null);

        // Table:
        String data[][] = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"103", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};

        JTable appointmentsTable = new JTable(data, column);
        appointmentsTable.setRowHeight(30);
        appointmentsTable.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 18));
        appointmentsTable.setFont(new Font("Montserrat", Font.PLAIN, 16));
        JScrollPane appointmentsTableScrollPane = new JScrollPane(appointmentsTable);
        appointmentsTableScrollPane.setBounds(panelWidth, 0, width - panelWidth, height);

        // Labels
        JLabel appointmentIdLabelForSearch = new JLabel("Appointment ID:");

        JLabel appointmentIdLabel = new JLabel("Appointment ID:");
        JLabel patientNameLabel = new JLabel("Patient Name:");
        JLabel patientAddressLabel = new JLabel("Patient Address:");
        JLabel patientPhoneNumberLabel = new JLabel("Patient Phone Number:");
        JLabel appointmentTreatmentLabel = new JLabel("Treatment Type:");
        JLabel appointmentIsPaidLabel = new JLabel("Payment Made:");

        appointmentIdLabelForSearch.setVerticalAlignment(JLabel.CENTER);
        appointmentIdLabelForSearch.setHorizontalAlignment(JLabel.CENTER);

        // Set bounds for labels
        appointmentIdLabelForSearch.setBounds((panelWidth - searchButtonWidth) / 2, (searchPanelHeight * 2 / 10), searchButtonWidth, searchButtonHeight);

        appointmentIdLabel.setBounds(labelX, 50, labelWidth, labelHeight);
        patientNameLabel.setBounds(labelX, 110, labelWidth, labelHeight);
        patientAddressLabel.setBounds(labelX, 170, labelWidth, labelHeight);
        patientPhoneNumberLabel.setBounds(labelX, 230, labelWidth, labelHeight);
        appointmentTreatmentLabel.setBounds(labelX, 290, labelWidth, labelHeight);
        appointmentIsPaidLabel.setBounds(labelX, 350, labelWidth, labelHeight);

        // Text fields
        String[] treatmentOptions = {"Cleaning" , "Whitening", "Filling", "Nerve Filling", "Root Canal Therapy"};

        appointmentIdFieldForSearch = new JTextField();

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

        ButtonGroup appointmentIsPaidButtonGroup = new ButtonGroup();
        appointmentIsPaidButtonGroup.add(appointmentYesPaidButton);
        appointmentIsPaidButtonGroup.add(appointmentNoPaidButton);

        // Set bounds for text fields
        appointmentIdFieldForSearch.setBounds((panelWidth - searchButtonWidth) / 2, (searchPanelHeight * 5 / 10), searchButtonWidth, searchButtonHeight);

        appointmentIdField.setBounds(textFieldX, 45, textFieldWidth, textFieldHeight);
        patientNameField.setBounds(textFieldX, 105, textFieldWidth, textFieldHeight);
        patientAddressField.setBounds(textFieldX, 165, textFieldWidth, textFieldHeight);
        patientPhoneNumberField.setBounds(textFieldX, 225, textFieldWidth, textFieldHeight);
        appointmentTreatmentField.setBounds(textFieldX, 285, textFieldWidth, textFieldHeight);
        appointmentYesPaidButton.setBounds(textFieldX, 355, textFieldWidth / 2, textFieldHeight);
        appointmentNoPaidButton.setBounds(textFieldX + (textFieldWidth / 2), 355, textFieldWidth / 2, textFieldHeight);

        // Buttons:
        searchButton = new JButton();
        searchButton.setText("SEARCH");
        searchButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        searchButton.setVerticalAlignment(JButton.CENTER);
        searchButton.setHorizontalAlignment(JButton.CENTER);
        searchButton.setFocusable(false);
        searchButton.setBackground(Color.lightGray);
        searchButton.setBounds((panelWidth - searchButtonWidth) / 2, (searchPanelHeight * 7 / 10), searchButtonWidth, searchButtonHeight);
        searchButton.addActionListener(this);

        insertButton = new JButton();
        insertButton.setText("INSERT");
        insertButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        insertButton.setVerticalAlignment(JButton.CENTER);
        insertButton.setHorizontalAlignment(JButton.CENTER);
        insertButton.setFocusable(false);
        insertButton.setBackground(Color.lightGray);
        insertButton.setBounds(labelX, 450, formButtonWidth, formButtonHeight);
        insertButton.addActionListener(this);

        payButton = new JButton();
        payButton.setText("PAYMENT");
        payButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        payButton.setVerticalAlignment(JButton.CENTER);
        payButton.setHorizontalAlignment(JButton.CENTER);
        payButton.setFocusable(false);
        payButton.setBackground(Color.lightGray);
        payButton.setBounds(labelX + (formButtonWidth + formButtonMargin), 450, formButtonWidth, formButtonHeight);
        payButton.addActionListener(this);

        editButton = new JButton();
        editButton.setText("UPDATE");
        editButton.setFont(new Font("Montserrat", Font.PLAIN, 14));
        editButton.setVerticalAlignment(JButton.CENTER);
        editButton.setHorizontalAlignment(JButton.CENTER);
        editButton.setFocusable(false);
        editButton.setBackground(Color.lightGray);
        editButton.setBounds(labelX + (2 * (formButtonWidth + formButtonMargin)), 450, formButtonWidth, formButtonHeight);
        editButton.addActionListener(this);

        // Window:
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

        this.setVisible(true);

    }

    private boolean isPaid(){

        if(appointmentYesPaidButton.isSelected()){
            return true;
        }
        return false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == insertButton){
            String patientName = patientNameField.getText();
            String patientAddress = patientAddressField.getText();
            String patientTelephoneNumber = patientPhoneNumberField.getText();
            String treatmentType = appointmentTreatmentField.getSelectedItem().toString();
            boolean isPaid = isPaid();
            var homeManager = HomeManager.getHomeManager();
            homeManager.insert(patientName, patientAddress, patientTelephoneNumber, treatmentType);
        }

    }

    // Public method to retrieve the single instance
    public static HomeFrame getHomeFrame(int width, int height, int panelWidth, int searchPanelHeight) {

        if (instance == null) {
            instance = new HomeFrame(width, height, panelWidth, searchPanelHeight);
        }
        return instance;

    }

}