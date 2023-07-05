
package za.ac.cput.myunidbproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import za.ac.cput.myunidbproject.connection.DBConnection;
import za.ac.cput.myunidbproject.domain.Subject;

/**
 * SubjectDAO.java
 * this is my DAO class
 * @author Genereux
 */
public class SubjectDAO {
    private Connection con;
    private Statement stmnt;
    private PreparedStatement pstmnt;
    
    public SubjectDAO(){
        try{
            this.con = DBConnection.derbyConnection();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    
    public Subject save(Subject subject){
      int ok;
      String sql = "INSERT INTO SUBJECT(subject_code, subject_description) VALUES('%s', '%s')";
      try{
          sql = String.format(sql, subject.getSubjectCode(), subject.getSubjectDescription());
          System.out.println("sql:"+ sql);
          stmnt = this.con.createStatement();
          ok = stmnt.executeUpdate(sql);
          if(ok > 0){
              return subject;
          }else{
              return null;
          }
      }catch(SQLException SQL){
                  System.out.println("Error has occured"+ sql);
         }catch(Exception e){
                  System.out.println("Error has occured"+ e);
         }finally{
          try{
             if(stmnt != null){
              stmnt.close(); 
          }
          
          }catch(Exception e){
              System.out.println("Error has occured"+ e);    
           }
      }
      return null;
    }
    
    public ArrayList<Subject> getAll(){
        ArrayList<Subject> subjectList = new ArrayList();
        try{
             
        String getAllSQL = "select * from subject";
        pstmnt = this.con.prepareStatement(getAllSQL);
        ResultSet rs = pstmnt.executeQuery();
        if(rs != null){
            while(rs.next()){
                System.out.println("DB records: "+rs.getString(1)+" "+rs.getString(2));
                subjectList.add(new Subject(rs.getString("subject_code"), rs.getString("subject_description")));
        
            }
            rs.close();
        }
        }catch(SQLException sql){
           System.out.println("Error has occured"+ sql);
         }catch(Exception e){
                  System.out.println("Error has occured"+ e);
         }finally{
          try{
             if(pstmnt != null){
              pstmnt.close(); 
          }   
    }catch(Exception e){
              System.out.println("Error has occured"+ e);    
           } 
          }
        return subjectList;
       
  }
  
    public Subject remove(Subject sub){
       int ok;
       
        try{
            String rmvSQL = "DELETE FROM Subject WHERE subject_code = ?";
            rmvSQL = String.format(rmvSQL, sub.getSubjectCode());
            stmnt = this.con.prepareStatement(rmvSQL);
            //stmnt.set(1, sub.getSubjectCode());
            ok = stmnt.executeUpdate(rmvSQL);
            if(ok > 0){
                System.out.println("success!");
              //return subject;
          }else{
                System.out.println("unsucessful");
              //return null;
          }
        }catch(SQLException sql){
           System.out.println("Error has occured"+ sql);
         }catch(Exception e){
                  System.out.println("Error has occured"+ e);
         }finally{
          try{
             if(stmnt != null){
              stmnt.close(); 
          }   
    }catch(Exception e){
              System.out.println("Error has occured"+ e);    
           } 
          }
     return null;}
    
//    public static void main(String[] args) {
//        SubjectDAO sub = new SubjectDAO();
//        String del = JOptionPane.showInputDialog("Enter Code");
//        
//       Subject sd = new Subject(del);
//       sub.remove(sd);
//    }
}
