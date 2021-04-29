package module2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class SalaryDetails extends JFrame {

    JLabel Header_Label, Footer_Label;
    JTable Table, Table2;
    JPanel Pane, Pane2;
    JButton Receptionist, Doctor, Back;

    private SalaryDetails() {
        Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setOpaque(true);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setBounds(0, 20, 1920, 90);

        Footer_Label = new JLabel("Salary Details", JLabel.CENTER);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 24));
        Footer_Label.setOpaque(true);
        Footer_Label.setBackground(new Color(171, 222, 158));
        Footer_Label.setBounds(0, 150, 1920, 50);

        Receptionist = new JButton("Receptionist Salary");
        Receptionist.setFont(new Font("Verdana", Font.PLAIN, 17));
        Receptionist.setOpaque(true);
        Receptionist.setBackground(new Color(171, 222, 158));
        Receptionist.setBounds(240, 220, 200, 50);
        Receptionist.addActionListener(e -> {
            Pane2.setVisible(false);
            Pane.setVisible(true);
        });

        Doctor = new JButton("Doctor Salary");
        Doctor.setFont(new Font("Verdana", Font.PLAIN, 17));
        Doctor.setOpaque(true);
        Doctor.setBackground(new Color(171, 222, 158));
        Doctor.setBounds(500, 220, 200, 50);
        Doctor.addActionListener(e -> {
            Pane.setVisible(false);
            Pane2.setVisible(true);
        });

        Pane = new JPanel();
        Pane.setBounds(240, 300, 1420, 600);
        Pane.setLayout(new GridLayout());
        Pane.setVisible(false);
        Vector<String> Column = new Vector<>();
        Table = new JTable();
        Column.add("Receptionist ID");
        Column.add("First name");
        Column.add("Last name");
        Column.add("Age");
        Column.add("Salary");
        Vector<Vector<Object>> row = new Vector<Vector<Object>>();
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("Select * from receptionist WHERE `Status`=1 ORDER BY Recp_ID;");
            while (rs.next()) {
                Vector<Object> temp = new Vector<>();
                temp.add(rs.getString("Recp_ID"));
                temp.add(rs.getString("F_name"));
                temp.add(rs.getString("L_name"));
                temp.add(rs.getString("Age"));
                temp.add(rs.getString("Salary"));
                row.add(temp);
            }

        } catch (Exception e) {
        }
        DefaultTableModel model = new DefaultTableModel(row, Column);
        Table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                }
                return false;
            }
        };
        Table.setRowHeight(30);
        Table.setFont(new Font("Verdana", Font.PLAIN, 18));
        Table.setBounds(0, 0, 100, 100);
        Pane.add(new JScrollPane(Table));

        Pane2 = new JPanel();
        Pane2.setBounds(240, 300, 1420, 600);
        Pane2.setLayout(new GridLayout());
        Pane2.setVisible(false);
        Vector<String> Column2 = new Vector<>();
        Table2 = new JTable();
        Column2.add("Doctor ID");
        Column2.add("First name");
        Column2.add("Last name");
        Column2.add("Age");
        Column2.add("Salary");
        Vector<Vector<Object>> row2 = new Vector<Vector<Object>>();
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("Select * from doctor where `Status`=1 ORDER BY doctor_id; ");
            while (rs.next()) {
                Vector<Object> temp = new Vector<>();
                temp.add(rs.getString("doctor_id"));
                temp.add(rs.getString("first_name"));
                temp.add(rs.getString("second_name"));
                temp.add(rs.getInt("age"));
                temp.add(rs.getString("salary"));
                row2.add(temp);
            }

        } catch (Exception e) {
        }
        DefaultTableModel model2 = new DefaultTableModel(row2, Column2);
        Table2 = new JTable(model2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                }
                return false;
            }
        };
        Table2.setRowHeight(30);
        Table2.setFont(new Font("Verdana", Font.PLAIN, 18));
        Table2.setBounds(0, 0, 100, 100);
        Pane2.add(new JScrollPane(Table2));

        Back = new JButton("Back");
        Back.setFont(new Font("Verdana", Font.PLAIN, 18));
        Back.setBackground(new Color(171, 222, 158));
        Back.setBounds(1500, 220, 120, 40);
        Back.addActionListener(e -> {
            dispose();
            Option.getInstance();
        });

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                try {
                    PreparedStatement s1 = Login.getConnection()
                            .prepareStatement("UPDATE `receptionist` SET `Salary` =? WHERE `Recp_ID` = ?;");
                    s1.setString(1, String.valueOf(model.getValueAt(row, 4)));
                    s1.setString(2, String.valueOf(model.getValueAt(row, 0)));
                    s1.executeUpdate();
                }

                catch (Exception sql) {
                }
            }
        });

        model2.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                try {
                    PreparedStatement s1 = Login.getConnection()
                            .prepareStatement("UPDATE `doctor` SET `salary` =? WHERE `doctor_ID` = ?;");
                    s1.setString(1, String.valueOf(model2.getValueAt(row, 4)));
                    s1.setString(2, String.valueOf(model2.getValueAt(row, 0)));
                    s1.executeUpdate();
                }

                catch (Exception sql) {
                }
            }
        });

        add(Pane2);
        add(Back);
        add(Header_Label);
        add(Footer_Label);
        add(Pane);
        add(Receptionist);
        add(Doctor);
        getContentPane().setBackground(new Color(215, 247, 224));
        setSize(1920, 1080);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);

    }

    public static SalaryDetails getInstance() {
        return new SalaryDetails();
    }

}