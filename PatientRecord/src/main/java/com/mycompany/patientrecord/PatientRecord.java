
package com.mycompany.patientrecord;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Marina Šarar 
 */
public class PatientRecord {

   
    public static void main(String[] args) {
        
     
        ApplicationContext context= new AnnotationConfigApplicationContext(PatientRecordConfig.class);
        
   
      PatientView pView=context.getBean(PatientView.class);
     
       
       PatientController ltController=context.getBean(PatientController.class);
       
       
       
       pView.setVisible(true);
       
      ltController.tm();
      
        
        
    }
    
}
