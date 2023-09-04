
package za.ac.cput.myunidbproject.domain;

/**
 * Subject.java
 * this is my worker class
 * @author Genereux
 */
public class Subject {
    private String subjectCode;
    private String subjectDescription;

    public Subject(String subjectCode, String subjectDescription) {
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
    }

    public Subject(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectCode=" + subjectCode + ", subjectDescription=" + subjectDescription + '}';
    }
    
    
}
