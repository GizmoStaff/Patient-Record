
package com.mycompany.patientrecord;


import org.springframework.stereotype.Component;


@Component

public class PatientModel {
  private  String name;
  private String surname; 
  private String address;
  private String city;
  public  String  gender;
   private String email;
   private String phone;
   private String patientDOB;
   public int patientMedicalNo;
   public  String  bloodGroup;

   
    public PatientModel() {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.city=city;
        this.email = email;
        this.phone=phone;
        this.patientMedicalNo = patientMedicalNo;
        this.patientDOB=patientDOB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }
   
   
    
}