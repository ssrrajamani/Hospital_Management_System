package module3;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import module2.Login;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.sql.*;
import java.time.LocalDate;

public class AddAppointment {
    Clip c;
    String str = "";
    int i=0;

    private AddAppointment() {
        LocalDate date = LocalDate.now();

        JFrame frame = new JFrame();
        JLabel headerLabel = new JLabel("Hospital Management System", JLabel.CENTER);
        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(810, 500, 200, 40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Search_Button.setBackground(new Color(171, 222, 158));
        JButton Save_Button = new JButton("SAVE");
        Save_Button.setBounds(650, 600, 200, 40);
        Save_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Save_Button.setBackground(new Color(171, 222, 158));
        JButton Cancel_Button = new JButton("CANCEL");
        Cancel_Button.setBounds(1050, 600, 200, 40);
        Cancel_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Cancel_Button.setBackground(new Color(171, 222, 158));

        headerLabel.setOpaque(true);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setBackground(new Color(171, 222, 158));
        headerLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        headerLabel.setBounds(0, 20, 1920, 90);

        JLabel Add_Appointment_Label = new JLabel("ADD APPOINTMENT", JLabel.CENTER);
        Add_Appointment_Label.setOpaque(true);
        Add_Appointment_Label.setForeground(Color.BLACK);
        Add_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Add_Appointment_Label.setBounds(850, 120, 200, 30);
        Add_Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel ID_Label = new JLabel("ENTER THE ID", JLabel.CENTER);
        ID_Label.setOpaque(true);
        ID_Label.setForeground(Color.BLACK);
        ID_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        ID_Label.setBounds(600, 226, 300, 30);
        ID_Label.setBackground(new Color(215, 247, 224));

        JLabel Appointment_Label = new JLabel("APPOINTMENT DATE", JLabel.CENTER);
        Appointment_Label.setOpaque(true);
        Appointment_Label.setForeground(Color.BLACK);
        Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Label.setBounds(630, 326, 300, 30);
        Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel Date_Label = new JLabel("DATE", JLabel.CENTER);
        Date_Label.setOpaque(true);
        Date_Label.setForeground(Color.BLACK);
        Date_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Date_Label.setBounds(20, 220, 100, 30);
        Date_Label.setBackground(new Color(215, 247, 224));

        JLabel Appointment_Time = new JLabel("APPOINTMENT TIME", JLabel.CENTER);
        Appointment_Time.setOpaque(true);
        Appointment_Time.setForeground(Color.BLACK);
        Appointment_Time.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Time.setBounds(630,426,265,30);
        Appointment_Time.setBackground(new Color(215, 247, 224));

        JTextField Appointment_Time_Field = new JTextField("HH:MM:SS", JLabel.CENTER);
        Appointment_Time_Field.setOpaque(true);
        Appointment_Time_Field.setForeground(Color.BLACK);
        Appointment_Time_Field.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Time_Field.setBounds(900, 426, 200, 40);

        JTextField ID_Tfield = new JTextField();
        ID_Tfield.setBounds(900, 220, 200, 40);
        ID_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Appointment_Tfield = new JTextField();
        Appointment_Tfield.setBounds(900, 320, 200, 40);
        Appointment_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Date_Tfield = new JTextField(String.valueOf(date));
        Date_Tfield.setBounds(130, 220, 170, 30);
        Date_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Date_Tfield.setEditable(false);

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                frame.setVisible(false);
            }
        });
        
        Appointment_Time_Field.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                if(i==0){
                Appointment_Time_Field.setText("");
                i=1;}
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointment_Tfield.setText("");
                String s = ID_Tfield.getText();
                int n = s.length();
                if (n != 0) {
                    try {
                        PreparedStatement ps = Login.getConnection()
                                .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=(?) AND `Status`=?");
                        ps.setString(1, s);
                        ps.setString(2, Receptionist.Doc_Recp_ID);
                        ps.setString(3, "Yes");
                        ResultSet rs = ps.executeQuery();
                        String date = "";
                        while (rs.next()) {
                            date = rs.getString(10);
                            Appointment_Tfield.setText(date);
                            Appointment_Time_Field.setText(String.valueOf(rs.getTime("Time")));
                        }
                        str = Appointment_Tfield.getText();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        Save_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ID_Tfield.getText().isEmpty() || Appointment_Tfield.getText().isEmpty() || Appointment_Tfield.getText().isEmpty() || Appointment_Tfield.getText().equals("HH:MM:SS")) {
                        try {
                            AudioInputStream a = AudioSystem
                                    .getAudioInputStream(new File("done-for-you.aiff").getAbsoluteFile());
                            c = AudioSystem.getClip();
                            c.open(a);
                            c.start();
                        } catch (Exception audio) {
                        }
                        if (JOptionPane.showConfirmDialog(frame, "FILL ALL THE DETAILS", "error",
                                JOptionPane.DEFAULT_OPTION) == 0) {
                            try {
                                c.stop();
                            } catch (Exception sl) {
                            }
                        }
                    }
                    String date1 = Appointment_Tfield.getText();
                    LocalDate next = LocalDate.parse(date1);
                    String id = ID_Tfield.getText();
                    int a = next.compareTo(date);
                    if (a <= -1 || a == 0) {
                        JOptionPane.showMessageDialog(frame, "Enter Proper Date", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            PreparedStatement ps = Login.getConnection().prepareStatement(
                                    "UPDATE patient SET Next_Appointment=?, LAST_APPOINTMENT=?, Time=? WHERE ID=? AND Doctor=?");
                            ps.setString(1, String.valueOf(next));
                            ps.setString(2, String.valueOf(date));
                            ps.setString(3, String.valueOf(Appointment_Time_Field.getText()));
                            ps.setString(4, id);
                            ps.setString(5, Receptionist.Doc_Recp_ID);
                            if (str.equals(Appointment_Tfield.getText())) {
                                JOptionPane.showMessageDialog(frame, "Date Not Changed", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                            } else {
                                ps.executeUpdate();
                                JOptionPane.showMessageDialog(frame, "Appointment Added", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                                if (JOptionPane.showConfirmDialog(frame, "Appointment Added.", "Alert",
                                        JOptionPane.WARNING_MESSAGE) == 0) {
                                    try {
                                        AudioInputStream a1 = AudioSystem
                                                .getAudioInputStream(new File("done-for-you.aiff").getAbsoluteFile());
                                        Clip c1 = AudioSystem.getClip();
                                        c1.open(a1);
                                        c1.start();
                                        c1.stop();
                                    } catch (Exception sl) {
                                    }
                                    ID_Tfield.setText("");
                                    Appointment_Tfield.setText("");
                                    Appointment_Time_Field.setText("");
                                }
                            }
                        }

                        catch (Exception ex) {
                        }
                    }
                } catch (Exception nul) {
                }
            }
        });
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                frame.setVisible(false);
            }
        });
        frame.setSize(1920, 1080);
        frame.getContentPane().setBackground(new Color(215, 247, 224));
        frame.add(headerLabel);
        frame.add(Back_Button);
        frame.add(ID_Tfield);
        frame.add(Search_Button);
        frame.add(Appointment_Tfield);
        frame.add(Save_Button);
        frame.add(Cancel_Button);
        frame.add(Add_Appointment_Label);
        frame.add(ID_Label);
        frame.add(Appointment_Label);
        frame.add(Date_Label);
        frame.add(Date_Tfield);
        frame.add(Appointment_Time);
        frame.add(Appointment_Time_Field);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

    }

    public static AddAppointment getInstance() {
        return new AddAppointment();
    }

    
}
