package module1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import module2.Login;

public class DeleteForm {
    private DeleteForm() {
        JFrame deleteframe = new JFrame();
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
        deleteframe.add(l2);

        JPanel Panel = new JPanel();
        Panel.setBounds(250, 350, 1470, 500);
        Panel.setBackground(new Color(171, 222, 158));
        Panel.setLayout(null);
        ;
        deleteframe.add(Panel);
        // doc id
        JLabel doc_id_lbl = new JLabel("Doctor's id :");
        doc_id_lbl.setBounds(410, 150, 150, 50);
        doc_id_lbl.setFont(new Font("Verdana", Font.PLAIN, 21));

        JTextField doc_id_txtfield = new JTextField();
        doc_id_txtfield.setBounds(710, 150, 350, 60);
        doc_id_txtfield.setFont(new Font("Verdana", Font.PLAIN, 21));
        doc_id_txtfield.setBackground(Color.WHITE);
        doc_id_txtfield.setBorder(null);
        // back_btn
        JButton back_btn = new JButton("Back");
        back_btn.setBounds(1520, 250, 200, 60);
        back_btn.setBackground(new Color(171, 222, 158));
        back_btn.setFont(new Font("Verdana", Font.PLAIN, 19));
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteframe.dispose();
                DoctorMainPane.getInstance();
            }
        });
        // submit
        JButton submit_btn = new JButton("Delete");
        submit_btn.setBounds(913, 300, 150, 50);
        submit_btn.setBorder(null);
        submit_btn.setCursor(new Cursor(12));
        submit_btn.setBackground(new Color(215, 247, 224));
        submit_btn.setFont(new Font("Verdana", Font.PLAIN, 19));
        submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doc_id_txtfield.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(deleteframe, "Do Fill All Details", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    try {

                        String Str = doc_id_txtfield.getText();
                        Statement s = Login.getConnection().createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM doctor WHERE `doctor_id`='" + Str + "';");
                        String Reception = "";
                        if (rs.next()) {
                            Reception = rs.getString("Recp_ID");
                            String sql = "update `doctor` set `Status`=0 where `doctor_id`='"
                                    + doc_id_txtfield.getText() + "';";
                            PreparedStatement st = Login.getConnection().prepareStatement(sql);
                            st.executeUpdate();
                            JOptionPane.showMessageDialog(deleteframe, "Successfully Deleted.", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                            doc_id_txtfield.setText("");
                        } else {
                            JOptionPane.showMessageDialog(deleteframe, "ID not found", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        Statement s1 = Login.getConnection().createStatement();
                        s1.executeUpdate(
                                "UPDATE `receptionist` SET `Doctor_ID`='' where `Recp_ID`='" + Reception + "';");
                    } catch (Exception n) {
                        System.out.println(n);
                    }
                }
            }
        });

        // add elements
        deleteframe.add(back_btn);
        Panel.add(submit_btn);
        Panel.add(doc_id_lbl);
        Panel.add(doc_id_txtfield);
        deleteframe.add(head_label);
        deleteframe.setDefaultCloseOperation(3);
        deleteframe.setSize(1920, 1080);
        deleteframe.getContentPane().setBackground(new Color(215, 247, 224));
        deleteframe.add(head_label);
        deleteframe.setLayout(null);
        deleteframe.setVisible(true);
    }

    public static DeleteForm getInstance() {
        return new DeleteForm();
    }

}
