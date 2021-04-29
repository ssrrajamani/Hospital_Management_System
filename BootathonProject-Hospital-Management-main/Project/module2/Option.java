package module2;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import module1.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Option extends JFrame implements ActionListener {

    private JLabel Header_Label, Footer_Label, Icon_Label_1, Icon_Label_2, Icon_Label_3;
    private JButton Doctor_Button, Receptionist_Button, Patient_Button, logout, AdminDetail_Button, Salary_Button,
            Recp_details_Button, Doc_details_Button;
    DefaultTableModel model, model1;
    JTable Table, Table1;
    JPanel Pane, Pane1;

    private Option() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon i1 = new ImageIcon("module2\\1doc3.png");
        ImageIcon i2 = new ImageIcon("module2\\3recp3.png");
        ImageIcon i3 = new ImageIcon("module2\\2Pat2.jpg");

        Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setOpaque(true);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setBounds(0, 20, (int) ss.getWidth(), 90);

        Footer_Label = new JLabel("Admin Panel", JLabel.CENTER);
        Footer_Label.setOpaque(true);
        Footer_Label.setBackground(new Color(171, 222, 158));
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setBounds(0, 150, (int) ss.getWidth(), 50);

        Icon_Label_1 = new JLabel(i1);
        Icon_Label_1.setBounds(300, 310, i1.getIconWidth() - 70, i1.getIconHeight() - 50);

        Icon_Label_2 = new JLabel(i2);
        Icon_Label_2.setBounds(800, 310, i2.getIconWidth() - 10, i2.getIconHeight());

        Icon_Label_3 = new JLabel(i3);
        Icon_Label_3.setBounds(1300, 310, 270, 280);

        Doctor_Button = new JButton("DOCTOR DETAILS");
        Doctor_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Doctor_Button.setOpaque(true);
        Doctor_Button.setBackground(new Color(171, 222, 158));
        Doctor_Button.setBounds(330, 660, 230, 60);
        Doctor_Button.addActionListener(e -> {

            DoctorMainPane.getInstance();
            dispose();
        });

        Receptionist_Button = new JButton("RECEPTIONIST DETAILS");
        Receptionist_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Receptionist_Button.setOpaque(true);
        Receptionist_Button.setBackground(new Color(171, 222, 158));
        Receptionist_Button.setBounds(815, 660, 265, 60);
        Receptionist_Button.addActionListener(e -> {
            ReceptionistAdmin.getInstance();
            dispose();
        });

        Patient_Button = new JButton("PATIENT DETAILS");
        Patient_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Patient_Button.setOpaque(true);
        Patient_Button.setBackground(new Color(171, 222, 158));
        Patient_Button.setBounds(1330, 660, 230, 60);
        Patient_Button.addActionListener(e -> {
            PatientAdmin.getInstance();
            dispose();
        });

        AdminDetail_Button = new JButton("CHANGE ADMIN");
        AdminDetail_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        AdminDetail_Button.setOpaque(true);
        AdminDetail_Button.setBackground(new Color(171, 222, 158));
        AdminDetail_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        AdminDetail_Button.setBounds(1600, 875, 250, 60);
        AdminDetail_Button.addActionListener(e -> {
            JFrame Admin_Detail = new JFrame();
            JLabel New_Name_Label, New_Password_Label;
            JTextField New_Name_TField;
            JPasswordField New_Password_PField;
            JButton Submit_Button = new JButton("Submit");

            New_Name_Label = new JLabel("New Name :");
            New_Name_Label.setFont(new Font("Verdana", Font.PLAIN, 16));
            New_Name_Label.setBounds(40, 100, 150, 50);
            Admin_Detail.add(New_Name_Label);

            New_Password_Label = new JLabel("New Password :");
            New_Password_Label.setFont(new Font("Verdana", Font.PLAIN, 16));
            New_Password_Label.setBounds(40, 200, 150, 50);
            Admin_Detail.add(New_Password_Label);

            New_Name_TField = new JTextField("");
            New_Name_TField.setFont(new Font("Verdana", Font.PLAIN, 17));
            New_Name_TField.setBorder(null);
            New_Name_TField.setBounds(220, 100, 200, 40);
            Admin_Detail.add(New_Name_TField);

            New_Password_PField = new JPasswordField("");
            New_Password_PField.setFont(new Font("Verdana", Font.PLAIN, 17));
            New_Password_PField.setBorder(null);
            New_Password_PField.setBounds(220, 200, 200, 40);
            Admin_Detail.add(New_Password_PField);
            JLabel temp = new JLabel("Is not Valid");
            Admin_Detail.add(temp);
            New_Password_PField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    Pattern p = Pattern.compile(
                            "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");
                    Matcher m = p.matcher(New_Password_PField.getText());
                    if (!m.matches()) {
                        temp.setBounds(220, 250, 70, 25);
                        temp.setVisible(true);
                        Submit_Button.setEnabled(false);
                    } else {
                        temp.setVisible(false);
                        Submit_Button.setEnabled(true);
                    }
                }
            });

            Submit_Button.setFont(new Font("Verdana", Font.PLAIN, 17));
            Submit_Button.setBackground(new Color(171, 222, 158));
            Submit_Button.setBorder(null);
            Submit_Button.setBounds(320, 300, 100, 40);
            Submit_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            Admin_Detail.add(Submit_Button);
            Submit_Button.addActionListener(e1 -> {
                if (New_Name_TField.getText().isEmpty() || New_Password_PField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Do Enter Something");
                } else {
                    int i = JOptionPane.showConfirmDialog(Admin_Detail, "Do Confirm ?");
                    if (i == 0) {
                        try {
                            PreparedStatement S = Login.getConnection()
                                    .prepareStatement("UPDATE `adam` SET `Name`=?,`Password`=?;");
                            S.setString(1, New_Name_TField.getText());
                            S.setString(2, New_Password_PField.getText());
                            S.executeUpdate();
                        } catch (Exception sql) {
                        }
                        Admin_Detail.dispose();
                    }
                }
            });

            Admin_Detail.getContentPane().setBackground(new Color(215, 247, 224));
            Admin_Detail.setSize(480, 550);
            Admin_Detail.setLocationRelativeTo(null);
            Admin_Detail.setLayout(null);
            Admin_Detail.setVisible(true);

        });

        Salary_Button = new JButton("Salary Details");
        Salary_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Salary_Button.setOpaque(true);
        Salary_Button.setBackground(new Color(171, 222, 158));
        Salary_Button.setBounds(1600, 800, 250, 60);
        Salary_Button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Salary_Button.addActionListener(e -> {
            dispose();
            SalaryDetails.getInstance();
        });

        Recp_details_Button = new JButton("Receptionist History");
        Recp_details_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Recp_details_Button.setBounds(50, 850, 250, 40);
        Recp_details_Button.setBackground(new Color(171, 222, 158));
        Recp_details_Button.addActionListener(e -> {
            JFrame f = new JFrame();
            JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
            Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
            Header_Label.setOpaque(true);
            Header_Label.setBackground(new Color(171, 222, 158));
            Header_Label.setBounds(0, 20, (int) ss.getWidth(), 90);

            JLabel Footer_Label = new JLabel("Receptionist History", JLabel.CENTER);
            Footer_Label.setOpaque(true);
            Footer_Label.setBackground(new Color(171, 222, 158));
            Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
            Footer_Label.setBounds(0, 150, (int) ss.getWidth(), 50);

            Pane = new JPanel();
            Pane.setBounds(200, 300, 1500, 600);
            Pane.setBackground(new Color(215, 247, 224));
            Pane.setLayout(new GridLayout());
            Table();

            f.getContentPane().setBackground(new Color(215, 247, 224));
            f.add(Header_Label);
            f.add(Footer_Label);
            f.add(Pane);
            f.setSize(1920, 1080);
            f.setLayout(null);
            f.setVisible(true);

        });

        Doc_details_Button = new JButton("Doctor History");
        Doc_details_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Doc_details_Button.setBounds(50, 910, 250, 40);
        Doc_details_Button.setBackground(new Color(171, 222, 158));
        Doc_details_Button.addActionListener(e -> {
            JFrame f = new JFrame();
            JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
            Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
            Header_Label.setOpaque(true);
            Header_Label.setBackground(new Color(171, 222, 158));
            Header_Label.setBounds(0, 20, (int) ss.getWidth(), 90);

            JLabel Footer_Label = new JLabel("Doctor History", JLabel.CENTER);
            Footer_Label.setOpaque(true);
            Footer_Label.setBackground(new Color(171, 222, 158));
            Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
            Footer_Label.setBounds(0, 150, (int) ss.getWidth(), 50);

            Pane1 = new JPanel();
            Pane1.setBounds(200, 300, 1500, 600);
            Pane1.setBackground(new Color(215, 247, 224));
            Pane1.setLayout(new GridLayout());
            Table1();

            f.getContentPane().setBackground(new Color(215, 247, 224));
            f.add(Header_Label);
            f.add(Footer_Label);
            f.add(Pane1);
            f.setSize(1920, 1080);
            f.setLayout(null);
            f.setVisible(true);
        });

        logout = new JButton("LOGOUT");
        logout.setFont(new Font("Verdana", Font.PLAIN, 18));
        logout.setOpaque(true);
        logout.setBackground(new Color(171, 222, 158));
        logout.setBounds(1600, 950, 250, 60);
        logout.addActionListener(this);

        getContentPane().setBackground(new Color(215, 247, 224));
        add(Doc_details_Button);
        add(Recp_details_Button);
        add(Header_Label);
        add(Footer_Label);
        add(Icon_Label_1);
        add(Icon_Label_2);
        add(Icon_Label_3);
        add(Doctor_Button);
        add(Receptionist_Button);
        add(Patient_Button);
        add(AdminDetail_Button);
        add(logout);
        add(Salary_Button);
        setSize((int) ss.getWidth(), (int) ss.getHeight());
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    Vector<String> Table() {
        Vector<String> column = new Vector<>();
        column.add("Recp_ID");
        column.add("F_name");
        column.add("L_name");
        column.add("Age");
        column.add("Phone");
        column.add("Gender");
        column.add("City");
        column.add("Address");
        column.add("Joining_Date");
        column.add("Password");
        column.add("Salary");
        column.add("Doctor_ID");
        column.add("Status");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            Statement S = Login.getConnection().createStatement();
            ResultSet rs = S.executeQuery("SELECT * FROM receptionist");
            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();
                temp.add(rs.getString("Recp_ID"));
                temp.add(rs.getString("F_name"));
                temp.add(rs.getString("L_name"));
                temp.add(rs.getString("Age"));
                temp.add(rs.getString("Phone"));
                temp.add(rs.getString("Gender"));
                temp.add(rs.getString("City"));
                temp.add(rs.getString("Address"));
                temp.add(rs.getString("Joining_Date"));
                temp.add("*******");
                temp.add(rs.getString("Salary"));
                temp.add(rs.getString("Doctor_ID"));
                if (rs.getInt("Status") == 1) {
                    temp.add("Yes");
                } else {
                    temp.add("No");
                }
                data.add(temp);

            }

        } catch (Exception sql) {
        }
        model = new DefaultTableModel(data, column);
        Table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table.setModel(model);
        Table.setAutoCreateRowSorter(true);
        Table.setBounds(400, 740, 1450, 250);
        Table.setFont(new Font("Verdana", Font.PLAIN, 15));
        Table.setRowHeight(30);
        Pane.add(new JScrollPane(Table));

        return column;
    }

    Vector<String> Table1() {
        Vector<String> column = new Vector<>();
        column.add("Doctor_ID");
        column.add("First_name");
        column.add("Second_name");
        column.add("Age");
        column.add("Blood_grp");
        column.add("Gender");
        column.add("Dept");
        column.add("Address");
        column.add("ph_no");
        column.add("City");
        column.add("Visiting Time");
        column.add("Visiting Days");
        column.add("username");
        column.add("Password");
        column.add("salary");
        column.add("Recp_ID");
        column.add("Status");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {

            Statement S = Login.getConnection().createStatement();
            ResultSet rs = S.executeQuery("SELECT * FROM doctor ORDER BY doctor_id;");
            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();

                temp.add(rs.getString("doctor_id"));
                temp.add(rs.getString("first_name"));
                temp.add(rs.getString("second_name"));
                temp.add(rs.getString("age"));
                temp.add(rs.getString("blood_grp"));
                temp.add(rs.getString("gender"));
                temp.add(rs.getString("dept"));
                temp.add(rs.getString("address"));
                temp.add(rs.getString("ph_no"));
                temp.add(rs.getString("city"));
                temp.add(rs.getString("visiting_time"));
                temp.add(rs.getString("days"));
                temp.add(rs.getString("username"));
                temp.add(rs.getString("password"));
                temp.add(rs.getString("salary"));
                temp.add(rs.getString("Recp_ID"));
                if (rs.getInt("Status") == 1) {
                    temp.add("Yes");
                } else {
                    temp.add("No");
                }
                data.add(temp);

            }

        } catch (Exception sql) {
        }
        model1 = new DefaultTableModel(data, column);
        Table1 = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table1.setModel(model1);
        Table1.setFont(new Font("Verdana", Font.PLAIN, 15));
        Table1.setRowHeight(30);

        Pane1.add(new JScrollPane(Table1));

        return column;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logout) {
            Login.getInstance();
            setVisible(false);
        }
    }

    public static Option getInstance() {
        return new Option();
    }

}