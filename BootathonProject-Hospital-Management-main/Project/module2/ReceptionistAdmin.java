package module2;

import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ReceptionistAdmin extends JFrame {
    JLabel Header_Label, Footer_Label, Receptionist_ID_Label, First_Name_Label, Last_Name_Label, Age_Label,
            Gender_Label, Phone_Label, City_Label, Address_Label, Password_Label, Joining_Date_Label, Salary_Label,
            Doctor_ID_Label;
    JTextField Receptionist_ID_TField, First_Name_TField, Last_Name_TField, Age_TField, Phone_TField,
            Joining_Date_TField, salary_TField;
    JComboBox<String> Gender_Combo, City_Combo, Doctor_ID_TField;
    JTextArea Address_TArea;
    JScrollPane Scrollbar;
    JPasswordField Password_TField;
    JTable Table;
    JPanel Pane;
    JButton Update_Button, Remove_Button, Add_Button, Search_Button, Back, Generate_Button;
    ArrayList<String> Search = new ArrayList<>();
    DefaultTableModel model;
    String s, doc_ID;

    private ReceptionistAdmin() {
        Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
        Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setOpaque(true);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setBounds(0, 20, (int) ss.getWidth(), 90);

        Footer_Label = new JLabel(" Front Desk Executive Info", JLabel.CENTER);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setOpaque(true);
        Footer_Label.setBackground(new Color(171, 222, 158));
        Footer_Label.setBounds(0, 150, (int) ss.getWidth(), 50);

        Receptionist_ID_Label = new JLabel("Receptionist ID :");
        Receptionist_ID_Label.setFont(new Font("Verdana", Font.PLAIN, 23));
        Receptionist_ID_Label.setBounds(300, 230, 200, 50);
        Receptionist_ID_TField = new JTextField();
        Receptionist_ID_TField.setFont(new Font("Verdana", Font.PLAIN, 21));
        Receptionist_ID_TField.setBorder(null);
        Receptionist_ID_TField.setBounds(600, 230, 350, 50);
        First_Name_Label = new JLabel("First Name :");
        First_Name_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        First_Name_Label.setBounds(70, 380, 150, 40);
        Last_Name_Label = new JLabel("Last Name :");
        Last_Name_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Last_Name_Label.setBounds(750, 380, 150, 40);
        First_Name_TField = new JTextField();
        First_Name_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        First_Name_TField.setBounds(280, 380, 290, 40);
        First_Name_TField.setBorder(null);
        Last_Name_TField = new JTextField();
        Last_Name_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Last_Name_TField.setBounds(900, 380, 290, 40);
        Last_Name_TField.setBorder(null);

        Joining_Date_Label = new JLabel("Joining Date :");
        Joining_Date_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Joining_Date_Label.setBounds(1300, 250, 150, 40);
        Joining_Date_Label.setBorder(null);
        Joining_Date_TField = new JTextField(String.valueOf(LocalDate.now()));
        Joining_Date_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Joining_Date_TField.setBounds(1500, 250, 260, 40);
        Joining_Date_TField.setBorder(null);
        Joining_Date_TField.setEditable(false);

        Age_Label = new JLabel("Age :");
        Age_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Age_Label.setBounds(70, 500, 150, 40);
        Age_Label.setBorder(null);
        Age_TField = new JTextField();
        Age_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Age_TField.setBounds(280, 500, 290, 40);
        Age_TField.setBorder(null);
        Age_TField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                try {
                    Integer.parseInt(Age_TField.getText());

                } catch (Exception ex) {
                    Age_TField.setText("");
                }

            }

        });

        Gender_Label = new JLabel("Gender :");
        Gender_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Gender_Label.setBounds(750, 500, 290, 40);
        Gender_Label.setBorder(null);

        String array[] = { "Male", "Female", "Others" };
        Gender_Combo = new JComboBox<>(array);
        Gender_Combo.setFont(new Font("Verdana", Font.PLAIN, 19));
        Gender_Combo.setBounds(900, 500, 290, 40);
        Gender_Combo.setBorder(null);
        Gender_Combo.getModel().setSelectedItem("Select Gender");

        Phone_Label = new JLabel("Phone :");
        Phone_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Phone_Label.setBounds(70, 620, 150, 40);
        Phone_Label.setBorder(null);

        Phone_TField = new JTextField();
        Phone_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Phone_TField.setBounds(280, 620, 290, 40);
        Phone_TField.setBorder(null);
        Phone_TField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String v = Phone_TField.getText();
                try {
                    Long.parseLong(v);

                } catch (Exception ex) {
                    Phone_TField.setText("");
                    System.out.println(ex);
                }
                if (Phone_TField.getText().length() != 10) {
                    Update_Button.setEnabled(false);
                    Add_Button.setEnabled(false);
                } else if (Phone_TField.getText().length() == 10 || Phone_TField.getText().isEmpty()) {
                    Update_Button.setEnabled(true);
                    Add_Button.setEnabled(true);
                }
            }
        });

        City_Label = new JLabel("City :");
        City_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        City_Label.setBounds(750, 620, 290, 40);
        City_Label.setBorder(null);

        String array2[] = { "Ariyalur", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri", "Dindigul", "Erode",
                "Kanchipuram", "Kanyakumari", "Karur", "Madurai", "Nagapattinam", "Nilgiris", "Namakkal", "Perambalur",
                "Pudukkottai", "Ramanathapuram", "Salem", "Sivaganga", "Tirupur", "Tiruchirappalli", "Theni",
                "Tirunelveli", "Thanjavur", "Thoothukudi", "Tiruvallur", "Tiruvarur", "Tiruvannamalai", "Vellore",
                "Viluppuram", "Virudhunagar" };

        City_Combo = new JComboBox<>(array2);
        City_Combo.setFont(new Font("Verdana", Font.PLAIN, 19));
        City_Combo.setBounds(900, 620, 290, 40);
        City_Combo.setBorder(null);
        City_Combo.getModel().setSelectedItem("Select City");

        Address_Label = new JLabel("Address :");
        Address_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Address_Label.setBounds(1300, 380, 130, 40);
        Address_Label.setBorder(null);

        Address_TArea = new JTextArea();
        Address_TArea.setFont(new Font("Verdana", Font.PLAIN, 19));
        Address_TArea.setBorder(null);
        Scrollbar = new JScrollPane(Address_TArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scrollbar.setBorder(null);
        Scrollbar.getViewport().setBackground(Color.WHITE);
        Scrollbar.setBounds(1440, 380, 400, 190);

        Password_Label = new JLabel("Password :");
        Password_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Password_Label.setBounds(1300, 620, 290, 40);
        Password_Label.setBorder(null);

        Password_TField = new JPasswordField();
        Password_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Password_TField.setBounds(1440, 620, 290, 40);
        Password_TField.setBorder(null);

        JLabel temp = new JLabel("Is not Valid");

        Password_TField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
                Matcher m = p.matcher(Password_TField.getText());
                if (!m.matches()) {
                    temp.setBounds(1740, 630, 70, 25);
                    temp.setVisible(true);
                    Add_Button.setEnabled(false);
                    Update_Button.setEnabled(false);
                } else {
                    temp.setVisible(false);
                    Add_Button.setEnabled(true);
                    Update_Button.setEnabled(true);
                }

            }
        });

        Salary_Label = new JLabel("Salary :");
        Salary_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Salary_Label.setBounds(1300, 680, 290, 40);
        Salary_Label.setBorder(null);

        salary_TField = new JTextField();
        salary_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        salary_TField.setBounds(1440, 680, 290, 40);
        salary_TField.setBorder(null);

        Doctor_ID_Label = new JLabel("Doctor :");
        Doctor_ID_Label.setFont(new Font("Verdana", Font.PLAIN, 19));
        Doctor_ID_Label.setBounds(70, 680, 290, 40);
        Doctor_ID_Label.setBorder(null);

        Vector<String> Doctor = new Vector<>();
        Doctor.add("");
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("Select * from `doctor` where `Recp_ID`='' AND `Status`=1;");
            while (rs.next()) {
                Doctor.add(rs.getString("doctor_id"));
            }
        } catch (Exception sql) {
        }

        Doctor_ID_TField = new JComboBox<>(Doctor);
        Doctor_ID_TField.setFont(new Font("Verdana", Font.PLAIN, 19));
        Doctor_ID_TField.setBounds(280, 680, 290, 40);
        Doctor_ID_TField.setBorder(null);
        Doctor_ID_TField.getModel().setSelectedItem("Doctor");

        Pane = new JPanel();
        Pane.setBounds(400, 760, 1450, 250);
        Pane.setBackground(new Color(215, 247, 224));
        Pane.setLayout(new GridLayout());
        Vector<String> column = Table();

        Update_Button = new JButton("Update RECPTIONIST");
        Update_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Update_Button.setBounds(50, 770, 250, 60);
        Update_Button.setBackground(new Color(171, 222, 158));
        Update_Button.addActionListener(e -> {
            int i = 0;

            if (JOptionPane.showConfirmDialog(this, "Do You Want To Update") == 0) {

                for (i = 0; i <= model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(Receptionist_ID_TField.getText())) {
                        break;
                    }
                }
                model.removeRow(i);
                model.insertRow(i, new String[] { Receptionist_ID_TField.getText(), First_Name_TField.getText(),
                        Last_Name_TField.getText(), Age_TField.getText(), Phone_TField.getText(),
                        String.valueOf(Gender_Combo.getSelectedItem()), String.valueOf(City_Combo.getSelectedItem()),
                        Address_TArea.getText(), Joining_Date_TField.getText(), "*******", salary_TField.getText(),
                        String.valueOf(Doctor_ID_TField.getSelectedItem()) });
                try {
                    PreparedStatement s = Login.getConnection().prepareStatement(
                            "UPDATE `receptionist` SET `F_name`=?,`L_name`=?,`Age`=?,`Phone`=?,`Gender`=?,`City`=?,`Address`=?,`Password`=?, `Joining_Date`=? ,`Salary`=?,`Doctor_ID`=? where Recp_ID='"
                                    + Receptionist_ID_TField.getText() + "';");
                    s.setString(1, First_Name_TField.getText());
                    s.setString(2, Last_Name_TField.getText());
                    s.setString(3, Age_TField.getText());
                    s.setString(4, Phone_TField.getText());
                    s.setString(5, String.valueOf(Gender_Combo.getSelectedItem()));
                    s.setString(6, String.valueOf(City_Combo.getSelectedItem()));
                    s.setString(7, Address_TArea.getText());
                    s.setString(9, Joining_Date_TField.getText());
                    s.setString(8, Password_TField.getText());
                    s.setString(10, salary_TField.getText());
                    s.setString(11, String.valueOf(Doctor_ID_TField.getSelectedItem()));
                    s.executeUpdate();

                    Statement s1 = Login.getConnection().createStatement();
                    if (String.valueOf(Doctor_ID_TField.getSelectedItem()).isEmpty()) {
                        s1.executeUpdate("Update `doctor` SET `Recp_ID`='' where `doctor_id`='" + doc_ID + "';");
                    } else {
                        s1.executeUpdate("Update `doctor` SET `Recp_ID`='" + Receptionist_ID_TField.getText()
                                + "' where `doctor_id`='" + String.valueOf(Doctor_ID_TField.getSelectedItem()) + "';");
                    }

                } catch (Exception sql) {
                }
            }
        });

        Remove_Button = new JButton("REMOVE RECPTIONIST");
        Remove_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Remove_Button.setBounds(50, 860, 250, 60);
        Remove_Button.setBackground(new Color(171, 222, 158));
        Remove_Button.addActionListener(e -> {
            JLabel current = new JLabel("Are You Sure....Do You Want To Remove ? ");
            current.setFont(new Font("Verdana", Font.PLAIN, 18));
            if (JOptionPane.showConfirmDialog(this, current) == 0) {
                int i = 0;
                for (i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(Receptionist_ID_TField.getText())) {
                        model.removeRow(i);
                    }
                }
                try {
                    String doc = String.valueOf(Doctor_ID_TField.getSelectedItem());
                    Statement remove = Login.getConnection().createStatement();
                    remove.executeUpdate("update receptionist set `Status`=0 where Recp_ID='"
                            + Receptionist_ID_TField.getText() + "';");

                    Statement s = Login.getConnection().createStatement();
                    s.executeUpdate("Update `doctor` set `Recp_ID`='' where `doctor_id`='" + doc + "';");
                } catch (Exception e1) {
                }
            }
        });

        Add_Button = new JButton("ADD RECPTIONIST");
        Add_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Add_Button.setBounds(50, 950, 250, 60);
        Add_Button.setBackground(new Color(171, 222, 158));
        Add_Button.addActionListener(e -> {
            JLabel current = new JLabel("Are You Sure....Do You Want To Add ? ");
            current.setFont(new Font("Verdana", Font.PLAIN, 18));
            if (JOptionPane.showConfirmDialog(this, current) == 0) {
                if (Receptionist_ID_TField.getText().isEmpty() || First_Name_TField.getText().isEmpty()
                        || Last_Name_TField.getText().isEmpty() || Age_TField.getText().isEmpty()
                        || Phone_TField.getText().isEmpty() || City_Combo.getSelectedItem() == "Select City"
                        || Gender_Combo.getSelectedItem() == "Select Gender" || Address_TArea.getText().isEmpty()
                        || Password_TField.getText().isEmpty()) {
                } else {
                    try {
                        PreparedStatement s = Login.getConnection()
                                .prepareStatement("Insert into receptionist values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
                        s.setString(1, Receptionist_ID_TField.getText());
                        s.setString(2, First_Name_TField.getText());
                        s.setString(3, Last_Name_TField.getText());
                        s.setString(4, Age_TField.getText());
                        s.setString(5, Phone_TField.getText());
                        s.setString(6, String.valueOf(Gender_Combo.getSelectedItem()));
                        s.setString(7, String.valueOf(City_Combo.getSelectedItem()));
                        s.setString(8, Address_TArea.getText());
                        s.setString(10, Joining_Date_TField.getText());
                        s.setString(9, Password_TField.getText());
                        s.setString(11, salary_TField.getText());
                        s.setInt(13, 1);
                        if (Doctor_ID_TField.getSelectedItem().equals("Doctor")) {
                            s.setString(12, "");
                        } else {
                            s.setString(12, String.valueOf(Doctor_ID_TField.getSelectedItem()));
                        }
                        s.executeUpdate();

                        Statement s1 = Login.getConnection().createStatement();
                        s1.executeUpdate("UPDATE `doctor` SET `Recp_ID`='" + Receptionist_ID_TField.getText()
                                + "' where doctor_id='" + String.valueOf(Doctor_ID_TField.getSelectedItem()) + "';");
                    } catch (Exception sql) {
                        System.out.print(sql);
                    }

                    model.addRow(new String[] { Receptionist_ID_TField.getText(), First_Name_TField.getText(),
                            Last_Name_TField.getText(), Age_TField.getText(), Phone_TField.getText(),
                            String.valueOf(Gender_Combo.getSelectedItem()),
                            String.valueOf(City_Combo.getSelectedItem()), Address_TArea.getText(),
                            Joining_Date_TField.getText(), "*******", salary_TField.getText(),
                            String.valueOf(Doctor_ID_TField.getSelectedItem()) });

                }
            }
        });

        Search_Button = new JButton("Search");
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Search_Button.setBounds(1050, 235, 180, 40);
        Search_Button.setBackground(new Color(171, 222, 158));
        Search_Button.addActionListener(e -> {
            if (!Receptionist_ID_TField.getText().isEmpty()) {
                Update_Button.setEnabled(true);
                Add_Button.setEnabled(true);
                temp.setVisible(false);

                Search.clear();
                try {
                    String Str = Receptionist_ID_TField.getText();
                    Statement S = Login.getConnection().createStatement();
                    ResultSet rs = S
                            .executeQuery("SELECT * FROM `receptionist` WHERE Recp_ID='" + Str + "' AND `Status`=1;");
                    while (rs.next()) {

                        for (String i : column) {
                            Search.add(rs.getString(i));
                        }
                        s = rs.getString("Doctor_ID");
                    }

                    First_Name_TField.setText(Search.get(1));
                    Last_Name_TField.setText(Search.get(2));
                    Age_TField.setText(Search.get(3));
                    Gender_Combo.getModel().setSelectedItem(Search.get(5));
                    Phone_TField.setText(Search.get(4));
                    City_Combo.getModel().setSelectedItem(Search.get(6));
                    Address_TArea.setText(Search.get(7));
                    Password_TField.setText(Search.get(9));
                    Joining_Date_TField.setText(Search.get(8));
                    salary_TField.setText(Search.get(10));
                    Doctor_ID_TField.getModel().setSelectedItem(s);

                    doc_ID = String.valueOf(Doctor_ID_TField.getSelectedItem());
                } catch (Exception sql) {
                    First_Name_TField.setText("");
                    Last_Name_TField.setText("");
                    Age_TField.setText("");
                    Gender_Combo.getModel().setSelectedItem("Select Gender");
                    Phone_TField.setText("");
                    City_Combo.getModel().setSelectedItem("Select City");
                    Address_TArea.setText("");
                    Password_TField.setText("");
                    Joining_Date_TField.setText(String.valueOf(LocalDate.now()));
                    Doctor_ID_TField.getModel().setSelectedItem("Doctor");

                }
            } else {
                First_Name_TField.setText("");
                Last_Name_TField.setText("");
                Age_TField.setText("");
                Gender_Combo.getModel().setSelectedItem("Select Gender");
                Phone_TField.setText("");
                City_Combo.getModel().setSelectedItem("Select City");
                Address_TArea.setText("");
                Password_TField.setText("");
                Joining_Date_TField.setText(String.valueOf(LocalDate.now()));
                Doctor_ID_TField.getModel().setSelectedItem("Doctor");
            }
        });

        Generate_Button = new JButton("Fetch");
        Generate_Button.setFont(new Font("Verdana", Font.PLAIN, 18));
        Generate_Button.setBounds(790, 290, 160, 40);
        Generate_Button.setBackground(new Color(171, 222, 158));
        Generate_Button.addActionListener(e -> {
            try {
                Statement s = Login.getConnection().createStatement();
                ResultSet rs = s.executeQuery("select *from receptionist ORDER BY `Recp_ID` DESC LIMIT 1;");
                String str = "";
                if (rs.next()) {
                    str = rs.getString("Recp_ID");
                }
                if(str.isEmpty()){Receptionist_ID_TField.setText("R_ID_001");}
                else{
                String out = String.format("%03d", Integer.parseInt(str.substring(5)) + 1);
                out = "R_ID_" + out;
                Receptionist_ID_TField.setText(out);}
            } catch (Exception sql) {
            }
        });

        Back = new JButton("Back");
        Back.setFont(new Font("Verdana", Font.PLAIN, 18));
        Back.setBackground(new Color(171, 222, 158));
        Back.setBounds(70, 230, 120, 40);
        Back.addActionListener(e -> {
            setVisible(false);
            Option.getInstance();
        });
        add(Header_Label);
        add(Footer_Label);
        add(Receptionist_ID_Label);
        add(Receptionist_ID_TField);
        add(First_Name_Label);
        add(Last_Name_Label);
        add(Joining_Date_Label);
        add(Joining_Date_TField);
        add(First_Name_TField);
        add(Last_Name_TField);
        add(Age_Label);
        add(Age_TField);
        add(Gender_Label);
        add(Gender_Combo);
        add(Phone_Label);
        add(Phone_TField);
        add(City_Label);
        add(City_Combo);
        add(Address_Label);
        add(Scrollbar);
        add(Password_Label);
        add(Password_TField);
        add(Pane);
        add(Update_Button);
        add(Add_Button);
        add(Remove_Button);
        add(Search_Button);
        add(Back);
        add(Salary_Label);
        add(salary_TField);
        add(Doctor_ID_Label);
        add(Doctor_ID_TField);
        getContentPane().setBackground(new Color(215, 247, 224));
        setSize((int) ss.getWidth(), (int) ss.getHeight());
        add(temp);
        add(Generate_Button);
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
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            Statement S = Login.getConnection().createStatement();
            ResultSet rs = S.executeQuery("SELECT * FROM receptionist where `Status`=1;");
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

    public static ReceptionistAdmin getInstance() {
        return new ReceptionistAdmin();
    }

}