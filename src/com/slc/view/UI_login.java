package com.slc.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.slc.oper.Operation;
import com.slc.util.Usercy;


public class UI_login extends JFrame{

	public static Usercy u;
	public UI_login() {
		super("Login");
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JLabel jl = new JLabel("username            ");
		JTextField jt = new JTextField(12);
		JLabel jl1 = new JLabel("password            ");
		JPasswordField jt1 = new JPasswordField(12);
		c.add(jl);
		c.add(jt);
		c.add(jl1);
		c.add(jt1);
		JButton[] b = { new JButton("登陆"), new JButton("取消"), new JButton("注册") };
		for (int i = 0; i <= 2; i++)
			c.add(b[i]);
		
		b[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = jt.getText();
				String password = new String(jt1.getPassword());
				u = new Usercy();
				u.setUsername(username);
				u.setPassword(password);
				if (jt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return;
				}

				Operation oper = new Operation();
				if(oper.selectUser(username, password)) {
					new UI_t().setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "无");
					return;
				}
			}
		});
		
		b[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UI_reg().setVisible(true);
				
			}
			
		});
		
		
		
		this.setBounds(800, 400, 300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new UI_login();
	}
}
