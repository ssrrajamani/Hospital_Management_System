package module4;

import javax.swing.*;

import module2.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Fees {
    String id, Fees;

    private Fees() {

        JFrame Frame = new JFrame();
        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 130, 150, 40);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 15));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(1150, 220, 150, 40);

        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Search_Button.setBackground(new Color(171, 222, 158));

        JButton Confirm_Button = new JButton("CONFIRM");
        Confirm_Button.setBounds(900, 520, 150, 40);
        Confirm_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Confirm_Button.setBackground(new Color(171, 222, 158));

        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("Fees", JLabel.CENTER);
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

        JLabel Name_Label = new JLabel("NAME", JLabel.CENTER);
        Name_Label.setOpaque(true);
        Name_Label.setForeground(Color.BLACK);
        Name_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Name_Label.setBounds(560, 326, 300, 30);
        Name_Label.setBackground(new Color(215, 247, 224));

        JLabel Fees_Label = new JLabel("Fees", JLabel.CENTER);
        Fees_Label.setOpaque(true);
        Fees_Label.setForeground(Color.BLACK);
        Fees_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Fees_Label.setBounds(560, 426, 300, 30);
        Fees_Label.setBackground(new Color(215, 247, 224));

        JTextField Id_Field = new JTextField();
        Id_Field.setBounds(900, 220, 200, 40);
        Id_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Name_Field = new JTextField();
        Name_Field.setBounds(900, 320, 300, 40);
        Name_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Fees_Field = new JTextField();
        Fees_Field.setBounds(900, 420, 200, 40);
        Fees_Field.setFont(new Font("Verdana", Font.PLAIN, 20));

        Back_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s[] = {};
                Main.getInstance();
                Frame.setVisible(false);
            }
        });

        Search_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Id_Field.getText();
                try {
                    Name_Field.setText("");
                    Fees_Field.setText("");
                    PreparedStatement ps = Login.getConnection()
                            .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=(?) AND `Status`='Yes';");
                    ps.setString(1, Id_Field.getText());
                    ps.setString(2, Login.Doctor_Login_ID);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Name_Field.setText(rs.getString(1));
                        Fees_Field.setText(String.valueOf(rs.getInt(14)));
                    }
                    Fees = Fees_Field.getText();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        Confirm_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fees = Fees_Field.getText();
                try {
                    PreparedStatement ps = Login.getConnection()
                            .prepareStatement("UPDATE patient SET Fees=(?) WHERE ID=(?)");
                    ps.setString(1, Fees);
                    ps.setString(2, id);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(Frame, "SUCCESSFULL", "Success", JOptionPane.PLAIN_MESSAGE);
                    Id_Field.setText("");
                    Name_Field.setText("");
                    Fees_Field.setText("");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.add(Header_Label);
        Frame.add(Footer_Label);
        Frame.add(Back_Button);
        Frame.add(Id_Label);
        Frame.add(Name_Label);
        Frame.add(Search_Button);
        Frame.add(Id_Field);
        Frame.add(Name_Field);
        Frame.add(Fees_Label);
        Frame.add(Fees_Field);
        Frame.add(Confirm_Button);
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setDefaultCloseOperation(3);
        Frame.setVisible(true);
    }

    public static Fees getInstance() {
        return new Fees();
    }
}
