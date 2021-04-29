package module4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import module2.Login;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class AddPatient {

    String s;
    String prob;
    AudioInputStream a;
    Clip c;

    private AddPatient() {
        JFrame Frame = new JFrame();
        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 130, 150, 40);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 15));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Add_Button = new JButton("ADD");
        Add_Button.setBounds(900, 880, 150, 50);
        Add_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Add_Button.setBackground(new Color(171, 222, 158));

        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("ADD PATIENT", JLabel.CENTER);
        Footer_Label.setOpaque(true);
        Footer_Label.setForeground(Color.BLACK);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Footer_Label.setBounds(800, 120, 300, 30);
        Footer_Label.setBackground(new Color(215, 247, 224));

        JLabel Id_Label = new JLabel("ENTER THE ID", JLabel.CENTER);
        Id_Label.setOpaque(true);
        Id_Label.setForeground(Color.BLACK);
        Id_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Id_Label.setBounds(600, 226, 300, 30);
        Id_Label.setBackground(new Color(215, 247, 224));

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
        Phone_Label.setBounds(740, 429, 250, 30);
        Phone_Label.setBackground(new Color(215, 247, 224));

        JLabel Address_Label = new JLabel("ADDRESS", JLabel.CENTER);
        Address_Label.setOpaque(true);
        Address_Label.setForeground(Color.BLACK);
        Address_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Address_Label.setBounds(1320, 429, 150, 30);
        Address_Label.setBackground(new Color(215, 247, 224));

        JLabel Last_Appointment_Label = new JLabel("LAST APPOINTMENT", JLabel.CENTER);
        Last_Appointment_Label.setOpaque(true);
        Last_Appointment_Label.setForeground(Color.BLACK);
        Last_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Last_Appointment_Label.setBounds(180, 529, 300, 30);
        Last_Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel Appointment_Changes_Label = new JLabel("NO OF APPOINTMENT CHANGES", JLabel.CENTER);
        Appointment_Changes_Label.setOpaque(true);
        Appointment_Changes_Label.setForeground(Color.BLACK);
        Appointment_Changes_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Changes_Label.setBounds(740, 529, 400, 30);
        Appointment_Changes_Label.setBackground(new Color(215, 247, 224));

        JLabel Gender_Label = new JLabel("GENDER", JLabel.CENTER);
        Gender_Label.setOpaque(true);
        Gender_Label.setForeground(Color.BLACK);
        Gender_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Gender_Label.setBounds(1260, 529, 250, 30);
        Gender_Label.setBackground(new Color(215, 247, 224));

        JLabel Problem_Label = new JLabel("PROBLEM OF VISIT", JLabel.CENTER);
        Problem_Label.setOpaque(true);
        Problem_Label.setForeground(Color.BLACK);
        Problem_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Problem_Label.setBounds(500, 729, 250, 30);
        Problem_Label.setBackground(new Color(215, 247, 224));

        JLabel Blood_Label = new JLabel("BLOOD GROUP", JLabel.CENTER);
        Blood_Label.setOpaque(true);
        Blood_Label.setForeground(Color.BLACK);
        Blood_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Blood_Label.setBounds(480, 629, 250, 30);
        Blood_Label.setBackground(new Color(215, 247, 224));

        JTextField Id_Field = new JTextField();
        Id_Field.setBounds(900, 220, 200, 40);
        Id_Field.setFont(new Font("Verdana", Font.PLAIN, 20));
        Id_Field.setEditable(false);

        JTextField Name_Field = new JTextField();
        Name_Field.setBounds(900, 320, 300, 40);
        Name_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Age_Field = new JTextField();
        Age_Field.setBounds(500, 426, 100, 40);
        Age_Field.setFont(new Font("Verdana", Font.PLAIN, 20));
        Age_Field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {

                String v = Age_Field.getText();
                try {
                    Integer.parseInt(v);

                } catch (Exception ex) {
                    Age_Field.setText("");
                }
            }

        });

        JTextField Phone_Field = new JTextField();
        Phone_Field.setBounds(1000, 426, 200, 40);
        Phone_Field.setFont(new Font("Verdana", Font.PLAIN, 20));
        Phone_Field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {

                try {
                    Long.parseLong(Phone_Field.getText());

                } catch (Exception ex) {
                    Phone_Field.setText("");
                }
                if (Phone_Field.getText().length() != 10) {
                    Add_Button.setEnabled(false);
                } else if (Phone_Field.getText().length() == 10 || Phone_Field.getText().isEmpty()) {
                    Add_Button.setEnabled(true);
                }
            }
        });

        JTextArea Address_Area = new JTextArea();
        Address_Area.setBounds(1700, 426, 300, 70);
        Address_Area.setLineWrap(true);
        Address_Area.setFont(new Font("Verdana", Font.PLAIN, 20));
        JScrollPane pane = new JScrollPane(Address_Area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(1600, 426, 200, 70);

        JTextField Last_Appointment_Field = new JTextField();
        Last_Appointment_Field.setBounds(500, 526, 150, 40);
        Last_Appointment_Field.setFont(new Font("Verdana", Font.PLAIN, 20));
        Last_Appointment_Field.setText(String.valueOf(LocalDate.now()));
        Last_Appointment_Field.setEditable(false);

        JTextField Appointment_Changes_Field = new JTextField("0");
        Appointment_Changes_Field.setEditable(false);
        Appointment_Changes_Field.setBounds(1120, 526, 50, 40);
        Appointment_Changes_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Gender_Field = new JTextField();
        Gender_Field.setBounds(1600, 526, 100, 40);
        Gender_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextArea Problem_Area = new JTextArea();
        Problem_Area.setBounds(1200, 629, 600, 40);
        Problem_Area.setFont(new Font("Verdana", Font.PLAIN, 20));
        Problem_Area.setLineWrap(true);
        JScrollPane pane1 = new JScrollPane(Problem_Area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBounds(800, 729, 600, 100);

        JTextField Blood_Field = new JTextField();
        Blood_Field.setBounds(800, 629, 100, 40);
        Blood_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.setVisible(false);
                Patient.getInstance();
            }
        });

        Add_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Id_Field.getText().isEmpty() || Name_Field.getText().isEmpty() || Age_Field.getText().isEmpty()
                        || Phone_Field.getText().isEmpty() || Address_Area.getText().isEmpty()
                        || Last_Appointment_Field.getText().isEmpty() || Appointment_Changes_Field.getText().isEmpty()
                        || Gender_Field.getText().isEmpty() || Problem_Area.getText().isEmpty()
                        || Blood_Field.getText().isEmpty()) {
                    try {
                        a = AudioSystem.getAudioInputStream(new File("done-for-you.aiff").getAbsoluteFile());
                        c = AudioSystem.getClip();
                        c.open(a);
                        c.start();
                    } catch (Exception audio) {
                    }
                    if (JOptionPane.showConfirmDialog(Frame, "FILL ALL THE DETAILS", "error",
                            JOptionPane.DEFAULT_OPTION) == 0) {
                        try {
                            c.stop();
                            c.close();
                        } catch (Exception sl) {
                        }
                    }
                } else {
                    String id = Id_Field.getText();
                    String name = Name_Field.getText();
                    int age = Integer.parseInt(Age_Field.getText());
                    String num = Phone_Field.getText();
                    String address = Address_Area.getText();
                    String date = Last_Appointment_Field.getText();
                    String bg = Blood_Field.getText();
                    String gen = Gender_Field.getText();
                    String po = Problem_Area.getText();
                    try {
                        PreparedStatement ps = Login.getConnection().prepareStatement(
                                "INSERT INTO patient (ID,Name,Age,Phone_Number,Address,Last_Appointment,No_Of_Appointment_Changes,Blood_Group,Problem_Of_Visit,Gender,Doctor) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                        ps.setString(1, id);
                        ps.setString(2, name);
                        ps.setInt(3, age);
                        ps.setString(4, num);
                        ps.setString(5, address);
                        ps.setString(6, date);
                        ps.setString(7, Appointment_Changes_Field.getText());
                        ps.setString(10, gen);
                        ps.setString(9, po);
                        ps.setString(8, bg);
                        ps.setString(11, Login.Doctor_Login_ID);
                        ps.execute();
                        ps.close();
                        JOptionPane.showMessageDialog(Frame, "SUCCESSFULL", "Success", JOptionPane.WARNING_MESSAGE);
                        Id_Field.setText("");
                        Name_Field.setText("");
                        Age_Field.setText("");
                        Phone_Field.setText("");
                        Address_Area.setText("");
                        Gender_Field.setText("");
                        Blood_Field.setText("");
                        Problem_Area.setText("");
                    } catch (Exception ec) {
                        JOptionPane.showMessageDialog(Frame, "ID ALREADY EXISTS", "Alert", JOptionPane.WARNING_MESSAGE);
                        System.out.print(ec);
                    }
                }
            }
        });
        JButton Generate_Button = new JButton("Fetch");
        Generate_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Generate_Button.setBounds(1200, 220, 160, 40);
        Generate_Button.setBackground(new Color(171, 222, 158));
        Generate_Button.addActionListener(e -> {
            try {
                Statement s = Login.getConnection().createStatement();
                ResultSet rs = s.executeQuery("select * from `patient` ORDER BY `ID` DESC LIMIT 1;");
                String str = "";
                if (rs.next()) {
                    str = rs.getString("ID");
                }
                if(str.isEmpty()){Id_Field.setText("P_ID_001");}
                else{
                String out = String.format("%03d", Integer.parseInt(str.substring(5)) + 1);
                out = "P_ID_" + out;
                Id_Field.setText(out);}
            } catch (Exception sql) {
            }
        });

        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.add(Header_Label);
        Frame.add(Footer_Label);
        Frame.add(Id_Label);
        Frame.add(Name_Label);
        Frame.add(Id_Field);
        Frame.add(Name_Field);
        Frame.add(Generate_Button);
        Frame.add(Back_Button);
        Frame.add(Add_Button);
        Frame.add(Age_Field);
        Frame.add(Phone_Field);
        Frame.add(Phone_Label);
        Frame.add(Last_Appointment_Field);
        Frame.add(Appointment_Changes_Field);
        Frame.add(Gender_Field);
        Frame.add(Name_Label);
        Frame.add(Age_Label);
        Frame.add(pane);
        Frame.add(Address_Label);
        Frame.add(Last_Appointment_Label);
        Frame.add(Appointment_Changes_Label);
        Frame.add(Gender_Label);
        Frame.add(Problem_Label);
        Frame.add(Blood_Label);
        Frame.add(Blood_Field);
        Frame.add(pane1);
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setDefaultCloseOperation(3);
        Frame.setVisible(true);
    }

    public static AddPatient getInstance() {
        return new AddPatient();
    }

}
