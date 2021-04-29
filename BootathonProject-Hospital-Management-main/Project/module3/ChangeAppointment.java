package module3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import module2.Login;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ChangeAppointment {
    JTable tb;
    String s2, s, id;
    DefaultTableModel model;
    int val = 0;
    JPanel Pane = new JPanel();
    int a = 0;

    private ChangeAppointment() {
        JFrame Frame = new JFrame();
        JLabel Head_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(1300, 250, 200, 40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Search_Button.setBackground(new Color(171, 222, 158));
        JButton Save_Button = new JButton("SAVE");
        Save_Button.setBounds(650, 450, 200, 40);
        Save_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Save_Button.setBackground(new Color(171, 222, 158));
        JButton Cancel_Button = new JButton("CANCEL");
        Cancel_Button.setBounds(1050, 450, 200, 40);
        Cancel_Button.setFont(new Font("Verdana", Font.PLAIN, 20));
        Cancel_Button.setBackground(new Color(171, 222, 158));

        JTextField Search_Tfield = new JTextField();
        Search_Tfield.setBounds(650, 250, 600, 40);
        Search_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Date_Tfield = new JTextField();
        Date_Tfield.setBounds(720, 350, 200, 40);
        Date_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        
        JTextField Time_Tfield = new JTextField("HH:MM:SS");
        Time_Tfield.setBounds(980, 350, 200, 40);
        Time_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Time_Tfield.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent e) {
                Time_Tfield.setText("");
            }
        });

        JLabel Change_Appointment_Label = new JLabel("CHANGE APPOINTMENT", JLabel.CENTER);
        Change_Appointment_Label.setOpaque(true);
        Change_Appointment_Label.setForeground(Color.BLACK);
        Change_Appointment_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Change_Appointment_Label.setBounds(800, 120, 300, 30);
        Change_Appointment_Label.setBackground(new Color(215, 247, 224));

        Head_Label.setOpaque(true);
        Head_Label.setForeground(Color.BLACK);
        Head_Label.setBackground(new Color(171, 222, 158));
        Head_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Head_Label.setBounds(0, 20, 1920, 90);

        Pane.setBounds(500, 600, 1000, 200);
        Pane.setBackground(new Color(215, 247, 224));
        Pane.setLayout(new GridLayout());
        Table();

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                Frame.dispose();
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date_Tfield.setText("");
                s = Search_Tfield.getText();
                int n = s.length();
                if (n != 0) {
                    try {
                        PreparedStatement ps = Login.getConnection().prepareStatement(
                                "SELECT * from patient WHERE ID=(?) AND APPOINTMENT_CHANGE_REQUEST=(?) AND Doctor=?;");
                        ps.setString(1, s);
                        ps.setInt(2, 1);
                        ps.setString(3, Receptionist.Doc_Recp_ID);
                        ResultSet rs = ps.executeQuery();
                        String date = "";
                        while (rs.next()) {
                            date = rs.getString(10);
                            Date_Tfield.setText(date);
                            Time_Tfield.setText(String.valueOf(rs.getString("Time")));
                        }
                        s2 = Date_Tfield.getText();

                    } catch (Exception ex) {
                    }
                }
            }
        });
        Save_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(Frame, "Are you sure?");
                if (a == JOptionPane.YES_OPTION) {
                    if (Search_Tfield.getText().isEmpty() || Date_Tfield.getText().isEmpty() || Date_Tfield.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(Frame, "Fill All The Details", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                        a = 0;
                    }
                    s = Search_Tfield.getText();
                    String date = Date_Tfield.getText();
                    if (date.isEmpty()) {
                    } else {
                        try {
                            PreparedStatement ps = Login.getConnection().prepareStatement(
                                    "UPDATE patient SET NEXT_APPOINTMENT=?,APPOINTMENT_CHANGE_REQUEST=?,Time=? WHERE ID=? AND Doctor=?");
                            ps.setString(1, date);
                            ps.setString(3, Time_Tfield.getText());
                            ps.setString(4, s);
                            ps.setString(5, Receptionist.Doc_Recp_ID);

                            if (s2.equals(Date_Tfield.getText())) {
                                JOptionPane.showMessageDialog(Frame, "APPOINTMENT DATE NOT CHANGED", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                                a = 0;
                                ps.setInt(2, 1);
                            } else {
                                a = 1;
                                JOptionPane.showMessageDialog(Frame, "DATE CHANGED SUCCESSFULLY", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                                ps.setInt(2, 0);
                                model = (DefaultTableModel) tb.getModel();
                                for (int i = 0; i < model.getRowCount(); i++) {
                                    {
                                        if (String.valueOf(model.getValueAt(i, 0)).equals(s)) {
                                            model.removeRow(i);
                                        }
                                    }
                                }

                                try {
                                    PreparedStatement ps1 = Login.getConnection()
                                            .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=(?)");
                                    ps1.setString(1, s);
                                    ps1.setString(2, Receptionist.Doc_Recp_ID);
                                    ResultSet rs = ps1.executeQuery();
                                    while (rs.next()) {
                                        val = rs.getInt(11);
                                        val = val + 1;
                                    }

                                } catch (Exception eq) {
                                    System.out.println(eq);
                                }
                                update();
                                Search_Tfield.setText("");
                                Date_Tfield.setText("");
                                Time_Tfield.setText("");    

                            }
                            ps.executeUpdate();
                            ps.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        });
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Receptionist.getInstance();
                Frame.setVisible(false);
            }
        });
        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.add(Head_Label);
        Frame.add(Back_Button);
        Frame.add(Search_Tfield);
        Frame.add(Search_Button);
        Frame.add(Date_Tfield);
        Frame.add(Save_Button);
        Frame.add(Cancel_Button);
        Frame.add(Time_Tfield);
        Frame.add(Change_Appointment_Label);
        Frame.add(Pane);
        Frame.setLayout(null);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(3);
    }

    Vector<String> Table() {
        Vector<String> column = new Vector<>();
        column.add("ID");
        column.add("NAME");
        column.add("PHONE_NUMBER");
        column.add("LAST_APPOINTMENT");
        column.add("NEXT_APPOINTMENT");
        column.add("NO_OF_APPOINTMENT_CHANGES");
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try {
            Statement s = Login.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * from patient WHERE APPOINTMENT_CHANGE_REQUEST='1' AND Doctor='"
                    + Receptionist.Doc_Recp_ID + "';");

            while (rs.next()) {
                Vector<String> temp = new Vector<>();
                temp.clear();
                temp.add(rs.getString("ID"));
                temp.add(rs.getString("NAME"));
                temp.add(rs.getString("PHONE_NUMBER"));
                temp.add(String.valueOf(rs.getObject("LAST_APPOINTMENT")));
                ;
                temp.add(String.valueOf(rs.getObject("NEXT_APPOINTMENT")));
                temp.add(String.valueOf(rs.getInt("NO_OF_APPOINTMENT_CHANGES")));
                data.add(temp);
            }
        } catch (Exception ex) {
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

    public void update() {
        try {
            PreparedStatement ps = Login.getConnection()
                    .prepareStatement("UPDATE patient SET NO_OF_APPOINTMENT_CHANGES=(?) WHERE ID=(?) AND Doctor=?;");
            ps.setInt(1, val);
            ps.setString(2, s);
            ps.setString(3, Receptionist.Doc_Recp_ID);
            ps.executeUpdate();
        } catch (Exception ry) {
            System.out.println(ry);
        }
    }

    public static ChangeAppointment getInstance() {
        return new ChangeAppointment();
    }
    
}
