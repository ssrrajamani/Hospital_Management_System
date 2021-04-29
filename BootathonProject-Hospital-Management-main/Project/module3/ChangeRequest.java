package module3;

import javax.swing.*;

import module2.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangeRequest {
    String s;

    private ChangeRequest() {
        JFrame Frame = new JFrame();
        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(1150, 220, 150, 40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Search_Button.setBackground(new Color(171, 222, 158));
        JButton Add_Request_Button = new JButton("ADD REQUEST");
        Add_Request_Button.setBounds(650, 726, 250, 50);
        Add_Request_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Add_Request_Button.setBackground(new Color(171, 222, 158));

        JLabel Change_Request_Label = new JLabel("CHANGE REQUEST", JLabel.CENTER);
        Change_Request_Label.setOpaque(true);
        Change_Request_Label.setForeground(Color.BLACK);
        Change_Request_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Change_Request_Label.setBounds(800, 120, 300, 30);
        Change_Request_Label.setBackground(new Color(215, 247, 224));

        JLabel ID_Label = new JLabel("ENTER THE ID", JLabel.CENTER);
        ID_Label.setOpaque(true);
        ID_Label.setForeground(Color.BLACK);
        ID_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        ID_Label.setBounds(600, 226, 300, 30);
        ID_Label.setBackground(new Color(215, 247, 224));

        JLabel Name_Label = new JLabel("NAME", JLabel.CENTER);
        Name_Label.setOpaque(true);
        Name_Label.setForeground(Color.BLACK);
        Name_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Name_Label.setBounds(560, 326, 300, 30);
        Name_Label.setBackground(new Color(215, 247, 224));

        JLabel Age_Label = new JLabel("AGE", JLabel.CENTER);
        Age_Label.setOpaque(true);
        Age_Label.setForeground(Color.BLACK);
        Age_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Age_Label.setBounds(200, 429, 100, 30);
        Age_Label.setBackground(new Color(215, 247, 224));

        JLabel Phone_Label = new JLabel("PHONE NUMBER", JLabel.CENTER);
        Phone_Label.setOpaque(true);
        Phone_Label.setForeground(Color.BLACK);
        Phone_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Phone_Label.setBounds(720, 429, 250, 30);
        Phone_Label.setBackground(new Color(215, 247, 224));

        JLabel Address_Label = new JLabel("ADDRESS", JLabel.CENTER);
        Address_Label.setOpaque(true);
        Address_Label.setForeground(Color.BLACK);
        Address_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Address_Label.setBounds(1300, 429, 150, 30);
        Address_Label.setBackground(new Color(215, 247, 224));

        JLabel Next_Appointment_Label = new JLabel("NEXT APPOINTMENT", JLabel.CENTER);
        Next_Appointment_Label.setOpaque(true);
        Next_Appointment_Label.setForeground(Color.BLACK);
        Next_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Next_Appointment_Label.setBounds(180, 629, 300, 30);
        Next_Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel Last_Appointment_Label = new JLabel("LAST APPOINTMENT", JLabel.CENTER);
        Last_Appointment_Label.setOpaque(true);
        Last_Appointment_Label.setForeground(Color.BLACK);
        Last_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Last_Appointment_Label.setBounds(740, 629, 250, 30);
        Last_Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel No_Of_Appointment_Label = new JLabel("NO OF APPOINTMENT CHANGES", JLabel.CENTER);
        No_Of_Appointment_Label.setOpaque(true);
        No_Of_Appointment_Label.setForeground(Color.BLACK);
        No_Of_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        No_Of_Appointment_Label.setBounds(1290, 629, 400, 30);
        No_Of_Appointment_Label.setBackground(new Color(215, 247, 224));

        JTextField ID_Tfield = new JTextField();
        ID_Tfield.setBounds(900, 220, 200, 40);
        ID_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Name_Tfield = new JTextField();
        Name_Tfield.setBounds(900, 320, 300, 40);
        Name_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Age_Tfield = new JTextField();
        Age_Tfield.setBounds(350, 426, 100, 40);
        Age_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Phone_Tfield = new JTextField();
        Phone_Tfield.setBounds(1000, 426, 200, 40);
        Phone_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextArea Address_Tarea = new JTextArea();
        Address_Tarea.setFont(new Font("Verdana", Font.PLAIN, 20));
        Address_Tarea.setLineWrap(true);
        JScrollPane pane = new JScrollPane(Address_Tarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(1500, 426, 400, 150);

        JTextField Next_Appointment_Tfield = new JTextField();
        Next_Appointment_Tfield.setBounds(500, 626, 150, 40);
        Next_Appointment_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Next_Appointment_Tfield.setEditable(false);

        JTextField Last_Appointment_Tfield = new JTextField();
        Last_Appointment_Tfield.setBounds(1000, 626, 150, 40);
        Last_Appointment_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Last_Appointment_Tfield.setEditable(false);

        JTextField No_Of_Appointment_Tfield = new JTextField();
        No_Of_Appointment_Tfield.setBounds(1700, 626, 50, 40);
        No_Of_Appointment_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        No_Of_Appointment_Tfield.setEditable(false);

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                Frame.setVisible(false);
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s = ID_Tfield.getText();
                Name_Tfield.setText("");
                Age_Tfield.setText("");
                Phone_Tfield.setText("");
                Address_Tarea.setText("");
                Next_Appointment_Tfield.setText("");
                Last_Appointment_Tfield.setText("");
                No_Of_Appointment_Tfield.setText("");
                try {
                    PreparedStatement ps = Login.getConnection()
                            .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=?;");
                    ps.setString(1, ID_Tfield.getText());
                    ps.setString(2, Receptionist.Doc_Recp_ID);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Name_Tfield.setText(rs.getString(1));
                        Age_Tfield.setText(rs.getString(4));
                        Phone_Tfield.setText(rs.getString(7));
                        Address_Tarea.setText(rs.getString(5));
                        Next_Appointment_Tfield.setText(rs.getString(10));
                        Last_Appointment_Tfield.setText(rs.getString(9));
                        No_Of_Appointment_Tfield.setText(String.valueOf(rs.getInt(11)));
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        Add_Request_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (Name_Tfield.getText().isEmpty() || Age_Tfield.getText().isEmpty()
                            || Phone_Tfield.getText().isEmpty() || Address_Tarea.getText().isEmpty()
                            || Next_Appointment_Tfield.getText().isEmpty()
                            || Last_Appointment_Tfield.getText().isEmpty()
                            || No_Of_Appointment_Tfield.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(Frame, "Enter Valid And Search", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        PreparedStatement ps = Login.getConnection().prepareStatement(
                                "UPDATE patient SET APPOINTMENT_CHANGE_REQUEST=(?),INFORMED=(?) WHERE ID=(?) AND Doctor=(?)");
                        ps.setInt(1, 1);
                        ps.setInt(2, 0);
                        ps.setString(4, Receptionist.Doc_Recp_ID);
                        ps.setString(3, s);
                        ps.executeUpdate();

                        ID_Tfield.setText("");
                        Name_Tfield.setText("");
                        Age_Tfield.setText("");
                        Phone_Tfield.setText("");
                        Address_Tarea.setText("");
                        Next_Appointment_Tfield.setText("");
                        Last_Appointment_Tfield.setText("");
                        No_Of_Appointment_Tfield.setText("");
                        JOptionPane.showMessageDialog(Frame, "Initiated Appointment Change Request", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });

        JButton b5 = new JButton("CANCEL REQUEST");
        b5.setBounds(1050, 726, 250, 50);
        b5.setFont(new Font("Verdana", Font.PLAIN, 11));
        b5.setBackground(new Color(171, 222, 158));
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    PreparedStatement ps = Login.getConnection().prepareStatement(
                            "UPDATE patient SET APPOINTMENT_CHANGE_REQUEST=(?),INFORMED=(?) WHERE ID=(?)");
                    ps.setInt(1, 0);
                    ps.setInt(2, 1);
                    ps.setString(3, s);
                    ps.executeUpdate();

                    ID_Tfield.setText("");
                    Name_Tfield.setText("");
                    Age_Tfield.setText("");
                    Phone_Tfield.setText("");
                    Address_Tarea.setText("");
                    Next_Appointment_Tfield.setText("");
                    Last_Appointment_Tfield.setText("");
                    No_Of_Appointment_Tfield.setText("");
                    JOptionPane.showMessageDialog(Frame, "Cancelled Appointment Change Request", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } catch (Exception ev) {
                    System.out.println(ev);
                }
            }
        });

        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.add(b5);
        Frame.add(Header_Label);
        Frame.add(Back_Button);
        Frame.add(Change_Request_Label);
        Frame.add(ID_Label);
        Frame.add(ID_Tfield);
        Frame.add(Name_Label);
        Frame.add(Name_Tfield);
        Frame.add(Age_Label);
        Frame.add(Age_Tfield);
        Frame.add(Phone_Label);
        Frame.add(Phone_Tfield);
        Frame.add(Address_Label);
        Frame.add(Next_Appointment_Label);
        Frame.add(Last_Appointment_Label);
        Frame.add(Next_Appointment_Tfield);
        Frame.add(Last_Appointment_Tfield);
        Frame.add(No_Of_Appointment_Label);
        Frame.add(No_Of_Appointment_Tfield);
        Frame.add(Search_Button);
        Frame.add(Add_Request_Button);
        Frame.add(pane);
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(3);
    }

    public static ChangeRequest getInstance() {
        return new ChangeRequest();
    }
}
