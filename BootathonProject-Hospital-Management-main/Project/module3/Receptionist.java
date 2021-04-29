package module3;

import javax.swing.*;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class Receptionist extends JFrame {
    public static String Doc_Recp_ID = "";

    Receptionist() {
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery(
                    "Select Doctor_ID from `receptionist` where `Recp_ID`='" + Login.Recp_Login_ID + "';");
            while (rs.next()) {
                Doc_Recp_ID = rs.getString("Doctor_ID");
            }
        } catch (Exception e) {
        }

        JButton Add_Appointment_Button = new JButton("Add Appointment");
        Add_Appointment_Button.setBounds(795, 300, 300, 50);
        Add_Appointment_Button.setBackground(new Color(171, 222, 158));
        Add_Appointment_Button.setFont(new Font("Verdana", Font.PLAIN, 17));

        JButton Doctor_Availability_Button = new JButton("Availability");
        Doctor_Availability_Button.setBounds(795, 400, 300, 50);
        Doctor_Availability_Button.setBackground(new Color(171, 222, 158));
        Doctor_Availability_Button.setFont(new Font("Verdana", Font.PLAIN, 17));

        JButton Change_Request_Button = new JButton("Change Request");
        Change_Request_Button.setBounds(795, 500, 300, 50);
        Change_Request_Button.setBackground(new Color(171, 222, 158));
        Change_Request_Button.setFont(new Font("Verdana", Font.PLAIN, 17));

        JButton Change_Appointment_Button = new JButton("Change Appointment");
        Change_Appointment_Button.setBounds(795, 600, 300, 50);
        Change_Appointment_Button.setBackground(new Color(171, 222, 158));
        Change_Appointment_Button.setFont(new Font("Verdana", Font.PLAIN, 17));

        JButton Log_Out_Button = new JButton("Log Out");
        Log_Out_Button.setBounds(1680, 900, 170, 50);
        Log_Out_Button.setFont(new Font("Verdana", Font.PLAIN, 17));
        Log_Out_Button.setBackground(new Color(171, 222, 158));

        JButton Fee_Payment_Button = new JButton("Fee Payment");
        Fee_Payment_Button.setBounds(795, 700, 300, 50);
        Fee_Payment_Button.setFont(new Font("Verdana", Font.PLAIN, 17));
        Fee_Payment_Button.setBackground(new Color(171, 222, 158));

        JButton Confirm_Appointment_Change_Button = new JButton("Confirm Appointment Change");
        Confirm_Appointment_Change_Button.setBounds(795, 800, 300, 50);
        Confirm_Appointment_Change_Button.setFont(new Font("Verdana", Font.PLAIN, 17));
        Confirm_Appointment_Change_Button.setBackground(new Color(171, 222, 158));

        JLabel header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        header_Label.setOpaque(true);
        header_Label.setForeground(Color.BLACK);
        header_Label.setBackground(new Color(171, 222, 158));
        header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel(" Front Desk Executive ", JLabel.CENTER);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setOpaque(true);
        Footer_Label.setBackground(new Color(171, 222, 158));
        Footer_Label.setBounds(0, 150, 1920, 50);

        Add_Appointment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AddAppointment.getInstance();
            }
        });

        Doctor_Availability_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DoctorsAvailability.getInstance();
            }
        });

        Change_Request_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ChangeRequest.getInstance();
            }
        });

        Change_Appointment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                ChangeAppointment.getInstance();

            }
        });

        Log_Out_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                Login.getInstance();

            }
        });

        Fee_Payment_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                FeePayment.getInstance();
            }
        });

        Confirm_Appointment_Change_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ConfirmChangeAppointment.getInstance();
            }
        });

        setSize(1920, 1080);
        getContentPane().setBackground(new Color(215, 247, 224));
        add(header_Label);
        add(Footer_Label);
        add(Add_Appointment_Button);
        add(Doctor_Availability_Button);
        add(Change_Request_Button);
        add(Change_Appointment_Button);
        add(Log_Out_Button);
        add(Fee_Payment_Button);
        add(Confirm_Appointment_Change_Button);
        setLayout(null);
        setResizable(true);
        setDefaultCloseOperation(3);
        setVisible(true);
    }

    public static Receptionist getInstance() {
        return new Receptionist();
    }

}
