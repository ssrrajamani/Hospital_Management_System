package module3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import jdk.internal.org.objectweb.asm.tree.analysis.Frame;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ConfirmChangeAppointment {

    JFrame Frame = new JFrame();
    DefaultTableModel model;
    JTable tb;
    JPanel Pane = new JPanel();
    String s, s1;
    JTextField ID_Tfield = new JTextField();

    private ConfirmChangeAppointment() {
        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(1150, 220, 150, 40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Search_Button.setBackground(new Color(171, 222, 158));
        JButton Confirm_Button = new JButton("CONFIRM");
        Confirm_Button.setBounds(900, 520, 100, 40);
        Confirm_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Confirm_Button.setBackground(new Color(171, 222, 158));

        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Confirm_Appointment_Change_Label = new JLabel("CONFIRM APPOINTMENT CHANGE", JLabel.CENTER);
        Confirm_Appointment_Change_Label.setOpaque(true);
        Confirm_Appointment_Change_Label.setForeground(Color.BLACK);
        Confirm_Appointment_Change_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Confirm_Appointment_Change_Label.setBounds(700, 120, 500, 30);
        Confirm_Appointment_Change_Label.setBackground(new Color(215, 247, 224));

        JLabel ID_Label = new JLabel("ENTER THE ID", JLabel.CENTER);
        ID_Label.setOpaque(true);
        ID_Label.setForeground(Color.BLACK);
        ID_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        ID_Label.setBounds(600, 226, 300, 30);
        ID_Label.setBackground(new Color(215, 247, 224));

        JLabel Name_Label = new JLabel("NAME", JLabel.CENTER);
        Name_Label.setOpaque(true);
        Name_Label.setForeground(Color.BLACK);
        Name_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Name_Label.setBounds(560, 326, 300, 30);
        Name_Label.setBackground(new Color(215, 247, 224));

        JLabel Appointment_Date_Label = new JLabel("APPOINTMENT", JLabel.CENTER);
        Appointment_Date_Label.setOpaque(true);
        Appointment_Date_Label.setForeground(Color.BLACK);
        Appointment_Date_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Date_Label.setBounds(560, 426, 300, 30);
        Appointment_Date_Label.setBackground(new Color(215, 247, 224));

        ID_Tfield.setBounds(900, 220, 200, 40);
        ID_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Name_Tfield = new JTextField();
        Name_Tfield.setBounds(900, 320, 300, 40);
        Name_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Appointment_Date_Tfield = new JTextField();
        Appointment_Date_Tfield.setBounds(900, 420, 200, 40);
        Appointment_Date_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Date_Tfield.setEditable(false);

        JTextField Appointment_Time_Tfield = new JTextField();
        Appointment_Time_Tfield.setBounds(1150, 420, 200, 40);
        Appointment_Time_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Appointment_Time_Tfield.setEditable(false);

        Pane.setBounds(500, 600, 1000, 200);
        Pane.setBackground(new Color(215, 247, 224));
        Pane.setLayout(new GridLayout());
        Table();

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                Frame.setVisible(false);
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s = ID_Tfield.getText();
                Name_Tfield.setText("");
                Appointment_Date_Tfield.setText("");
                try {
                    PreparedStatement ps = Login.getConnection().prepareStatement(
                            "SELECT * from patient WHERE ID=(?) AND INFORMED=(?) AND APPOINTMENT_CHANGE_REQUEST=(?) AND Doctor=?;");
                    ps.setString(1, ID_Tfield.getText());
                    ps.setInt(2, 0);
                    ps.setInt(3, 0);
                    ps.setString(4, Receptionist.Doc_Recp_ID);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Name_Tfield.setText(rs.getString(1));
                        Appointment_Date_Tfield.setText(rs.getString(10));
                        Appointment_Time_Tfield.setText(String.valueOf(rs.getString("Time")));
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        Confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(Frame, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    if ((Name_Tfield.getText().isEmpty()) || (Appointment_Date_Tfield.getText().isEmpty())
                            || Appointment_Time_Tfield.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(Frame, "INVALID", "Alert", JOptionPane.WARNING_MESSAGE);

                    } else {
                        Name_Tfield.setText("");
                        Appointment_Date_Tfield.setText("");
                        Appointment_Time_Tfield.setText("");
                        confirm();
                        model = (DefaultTableModel) tb.getModel();
                        for (int i = 0; i < model.getRowCount(); i++) {
                            {
                                if (String.valueOf(model.getValueAt(i, 0)).equals(s1)) {
                                    model.removeRow(i);

                                }
                            }
                        }
                    }
                }
            }
        });

        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(3);
        Frame.add(Header_Label);
        Frame.add(Back_Button);
        Frame.add(Confirm_Appointment_Change_Label);
        Frame.add(Pane);
        Frame.add(ID_Label);
        Frame.add(ID_Tfield);
        Frame.add(Search_Button);
        Frame.add(Name_Label);
        Frame.add(Name_Tfield);
        Frame.add(Appointment_Date_Tfield);
        Frame.add(Appointment_Time_Tfield);
        Frame.add(Appointment_Date_Label);
        Frame.add(Confirm_Button);

    }

    Vector<String> Table() {
        Vector<String> column = new Vector<>();
        column.add("ID");
        column.add("NAME");
        column.add("PHONE_NUMBER");
        column.add("LAST_APPOINTMENT");
        column.add("NEXT_APPOINTMENT");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT * from patient WHERE  INFORMED='0' AND APPOINTMENT_CHANGE_REQUEST='0' AND Doctor='"
                            + Receptionist.Doc_Recp_ID + "';");
            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();
                temp.add(rs.getString("ID"));
                temp.add(rs.getString("NAME"));
                temp.add(rs.getString("PHONE_NUMBER"));
                temp.add(String.valueOf(rs.getObject("LAST_APPOINTMENT")));
                temp.add(String.valueOf(rs.getObject("NEXT_APPOINTMENT")));
                data.add(temp);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        model = new DefaultTableModel(data, column);
        tb = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tb.setModel(model);
        tb.setBounds(500, 500, 400, 300);
        tb.setFont(new Font("Verdana", Font.PLAIN, 15));
        tb.setRowHeight(30);
        Pane.add(new JScrollPane(tb));

        return column;
    }

    public void confirm() {
        s1 = ID_Tfield.getText();
        try {
            PreparedStatement ps = Login.getConnection()
                    .prepareStatement("UPDATE patient SET INFORMED=(?)  WHERE ID=(?) AND Doctor=?");
            ps.setInt(1, 1);
            ps.setString(3, Receptionist.Doc_Recp_ID);
            ps.setString(2, s1);
            ps.executeUpdate();

        } catch (Exception ec) {
            System.out.println(ec);
        }
    }

    public static ConfirmChangeAppointment getInstance() {
        return new ConfirmChangeAppointment();
    }

}
