package forms;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    //Private variable for storing the singular instance
    private static HomeFrame instance;

    //Private Constructor
    private HomeFrame(int width, int height, int formPanelWidth, int formPanelHeight, int searchPanelWidth, int searchPanelHeight){

        //Panels:
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(0xB76D68));
        formPanel.setBounds(0, 0, formPanelWidth, formPanelHeight);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xFF8F73));
        searchPanel.setBounds(formPanelWidth, 0, searchPanelWidth, searchPanelHeight);

        //Table:
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"103","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};

        JTable appointmentsTable = new JTable(data,column);
        appointmentsTable.setRowHeight(30);
        appointmentsTable.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 18));
        appointmentsTable.setFont(new Font("Montserrat", Font.PLAIN, 16));
        JScrollPane appointmentsTableScrollPane = new JScrollPane(appointmentsTable);
        appointmentsTableScrollPane.setBounds(formPanelWidth, searchPanelHeight, searchPanelWidth, height - searchPanelHeight);


        //Window:
        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0x403F4C));

        this.add(formPanel);
        this.add(searchPanel);
        this.add(appointmentsTableScrollPane);
        this.setVisible(true);

    }

    //Public method to retrieve the single instance
    public static HomeFrame getHomeFrame(int width, int height, int formPanelWidth, int formPanelHeight, int searchPanelWidth, int searchPanelHeight){

        if(instance == null){
            instance = new HomeFrame(width, height, formPanelWidth, formPanelHeight, searchPanelWidth, searchPanelHeight);
        }
        return instance;

    }

}
