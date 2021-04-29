package module1;

import module2.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.*;
import javax.swing.*;

public class UpdateForm {
    JButton submit_btn;

    private UpdateForm() {
        JFrame updateframe = new JFrame();
        JLabel head_label = new JLabel("Hospital Management System", JLabel.CENTER);
        head_label.setOpaque(true);
        head_label.setForeground(Color.BLACK);
        head_label.setBackground(new Color(171, 222, 158));
        head_label.setFont(new Font("Verdana", Font.PLAIN, 30));
        head_label.setBounds(0, 20, 1920, 80);
        JLabel head_label2 = new JLabel("Physician Info", JLabel.CENTER);
        head_label2.setFont(new Font("Verdana", Font.PLAIN, 24));
        head_label2.setOpaque(true);
        head_label2.setBackground(new Color(171, 222, 158));
        head_label2.setBounds(0, 150, 1920, 50);
        updateframe.add(head_label2);
        // doc id
        JLabel doc_id_lbl = new JLabel("Doctor's id :");
        doc_id_lbl.setBounds(500, 370, 200, 50);
        doc_id_lbl.setFont(new Font("Verdana", Font.PLAIN, 21));

        JTextField doc_id_txtfield = new JTextField();
        doc_id_txtfield.setBounds(800, 370, 350, 50);
        doc_id_txtfield.setFont(new Font("Verdana", Font.PLAIN, 19));
        doc_id_txtfield.setBorder(null);
        doc_id_txtfield.setBorder(null);
        // update field
        JLabel field_lbl = new JLabel("Fields :");
        field_lbl.setBounds(500, 530, 200, 40);
        field_lbl.setFont(new Font("Verdana", Font.PLAIN, 21));
        String Field[] = { "first_name", "second_name", "age", "blood_grp", "gender", "dept", "address", "ph_no",
                "city", "visiting_time", "days", "username", "password", "salary", "Recp_ID" };

        JComboBox<String> field_combo = new JComboBox<>(Field);
        field_combo.setBounds(800, 530, 350, 40);
        field_combo.setFont(new Font("Verdana", Font.PLAIN, 18));
        // New update value
        JLabel update_lbl = new JLabel("Enter new value :");
        update_lbl.setFont(new Font("Verdana", Font.PLAIN, 21));
        update_lbl.setBounds(500, 680, 250, 55);
        JTextField update_txtfield = new JTextField();
        update_txtfield.setBounds(800, 680, 350, 55);
        update_txtfield.setFont(new Font("Verdana", Font.PLAIN, 19));
        update_txtfield.setBorder(null);
        JLabel temp = new JLabel("Is not Valid");

        update_txtfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                if (field_combo.getSelectedItem() == "password") {
                    Pattern p = Pattern.compile(
                            "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$");
                    Matcher m = p.matcher(update_txtfield.getText());
                    if (!m.matches()) {
                        temp.setBounds(800, 735, 70, 25);
                        temp.setVisible(true);
                        submit_btn.setEnabled(false);
                    } else {
                        temp.setVisible(false);
                        submit_btn.setEnabled(true);
                    }
                } else {
                    temp.setVisible(false);
                    submit_btn.setEnabled(true);
                }
            }
        });

        // submit_btn
        submit_btn = new JButton("Update");
        submit_btn.setBounds(1000, 830, 150, 40);
        submit_btn.setBackground(new Color(171, 222, 158));
        submit_btn.setFont(new Font("Verdana", Font.PLAIN, 19));
        // back_btn
        JButton back_btn = new JButton("Back");
        back_btn.setBounds(1520, 220, 140, 40);
        back_btn.setBackground(new Color(171, 222, 158));
        back_btn.setFont(new Font("Verdana", Font.PLAIN, 19));
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateframe.dispose();
                DoctorMainPane.getInstance();
            }
        });
        // Add elements
        updateframe.add(head_label);
        updateframe.add(doc_id_lbl);
        updateframe.add(doc_id_txtfield);
        updateframe.add(field_lbl);
        updateframe.add(field_combo);
        updateframe.add(submit_btn);
        updateframe.add(update_lbl);
        updateframe.add(update_txtfield);
        updateframe.add(back_btn);
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sql database for update
                try {
                    String Str = doc_id_txtfield.getText();
                    Statement s = Login.getConnection().createStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM doctor WHERE `doctor_id`='" + Str + "';");
                    if (rs.next()) {
                        String sql = "UPDATE  doctor set  " + field_combo.getSelectedItem() + "=' "
                                + update_txtfield.getText() + " ' where doctor_id='" + doc_id_txtfield.getText() + "';";
                        PreparedStatement st = Login.getConnection().prepareStatement(sql);
                        st.executeUpdate(sql);
                        JOptionPane.showMessageDialog(updateframe, "Successfully Updated", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog(updateframe, "ID not found", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                    if (String.valueOf(field_combo.getSelectedItem()).equals("Recp_ID")) {
                        Statement s1 = Login.getConnection().createStatement();
                        s1.executeUpdate("Update `receptionist` SET `Doctor_ID`='" + doc_id_txtfield.getText()
                                + "' where `Recp_ID`='" + update_txtfield.getText() + "';");
                    }
                    doc_id_txtfield.setText("");
                    update_txtfield.setText("");
                    field_combo.setSelectedItem("first_name");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(updateframe, "Not Sucessfully Updated", "ALERT",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        updateframe.setDefaultCloseOperation(3);
        updateframe.setSize(1920, 1080);
        updateframe.add(temp);
        updateframe.getContentPane().setBackground(new Color(215, 247, 224));
        updateframe.add(head_label);
        updateframe.setLayout(null);
        updateframe.setVisible(true);
    }

    public static UpdateForm getInstance() {
        return new UpdateForm();
    }
}
