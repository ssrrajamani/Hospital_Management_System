package module3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
public class DoctorsAvailability {
    DefaultTableModel model;
    JPanel pane = new JPanel();
    JTable Table = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private DoctorsAvailability() {
        JFrame Frame = new JFrame();

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));

        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Doctor_Availability_Label = new JLabel("DOCTOR'S AVAILABILITY", JLabel.CENTER);
        Doctor_Availability_Label.setOpaque(true);
        Doctor_Availability_Label.setForeground(Color.BLACK);
        Doctor_Availability_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Doctor_Availability_Label.setBounds(800, 120, 300, 30);
        Doctor_Availability_Label.setBackground(new Color(215, 247, 224));

        pane.setBounds(500, 200, 1000, 200);
        pane.setBackground(new Color(215, 247, 224));
        pane.setLayout(new GridLayout());
        Table();

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                Frame.setVisible(false);
            }
        });



        JLabel Search_Label = new JLabel("PEOPLE UNDER CARE");
        Search_Label.setFont(new Font("Verdana", Font.PLAIN, 23));
        Search_Label.setBounds(820, 440, 300, 30);

        JTextField Search_TField = new JTextField();
        Search_TField.setFont(new Font("Verdana", Font.PLAIN, 21));
        Search_TField.setBorder(null);
        Search_TField.setBounds(80, 430, 350, 50);

        JPanel Table_Panel = new JPanel();
        Table_Panel.setBounds(50, 530, 1800, 470);
        Table_Panel.setLayout(new GridLayout());
        Vector<String> Data = new Vector<>();
        Data.add("Name");
        Data.add("ID");
        Data.add("Age");
        Data.add("Address");
        Data.add("Blood Group");
        Data.add("Phone Number");
        Data.add("Problem Of Visit");
        Data.add("Last Appointment");
        Data.add("Next Appointment");
        Data.add("Next Appointment Time");
        Data.add("Doctor");
        Data.add("Status");
        Data.add("Fees");
        Vector<Vector<Object>> Column = new Vector<Vector<Object>>();
        try {
            Statement S = Login.getConnection().createStatement();
            ResultSet rs = S.executeQuery("select * from `patient` where `Doctor`='"+Login.Recp_Doc_ID+"';");

            while (rs.next()) {
                Vector<Object> temp = new Vector<>();
                temp.add(rs.getString("Name"));
                temp.add(rs.getString("ID"));
                temp.add(rs.getInt("Age"));
                temp.add(rs.getString("Address"));
                temp.add(rs.getString("Blood_Group"));
                temp.add(rs.getString("Phone_Number"));
                temp.add(rs.getString("Problem_Of_Visit"));
                temp.add(rs.getString("Last_Appointment"));
                temp.add(rs.getString("Next_Appointment"));
                temp.add(rs.getString("Time"));
                temp.add(rs.getString("Doctor"));
                temp.add(rs.getString("Status"));
                temp.add(rs.getInt("Fees"));
                Column.add(temp);
            }
        } catch (Exception e) {System.out.println(e);
        }

        DefaultTableModel model = new DefaultTableModel(Column, Data);
        Table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table.setModel(model);
        Table.setBounds(400, 740, 1450, 250);
        Table.setFont(new Font("Verdana", Font.PLAIN, 15));
        Table.setRowHeight(30);
        Table_Panel.add(new JScrollPane(Table));
        Table.setRowHeight(30);
        Table.setBorder(null);
        TableRowSorter rowSorter = new TableRowSorter<>(model);
        Table.setRowSorter(rowSorter);
        Search_TField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(Search_TField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(Search_TField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(Search_TField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        




        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.add(Header_Label);
        Frame.add(Doctor_Availability_Label);
        Frame.add(Back_Button);
        Frame.add(pane);
        Frame.add(Table_Panel);
        Frame.add(Search_Label);
        Frame.add(Search_TField);
        Frame.setLayout(null);
        Frame.setDefaultCloseOperation(3);
        Frame.setVisible(true);

    }

    Vector<String> Table() {
        Vector<String> column = new Vector<>();
        column.add("NAME");
        column.add("VISITING TIME");
        column.add("VISITING DAYS");
        column.add("DEPARTMENT");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * from doctor where `Status`=1;");
            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();
                temp.add(rs.getString(2) +" "+ rs.getString(3));
                temp.add(rs.getString("VISITING_TIME"));
                temp.add(rs.getString("days"));
                temp.add(rs.getString("dept"));
                data.add(temp);
            }
        } catch (Exception eq) {
        }
        model = new DefaultTableModel(data, column);
        Table.setModel(model);
        Table.setBounds(500, 500, 400, 100);
        Table.setFont(new Font("Verdana", Font.PLAIN, 15));
        Table.setRowHeight(30);
        pane.add(new JScrollPane(Table));

        return column;
    }

    public static DoctorsAvailability getInstance() {
        return new DoctorsAvailability();
    }
    
}
