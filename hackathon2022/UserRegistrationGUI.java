/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * UserRegistrationGUI.java
 * This is the app GUI
 * Author: Mdumisi Kelvin Letsie (220120137)
 * 23 July 2022
 */
package za.ac.cput.hackathon2022;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UserRegistrationGUI extends JFrame implements ActionListener {
    private JPanel panelWest, panelCenter, panelEast, panelSouth;
    private JLabel lblTitle;
    private JLabel lblFirstName;
    private JLabel lblSurname;
    private JLabel lblGender;
    private JLabel lblEmail;
    private JLabel lblPhoneNumber;
    private JLabel lblPassword;
    private JLabel lblConfirmPassword;
    private JLabel lblAcceptTerms;
//
    private JLabel lblFirstNameErr1;
//
    private JLabel lblComboboxErr;
    private JLabel lblPasswordErr1;
    private JLabel lblPasswordErr2;
    private JLabel lblPasswordErr3;
    private JLabel lblPasswordErr4;
    private JLabel lblPasswordErr5;
    private JComboBox comboTitle;
    private JTextField txtFirstName;
    private JTextField txtSurname;
    private JRadioButton btnGenderMale;
    private JRadioButton btnGenderFemale;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JCheckBox btnAcceptTerms;
    private JButton btnSave;
    private JButton btnClear;
    private JButton btnCancel;
    String userTitle = "";
    boolean isValid = true;
    boolean matches = false;

    String userGetPassword;
    String userPassword;
    String userConfirmPassword;

public UserRegistrationGUI() {
    super("AestheTech - Sign Up");
    panelWest = new JPanel();
    panelCenter = new JPanel();
    panelEast = new JPanel();
    panelSouth = new JPanel();
    lblTitle = new JLabel("Preferred Title: ");
    lblFirstName = new JLabel("Please enter your First Name: ");
    lblSurname = new JLabel("Please enter your Surname: ");
    lblEmail = new JLabel("Please enter your Email: ");
    lblPhoneNumber = new JLabel("Please enter your Phone Number: ");
    lblPassword = new JLabel("Create Password: ");
    lblConfirmPassword = new JLabel("Confirm Password: ");
    lblAcceptTerms = new JLabel("I agree to recieving Newsletters from AestheTech");
    txtFirstName = new JTextField();
    txtSurname = new JTextField();
    txtEmail = new JTextField();
    txtPhoneNumber = new JTextField();
    txtPassword = new JPasswordField();
    txtConfirmPassword = new JPasswordField();
    btnAcceptTerms = new JCheckBox();
    lblComboboxErr = new JLabel("");
    lblPasswordErr1 = new JLabel("Password must be less than 20 and more than 8 characters in length.");
    lblPasswordErr2 = new JLabel("Password must have atleast one uppercase character");
    lblPasswordErr3 = new JLabel("Password must have atleast one lowercase character");
    lblPasswordErr4 = new JLabel("Password must have atleast one number");
    lblPasswordErr5 = new JLabel("Password must have atleast one special character among @#$%");
    btnSave = new JButton("Save");
    btnClear = new JButton("Clear");
    btnCancel = new JButton("Cancel");


    // ComboBox initialization
    String[] titles = {"Mr", "Ms", "Mrs"};
    comboTitle = new JComboBox(titles);
    comboTitle.addActionListener(this);
}

public void setGUI() {
        panelWest.setLayout(new GridLayout(8, 1));
        panelCenter.setLayout(new GridLayout(8, 1));
        panelEast.setLayout(new GridLayout(7, 1));
        panelSouth.setLayout(new GridLayout(1, 2));
        panelWest.setPreferredSize(new Dimension(426, 30));
        panelCenter.setPreferredSize(new Dimension(426, 90));
        panelEast.setPreferredSize(new Dimension(426, 5));
        panelSouth.setPreferredSize(new Dimension(200, 30));
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelEast, BorderLayout.EAST);
        this.add(panelSouth, BorderLayout.SOUTH);

// PANEL WEST
        panelWest.add(lblTitle);
        panelWest.add(lblFirstName);
        panelWest.add(lblSurname);
        panelWest.add(lblEmail);
        panelWest.add(lblPhoneNumber);
        panelWest.add(lblPassword);
        panelWest.add(lblConfirmPassword);
        panelWest.add(lblAcceptTerms);

        panelWest.setBackground(new Color(0xE7DCCA));

// PANEL CENTER
        panelCenter.add(comboTitle);
        panelCenter.add(txtFirstName);
        panelCenter.add(txtSurname);
        panelCenter.add(txtEmail);
        panelCenter.add(txtPhoneNumber);
        panelCenter.add(txtPassword);
        panelCenter.add(txtConfirmPassword);
        panelCenter.add(btnAcceptTerms);
        btnAcceptTerms.setOpaque(false);

        panelCenter.setBackground(new Color(0xE7DCCA));

        btnAcceptTerms.addActionListener(this);

// PANEL EAST

panelEast.add(lblComboboxErr);

//        panelEast.add(lblPasswordErr1);
//        panelEast.add(lblPasswordErr2);
//        panelEast.add(lblPasswordErr3);
//        panelEast.add(lblPasswordErr4);
//        panelEast.add(lblPasswordErr5);

        lblPasswordErr1.setVisible(false);
        lblPasswordErr2.setVisible(false);
        lblPasswordErr3.setVisible(false);
        lblPasswordErr4.setVisible(false);
        lblPasswordErr5.setVisible(false);


        panelEast.setBackground(new Color(0xE7DCCA));

// PANEL SOUTH
        panelSouth.add(btnSave);
        panelSouth.add(btnClear);
        panelSouth.add(btnCancel);

        btnSave.setEnabled(false);

// BUTTON COLORS

        btnSave.setBackground(new Color(0xE7DCCA));
        btnClear.setBackground(new Color(0xCAD5E7));
        btnCancel.setBackground(new Color(0xFFCCCC));

// BUTTON ACTION LISTENERS

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnCancel.addActionListener(this);

// FRAME SIZE AND VISIBILITY
        this.setSize(1280, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center screen
}

//

public static boolean isValidPassword(String userPassword) {
        boolean isValid = true;
            if (userPassword.length() > 20 || userPassword.length() < 8)
            {
                    JOptionPane.showMessageDialog(null, "Password must be less than 20 and more than 8 characters in length.");
                    isValid = false;
            }
            String upperCaseChars = "(.*[A-Z].*)";
            if (!userPassword.matches(upperCaseChars ))
            {
                    JOptionPane.showMessageDialog(null, "Password must have atleast one uppercase character");
                    isValid = false;
            }
            String lowerCaseChars = "(.*[a-z].*)";
            if (!userPassword.matches(lowerCaseChars ))
            {
                    JOptionPane.showMessageDialog(null, "Password must have atleast one lowercase character");
                    isValid = false;
            }
            String numbers = "(.*[0-9].*)";
            if (!userPassword.matches(numbers ))
            {
                JOptionPane.showMessageDialog(null, "Password must have atleast one number");
                    isValid = false;
            }
            String specialChars = "(.*[@,#,$,%].*$)";
            if (!userPassword.matches(specialChars ))
            {
                    JOptionPane.showMessageDialog(null, "Password must have atleast one special character among @#$%");
                    isValid = false;
            }
            if (isValid = false) {
                    JOptionPane.showMessageDialog(null, "Please check to ensure all fields are filled correctly");
            }
        return isValid;
    }

    public void notEmpty() {
    lblFirstNameErr1 = new JLabel("First Name cannot be empty");
    panelEast.add(lblFirstNameErr1);
    lblFirstNameErr1.setVisible(false);

        if (txtFirstName.getText().equals("")){
        lblFirstNameErr1.setVisible(true);
        }
        if (txtSurname.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Surname cannot be empty");
        }
        if (txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Email cannot be empty");
        }
        if (txtPhoneNumber.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Phone Number cannot be empty");
        }
        if (txtPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Password cannot be empty");
        }
        if (txtConfirmPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Confirm Password cannot be empty");
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAcceptTerms) {
//            String userPhoneNumber = txtConfirmPassword.getText();
//            String userPassword = txtPassword.getText();
           // isValidPassword(userPassword);
           // notEmpty();

//if (isValid = false) {
//        JOptionPane.showMessageDialog(null, "Please check to ensure all fields are filled correctly");
//        txtPassword.hasFocus();
////            btnSave.setEnabled(true);
//}

if (btnAcceptTerms.isSelected()) {
        btnSave.setEnabled(true);
}
else if (!btnAcceptTerms.isSelected()){
        btnSave.setEnabled(false);
}
        }

        if (e.getSource() == btnSave){
            // SAVE TO DATABASE
            userTitle = String.valueOf(comboTitle.getSelectedItem());
            String userFirstName = txtFirstName.getText();
            String userSurname = txtSurname.getText();
            String userEmail = txtEmail.getText();
            String userPhoneNumber = txtPhoneNumber.getText();
            userGetPassword = String.valueOf(txtPassword.getPassword());
            String userPassword = userGetPassword;
            UserRegistration s = new UserRegistration(userTitle, userFirstName, userSurname, userEmail, userPhoneNumber, userPassword);

            s.save();
        }



        else if (e.getSource() == btnClear){
            // CLEAR ALL INPUTS
        }

        else if (e.getSource() == btnCancel) {
            // ABORT PROGRAM
            System.exit(0);
        }

    }    
}
