# Patient-Record

![Patient Record App](/Images/PatientRecordApp.png)
Desktop application for keeping track of patient information along with the database (database is filled with dummy data).

Features:
- insert patient
- delete patient
- update patient
- clear form
- search by all, blood group,medical number, surname 
- report -> list of all patients along with the statistics

### BUILT
- IDE: NetBeans 13
- Java
- MVC
- Spring
- Maven
- MySQL 
- Jaspersoft Studio



### DATABASE DESIGN
![Database Design](Images/DB%20design.png)




### JASPERSOFT STUDIO - JASPERREPORT
Report that lists all patients along with the statistics was made in Jaspersoft Studio and implemented in the application afterwards.



##### REPORT DESIGN

![Report Design](/Images/PatientRecord%20Design.png)

- Chart Data Configuration for the Pie Chart by patient gender 

![Chart Data Configuration](/Images/Chart%20Data%20Configuration%20by%20gender.png)

### REPORT IMPLEMENTATION 
Code snippet for implementing report created in Jaspersoft Studio

```java

       String pathToJR = "./src/main/resources/resource/PatientRecord_A4_Landscape.jrxml";
                
        JasperDesign jDesign = JRXmlLoader.load(pathToJR);
                
        JasperReport jreportPatient = JasperCompileManager.compileReport(jDesign);
                
        JasperPrint jPrintPatient = JasperFillManager.fillReport(jreportPatient, null, databaseConnection());
        JasperViewer.viewReport(jPrintPatient, false);
                
        databaseConnection().close();
```


##### REPORT EXAMPLE
When the user selects "DATA AND STATISTICS", based on the data from the database following report, with statistics on the first page and all patients data
on the next pages, is generated:

![Data and Statistics Report](/Images/PatientRecord%20Report%20Example.png)

### CLONE THE REPO
 
```
$ git clone https://github.com/GizmoStaff/Patient-Record.git
```


### DOCUMENTATION
- [Jaspersoft Studio](https://community.jaspersoft.com/documentation?version=64256)
