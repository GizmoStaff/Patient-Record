package com.mycompany.patientrecord;

import com.lowagie.text.Font;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Locale;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.springframework.stereotype.Component;

@Component

public class PatientView extends JFrame implements Patient {

    private JLabel lblName = new JLabel("Name: ");
    JTextField patientName = new JTextField(20);
    private JLabel lblSurname = new JLabel("Surname: ");
    JTextField patientSurname = new JTextField(20);

    private JLabel lblAddress = new JLabel("Address:");
    JTextField patientAddress = new JTextField(60);

    private JLabel lblCity = new JLabel("City:");
    JTextField patientCity = new JTextField(60);

    private JLabel lblPatientDOB = new JLabel("DOB:");

    private JLabel lblcitycode = new JLabel("City code:");
    JTextField cityCode = new JTextField(10);

    private JLabel lblGender = new JLabel("Gender: ");

    JComboBox<String> patientGender;

    private JLabel bloodType = new JLabel("Blood group:");
    JComboBox<String> patientBloodType;

    private JLabel lblEmail = new JLabel("Email: ");
    JTextField patientEmail = new JTextField(60);

    private JLabel lblPhone = new JLabel("Phone: ");
    JTextField patientPhone = new JTextField(20);

    private JLabel lblHealthNo = new JLabel("Medical no.: ");
    JFormattedTextField patientMedicalNo;

    private JButton btnInsert = new JButton("INSERT");
    private JButton btnDelete = new JButton("DELETE");
    private JButton btnUpdate = new JButton("UPDATE");
    private JButton btnSearch = new JButton("SEARCH");
    private JButton btnExport = new JButton("DATA AND STATISTICS");
    private JButton btnClearForm = new JButton("CLEAR FORM");

    JLabel lblSearch = new JLabel("Search by:");
    JTextField searchPatient = new JTextField(20);
    JComboBox<String> searchBy;

    DefaultTableModel model = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JTable tablePatient = new JTable(model);

    JDatePickerImpl jDPSelectDate;

