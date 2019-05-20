import java.util.*;
import java.io.*;

// gui
import javax.swing.*;
import java.awt.event.*;

public class Dovi extends JFrame {

    //////////////////////////////// instance vars /////////////////////////////////////////

    private JTextField txtEnter = new JTextField(); // typing area
    private JTextArea txtChat = new JTextArea(); // chat area
    public String user = ""; // private variable to store the username
    public String pass = ""; // private variable to store the password
    private String rv = ""; // return value for method
    private ArrayList<Athlete> athletes;

    ///////////////////////////////// ctors ////////////////////////////////////////////////

    /**
     * default constuctor, creates GUI environment and checks if text is entered.
     */
    public Dovi() {

        // general gui config
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 100);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Dovi");

        txtEnter.setLocation(15, 40);
        txtEnter.setSize(560, 30);
        txtChat.setLocation(15, 15);
        txtChat.setSize(560, 20);
        txtChat.setEditable(false);

        this.add(txtEnter);
        this.add(txtChat);
        
        // initial text/instructions
        ArrayList<Athlete> athletes = IcLogin.athletesInit();
        txtChat.append("PLEASE TYPE YOUR NAME\n");
        
        
        
        // chat entered
        txtEnter.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            
            String uText = txtEnter.getText();
            
            IcLogin.checkIn(uText, athletes);
            txtEnter.setText("");
            
            }
        });
    }

    ////////////////////////////////// methods /////////////////////////////////////////////
    
    

    /**
     * creates a new Dovi() object when program runs.
     * see ctor for details.
     */
    public static void main(String[] args) throws InterruptedException {
        new Dovi();
        // bluej isnt very optimal so you LITERALLY
        // have to TELL it that the program is running
        // in order for scanner to work
        // ( 0 _ 0 )
        Thread.sleep(1000000);
    }

    /**
     * prompts the user for their username and password to login onto IC.
     */
    
}