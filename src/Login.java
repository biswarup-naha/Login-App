import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    JLabel head,usn,psw;
    JTextField usnField;
    JPasswordField pswField;
    JButton button;

    Login(){
        Font font=new Font("Arial",Font.BOLD,24);

        head=new JLabel("Login Page");
        head.setFont(font);

        usn=new JLabel("Username: ");
        usnField=new JTextField();

        psw=new JLabel("Password: ");
        pswField=new JPasswordField();

        button=new JButton("Login");

        head.setBounds(70,40,200,40);
        usn.setBounds(70,100,100,20);
        usnField.setBounds(70,120,200,30);
        psw.setBounds(70,170,100,20);
        pswField.setBounds(70,190,200,30);
        button.setBounds(170,240,100,30);
        add(head);
        add(usn);
        add(usnField);
        add(psw);
        add(pswField);
        add(button);
        setLayout(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
//                    head.setText("Login successful");
                    Connection connect= DriverManager.getConnection("jdbc:oracle:thin:@localhost:8080:xe","biswarup","006902");
                    String sql="select * from studentlogin where NAME='"+usnField.getText()+"' and PASSWORD='"+pswField.getPassword()+"'";
                    PreparedStatement ps=connect.prepareStatement(sql);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"Login Successful!!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Invalid username/password");
                    }
                }
                catch(Exception exp){
                    System.out.println(exp);
                }
            }
        });
    }
}
