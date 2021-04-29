package module5;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.*;
import module2.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient extends JFrame {
    JButton Cancel_Request, Add_Request;
    String Password = "", Doc_ID = "", Recp_ID = "", Age = "", Phone = "", Last_Appointment = "", No_Appointment = "",
            doc_Name = "", doc_Phone = "";
    int Fees = 0;

    Patient() {
        JLabel Head_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Head_Label.setOpaque(true);
        Head_Label.setForeground(Color.BLACK);
        Head_Label.setBackground(new Color(171, 222, 158));
        Head_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Head_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("Patient Data", JLabel.CENTER);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setOpaque(true);
        Footer_Label.setBackground(new Color(171, 222, 158));
        Footer_Label.setBounds(0, 150, 1920, 50);

        JLabel Hi_Label = new JLabel("Hi " + Login.Pat_Login_Name + "!!");
        Hi_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Hi_Label.setBounds(50, 235, 300, 40);

        JButton Logout = new JButton("Logout");
        Logout.setFont(new Font("Verdana", Font.PLAIN, 18));
        Logout.setBackground(new Color(171, 222, 158));
        Logout.setBounds(1730, 230, 120, 40);
        Logout.addActionListener(e -> {
            setVisible(false);
            Login.getInstance();
        });

        JLabel Date_Label = new JLabel();
        Date_Label.setFont(new Font("Verdana", Font.BOLD, 45));
        Date_Label.setBackground(new Color(171, 222, 158));
        Date_Label.setBounds(1350, 400, 350, 60);

        JLabel Time_Label = new JLabel();
        Time_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Time_Label.setBackground(new Color(171, 222, 158));
        Time_Label.setBounds(1450, 470, 350, 50);
        int inform = 0;
        int request = 0;
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("Select * from `patient` where ID='" + Login.Pat_Login_ID + "';");
            while (rs.next()) {
                Date_Label.setText(rs.getString("Next_Appointment"));
                Time_Label.setText(String.valueOf(rs.getString("Time")));
                inform = rs.getInt("Informed");
                request = rs.getInt("Appointment_Change_Request");
                Password = rs.getString("PASSWORD");
                Doc_ID = rs.getString("Doctor");
                Age = rs.getString("Age");
                Phone = rs.getString("Phone_Number");
                Last_Appointment = String.valueOf(rs.getString("Last_Appointment"));
                No_Appointment = rs.getString("No_Of_Appointment_Changes");
                Fees = rs.getInt("Fees");
            }
        } catch (Exception E) {
        }

        Add_Request = new JButton("Add Request");
        Add_Request.setFont(new Font("Verdana", Font.PLAIN, 16));
        Add_Request.setBackground(new Color(171, 222, 158));
        Add_Request.setBounds(1330, 570, 180, 50);
        Add_Request.addActionListener(e -> {
            String pass = JOptionPane.showInputDialog(this, "Enter Ur Password");
            if (pass.equals(Password)) {
                if (JOptionPane.showConfirmDialog(this, "Do Wanna Request For Appointment?", "Confirm Message",
                        JOptionPane.WARNING_MESSAGE) == 0) {
                    try {
                        PreparedStatement ps = Login.getConnection().prepareStatement(
                                "UPDATE patient SET APPOINTMENT_CHANGE_REQUEST=(?),INFORMED=(?) WHERE ID=(?)");
                        ps.setInt(1, 1);
                        ps.setInt(2, 0);
                        ps.setString(3, Login.Pat_Login_ID);
                        ps.executeUpdate();
                    } catch (Exception E1) {
                    }
                    Add_Request.setEnabled(false);
                    Cancel_Request.setEnabled(true);
                }
            } else {
                JOptionPane.showConfirmDialog(this, "Do Enter Proper Password", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateBeforeString = String.valueOf(LocalDate.now());
        String dateAfterString = Date_Label.getText();
        float daysBetween = 0.0f;

        try {
            Date dateBefore = myFormat.parse(dateBeforeString);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
        }

        Cancel_Request = new JButton("Cancel Request");
        Cancel_Request.setFont(new Font("Verdana", Font.PLAIN, 16));
        Cancel_Request.setBackground(new Color(171, 222, 158));
        Cancel_Request.setBounds(1530, 570, 180, 50);

        Cancel_Request.addActionListener(e -> {
            try {
                PreparedStatement ps = Login.getConnection().prepareStatement(
                        "UPDATE patient SET APPOINTMENT_CHANGE_REQUEST=(?),INFORMED=(?) WHERE ID=(?)");
                ps.setInt(1, 0);
                ps.setInt(2, 1);
                ps.setString(3, Login.Pat_Login_ID);
                ps.executeUpdate();
            } catch (Exception sql) {
            }
            Add_Request.setEnabled(true);
        });

        if ((request == 0 && inform == 1) && (int) daysBetween >= 1) {
            Add_Request.setEnabled(true);
        } else {
            Add_Request.setEnabled(false);
        }
        if (request == 1 && inform == 0) {
            Cancel_Request.setEnabled(true);
        } else {
            Cancel_Request.setEnabled(false);
        }

        JLabel Appointment_Label = new JLabel("Appointment");
        Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Label.setBackground(new Color(171, 222, 158));
        Appointment_Label.setBounds(1440, 350, 200, 30);

        JLabel Notify_Label = new JLabel();
        Notify_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Notify_Label.setBackground(new Color(171, 222, 158));
        Notify_Label.setForeground(new Color(123, 181, 47));
        Notify_Label.setBounds(1250, 650, 600, 30);
        if ((int) daysBetween != 0) {
            Notify_Label.setText(
                    "Your Next Appointment is Scheduled Within " + String.valueOf((int) daysBetween) + " Days !");
        } else if ((int) daysBetween == 0) {
            Notify_Label.setText("Your Next Appointment is Scheduled Today!");
            Notify_Label.setBounds(1300, 650, 600, 30);
        } else {
            Notify_Label.setText("");
        }

        JButton Refresh_Button = new JButton("Refresh");
        Refresh_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Refresh_Button.setBackground(new Color(171, 222, 158));
        Refresh_Button.setBounds(1570, 230, 120, 40);
        Refresh_Button.addActionListener(e -> {
            getInstance();
            dispose();
        });

        JPanel pane = new JPanel();
        pane.setBackground(new Color(171, 222, 158));
        pane.setBounds(1220, 710, 600, 290);
        pane.setLayout(null);

        JLabel Recp_Details = new JLabel("RECEPTIONIST FOR YOU ");
        Recp_Details.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 22));
        Recp_Details.setBounds(155, 20, 400, 40);
        Recp_Details.setForeground(new Color(123, 181, 47));
        pane.add(Recp_Details);

        JLabel Recp_Name = new JLabel("Name    :   ");
        Recp_Name.setFont(new Font("Verdana", Font.PLAIN, 21));
        Recp_Name.setBounds(180, 90, 400, 40);
        pane.add(Recp_Name);

        JLabel Recp_ID_Pane = new JLabel("ID          :   ");
        Recp_ID_Pane.setFont(new Font("Verdana", Font.PLAIN, 21));
        Recp_ID_Pane.setBounds(180, 140, 400, 40);
        pane.add(Recp_ID_Pane);

        JLabel recp_Phone = new JLabel("Phone    :   ");
        recp_Phone.setFont(new Font("Verdana", Font.PLAIN, 21));
        recp_Phone.setBounds(180, 190, 400, 40);
        pane.add(recp_Phone);

        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("Select * from `doctor` where `doctor_id`='" + Doc_ID + "';");
            if (rs.next()) {
                Recp_ID = rs.getString("Recp_ID");
                doc_Name = rs.getString("first_name") + " " + rs.getString("second_name");
                doc_Phone = rs.getString("ph_no");
            }
            Statement s1 = Login.getConnection().createStatement();
            ResultSet rs1 = s1.executeQuery("Select * from `receptionist` where `Recp_ID`='" + Recp_ID + "';");
            while (rs1.next()) {
                Recp_Name.setText(Recp_Name.getText() + rs1.getString("F_name") + " " + rs1.getString("L_name"));
                Recp_ID_Pane.setText(Recp_ID_Pane.getText() + rs1.getString("Recp_ID"));
                recp_Phone.setText(recp_Phone.getText() + rs1.getString("Phone"));
            }

        } catch (Exception e) {
            System.out.print(e);
        }

        JPanel Pane2 = new JPanel();
        Pane2.setBounds(50, 300, 400, 700);
        Pane2.setBackground(new Color(171, 222, 158));
        Pane2.setLayout(null);

        JLabel Pat_ID = new JLabel("ID    :   " + Login.Pat_Login_ID);
        Pat_ID.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_ID.setBounds(30, 30, 400, 40);
        Pane2.add(Pat_ID);

        JLabel Pat_Age = new JLabel("Age   :   " + Age);
        Pat_Age.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_Age.setBounds(30, 90, 400, 40);
        Pane2.add(Pat_Age);

        JLabel Pat_Phone = new JLabel("Phone   :   " + Phone);
        Pat_Phone.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_Phone.setBounds(30, 150, 400, 40);
        Pane2.add(Pat_Phone);

        JLabel Pat_Last_Appointment = new JLabel("Last Appointment :   " + Last_Appointment);
        Pat_Last_Appointment.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_Last_Appointment.setBounds(30, 210, 400, 40);
        Pane2.add(Pat_Last_Appointment);

        JLabel Pat_Appointment_Change = new JLabel("No. Of Request :   " + No_Appointment);
        Pat_Appointment_Change.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_Appointment_Change.setBounds(30, 270, 400, 40);
        Pane2.add(Pat_Appointment_Change);

        JLabel Appointment_request = new JLabel();
        Appointment_request.setFont(new Font("Verdana", Font.PLAIN, 21));
        Appointment_request.setBounds(30, 330, 400, 40);
        if (request == 1) {
            Appointment_request.setText("Request Claimed  : Yes");
        } else {
            Appointment_request.setText("Request Claimed  : No");
        }
        Pane2.add(Appointment_request);

        JLabel Informed = new JLabel();
        Informed.setFont(new Font("Verdana", Font.PLAIN, 21));
        Informed.setBounds(30, 390, 400, 40);
        if (inform == 1) {
            Informed.setText("Informed  : Yes");
        } else {
            Informed.setText("Informed  : No");
        }
        Pane2.add(Informed);

        JLabel Pat_Fees = new JLabel("Fees :  " + String.valueOf(Fees));
        Pat_Fees.setFont(new Font("Verdana", Font.PLAIN, 21));
        Pat_Fees.setBounds(30, 450, 400, 40);
        Pane2.add(Pat_Fees);

        JPanel Pane3 = new JPanel();
        Pane3.setBackground(new Color(171, 222, 158));
        Pane3.setBounds(520, 710, 600, 290);
        Pane3.setLayout(null);

        JLabel Doc_Details = new JLabel("    DOCTOR FOR YOU ");
        Doc_Details.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 22));
        Doc_Details.setBounds(155, 20, 400, 40);
        Doc_Details.setForeground(new Color(123, 181, 47));
        Pane3.add(Doc_Details);

        JLabel Doc_Name = new JLabel("Name    :   " + doc_Name);
        Doc_Name.setFont(new Font("Verdana", Font.PLAIN, 21));
        Doc_Name.setBounds(180, 90, 400, 40);
        Pane3.add(Doc_Name);

        JLabel Doc_ID_Pane = new JLabel("ID          :   " + Doc_ID);
        Doc_ID_Pane.setFont(new Font("Verdana", Font.PLAIN, 21));
        Doc_ID_Pane.setBounds(180, 140, 400, 40);
        Pane3.add(Doc_ID_Pane);

        JLabel Doc_Phone = new JLabel("Phone    :   " + doc_Phone);
        Doc_Phone.setFont(new Font("Verdana", Font.PLAIN, 21));
        Doc_Phone.setBounds(180, 190, 400, 40);
        Pane3.add(Doc_Phone);

        JButton Change_Patient = new JButton("CHANGE PASSWORD");
        Change_Patient.setFont(new Font("Verdana", Font.PLAIN, 15));
        Change_Patient.setOpaque(true);
        Change_Patient.setBackground(new Color(171, 222, 158));
        Change_Patient.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Change_Patient.setBorder(null);
        Change_Patient.setBounds(520, 630, 240, 55);
        Change_Patient.addActionListener(e -> {
            JFrame Pat_Detail = new JFrame();
            JLabel Password_Label, Retype_Password_Label;
            JPasswordField Password_TField,Retype_Password_TField;
            
            JButton Submit_Button = new JButton("Submit");

            Password_Label = new JLabel("New Password :");
            Password_Label.setFont(new Font("Verdana", Font.PLAIN, 16));
            Password_Label.setBounds(40, 100, 150, 50);
            Pat_Detail.add(Password_Label);

            Retype_Password_Label = new JLabel("Retype Password :");
            Retype_Password_Label.setFont(new Font("Verdana", Font.PLAIN, 16));
            Retype_Password_Label.setBounds(40, 200, 160, 50);
            Pat_Detail.add(Retype_Password_Label);

            Password_TField = new JPasswordField("");
            Password_TField.setFont(new Font("Verdana", Font.PLAIN, 17));
            Password_TField.setBorder(null);
            Password_TField.setBounds(220, 100, 200, 40);
            Pat_Detail.add(Password_TField);

            Retype_Password_TField = new JPasswordField("");
            Retype_Password_TField.setFont(new Font("Verdana", Font.PLAIN, 17));
            Retype_Password_TField.setBorder(null);
            Retype_Password_TField.setBounds(220, 200, 200, 40);
            Pat_Detail.add(Retype_Password_TField);
            JLabel temp = new JLabel("Is not Valid");
            Pat_Detail.add(temp);
            Password_TField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    Pattern p = Pattern.compile(
                            "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");
                    Matcher m = p.matcher(Password_TField.getText());
                    if (!m.matches()) {
                        temp.setBounds(220, 150, 70, 25);
                        temp.setVisible(true);
                        Submit_Button.setEnabled(false);
                    } 
                    
                    else {
                        temp.setVisible(false);
                        Submit_Button.setEnabled(true);
                    
                }
            }});
            if(Retype_Password_TField.getText().isEmpty()){
                Submit_Button.setEnabled(false);
            }
            Retype_Password_TField.addKeyListener(new KeyAdapter(){
                public void keyReleased(KeyEvent e) {
                    if(Password_TField.getText().equals(Retype_Password_TField.getText())){
                        Submit_Button.setEnabled(true);
                    }
                    else{
                        Submit_Button.setEnabled(false);
                    }
                }
            });



            Submit_Button.setFont(new Font("Verdana", Font.PLAIN, 17));
            Submit_Button.setBackground(new Color(171, 222, 158));
            Submit_Button.setBorder(null);
            Submit_Button.setBounds(320, 300, 100, 40);
            Submit_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Pat_Detail.add(Submit_Button);
            Submit_Button.addActionListener(e1 -> {
                if (Password_TField.getText().isEmpty() || Retype_Password_TField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Do Enter Something");
                }
                 
                else {
                    int i = JOptionPane.showConfirmDialog(Pat_Detail, "Do Confirm ?");
                    if (i == 0) {
                        try {
                            PreparedStatement S = Login.getConnection()
                                    .prepareStatement("UPDATE `patient` SET `PASSWORD`=? WHERE `ID`=?;");
                            S.setString(1, Password_TField.getText());
                            S.setString(2, Login.Pat_Login_ID);
                            S.executeUpdate();
                        } catch (Exception sql) {
                        }
                        Pat_Detail.dispose();
                    }
                }
            });
            Pat_Detail.getContentPane().setBackground(new Color(215, 247, 224));
            Pat_Detail.setSize(480, 550);
            Pat_Detail.setLocationRelativeTo(null);
            Pat_Detail.setLayout(null);
            Pat_Detail.setVisible(true);

        });
        add(Hi_Label);
        add(Head_Label);
        add(Footer_Label);
        add(Logout);
        add(Date_Label);
        add(Time_Label);
        add(Appointment_Label);
        add(Notify_Label);
        add(Add_Request);
        add(Cancel_Request);
        add(Refresh_Button);
        add(pane);
        add(Pane2);
        add(Pane3);
        add(Change_Patient);
        setSize(1920, 1080);
        getContentPane().setBackground(new Color(215, 247, 224));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Patient getInstance() {
        return new Patient();
    }

}