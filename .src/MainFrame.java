/**
 * HackHers2023
 * Creates the mainframe for the 
 * @author Rhea
 **/

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
public class MainFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel; 
    private JButton button;
    private JLabel label; 
    public JTextField tf;
    public String city;

    boolean isClicked = false;

    public MainFrame() { 
        initialize();
    }
    private void initialize() {
        frame = new JFrame("Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

        //create textbox
        panel = new JPanel();

        label = createLabel();
        panel.add(label);

        tf = createText();
        panel.add(tf);  

        button = createButton();
        panel.add(button);


        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
        
    }

    private JButton createButton(){
        JButton button = new JButton("Send");
        button.setFocusable(false);

        button.addActionListener(this);

          
        return button;
    }

    public JButton getButton(){
        return button;
    }

    public void actionPerformed(ActionEvent e){
        city = tf.getText();
        isClicked = true;

    }

    public String getCity(){
        return city;
    }
    
    private JLabel createLabel(){
        label = new JLabel("Enter Text");
        return label;
    }

    private JTextField createText(){
        tf = new JTextField(30);
        return tf;
    }
}   