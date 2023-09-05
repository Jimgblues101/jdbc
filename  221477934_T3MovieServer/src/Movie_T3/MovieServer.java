/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movie_T3;

import Movie_T3.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author 221477934
 */
public class MovieServer extends JFrame implements ActionListener{
   private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Object receivedObject;
    private JPanel panel;
    private static JTextArea txtA;
    private static JButton btnExit;
    private static ArrayList<Movie> MovieRecords = new ArrayList<>();

public MovieServer(){
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
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(clientSocket.getInputStream());
        txtA.append("Streams opened succesfully\n");
    }catch(IOException ioe){
       ioe.printStackTrace();
    }
}

//public void getStreams(){
//    try{
//        
//    }catch(IOException ioe){
//       ioe.printStackTrace();
//    }
//}


    public void processClient() {
         
         while(true){
             try{
                 receivedObject = in.readObject();
                 if(receivedObject instanceof Movie){
                     Movie newMovieRec = (Movie) receivedObject;
                     MovieRecords.add(newMovieRec);
                     txtA.append("New Movie record: "+newMovieRec+" Added!"+"\n");
                     
                 }else if(receivedObject instanceof String &&((String) receivedObject).equals("retrieve")){
                 ArrayList movieList = (ArrayList) MovieRecords.clone();
                 out.writeObject(movieList);
                 out.flush();
                 txtA.append("Records sent to client...."+"\n");
                 
             }else if(receivedObject instanceof String &&((String) receivedObject).equals("exit")){
                 closeConnection();
                 
             }else {
                 txtA.append("searching for record...."+"\n"); 
                 ArrayList<Movie> results = searchStudents ((String)receivedObject);
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
   }


private static ArrayList<Movie> searchStudents(String search){
    ArrayList<Movie> results = new ArrayList<>();
    
    for(Movie movie: MovieRecords){
        if(movie.getMovieGenre().toLowerCase().contains(search.toLowerCase())){
            results.add(movie);
        }
    }
    return results;
}

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnExit){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MovieServer movs = new MovieServer();
        movs.processClient();
    }
}

  

