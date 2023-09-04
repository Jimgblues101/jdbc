
package za.ac.cput.studentrecordapp1;

/**
 *
 * @author 221477934
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientStudentRecord implements ActionListener{
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static JFrame frame;
    private static JTextField nameField;
    private static JTextField idField;
    private static JTextField scoreField;
    private static JTextField searchRecord;
    private static JTextArea recordsTextArea;
    private static JButton addButton;
    private static JButton retrieveButton;
    private static JButton exitButton;
    private static JButton searchRec;
    private static Socket socket;

// Connect to the server, create io streams, and call the method that defines the gui
        public ClientStudentRecord(){
            String localHost = "127.0.0.1";
                int portNum = 1234;
            try{  
           socket = new Socket(localHost, 1234);          
           out = new ObjectOutputStream(socket.getOutputStream());
           out.flush();
           in = new ObjectInputStream(socket.getInputStream());
           createAndShowGUI();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
    }//end constructor

//------------------------------------------------------------------------------    

//Create the swing-based gui
    private void createAndShowGUI() {
    frame = new JFrame("Student Record Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);
    frame.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel(new GridLayout(4, 2));
    inputPanel.add(new JLabel("Name:"));
    nameField = new JTextField();
    inputPanel.add(nameField);
    inputPanel.add(new JLabel("ID:"));
    idField = new JTextField();
    inputPanel.add(idField);
    inputPanel.add(new JLabel("Score:"));
    scoreField = new JTextField();
    inputPanel.add(scoreField);
    searchRecord = new JTextField();
    inputPanel.add(searchRecord);
    searchRec = new JButton("Search Rec");
    inputPanel.add(searchRec);
    frame.add(inputPanel, BorderLayout.NORTH);

    addButton = new JButton("Add Record");
    addButton.addActionListener(this);
    retrieveButton = new JButton("Retrieve Records");
    retrieveButton.addActionListener(this);
    exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    searchRec.addActionListener(this);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(retrieveButton);
    buttonPanel.add(exitButton);
    frame.add(buttonPanel, BorderLayout.CENTER);

    recordsTextArea = new JTextArea(10,10);
    recordsTextArea.setEditable(false);
    frame.add(new JScrollPane(recordsTextArea), BorderLayout.SOUTH);

    frame.setVisible(true);

    }//end createAndShowGUI()

//------------------------------------------------------------------------------    
    
// In this method, construct a Student object that is initialized with the values entered by the user on the gui.   
// Send the object to the server.
// Clear the textfields and place the cursor in the name textfield    
    private static void addStudentRecord() {
        String name = nameField.getText();
        String  id = idField.getText();
        String score = scoreField.getText();
        Student s1 = new Student(name, id, score);
        try{
           out.writeObject(s1);
           out.flush();
           recordsTextArea.append("record sent to server...\n");
           nameField.setText("");
           idField.setText("");
           scoreField.setText("");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
    }//end addStudentRecord()

//------------------------------------------------------------------------------    

// In this method, send a string to the server that indicates a retrieve request.
// Read the Arraylist Object sent from the server, and call the method to display the student records.
    private static void retrieveStudentRecords() {
        try{
           out.writeObject("retrieve");
           out.flush();
           ArrayList<Student> record = (ArrayList) in.readObject();
            displayStudentRecords(record);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
    }//end retrieveStudentRecords()

//------------------------------------------------------------------------------    

// In this method, you must append the records in the arraylist (sent as a parameter) in the textarea.
    private static void displayStudentRecords(List<Student> studentList) {
         recordsTextArea.append(studentList.toString()+"\n");
    }//end displayStudentRecords()

//------------------------------------------------------------------------------    

// Send a string value to the server indicating an exit request. 
// Read the returning string from the server
// Close all connections and exit the application    
    private static void closeConnection() {
       try{
           out.writeObject("exit");
           out.flush();
           String read = (String) in.readObject();
           if(read.equals("exit")){
               out.close();
               in.close();
               socket.close();
               recordsTextArea.append("Connection closed");
               System.exit(0);
           }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            } 
    }//end closeConnection()
    public void searchRecord(){
        String search = searchRecord.getText();
        try{
            out.writeObject(search);
            out.flush();
            
            ArrayList<Student> results = (ArrayList)in.readObject();
            displayStudentRecords(results);
        }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            } 
    }
//------------------------------------------------------------------------------    

// Handle all action events generated by the user-interaction with the buttons
@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addButton){
            addStudentRecord();
        }else if(e.getSource()==retrieveButton){
            retrieveStudentRecords();
        }else if(e.getSource()==exitButton){
            closeConnection();
        }else if(e.getSource()==searchRec){
            searchRecord();
        }
    }
//------------------------------------------------------------------------------    

// Execute the application by calling the necessary methods   
    public static void main(String[] args) {
        ClientStudentRecord csr = new ClientStudentRecord();
    }//end main

    
}//end class


