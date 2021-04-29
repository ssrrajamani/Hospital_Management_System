package module2;

import module3.Receptionist;
import module4.Main;
import module5.Patient;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.awt.*;

public class Login extends JFrame implements ActionListener {
    private JLabel Header_Label, Admin_Panel_Label, Icon_Label, USER, PASSWORD;
    private JPanel Holding_Panel;
    private JTextField User_TField;
    private JPasswordField Password_PField;
    private JButton Submit_Button;
    private JComboBox<String> User_Combo;
    private static Connection Con;
    public static String Doctor_Login_ID = "";
    public static String Recp_Login_ID = "";
    public static String Pat_Login_ID = "", Pat_Login_Name = "", Recp_Doc_ID = "";

    private Login() {
        String array[] = { "RECEPTIONIST", "DOCTOR", "ADMIN", "PATIENT" };
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon i = new ImageIcon("module2\\TWO.png");
        Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setOpaque(true);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Admin_Panel_Label = new JLabel("USER  LOGIN", JLabel.CENTER);
        Admin_Panel_Label.setFont(new Font("Verdana", Font.PLAIN, 40));
        Icon_Label = new JLabel(i);
        Icon_Label.setOpaque(true);
        User_TField = new JTextField();
        User_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        User_TField.setBorder(null);
        Password_PField = new JPasswordField();
        Password_PField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Password_PField.setBorder(null);
        USER = new JLabel("USER : ", JLabel.LEFT);
        USER.setFont(new Font("Verdana", Font.PLAIN, 23));
        PASSWORD = new JLabel("PASSWORD :", JLabel.LEFT);
        PASSWORD.setFont(new Font("Verdana", Font.PLAIN, 23));
        Submit_Button = new JButton("ENTER");
        Submit_Button.setFont(new Font("Verdana", Font.PLAIN, 15));
        Submit_Button.setBackground(new Color(215, 247, 224));
        Submit_Button.addActionListener(this);
        User_Combo = new JComboBox<>(array);
        User_Combo.setBackground(new Color(215, 247, 224));
        User_Combo.setFont(new Font("Verdana", Font.PLAIN, 15));
        Holding_Panel = new JPanel(null);
        User_TField.setBounds(250, 150, 230, 40);
        Password_PField.setBounds(250, 300, 230, 40);
        USER.setBounds(50, 150, 150, 50);
        PASSWORD.setBounds(50, 300, 200, 50);
        Submit_Button.setBounds(339, 450, 110, 40);
        User_Combo.setBounds(90, 450, 140, 40);
        Holding_Panel.add(User_TField);
        Holding_Panel.add(Password_PField);
        Holding_Panel.add(USER);
        Holding_Panel.add(PASSWORD);
        Holding_Panel.add(Submit_Button);
        Holding_Panel.add(User_Combo);
        Holding_Panel.setBounds(1000, 300, 550, 600);
        Header_Label.setBounds(0, 20, (int) ss.getWidth(), 90);
        Admin_Panel_Label.setBounds(300, 350, 300, 40);
        Icon_Label.setBounds(320, 450, i.getIconWidth(), i.getIconHeight());
        Holding_Panel.setOpaque(true);
        Holding_Panel.setBackground(new Color(171, 222, 158));
        add(Header_Label);
        add(Icon_Label);
        add(Holding_Panel);
        add(Admin_Panel_Label);
        getContentPane().setBackground(new Color(215, 247, 224));
        setSize((int) ss.getWidth(), (int) ss.getHeight());
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Con = null;
        Con = DriverManager.getConnection("jdbc:mysql://localhost/admin", "harikrish", "orangeisblack");
        return Con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = User_TField.getText();
        String pass = Password_PField.getText();

        if (User_Combo.getSelectedItem().equals("ADMIN")) {
            String admin = "", Pass = "";
            try {
                Statement s = Con.createStatement();
                ResultSet rs = s.executeQuery("SELECT * from adam LIMIT 1;");
                while (rs.next()) {
                    admin = rs.getString("Name");
                    Pass = rs.getString("Password");
                }
                s.close();
            } catch (Exception s) {
            }

            if (user.equals(admin) && pass.equals(Pass)) {
                JOptionPane.showMessageDialog(this, "LOGIN SUCESSFUL!!");
                setVisible(false);
                User_TField.setText("");
                Password_PField.setText("");
                Option.getInstance();
            } else {
                JOptionPane.showMessageDialog(this, "INCORRENT USER OR PASSWORD");
            }
        }
        if (User_Combo.getSelectedItem().equals("RECEPTIONIST")) {
            try {
                Statement s = Login.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM receptionist where `Status`=1");
                boolean flag = false;
                while (rs.next()) {
                    Recp_Doc_ID = rs.getString("Doctor_ID");
                    if (rs.getString("Recp_ID").equals(user) && rs.getString("Password").equals(pass)) {
                        rs.getString("Recp_ID");
                        rs.getString("Password");

                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(this, "LOGIN SUCESSFUL!!");
                    setVisible(false);
                    Recp_Login_ID = User_TField.getText();
                    User_TField.setText("");
                    Password_PField.setText("");
                    Receptionist.getInstance();
                } else {
                    JOptionPane.showMessageDialog(this, "INCORRENT USER OR PASSWORD");

                }
            } catch (Exception sql) {
            }

        }

        if (User_Combo.getSelectedItem().equals("DOCTOR")) {
            try {
                Statement s = Login.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM doctor  where `Status`=1");
                boolean flag = false;
                while (rs.next()) {
                    if (rs.getString("doctor_id").equals(user) && rs.getString("password").equals(pass)) {
                        rs.getString("doctor_id");
                        rs.getString("password");
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(this, "LOGIN SUCESSFUL!!");
                    Doctor_Login_ID = User_TField.getText();
                    setVisible(false);
                    User_TField.setText("");
                    Password_PField.setText("");
                    Main.getInstance();
                } else {
                    JOptionPane.showMessageDialog(this, "INCORRENT USER OR PASSWORD");

                }

            } catch (Exception sql) {
            }
        }

        if (User_Combo.getSelectedItem().equals("PATIENT")) {
            try {
                Statement s = Login.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM `patient`  where `Status`='Yes'");
                boolean flag = false;
                while (rs.next()) {
                    if (rs.getString("ID").equals(user) && rs.getString("PASSWORD").equals(pass)) {
                        rs.getString("ID");
                        rs.getString("PASSWORD");
                        Pat_Login_Name = rs.getString("Name");
                        Pat_Login_ID = rs.getString("ID");
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(this, "LOGIN SUCESSFUL!!");
                    setVisible(false);
                    User_TField.setText("");
                    Password_PField.setText("");
                    Patient.getInstance();
                } else {
                    JOptionPane.showMessageDialog(this, "INCORRENT USER OR PASSWORD");

                }

            } catch (Exception sql) {
            }
        }

    }

    public static Login getInstance() {
        return new Login();
    }

    public static void main(String[] args) throws Exception {
        Login.getConnection();
        getInstance();

    }

}