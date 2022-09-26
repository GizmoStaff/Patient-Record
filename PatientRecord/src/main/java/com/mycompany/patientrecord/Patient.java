/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.patientrecord;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;



public interface Patient {
    
    
     void addbtnInsertListener(ActionListener insertBtn);
   
     void addbtnDeleteListener(ActionListener deleteBtn);
     
     void addbtnUpdateListener(ActionListener updateBtn);
      
     void addbtnSearchpListener(ActionListener searchBtn);
     
     void addbtnExportDataListener(ActionListener exportData);
     
     void addbtnClearFormListener(ActionListener clearForm);
         
     
     void displayErrorMessage(String errorMessage);
     
     
     JComboBox  addPatientGenderComboBox(JComboBox patientGender);
     
     JComboBox  addPatientBloodTypeComboBox(JComboBox patientBloodType);
    
     JComboBox addSearchByComboBox (JComboBox searchBy);
    
}
