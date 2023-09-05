/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movie_T3;


import Movie_T3.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BURGERR
 */
public class MovieClient  extends JFrame implements ActionListener {
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static Socket socket;

    private JPanel panelNorth;
    private JPanel panelCenter, panelCenter1, panelCenter2, inputPanel;
    private JPanel panelSouth;

    private static JTextField txtTitleField;
    private static JTextField txtDirectorField;
    private static JTextArea recordsTextArea;
    private static JButton exitButton;
    
    private JLabel lblHeading;
    private JLabel lblTitle;
    private JLabel lblDirector;           
    private JLabel lblGenre;
    private static JComboBox cboGenre;
    
    private JButton btnAdd, btnRetrieve, btnExit, btnSearch;
    private Font ft1, ft2, ft3;
       
    public MovieClient() {
        super("Movie Inventory App");
         
            String localHost = "127.0.0.1";
                int portNum = 1234;
            try{  
           socket = new Socket(localHost, 1234);          
           out = new ObjectOutputStream(socket.getOutputStream());
           out.flush();
           in = new ObjectInputStream(socket.getInputStream());
                System.out.println("Streams opened");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
    
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelCenter1 = new JPanel();
        panelCenter2 = new JPanel();
        panelSouth = new JPanel();
        inputPanel = new JPanel();
        lblHeading = new JLabel("Movie Inventory");

        lblGenre = new JLabel("Genre: ");
        cboGenre = new JComboBox<>(new String[]{"Select", "Comedy", "Action", "Horror", "Romance", "Sci-Fi", "Thriller", "Drama"});      
        btnAdd = new JButton("Add");
        btnRetrieve = new JButton("Retrieve");
        btnSearch = new JButton("Search");
        btnExit = new JButton("Exit");
        
        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 22);
        ft3 = new Font("Arial", Font.PLAIN, 24);
    }
    
    public void setGUI() {
        panelNorth.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(3, 1));
        panelSouth.setLayout(new GridLayout(1, 4));
        panelCenter1.setLayout(new FlowLayout());
        panelCenter2.setLayout(new FlowLayout());
        
        panelNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        panelNorth.setBackground(new Color(0, 106, 255));
        
        lblGenre.setFont(ft2);
        lblGenre.setHorizontalAlignment(JLabel.RIGHT);
        cboGenre.setFont(ft2);
        recordsTextArea = new JTextArea(10,40);
        recordsTextArea.setEditable(false);
        recordsTextArea.setFont(ft2);
        panelCenter1.add(lblGenre);
        panelCenter1.add(cboGenre);
        
        inputPanel.setLayout(new GridLayout(2, 2));        
        lblTitle = new JLabel("Title:");
        lblTitle.setFont(ft2);
        inputPanel.add(lblTitle);
        txtTitleField = new JTextField();
        txtTitleField.setFont(ft2);
        inputPanel.add(txtTitleField);
        lblDirector = new JLabel("Director:");
        lblDirector.setFont(ft2);
        inputPanel.add(lblDirector);
        txtDirectorField = new JTextField();
        txtDirectorField.setFont(ft2);
        inputPanel.add(txtDirectorField);             
        
        panelCenter2.add(new JScrollPane(recordsTextArea), BorderLayout.SOUTH);       
        panelCenter2.setBackground(new Color(36, 145, 255));
        
        panelCenter.add(panelCenter1);
        panelCenter.add(inputPanel);
        panelCenter.add(panelCenter2);
        
        btnAdd.setFont(ft3);
        btnRetrieve.setFont(ft3);
        btnSearch.setFont(ft3);
        btnExit.setFont(ft3);
        panelSouth.add(btnAdd);
        panelSouth.add(btnRetrieve);
        panelSouth.add(btnSearch);
        panelSouth.add(btnExit);
        
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        btnAdd.addActionListener(this);
        btnRetrieve.addActionListener(this);
        btnSearch.addActionListener(this);
        btnExit.addActionListener(this);
        
        this.setSize(600, 400);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

     private static void addMovieRecord() {
        String title = txtTitleField.getText();
        String  director = txtDirectorField.getText();
        String  genre = (String)cboGenre.getSelectedItem();
        Movie mov = new Movie(title, director, genre);
        try{
           out.writeObject(mov);
           out.flush();
           recordsTextArea.append("record sent to server...\n");
           txtTitleField.setText("");
           txtDirectorField.setText("");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
    }
     private static void retrieveMovieRecords() {
        try{
           out.writeObject("retrieve");
           out.flush();
           ArrayList<Movie> record = (ArrayList) in.readObject();
            displayMovieRecords(record);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
    }
    private static void displayMovieRecords(List<Movie> movieList) {
        recordsTextArea.setText(" ");
        recordsTextArea.append("Movies:"+"\n");
        for (Movie movie: movieList){
            recordsTextArea.append(movie.getMovieTitle()+"   "+movie.getMovieDirector()+"   "+movie.getMovieGenre()+"\n");  
        }
        recordsTextArea.append("   "+"\n");
         //
    }
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
    }
     public void searchMovieRecord(){
         String  genre = (String)cboGenre.getSelectedItem();
        try{
            out.writeObject(genre);
            out.flush();
            
            ArrayList<Movie> results = (ArrayList)in.readObject();
            displayMovieRecords(results);
        }catch(IOException ioe){
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            } 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
            addMovieRecord();
        }else if(e.getSource()==btnRetrieve){
            retrieveMovieRecords();
         
        }else if(e.getSource()==btnSearch){
            searchMovieRecord();
        } 
        else if(e.getSource()==btnExit){
            closeConnection();
            System.exit(0);
        } 
    }
    
    public static void main(String[] args) {
       new MovieClient().setGUI();
    }
   
}
