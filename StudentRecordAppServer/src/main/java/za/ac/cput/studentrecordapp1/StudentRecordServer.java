
package za.ac.cput.studentrecordapp1;

/**
 *
 * @author 221477934
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StudentRecordServer extends JFrame implements ActionListener{
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Object receivedObject;
    private JPanel panel;
    private static JTextArea txtA;
    private static JButton btnExit;
    private static ArrayList<Student> StudentRecords = new ArrayList<>();
//In the constructor listen for incoming client connections    
public StudentRecordServer(){
            super("ServerApp 1.1");
        panel = new JPanel();
        txtA = new JTextArea();
        btnExit = new JButton("Exit");
        panel.setLayout(new GridLayout(1,1));
        txtA.setBackground(Color.lightGray);
        btnExit.setBackground(Color.red);
        panel.add(txtA);
        btnExit.setVisible(false);
        panel.add(btnExit);
        this.add(panel, BorderLayout.CENTER);
        btnExit.addActionListener(this);
        this.setSize(600, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try{
        serverSocket = new ServerSocket(1234);

        clientSocket = serverSocket.accept();
        txtA.append("Client connected:"+clientSocket.getInetAddress().getHostAddress()+"\n");
    }catch(IOException ioe){
       ioe.printStackTrace();
    }
}
//end constructor

//------------------------------------------------------------------------------    
    
//create the io streams
public void getStreams(){
    try{
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(clientSocket.getInputStream());
        txtA.append("Streams opened succesfully\n");
    }catch(IOException ioe){
       ioe.printStackTrace();
    }
}//end getStreams()

//------------------------------------------------------------------------------    
    
//Declare arraylist and handle the communication between server and client    
    public void processClient() {
         
         while(true){
             try{
                 receivedObject = in.readObject();
                 if(receivedObject instanceof Student){
                     Student newStudent = (Student) receivedObject;
                     StudentRecords.add(newStudent);
                     txtA.append("New student record: "+newStudent+" Added!"+"\n");
                 }else if(receivedObject instanceof String &&((String) receivedObject).equals("retrieve")){
                 ArrayList studentList = (ArrayList) StudentRecords.clone();
                 out.writeObject(studentList);
                 out.flush();
                 txtA.append("Records sent to client...."+"\n");
             }else if(receivedObject instanceof String &&((String) receivedObject).equals("exit")){
                 closeConnection();
             }else {
                 txtA.append("searching for record...."+"\n"); 
                 ArrayList<Student> results = searchStudents ((String)receivedObject);
                 out.writeObject(results);
                 out.flush();
                 txtA.append("sending records to client...."+"\n"); 
             }
             }catch(ClassNotFoundException cnfe){
                 cnfe.printStackTrace();
             }catch(IOException ioe){
       ioe.printStackTrace();
    }
         }
   }//end processClient

//------------------------------------------------------------------------------    
private static ArrayList<Student> searchStudents(String search){
    ArrayList<Student> results = new ArrayList<>();
    for(Student student: StudentRecords){
        if(student.getStudentID().toLowerCase().contains(search.toLowerCase()) || 
                student.getStudentName().toLowerCase().contains(search.toLowerCase())){
            results.add(student);
        }
    }
    return results;
}
//close all connections to the server and exit the application   
    private static void closeConnection() {
         try{
        out.writeObject("exit");
        out.flush();
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
        txtA.append("Server connection closed");
        //txtA.setVisible(false);
        btnExit.setVisible(true);
    }catch(IOException ioe){
       ioe.printStackTrace();
    }
    }//end closeConnection()

//------------------------------------------------------------------------------    

//execute the program and call all necessary methods
    public static void main(String[] args) {
    StudentRecordServer srs = new StudentRecordServer();
    srs.getStreams();
    srs.processClient();
    }//end main

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnExit){
            System.exit(0);
        }
    }

}//end class


