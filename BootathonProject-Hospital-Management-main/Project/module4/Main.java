package module4;

import javax.swing.*;

import module2.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    Main() {
        JButton Patient_Button = new JButton("Patient");
        Patient_Button.setBounds(795, 300, 300, 60);
        Patient_Button.setBackground(new Color(215, 247, 224));
        Patient_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Patient_Button.setBorder(null);
        Patient_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton Patient_List_Button = new JButton("Patient's List");
        Patient_List_Button.setBounds(795, 410, 300, 60);
        Patient_List_Button.setBackground(new Color(215, 247, 224));
        Patient_List_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Patient_List_Button.setBorder(null);
        Patient_List_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton Monitoring_Patients_Button = new JButton("Monitoring Patient's");
        Monitoring_Patients_Button.setBounds(795, 520, 300, 60);
        Monitoring_Patients_Button.setBackground(new Color(215, 247, 224));
        Monitoring_Patients_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Monitoring_Patients_Button.setBorder(null);
        Monitoring_Patients_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton Fees_Button = new JButton("Fees");
        Fees_Button.setBounds(795, 630, 300, 60);
        Fees_Button.setBackground(new Color(215, 247, 224));
        Fees_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Fees_Button.setBorder(null);
        Fees_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton Log_Out_Button = new JButton("Log Out");
        Log_Out_Button.setBounds(1710, 900, 150, 40);
        Log_Out_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Log_Out_Button.setBackground(new Color(171, 222, 158));

        JButton Discharged_patients_Button = new JButton("Discharged Patient's");
        Discharged_patients_Button.setBounds(795, 740, 300, 60);
        Discharged_patients_Button.setBackground(new Color(215, 247, 224));
        Discharged_patients_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Discharged_patients_Button.setBorder(null);
        Discharged_patients_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("Physician", JLabel.CENTER);
        Footer_Label.setOpaque(true);
        Footer_Label.setForeground(Color.BLACK);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setBounds(800, 120, 300, 30);
        Footer_Label.setBackground(new Color(215, 247, 224));

        Patient_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Patient.getInstance();
            }
        });

        Patient_List_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TotalPatients.getInstance();
            }
        });

        Monitoring_Patients_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Monitoring.getInstance();
            }
        });

        Fees_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Fees.getInstance();
            }
        });

        Discharged_patients_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Discharged.getInstance();
            }
        });
        Log_Out_Button.addActionListener(e -> {
            Login.getInstance();
            setVisible(false);
        });

        JPanel Pane = new JPanel();
        Pane.setBackground(new Color(171, 222, 158));
        Pane.setBounds(300, 250, 1300, 590);
        Pane.setLayout(null);

        




        setSize(1920, 1080);
        getContentPane().setBackground(new Color(215, 247, 224));
        add(Header_Label);
        add(Footer_Label);
        add(Patient_Button);
        add(Patient_List_Button);
        add(Monitoring_Patients_Button);
        add(Fees_Button);
        add(Log_Out_Button);
        add(Discharged_patients_Button);
        add(Pane);
        setLayout(null);
        setResizable(true);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    public static Main getInstance() {
        return new Main();
    }
    

}
