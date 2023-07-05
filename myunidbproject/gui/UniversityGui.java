
package za.ac.cput.myunidbproject.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.myunidbproject.dao.SubjectDAO;
import za.ac.cput.myunidbproject.domain.Subject;

/**
 * UniversityGui.java
 * this is my Swing class
 * @author Genereux
 */
public class UniversityGui extends JFrame implements ActionListener, ItemListener{
    private JPanel pEast, pWest,pw1,pw2, pSouth, pCenter;
    private JLabel lblSC, lblSD, delRec;
    private JTextField txtSC, txtSD;
    private JButton btnS, btnC ,btnR, btnFC;
    private JComboBox<String> combo;
    private JTable tableSub;
    private DefaultTableModel tableModel;
    ArrayList<Subject> subjectList;
    Subject sub;
    SubjectDAO dao;
    public UniversityGui(){
        super("Add a Subject");
        pEast = new JPanel();
        pWest = new JPanel();
        pw1 = new JPanel();
        pw2 = new JPanel();
        pCenter = new JPanel();
        pSouth = new JPanel();
        
        lblSC = new JLabel("Subject code: ");
        lblSD = new JLabel("Subject description: ");
        delRec = new JLabel("Delete Record: ");
        
        txtSC = new JTextField(20);
        txtSD = new JTextField(20);
        
        btnS = new JButton("Save");
        btnC = new JButton("Cancel");
        btnR = new JButton("Read");
        btnFC = new JButton("Fill Combo");
        
        combo = new JComboBox<String>();
        tableModel = new DefaultTableModel();
        tableSub = new JTable(tableModel);
        dao = new SubjectDAO();
        subjectList = new ArrayList<>();
    }
    public void setGui(){
        pWest.setLayout(new GridLayout(2,1));
        pw1.setLayout(new FlowLayout());
        pw2.setLayout(new FlowLayout());
        pCenter.setLayout(new FlowLayout());
        pEast.setLayout(new FlowLayout());
        pSouth.setLayout(new GridLayout(1,4));
        
        txtSC.setPreferredSize(new Dimension(20,100));
        txtSD.setPreferredSize(new Dimension(20,100));
        
        pw1.add(lblSC);
        pw1.add(txtSC);
        pw2.add(lblSD);
        pw2.add(txtSD);
        pWest.add(pw1);
        pWest.add(pw2);
        
        pEast.add(delRec);
        combo.setAlignmentX(CENTER_ALIGNMENT);
        pEast.add(combo);
        
        tableModel.addColumn("Subject Code");
        tableModel.addColumn("Description");
        pCenter.add(new JScrollPane(tableSub));
        
        pSouth.add(btnS);
        pSouth.add(btnC);
        pSouth.add(btnR);
        pSouth.add(btnFC);
        
        this.add(pWest, BorderLayout.WEST);
        this.add(pEast, BorderLayout.EAST);
        this.add(pCenter, BorderLayout.CENTER);
        this.add(pSouth, BorderLayout.SOUTH);
        
        btnS.addActionListener(this);
        btnC.addActionListener(this);
        btnR.addActionListener(this);
        btnFC.addActionListener(this);
        
        combo.addItemListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }   
        });
        this.setSize(1100,500);
        this.setVisible(true);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnS){
            String SubjectCode = txtSC.getText();
            String SubjectDescription = txtSD.getText();
            
            Subject sbj = new Subject(SubjectCode, SubjectDescription);
            sub = dao.save(sbj);
            if(!sub.equals(sbj)){
                JOptionPane.showMessageDialog(null, "Error, Subject unsuccessfully added");
            }else{
                JOptionPane.showMessageDialog(null, "Successfull, Subject added");
            }
        }else if (e.getSource()==btnC){
            System.exit(0);
        }else if (e.getSource()==btnR){
            subjectList = dao.getAll();
            tableSub.setModel(tableModel);
            tableModel = (DefaultTableModel) tableSub.getModel();
            tableModel.setRowCount(0);
            
            tableSub.setModel(tableModel);
            tableModel = (DefaultTableModel) tableSub.getModel();
            tableModel.setRowCount(0);
            
            for(int i = 0; i < subjectList.size(); i++){
               String subjCode = subjectList.get(i).getSubjectCode();
               String subjDesc = subjectList.get(i).getSubjectDescription();
               Object []subjData = {subjCode ,subjDesc};
               tableModel.addRow(subjData);
            }
        } else if (e.getSource()==btnFC){
            subjectList = dao.getAll();
            for(int i = 0; i < subjectList.size(); i++){
               String subjCode = subjectList.get(i).getSubjectCode();
               combo.addItem(subjCode);
            }
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getItemSelectable().equals(combo)){
            String item;
            item  = String.valueOf(combo.getSelectedItem());
            Subject sbjct = new Subject(item, null);
           sub = dao.remove(sbjct); 
//           if(!sub.equals(sbjct)){
//                JOptionPane.showMessageDialog(null, "Error, Subject unsuccessfully Deleted");
//            }else{
//                JOptionPane.showMessageDialog(null, "Successfull, Subject deleted");
//            }
        }
            
    }
    //DB >>>>>
    //Username: Login
    //Password: Admin
}
