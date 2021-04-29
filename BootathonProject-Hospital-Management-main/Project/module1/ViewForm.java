package module1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import module2.Login;

public class ViewForm {
  DefaultTableModel model;
  JTable Table;
  JPanel Pane;

  private ViewForm() {
    JFrame viewframe = new JFrame();
    JLabel head_label = new JLabel("Hospital Management System", JLabel.CENTER);
    head_label.setOpaque(true);
    head_label.setForeground(Color.BLACK);
    head_label.setBackground(new Color(171, 222, 158));
    head_label.setFont(new Font("Verdana", Font.PLAIN, 30));
    head_label.setBounds(0, 20, 1920, 80);

    JLabel l2 = new JLabel("Physician Info", JLabel.CENTER);
    l2.setFont(new Font("Verdana", Font.PLAIN, 24));
    l2.setOpaque(true);
    l2.setBackground(new Color(171, 222, 158));
    l2.setBounds(0, 150, 1920, 50);
    viewframe.add(l2);
    // back_btn
    JButton back_btn = new JButton("Back");
    back_btn.setBounds(1620, 850, 200, 50);
    back_btn.setBackground(new Color(171, 222, 158));
    back_btn.setFont(new Font("Verdana", Font.PLAIN, 19));
    back_btn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        viewframe.dispose();
        DoctorMainPane.getInstance();
      }
    });
    // Pane
    Pane = new JPanel();
    Pane.setBounds(50, 300, 1800, 500);
    Pane.setBackground(new Color(215, 247, 224));
    Pane.setLayout(new GridLayout());
    Table();

    viewframe.add(head_label);
    viewframe.add(Pane);
    viewframe.setSize(1920, 1080);
    viewframe.getContentPane().setBackground(new Color(215, 247, 224));
    viewframe.add(head_label);
    viewframe.setDefaultCloseOperation(3);
    viewframe.add(back_btn);
    viewframe.setLayout(null);
    viewframe.setVisible(true);
  }

  Vector<String> Table() {
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

    Vector<Vector<String>> data = new Vector<Vector<String>>();
    try {
      Statement S = Login.getConnection().createStatement();
      ResultSet rs = S.executeQuery("SELECT * FROM doctor where `Status`=1;");
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
        temp.add("******");
        temp.add(rs.getString("salary"));
        temp.add(rs.getString("Recp_ID"));
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
    Table.setFont(new Font("Verdana", Font.PLAIN, 15));
    Table.setRowHeight(30);
    Pane.add(new JScrollPane(Table));
    return column;
  }

  public static ViewForm getInstance() {
    return new ViewForm();
  }

}
