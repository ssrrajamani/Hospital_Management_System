package module3;

import javax.swing.*;
import module2.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FeePayment {
    String id;
    int paid;

    private FeePayment() {
        JFrame Frame = new JFrame();

        JButton Back_Button = new JButton("BACK");
        Back_Button.setBounds(20, 120, 100, 30);
        Back_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Back_Button.setBackground(new Color(171, 222, 158));
        JButton Search_Button = new JButton("SEARCH");
        Search_Button.setBounds(1150, 220, 150, 40);
        Search_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Search_Button.setBackground(new Color(171, 222, 158));
        JButton Pay_Button = new JButton("PAY");
        Pay_Button.setBounds(900, 620, 150, 40);
        Pay_Button.setFont(new Font("Verdana", Font.PLAIN, 15));
        Pay_Button.setBackground(new Color(171, 222, 158));
        JButton Check_Balance_Button = new JButton("CHECK BALANCE");
        Check_Balance_Button.setBounds(1500, 520, 150, 40);
        Check_Balance_Button.setFont(new Font("Verdana", Font.PLAIN, 11));
        Check_Balance_Button.setBackground(new Color(171, 222, 158));

        JLabel Header_Label = new JLabel("Hospital Management System", JLabel.CENTER);
        Header_Label.setOpaque(true);
        Header_Label.setForeground(Color.BLACK);
        Header_Label.setBackground(new Color(171, 222, 158));
        Header_Label.setFont(new Font("Verdana", Font.PLAIN, 30));
        Header_Label.setBounds(0, 20, 1920, 90);

        JLabel Footer_Label = new JLabel("FEE PAYMENT", JLabel.CENTER);
        Footer_Label.setOpaque(true);
        Footer_Label.setForeground(Color.BLACK);
        Footer_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Footer_Label.setBounds(800, 120, 300, 30);
        Footer_Label.setBackground(new Color(215, 247, 224));

        JLabel Enter_ID_Label = new JLabel("ENTER THE ID", JLabel.CENTER);
        Enter_ID_Label.setOpaque(true);
        Enter_ID_Label.setForeground(Color.BLACK);
        Enter_ID_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Enter_ID_Label.setBounds(600, 226, 300, 30);
        Enter_ID_Label.setBackground(new Color(215, 247, 224));

        JLabel Name_Label = new JLabel("NAME", JLabel.CENTER);
        Name_Label.setOpaque(true);
        Name_Label.setForeground(Color.BLACK);
        Name_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Name_Label.setBounds(560, 326, 300, 30);
        Name_Label.setBackground(new Color(215, 247, 224));

        JLabel Fees_Label = new JLabel("FEES", JLabel.CENTER);
        Fees_Label.setOpaque(true);
        Fees_Label.setForeground(Color.BLACK);
        Fees_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Fees_Label.setBounds(560, 426, 300, 30);
        Fees_Label.setBackground(new Color(215, 247, 224));

        JLabel Amount_Paid_Label = new JLabel("AMOUNT PAID", JLabel.CENTER);
        Amount_Paid_Label.setOpaque(true);
        Amount_Paid_Label.setForeground(Color.BLACK);
        Amount_Paid_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Amount_Paid_Label.setBounds(400, 526, 300, 30);
        Amount_Paid_Label.setBackground(new Color(215, 247, 224));

        JLabel Change_Label = new JLabel("CHANGE", JLabel.CENTER);
        Change_Label.setOpaque(true);
        Change_Label.setForeground(Color.BLACK);
        Change_Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        Change_Label.setBounds(930, 526, 200, 30);
        Change_Label.setBackground(new Color(215, 247, 224));

        JTextField ID_Tfield = new JTextField();
        ID_Tfield.setBounds(900, 220, 200, 40);
        ID_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Name_Tfield = new JTextField();
        Name_Tfield.setBounds(900, 320, 300, 40);
        Name_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Fees_Tfield = new JTextField();
        Fees_Tfield.setBounds(900, 420, 200, 40);
        Fees_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Fees_Tfield.setEditable(false);

        JTextField Amount_Paid_Tfield = new JTextField();
        Amount_Paid_Tfield.setBounds(730, 520, 200, 40);
        Amount_Paid_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));

        JTextField Change_Tfield = new JTextField();
        Change_Tfield.setBounds(1200, 520, 200, 40);
        Change_Tfield.setFont(new Font("Verdana", Font.PLAIN, 20));
        Change_Tfield.setEditable(false);

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
                id = ID_Tfield.getText();
                try {
                    Name_Tfield.setText("");
                    Fees_Tfield.setText("");
                    Amount_Paid_Tfield.setText("");
                    Change_Tfield.setText("");

                    PreparedStatement ps = Login.getConnection()
                            .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=?");
                    ps.setString(1, ID_Tfield.getText());
                    ps.setString(2, Receptionist.Doc_Recp_ID);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        Name_Tfield.setText(rs.getString(1));
                        Fees_Tfield.setText(String.valueOf(rs.getInt(14)));
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        Pay_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int change = Integer.parseInt(Change_Tfield.getText());
                if (change >= 0) {
                    try {
                        PreparedStatement ps = Login.getConnection()
                                .prepareStatement("UPDATE patient SET FEES=(?) WHERE ID=(?) AND Doctor=?");
                        ps.setInt(1, 0);
                        ps.setString(2, id);
                        ps.setString(3, Receptionist.Doc_Recp_ID);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(Frame, "PAYMENT SUCCESSFULL", "Success",
                                JOptionPane.WARNING_MESSAGE);
                        ID_Tfield.setText("");
                        Name_Tfield.setText("");
                        Fees_Tfield.setText("");
                        Amount_Paid_Tfield.setText("");
                        Change_Tfield.setText("");

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(Frame, "PAYMENT NOT SUCCESSFULL", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Check_Balance_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((Amount_Paid_Tfield.getText().isEmpty()) || (ID_Tfield.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(Frame, "ENTER AMOUNT PAID and ID", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    paid = Integer.parseInt(Amount_Paid_Tfield.getText());
                    try {
                        PreparedStatement ps = Login.getConnection()
                                .prepareStatement("SELECT * from patient WHERE ID=(?) AND Doctor=?");
                        ps.setString(1, ID_Tfield.getText());
                        ps.setString(2, Receptionist.Doc_Recp_ID);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            Change_Tfield.setText(String.valueOf(paid - (rs.getInt(14))));
                        }

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });

        Frame.setSize(1920, 1080);
        Frame.getContentPane().setBackground(new Color(215, 247, 224));
        Frame.setLayout(null);
        Frame.setResizable(true);
        Frame.setDefaultCloseOperation(3);
        Frame.add(Header_Label);
        Frame.add(Back_Button);
        Frame.add(Footer_Label);
        Frame.add(Search_Button);
        Frame.add(Enter_ID_Label);
        Frame.add(Name_Label);
        Frame.add(ID_Tfield);
        Frame.add(Name_Tfield);
        Frame.add(Fees_Label);
        Frame.add(Fees_Tfield);
        Frame.add(Amount_Paid_Label);
        Frame.add(Amount_Paid_Tfield);
        Frame.add(Change_Label);
        Frame.add(Change_Tfield);
        Frame.add(Pay_Button);
        Frame.add(Check_Balance_Button);
        Frame.setVisible(true);

    }

    public static FeePayment getInstance() {
        return new FeePayment();
    }
}
