package forms;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    //Private variable for storing the singular instance
    private static HomeFrame instance;

    //Private Constructor
    private HomeFrame(int width, int height){
        //Window:
        this.setTitle("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.gray);

        this.setVisible(true);
    }

    //Public method to retrieve the single instance
    public static HomeFrame getHomeFrame(int width, int height){
        if(instance == null){
            instance = new HomeFrame(width, height);
        }
        return instance;
    }

}
