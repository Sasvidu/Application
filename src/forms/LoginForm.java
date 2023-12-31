package forms;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.sasvidu.LoginManager;


public class LoginForm extends JFrame implements ActionListener {

    //Private variable for storing the singular instance
    private static LoginForm instance;

    //Interactive Components
    private JButton loginButton;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JLabel invalidLoginLabel;

    //Declare dimensions of the JFrame window
    static final int width = 420;
    static final int height = 540;

    //Private Constructor
    private LoginForm() {

        //Borders
        Border loginButtonBorder = BorderFactory.createEtchedBorder();

        //Labels
        JLabel usernameLabel = new JLabel("Username: "); //Create the label for username.
        usernameLabel.setForeground(new Color(255, 255, 255)); //Set color to white
        usernameLabel.setFont(new Font("Montserrat", Font.PLAIN, 20)); //Set Font style.
        usernameLabel.setVerticalAlignment(JLabel.CENTER); //Center the label vertically within its space.
        usernameLabel.setHorizontalAlignment(JLabel.CENTER); //Center the label horizontally within its space.
        usernameLabel.setBounds(50, 90, 150, 50); //Place the label within the form.

        JLabel passwordLabel = new JLabel("Password: "); //Create the label for password.
        passwordLabel.setForeground(new Color(255, 255, 255)); //Set color to white
        passwordLabel.setFont(new Font("Montserrat", Font.PLAIN, 20)); //Set Font style.
        passwordLabel.setVerticalAlignment(JLabel.CENTER); //Center the label vertically within its space.
        passwordLabel.setHorizontalAlignment(JLabel.CENTER); //Center the label horizontally within its space.
        passwordLabel.setBounds(48, 150, 150, 50); //Place the label within the form.

        invalidLoginLabel = new JLabel("Error: Username and / or Password provided is invalid."); //Create the label for password.
        invalidLoginLabel.setForeground(new Color(255, 0, 0)); //Set color to white
        invalidLoginLabel.setFont(new Font("Montserrat", Font.BOLD, 14)); //Set Font style.
        invalidLoginLabel.setVerticalAlignment(JLabel.CENTER); //Center the label vertically within its space.
        invalidLoginLabel.setHorizontalAlignment(JLabel.CENTER); //Center the label horizontally within its space.
        invalidLoginLabel.setBounds(5, 420, 400, 20); //Place the label within the form.

        //Text Fields
        usernameTextField = new JTextField();
        usernameTextField.setBounds(230, 105, 120, 30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(230, 165, 120, 30);

        //Button
        loginButton = new JButton(); //Create the login button.
        loginButton.setText("Login"); //Set its text.
        loginButton.setFont(new Font("Montserrat", Font.BOLD, 16)); //Set Font style.
        loginButton.setVerticalAlignment(JButton.CENTER); //Center the button vertically within its space.
        loginButton.setHorizontalAlignment(JButton.CENTER); //Center the button horizontally within its space.
        loginButton.setFocusable(false); //Remove the box around the text within the button.
        loginButton.setBackground(Color.lightGray); //Set background to light gray color.
        loginButton.setBorder(loginButtonBorder); //Set border to loginButtonBorder
        loginButton.setBounds(160, 350, 100, 40); //Place the button within the form.
        loginButton.addActionListener(this); //Add an event listener for a clicking event.

        //Window
        this.setTitle("Login"); //Sets the title of the form to 'Login'.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Change the action of clicking the close icon to Exiting instead of Hiding.
        this.setResizable(false);
        this.setSize(width, height); //Sets the x-dimension to width, and y-dimension to height.
        this.setLayout(null); //Allows manual positioning for components.
        this.setLocationRelativeTo(null); //Centers the Login Form on the screen.
        this.getContentPane().setBackground(Color.darkGray); //Sets the color to gray.

        this.add(usernameLabel); //Add the label for Username to the form.
        this.add(passwordLabel); //Add the label for Password to the form.
        this.add(usernameTextField); //Add the text field for Username to the form.
        this.add(passwordTextField); //Add the text field for Password to the form.
        this.add(loginButton); //Add the login button to the form.
        this.add(invalidLoginLabel); //Add the label for invalid login to the form.
        invalidLoginLabel.setVisible(false); // Make the invalid login lable invisible by default.
        this.setVisible(true); //Makes the form visible upon instantiation.

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //If login button was clicked:
        if(e.getSource() == loginButton){
            var loginManager = LoginManager.getLoginManager();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            if(loginManager.login(username, password)){
                //If login is successful, clear the static variable:
                removeLoginForm();
            }else{
                //If login fails, display the error message:
                invalidLoginLabel.setVisible(true);
          }
        }
    }

    //Public method to retrieve the single instance
    public static LoginForm getLoginForm(){
        if(instance == null){
            instance = new LoginForm();
        }
        return instance;
    }

    //Private method to destroy the single instance
    private void removeLoginForm(){
        instance = null;
        this.dispose(); //Dispose of the current frame object.
    }

}