    PatientView() {

        JPanel panelPatient = new JPanel();

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent a) {
                int odabir = JOptionPane.showConfirmDialog(panelPatient, "End the program?", "Verification", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (odabir == JOptionPane.YES_OPTION) {
                    System.exit(0);

                }
            }

        });

        this.setSize(1100, 800);
        this.setTitle("PATIENTS");

        panelPatient.setBackground(Color.white);

        //formatter for Medical no. field
        final MaskFormatter mask;

        try {
            mask = new MaskFormatter("#########");
            mask.setPlaceholderCharacter('_');
            patientMedicalNo = new JFormattedTextField(mask);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ////Calendar for DOB
        Locale.setDefault(Locale.ENGLISH);

        UtilDateModel modelCalendarDOB = new UtilDateModel();

        JDatePanelImpl panelDOB = new JDatePanelImpl(modelCalendarDOB);

        jDPSelectDate = new JDatePickerImpl(panelDOB, new DateLabelFormatter());

        jDPSelectDate.setBounds(470, 20, 130, jDPSelectDate.getPreferredSize().height);

        Dimension sizeHealthNo = lblHealthNo.getPreferredSize();
        lblHealthNo.setBounds(50, 20, sizeHealthNo.width, sizeHealthNo.height);

        Dimension sizePatientMedicalNo = patientMedicalNo.getPreferredSize();
        patientMedicalNo.setBounds(200, 20, 150, sizePatientMedicalNo.height);

        lblPatientDOB.setBounds(400, 20, lblPatientDOB.getPreferredSize().width, lblPatientDOB.getPreferredSize().height);

        Dimension sizeName = lblName.getPreferredSize();
        lblName.setBounds(50, 60, sizeName.width, sizeName.height);

        Dimension sizePatientName = patientName.getPreferredSize();
        patientName.setBounds(200, 60, 400, sizePatientName.height);

        Dimension sizeSurname = lblSurname.getPreferredSize();
        lblSurname.setBounds(50, 100, sizeSurname.width, sizeSurname.height);

        Dimension sizePatientSurname = patientSurname.getPreferredSize();
        patientSurname.setBounds(200, 100, 400, sizePatientSurname.height);

        Dimension sizeAddress = lblAddress.getPreferredSize();
        lblAddress.setBounds(50, 140, sizeAddress.width, sizeAddress.height);

        Dimension sizePatientAddress = patientAddress.getPreferredSize();
        patientAddress.setBounds(200, 140, 400, sizePatientAddress.height);

        lblCity.setBounds(50, 180, lblCity.getPreferredSize().width, lblCity.getPreferredSize().height);
        patientCity.setBounds(200, 180, 150, patientCity.getPreferredSize().height);

        lblcitycode.setBounds(400, 180, lblcitycode.getPreferredSize().width, lblcitycode.getPreferredSize().height);
        cityCode.setBounds(500, 180, 100, cityCode.getPreferredSize().height);

        Dimension sizeContact = lblEmail.getPreferredSize();
        lblEmail.setBounds(50, 220, sizeContact.width, sizeContact.height);
        patientEmail.setBounds(200, 220, 400, patientEmail.getPreferredSize().height);

        lblPhone.setBounds(50, 260, lblPhone.getPreferredSize().width, lblPhone.getPreferredSize().height);
        patientPhone.setBounds(200, 260, 400, patientPhone.getPreferredSize().height);

        Dimension sizeGender = lblGender.getPreferredSize();
        lblGender.setBounds(50, 300, sizeGender.width, sizeGender.height);

        Dimension sizePatientGender = addPatientGenderComboBox(patientGender).getPreferredSize();
        addPatientGenderComboBox(patientGender).setBounds(200, 300, 150, sizePatientGender.height);

        Dimension sizeBloodType = bloodType.getPreferredSize();
        bloodType.setBounds(400, 300, sizeBloodType.width, sizeBloodType.height);
        Dimension sizePatientBloodType = addPatientBloodTypeComboBox(patientBloodType).getPreferredSize();
        addPatientBloodTypeComboBox(patientBloodType).setBounds(500, 300, 100, sizePatientBloodType.height);

        Dimension sizeInsert = btnInsert.getPreferredSize();
        btnInsert.setBounds(800, 20, 220, sizeInsert.height);

        Dimension sizeDelete = btnDelete.getPreferredSize();
        btnDelete.setBounds(800, 60, 220, sizeDelete.height);

        btnUpdate.setBounds(800, 100, 220, btnUpdate.getPreferredSize().height);

        btnClearForm.setBounds(800, 140, 220, btnClearForm.getPreferredSize().height);

        btnExport.setBounds(800, 180, 220, btnExport.getPreferredSize().height);

        lblSearch.setBounds(800, 230, lblSearch.getPreferredSize().width, lblSearch.getPreferredSize().height);

        Dimension sizeSearchBy = addSearchByComboBox(searchBy).getPreferredSize();
        addSearchByComboBox(searchBy).setBounds(900, 230, sizeSearchBy.width, sizeSearchBy.height);

        searchPatient.setBounds(800, 265, 220, 27);

        Dimension sizeSearch = btnSearch.getPreferredSize();
        btnSearch.setBounds(800, 300, 220, sizeSearch.height);

        /////////////////TABLE////////////////////////
        model.addColumn("Medical no.");
        model.addColumn("Name");
        model.addColumn("Surname");
        model.addColumn("Address");
        model.addColumn("Contact information");
        model.addColumn("Gender");
        model.addColumn("Blood group");
        model.addColumn("DOB");

        JScrollPane scrollPanep = new JScrollPane(tablePatient);
        tablePatient.setFillsViewportHeight(true);
        scrollPanep.setBounds(40, 350, 1000, 380);

        Color bgColor = new Color(154, 3, 30);

        tablePatient.getTableHeader().setForeground(Color.white);
        tablePatient.getTableHeader().setFont(tablePatient.getTableHeader().getFont().deriveFont(Font.BOLD));
        tablePatient.getTableHeader().setBackground(Color.red);

        tablePatient.getTableHeader().setBackground(bgColor);

        tablePatient.getColumnModel().getColumn(3).setPreferredWidth(200);
        tablePatient.getColumnModel().getColumn(4).setPreferredWidth(200);
        tablePatient.getColumnModel().getColumn(5).setPreferredWidth(40);

        //panel border
        Border panelBorder = BorderFactory.createLineBorder(bgColor, 4);
        panelPatient.setBorder(panelBorder);

        panelPatient.setLayout(null);

        panelPatient.add(lblName);
        panelPatient.add(patientName);
        panelPatient.add(lblSurname);
        panelPatient.add(patientSurname);
        panelPatient.add(lblAddress);
        panelPatient.add(patientAddress);
        panelPatient.add(lblCity);
        panelPatient.add(patientCity);
        panelPatient.add(lblEmail);
        panelPatient.add(lblcitycode);
        panelPatient.add(cityCode);
        panelPatient.add(patientEmail);
        panelPatient.add(lblPhone);
        panelPatient.add(patientPhone);
        panelPatient.add(lblGender);
        panelPatient.add(this.patientGender);
        panelPatient.add(bloodType);
        panelPatient.add(this.patientBloodType);
        panelPatient.add(lblHealthNo);
        panelPatient.add(patientMedicalNo);

        panelPatient.add(btnInsert);

        panelPatient.add(btnDelete);
        panelPatient.add(btnExport);
        panelPatient.add(lblSearch);
        panelPatient.add(btnSearch);
        panelPatient.add(scrollPanep);
        panelPatient.add(this.searchBy);
        panelPatient.add(searchPatient);
        panelPatient.add(btnClearForm);
        panelPatient.add(btnUpdate);
        panelPatient.add(jDPSelectDate);
        panelPatient.add(lblPatientDOB);

        this.add(panelPatient);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void addbtnInsertListener(ActionListener insertBtn) {
        btnInsert.addActionListener(insertBtn);
    }

    @Override

    public void addbtnDeleteListener(ActionListener deleteBtn) {
        btnDelete.addActionListener(deleteBtn);

    }

    @Override
    public void addbtnSearchpListener(ActionListener searchBtn) {
        btnSearch.addActionListener(searchBtn);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {

        JOptionPane.showMessageDialog(this, errorMessage);
    }

    @Override
    public JComboBox addPatientGenderComboBox(JComboBox patientGender) {
        this.patientGender = new JComboBox(new String[]{"Male", "Female", "Other"});

        return this.patientGender;
    }

    @Override
    public JComboBox addPatientBloodTypeComboBox(JComboBox patientBloodType) {
        this.patientBloodType = new JComboBox(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-"});
        return this.patientBloodType;
    }

    @Override
    public JComboBox addSearchByComboBox(JComboBox searchBy) {
        this.searchBy = new JComboBox(new String[]{"All", "Blood group", "Medical number", "Surname"});
        return this.searchBy;
    }

    @Override
    public void addbtnExportDataListener(ActionListener exportData) {
        btnExport.addActionListener(exportData);
    }

    @Override
    public void addbtnClearFormListener(ActionListener clearForm) {
        btnClearForm.addActionListener(clearForm);
    }

    @Override
    public void addbtnUpdateListener(ActionListener updateBtn) {
        btnUpdate.addActionListener(updateBtn);
    }

    public class DateLabelFormatter extends AbstractFormatter {

        private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;

                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }

}
