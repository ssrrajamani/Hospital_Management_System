package module4;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class TotalPatients {
    DefaultTableModel model;
    JTable tb;
    JPanel Pane = new JPanel();
    TableRowSorter rowSorter;
    JTextField Id_Field = new JTextField();

    private TotalPatients() {
        JFrame f = new JFrame();

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 130, 150, 40);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 15));
        Back_Button.setBackground(new Color(171, 222, 158));

        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("PATIENT'S DETAILS", JLabel.CENTER);
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

        Id_Field.setBounds(900, 220, 200, 40);
        Id_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        Pane.setBounds(200, 300, 1500, 600);
        Pane.setBackground(new Color(215, 247, 224));
        Pane.setLayout(new GridLayout());
        Table();

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s[] = {};
                Main.getInstance();
                f.setVisible(false);
            }
        });

        f.setSize(1920, 1080);
        f.getContentPane().setBackground(new Color(215, 247, 224));
        f.add(Header_Label);
        f.add(Footer_Label);
        f.add(Back_Button);
        f.add(Pane);
        f.add(Id_Label);
        f.add(Id_Field);
        f.setLayout(null);
        f.setResizable(true);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }

    Vector<String> Table() {
        Vector<String> column = new Vector<>();
        column.add("ID");
        column.add("NAME");
        column.add("AGE");
        column.add("GERDER");
        column.add("PHONE_NUMBER");
        column.add("ADDRESS");
        column.add("BLOOD GROUP");
        column.add("PROBLEM OF VISIT");
        column.add("LAST_APPOINTMENT");
        column.add("NEXT_APPOINTMENT");
        column.add("NO_OF_APPOINTMENT_CHANGES");
        column.add("FEES");
        column.add("DOCTOR");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            PreparedStatement ps = Login.getConnection().prepareStatement("SELECT * from patient WHERE doctor=?;");
            ps.setString(1, Login.Doctor_Login_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();
                temp.add(rs.getString("ID"));
                temp.add(rs.getString("NAME"));
                temp.add(String.valueOf(rs.getInt("AGE")));
                temp.add(rs.getString("Gender"));
                temp.add(rs.getString("PHONE_NUMBER"));
                temp.add(rs.getString("ADDRESS"));
                temp.add(rs.getString("BLOOD_GROUP"));
                temp.add(rs.getString("PROBLEM_OF_VISIT"));
                temp.add(String.valueOf(rs.getObject("LAST_APPOINTMENT")));
                temp.add(String.valueOf(rs.getObject("NEXT_APPOINTMENT")));
                temp.add(String.valueOf(rs.getInt("NO_OF_APPOINTMENT_CHANGES")));
                temp.add(String.valueOf(rs.getInt("FEES")));
                temp.add(rs.getString("Doctor"));
                data.add(temp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        model = new DefaultTableModel(data, column);
        tb = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tb.setColumnSelectionAllowed(false);
        tb.setRowSelectionAllowed(true);
        tb.setModel(model);
        tb.setBounds(500, 500, 400, 300);
        tb.setFont(new Font("Verdana", Font.PLAIN, 15));
        tb.setRowHeight(30);
        Pane.add(new JScrollPane(tb));
        rowSorter = new TableRowSorter<>(model);
        tb.setRowSorter(rowSorter);
        Id_Field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(Id_Field.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(Id_Field.getText());

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(Id_Field.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        return column;
    }

    public static TotalPatients getInstance() {
        return new TotalPatients();
    }

}
