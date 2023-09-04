
package za.ac.cput.studentrecordapp1;

import java.io.Serializable;

/**
 *
 * @author 22147794
 */
public class Student implements Serializable {
    private String studentName;
    private String studentID;
    private String Score;

    public Student(String studentName, String studentID, String Score) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.Score = Score;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getScore() {
        return Score;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }

    @Override
    public String toString() {
        return "Student{" + "studentName=" + studentName + ", studentID=" + studentID + ", Score=" + Score + '}';
    }
    
    
}
