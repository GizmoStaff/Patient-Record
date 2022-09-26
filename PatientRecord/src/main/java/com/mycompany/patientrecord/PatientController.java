package com.mycompany.patientrecord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class PatientController {
    
    @Autowired
    final private PatientView pView;
    
    @Autowired
    final private PatientModel pModel;
    
    @Autowired
    public PatientController(PatientView pView, PatientModel pModel) {
        this.pView = pView;
        this.pModel = pModel;
        
        this.pView.addbtnInsertListener(new btnInsertListener());
        this.pView.addbtnDeleteListener(new btnDeleteListener());
        this.pView.addbtnUpdateListener(new btnUpdateListener());
        this.pView.addbtnSearchpListener(new btnSearchListener());
        this.pView.addbtnClearFormListener(new btnClearFormListener());
        this.pView.addbtnExportDataListener(new btnExportDataListener());
        
    }
    
    public void tm() {
        
        try {
            
            String query = "SELECT * FROM patient_info,contact_info,patient_address "
                    + "WHERE patient_info.medicalNum=contact_info.medicalNum AND patient_info.medicalNum=patient_address.medicalNum ORDER BY patient_info.surname";
            PreparedStatement ps = databaseConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String medicalNu = rs.getString("medicalNum");
                String contact = rs.getString("phone") + "," + rs.getString("email");
                
                String address = rs.getString("address") + "," + rs.getString("city") + " " + rs.getString("code");
                
                String bloodtype = rs.getString("blood_group");
                String gender = rs.getString("gender");
                String patientDOB = rs.getDate("dob").toString();
                
                pView.model.addRow(new Object[]{medicalNu, name, surname, address, contact, gender, bloodtype, patientDOB});
            }
            
            pView.tablePatient.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    
                    int selectedRow = pView.tablePatient.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) pView.tablePatient.getModel();
                    
                    pView.patientMedicalNo.setText(model.getValueAt(selectedRow, 0).toString());
                    pView.patientMedicalNo.setEditable(false);
                    pView.jDPSelectDate.getJFormattedTextField().setText(model.getValueAt(selectedRow, 7).toString());
                    pView.jDPSelectDate.getComponent(1).setEnabled(false);
                    pView.patientName.setText(model.getValueAt(selectedRow, 1).toString());
                    pView.patientName.setEditable(false);
                    pView.patientSurname.setText(model.getValueAt(selectedRow, 2).toString());
                    
                    String paddress = model.getValueAt(selectedRow, 3).toString();
                    String[] addre = paddress.split(",");
                    
                    String[] cityCode = addre[1].split(" ");
                    
                    pView.patientAddress.setText(addre[0]);
                    pView.patientCity.setText(cityCode[0]);
                    pView.cityCode.setText(cityCode[1]);
                    
                    String contact = model.getValueAt(selectedRow, 4).toString();
                    String[] contSplit = contact.split(",");
                    
                    pView.patientEmail.setText(contSplit[1]);
                    pView.patientPhone.setText(contSplit[0]);
                    pView.patientGender.setSelectedItem(model.getValueAt(selectedRow, 5));
                    pView.patientGender.setEnabled(false);
                    pView.patientBloodType.setSelectedItem(model.getValueAt(selectedRow, 6));
                    pView.patientBloodType.setEnabled(false);
                    
                }
                
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pView, "Error occured!");
            e.printStackTrace();
        }
        
    }
    
    private Connection databaseConnection() {
        
        Connection con = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/patient_record?useUnicode=true&characterEncoding=UTF-8", "root", "");
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return con;
    }
    
    @Component
    
    class btnInsertListener implements ActionListener {
        
        @Autowired
        public btnInsertListener() {
        }
        
        @Override
        
        public void actionPerformed(ActionEvent arg0) {
            
            pModel.setName(pView.patientName.getText());
            pModel.setSurname(pView.patientSurname.getText());
            pModel.setAddress(pView.patientAddress.getText());
            pModel.setPatientDOB(pView.jDPSelectDate.getJFormattedTextField().getText());
            
            pModel.setCity(pView.cityCode.getText() + "," + pView.patientCity.getText());
            
            String city = pModel.getCity();
            
            pModel.setEmail(pView.patientEmail.getText());
            pModel.setPhone(pView.patientPhone.getText());
            
            String patientName = pModel.getName();
            String patientSurname = pModel.getSurname();
            String patientGender = pView.patientGender.getSelectedItem().toString();
            String patientEmail = pModel.getEmail();
            String patientMedicalNumber = pView.patientMedicalNo.getValue().toString();
            String patientBloodGroup = pView.patientBloodType.getSelectedItem().toString();
            String patientphone = pModel.getPhone();
            String patientAddress = pModel.getAddress();
            String patientDOB = pModel.getPatientDOB();
            
            if (pModel.getName().isBlank() || pModel.getSurname().isBlank() || pModel.getAddress().isBlank() || patientEmail.isBlank() || patientMedicalNumber.isBlank() || pModel.getCity().isBlank() || patientDOB.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please fill out all the fields!");
            } else if (patientMedicalNumber.length() < 9) {
                JOptionPane.showMessageDialog(null, "Medical number must contain nine digits");
            } else if (!patientEmail.contains("@") || !patientEmail.contains(".")) {
                JOptionPane.showMessageDialog(null, "Email is not valid!");
            } else {
                try {
                    String[] parseC = city.split(",");
                    
                    String codeC = parseC[0];
                    String cityP = parseC[1];
                    
                    Statement stmt = databaseConnection().createStatement();
                    String queryCheckRecord = "SELECT * FROM patient_info WHERE medicalNum=('" + patientMedicalNumber + "')";
                    
                    ResultSet rs = stmt.executeQuery(queryCheckRecord);
                    
                    if (rs.next()) {
                        
                        JOptionPane.showMessageDialog(pView, "Patient with medical number " + patientMedicalNumber + " already exists!");
                    } else {
                        String query = "INSERT INTO patient_info(medicalNum,name,surname,blood_group,gender,dob) "
                                + "VALUES ('" + patientMedicalNumber + "','" + patientName + "','" + patientSurname + "','" + patientBloodGroup + "','" + patientGender + "','" + patientDOB + "')";
                        stmt.executeUpdate(query);
                        
                        String queryTwo = "INSERT INTO contact_info(phone,email,medicalNum) VALUES ('" + patientphone + "','" + patientEmail + "','" + patientMedicalNumber + "')";
                        stmt.executeUpdate(queryTwo);
                        
                        String queryThree = "INSERT INTO patient_address(code,city,address,medicalNum) VALUES ('" + codeC + "','" + cityP + "','" + patientAddress + "','" + patientMedicalNumber + "')";
                        stmt.executeUpdate(queryThree);
                        
                        JOptionPane.showMessageDialog(null, "Record is inserted!");
                        
                        while (pView.model.getRowCount() > 0) {
                            ((DefaultTableModel) pView.tablePatient.getModel()).removeRow(0);
                        }
                        
                        tm();
                        
                    }
                    rs.close();
                    stmt.close();
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(pView, "Error occured!");
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Component
    class btnDeleteListener implements ActionListener {
        
        @Autowired
        public btnDeleteListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            int i = pView.tablePatient.getSelectedRow();
            
            int selection;
            
            try {
                
                if (i >= 0) {
                    selection = JOptionPane.showConfirmDialog(pView, "Are you sure you want to delete this record?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    
                    if (selection == JOptionPane.YES_OPTION) {
                        
                        String id = pView.model.getValueAt(i, 0).toString();
                        
                        String query = " DELETE a.*,b.* FROM patient_address a LEFT JOIN contact_info b ON (b.medicalNum=a.medicalNum) WHERE a.medicalNum='" + id + "'";
                        
                        String queryParent = " DELETE  FROM patient_info WHERE patient_info.medicalNum=?";
                        
                        PreparedStatement pstmt = databaseConnection().prepareStatement(query);
                        PreparedStatement pstmtTwo = databaseConnection().prepareStatement(queryParent);
                        
                        pstmt.executeUpdate();
                        pstmtTwo.setString(1, id);
                        pstmtTwo.executeUpdate();
                        pstmt.close();
                        pstmtTwo.close();
                        
                        JOptionPane.showMessageDialog(pView, "Record is deleted!");
                        pView.model.removeRow(i);
                        
                    }
                    
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pView, "Error occured!");
                e.printStackTrace();
            }
            
        }
    }
    
    @Component
    class btnSearchListener implements ActionListener {
        
        @Autowired
        public btnSearchListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            TableRowSorter<TableModel> sorter = new TableRowSorter(pView.model);
            
            pView.tablePatient.setRowSorter(sorter);
            
            String string = pView.searchPatient.getText();
            
            if (pView.searchBy.getSelectedIndex() == 0) {
                
                if (string.length() == 0) {
                    
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(string));
                }
            }
            
            if (pView.searchBy.getSelectedIndex() == 1) {
                
                if (string.length() == 0) {
                    
                    sorter.setRowFilter(null);
                } else {
                    if (string.charAt(0)=='+') {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + "\\+" + string, 6));
                        
                    } //sort table based on selected string blood group ; (?i)-case insensitive
                    else if(string.contains("+")){
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + string +"\\+", 6));
                    }
                    else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + string, 6));
                    }
                }
                
            }
            
            if (pView.searchBy.getSelectedIndex() == 2) {
                
                if (string.length() == 0) {
                    
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + string, 0));  //sort table based on selected string medical number
                }
                
            }
            
            if (pView.searchBy.getSelectedIndex() == 3) {
                
                if (string.length() == 0) {
                    
                    sorter.setRowFilter(null);
                } else {
                    //sort table based on selected string surname
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + string, 2));
                }
                
            }
            
        }
    }
    
    @Component
    class btnClearFormListener implements ActionListener {
        
        @Autowired
        public btnClearFormListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            pView.patientAddress.setText("");
            pView.patientEmail.setText("");
            pView.patientName.setText("");
            pView.patientSurname.setText("");
            pView.patientPhone.setText("");
            pView.patientCity.setText("");
            pView.cityCode.setText("");
            pView.patientMedicalNo.setValue(null);
            pView.patientMedicalNo.setEditable(true);
            pView.patientName.setEditable(true);
            pView.patientGender.setEnabled(true);
            pView.patientBloodType.setEnabled(true);
            pView.jDPSelectDate.getComponent(1).setEnabled(true);
            
            
        }
        
    }
    
    @Component
    class btnExportDataListener implements ActionListener {
        
        @Autowired
        public btnExportDataListener() {
        }
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
            
            try {

                //relative path to JRXML
                String pathToJR = "./src/main/resources/resource/PatientRecord_A4_Landscape.jrxml";
                
                JasperDesign jDesign = JRXmlLoader.load(pathToJR);
                
                JasperReport jreportPatient = JasperCompileManager.compileReport(jDesign);
                
                JasperPrint jPrintPatient = JasperFillManager.fillReport(jreportPatient, null, databaseConnection());
                JasperViewer.viewReport(jPrintPatient, false);
                
                databaseConnection().close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(pView, "Error occured!");
                e.printStackTrace();
            }
            
        }
    }
    
    class btnUpdateListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            
            pModel.setSurname(pView.patientSurname.getText());
            pModel.setAddress(pView.patientAddress.getText());
           
            pModel.setCity(pView.cityCode.getText() + "," + pView.patientCity.getText());
            pModel.setEmail(pView.patientEmail.getText());
            pModel.setPhone(pView.patientPhone.getText());
            
            String city = pModel.getCity();
            
            String []splitC=city.split(",");
            String cityC=splitC[0];
            String cityP=splitC[1];
            
            System.out.println(pModel.getEmail()+pModel.getPhone());
           
             if ( pModel.getSurname().isBlank() || pModel.getAddress().isBlank() || pModel.getEmail().isBlank() || pModel.getCity().isBlank()) {
                JOptionPane.showMessageDialog(null, "Please fill out all the fields!");
            }  else if (!pModel.getEmail().contains("@") || !pModel.getEmail().contains(".")) {
                JOptionPane.showMessageDialog(null, "Email is not valid!");
            }
            else{
            try{
            
                Statement stmtUpdate=databaseConnection().createStatement();
                
                String query="UPDATE patient_info,patient_address, contact_info SET patient_info.surname=('"+pModel.getSurname()+"'),"
                        + "patient_address.address=('"+pModel.getAddress()+"'),patient_address.city=('"+cityP+"'),patient_address.code=('"+cityC+"'),"
                        + "contact_info.email=('"+pModel.getEmail()+"'),contact_info.phone=('"+pModel.getPhone()+"') WHERE "
                        + "patient_info.medicalNum=('"+pView.patientMedicalNo.getText()+"') AND patient_address.medicalNum=('"+pView.patientMedicalNo.getText()+"')"
                        + "AND contact_info.medicalNum=('"+pView.patientMedicalNo.getText()+"')";
                stmtUpdate.executeUpdate(query);
            
                JOptionPane.showMessageDialog(pView, "Record updated!");
                
                while(pView.model.getRowCount()>0){
                    ((DefaultTableModel)pView.tablePatient.getModel()).removeRow(0);
                }
                
                tm();
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(pView, "Error occured!");
                e.printStackTrace();
            }
            }
            
        }
        
    }
    
}
