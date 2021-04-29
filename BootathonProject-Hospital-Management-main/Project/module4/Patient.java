package module4;

import javax.sound.sampled.Clip;
import javax.swing.*;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Patient {
    String s;String prob;Clip c ;
    private Patient(){
        JFrame f=new JFrame();
        JLabel Header_Label= new JLabel("Hospital Management System",JLabel.CENTER);
        
        JButton Back_Button=new JButton("Back");
        Back_Button.setBounds(20,130,150,40);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN,15));
        Back_Button.setBackground(new Color(171,222,158));

        JButton Search_Button=new JButton("Search");
        Search_Button.setBounds(1150,220,150,40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN,14));
        Search_Button.setBackground(new Color(171,222,158));

        JButton Ok_Button=new JButton("Ok");
        Ok_Button.setBounds(700,850,150,50);
        Ok_Button.setFont(new Font("Verdana", Font.PLAIN,20));
        Ok_Button.setBackground(new Color(171,222,158));

        JButton Discharge_Button=new JButton("Discharge");
        Discharge_Button.setBounds(900,850,200,50);
        Discharge_Button.setFont(new Font("Verdana", Font.PLAIN,20));
        Discharge_Button.setBackground(new Color(171,222,158));

        JButton Update_Button=new JButton("Update");
        Update_Button.setBounds(1150,850,200,50);
        Update_Button.setFont(new Font("Verdana", Font.PLAIN,20));
        Update_Button.setBackground(new Color(171,222,158));

        JButton New_Button=new JButton("New");
        New_Button.setBounds(1350,220,150,40);
        New_Button.setFont(new Font("Verdana", Font.PLAIN,14));
        New_Button.setBackground(new Color(171,222,158));

        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171,222,158));
        Header_Label.setFont(new Font("Verdana",Font.PLAIN,30));
        Header_Label.setBounds(0,20,1920,90);

        JLabel Footer_Label=new JLabel("PATIENT",JLabel.CENTER);
        Footer_Label.setOpaque(true);
        Footer_Label.setForeground(Color.BLACK);
        Footer_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Footer_Label.setBounds(800,120,300,30);
        Footer_Label.setBackground(new Color(215, 247, 224));

        JLabel Id_Label=new JLabel("ENTER THE ID",JLabel.CENTER);
        Id_Label.setOpaque(true);
        Id_Label.setForeground(Color.BLACK);
        Id_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Id_Label.setBounds(600,226,300,30);
        Id_Label.setBackground(new Color(215, 247, 224));

        JLabel Name_Label=new JLabel("NAME",JLabel.CENTER);
        Name_Label.setOpaque(true);
        Name_Label.setForeground(Color.BLACK);
        Name_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Name_Label.setBounds(560,326,300,30);
        Name_Label.setBackground(new Color(215, 247, 224));

        JLabel Age_Label=new JLabel("AGE",JLabel.CENTER);
        Age_Label.setOpaque(true);
        Age_Label.setForeground(Color.BLACK);
        Age_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Age_Label.setBounds(200,429,100,30);
        Age_Label.setBackground(new Color(215, 247, 224));

        JLabel Phone_Label=new JLabel("PHONE NUMBER",JLabel.CENTER);
        Phone_Label.setOpaque(true);
        Phone_Label.setForeground(Color.BLACK);
        Phone_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Phone_Label.setBounds(740,429,250,30);
        Phone_Label.setBackground(new Color(215, 247, 224));

        JLabel Address_Label=new JLabel("ADDRESS",JLabel.CENTER);
        Address_Label.setOpaque(true);
        Address_Label.setForeground(Color.BLACK);
        Address_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Address_Label.setBounds(1320,429,150,30);
        Address_Label.setBackground(new Color(215, 247, 224));

        JLabel Last_Appointment_Label=new JLabel("LAST APPOINTMENT",JLabel.CENTER);
        Last_Appointment_Label.setOpaque(true);
        Last_Appointment_Label.setForeground(Color.BLACK);
        Last_Appointment_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Last_Appointment_Label.setBounds(180,529,300,30);
        Last_Appointment_Label.setBackground(new Color(215, 247, 224));

        JLabel Appointment_Changes_Label=new JLabel("NO OF APPOINTMENT CHANGES",JLabel.CENTER);
        Appointment_Changes_Label.setOpaque(true);
        Appointment_Changes_Label.setForeground(Color.BLACK);
        Appointment_Changes_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Appointment_Changes_Label.setBounds(740,529,400,30);
        Appointment_Changes_Label.setBackground(new Color(215, 247, 224));

        JLabel Gender_Label=new JLabel("GENDER",JLabel.CENTER);
        Gender_Label.setOpaque(true);
        Gender_Label.setForeground(Color.BLACK);
        Gender_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Gender_Label.setBounds(1260,529,250,30);
        Gender_Label.setBackground(new Color(215, 247, 224));

        JLabel Problem_Label=new JLabel("PROBLEM OF VISIT",JLabel.CENTER);
        Problem_Label.setOpaque(true);
        Problem_Label.setForeground(Color.BLACK);
        Problem_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Problem_Label.setBounds(500,729,250,30);
        Problem_Label.setBackground(new Color(215, 247, 224));

        JLabel Blood_Label=new JLabel("BLOOD GROUP",JLabel.CENTER);
        Blood_Label.setOpaque(true);
        Blood_Label.setForeground(Color.BLACK);
        Blood_Label.setFont(new Font("Verdana",Font.PLAIN,20));
        Blood_Label.setBounds(480,629,250,30);
        Blood_Label.setBackground(new Color(215, 247, 224));

        JTextField Id_Field=new JTextField();
        Id_Field.setBounds(900,220,200,40);
        Id_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextField Name_Field=new JTextField();
        Name_Field.setBounds(900,320,300,40);
        Name_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextField Age_Field=new JTextField();
        Age_Field.setBounds(500,426,100,40);
        Age_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextField Phone_Field=new JTextField();
        Phone_Field.setBounds(1000,426,200,40);
        Phone_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextArea Address_Area=new JTextArea();
        Address_Area.setBounds(1700,426,300,70);
        Address_Area.setLineWrap(true);
        Address_Area.setFont(new Font("Verdana", Font.PLAIN,20));
        JScrollPane pane=new JScrollPane(Address_Area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(1600,426,200,70);


        JTextField Last_Appointment_Field=new JTextField();
        Last_Appointment_Field.setBounds(500,526,150,40);
        Last_Appointment_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextField Appointment_Changes_Field=new JTextField();
        Appointment_Changes_Field.setBounds(1120,526,50,40);
        Appointment_Changes_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        JTextField Gender_Field=new JTextField();
        Gender_Field.setBounds(1600,526,100,40);
        Gender_Field.setFont(new Font("Verdana", Font.PLAIN,20));


        JTextArea Problem_Area=new JTextArea();
        Problem_Area.setBounds(1200,629,600,40);
        Problem_Area.setFont(new Font("Verdana", Font.PLAIN,20));
        Problem_Area.setLineWrap(true);
        JScrollPane pane1=new JScrollPane(Problem_Area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBounds(800,729,600,100);

        JTextField Blood_Field=new JTextField();
        Blood_Field.setBounds(800,629,100,40);
        Blood_Field.setFont(new Font("Verdana", Font.PLAIN,20));

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getInstance();
                f.setVisible(false);
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s=Id_Field.getText();
                Name_Field.setText("");
                Age_Field.setText("");
                Phone_Field.setText("");
                Address_Area.setText("");
                Last_Appointment_Field.setText("");
                Appointment_Changes_Field.setText("");
                Gender_Field.setText("");
                Problem_Area.setText("");
                Blood_Field.setText("");
                try{
                    PreparedStatement ps=Login.getConnection().prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=(?) AND `Status`='Yes';");
                    ps.setString(1,s);
                    ps.setString(2, Login.Doctor_Login_ID);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        Name_Field.setText(rs.getString(1));
                        Age_Field.setText(String.valueOf(rs.getInt(4)));
                        Phone_Field.setText(rs.getString(7));
                        Address_Area.setText(rs.getString(5));
                        Last_Appointment_Field.setText(rs.getString(9));
                        Appointment_Changes_Field.setText(rs.getString(11));
                        Blood_Field.setText(rs.getString(6));
                        Gender_Field.setText(rs.getString(16));
                        Problem_Area.setText(rs.getString(8));
                    }


                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });

        Ok_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Id_Field.setText("");
                Name_Field.setText("");
                Age_Field.setText("");
                Phone_Field.setText("");
                Address_Area.setText("");
                Last_Appointment_Field.setText("");
                Appointment_Changes_Field.setText("");
                Gender_Field.setText("");Blood_Field.setText("");
                Problem_Area.setText("");
                Blood_Field.setText("");
            }
        });
        Discharge_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s=Id_Field.getText();
                int a=JOptionPane.showConfirmDialog(f,"Are you sure?");
                
                if(a==JOptionPane.YES_NO_OPTION){
                    discharge();
                    Id_Field.setText("");
                    Name_Field.setText("");
                    Age_Field.setText("");
                    Phone_Field.setText("");
                    Address_Area.setText("");
                    Last_Appointment_Field.setText("");
                    Appointment_Changes_Field.setText("");
                    Gender_Field.setText("");Blood_Field.setText("");
                    Problem_Area.setText("");
                    JOptionPane.showMessageDialog(f, "DISCHARGED", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Update_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    prob=Problem_Area.getText();
                    PreparedStatement ps = Login.getConnection().prepareStatement("UPDATE patient SET Problem_Of_Visit=? , Age=? ,Phone_Number=?, ADDRESS=? WHERE ID=? AND doctor=?;");
                    ps.setString(1,prob);
                    ps.setInt(2,Integer.parseInt(Age_Field.getText()));
                    ps.setString(3,Phone_Field.getText());
                    ps.setString(4,Address_Area.getText());
                    ps.setString(5,s);
                    ps.setString(6, Login.Doctor_Login_ID);
                    ps.executeUpdate();
                    ps.close();
                    JOptionPane.showMessageDialog(f, "UPDATED SUCCESSFULLY", "Success", JOptionPane.WARNING_MESSAGE);
                    Id_Field.setText("");
                    Name_Field.setText("");
                    Age_Field.setText("");
                    Phone_Field.setText("");
                    Address_Area.setText("");
                    Last_Appointment_Field.setText("");
                    Appointment_Changes_Field.setText("");
                    Gender_Field.setText("");Blood_Field.setText("");
                    Problem_Area.setText("");

                }
                catch (Exception ec){
                    System.out.println(ec);
                }

            }
        });

        New_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    AddPatient.getInstance();
            }
        });
        f.setSize(1920,1080);
        f.getContentPane().setBackground(new Color(215, 247, 224));
        f.add(Header_Label);f.add(Footer_Label);f.add(Id_Label);f.add(Name_Label);f.add(Id_Field);f.add(Name_Field);f.add(Back_Button);f.add(Search_Button);
        f.add(Age_Field);f.add(Phone_Field);f.add(Phone_Label);f.add(Last_Appointment_Field);f.add(Appointment_Changes_Field);f.add(Gender_Field);f.add(New_Button);
        f.add(Name_Label);f.add(Age_Label);f.add(pane);f.add(Address_Label);f.add(Last_Appointment_Label);f.add(Appointment_Changes_Label);f.add(Gender_Label);f.add(Problem_Label);f.add(Blood_Field);
        f.add(Ok_Button);f.add(Discharge_Button);f.add(pane1);f.add(Update_Button);f.add(Blood_Label);
        f.setLayout(null);f.setResizable(true);f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }
    void discharge(){
        try{
            PreparedStatement ps = Login.getConnection().prepareStatement("UPDATE patient SET STATUS='no' WHERE ID=? AND doctor=?");
            ps.setString(1,s);
            ps.setString(2, Login.Doctor_Login_ID);
            ps.executeUpdate();
            ps.close();
            }
        catch (Exception ec){
            System.out.println(ec);
        }
    }
    public static Patient getInstance(){
        return new Patient();
    }
}
