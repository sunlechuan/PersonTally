package com.slc.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.slc.oper.Operation;
import com.slc.util.Tool;
import com.slc.util.Usercy;

public class UI_reg extends JFrame{

	public UI_reg() {
		super("注册");
		this.setBounds(300, 200, 800, 600);
		initReg();
	}
	private void initReg() {
		Container c = getContentPane();
		c.setLayout(new GridLayout(5,2));
		
		JPanel jp =new JPanel();
		JLabel jl = new JLabel("用户名 ");
		JTextField jt = new JTextField(10);
		jp.add(jl);
		jp.add(jt);
		
		JPanel jp1 =new JPanel();
		JLabel jl1 = new JLabel("密码");
		JPasswordField jt1 = new JPasswordField(10);
		jp1.add(jl1);
		jp1.add(jt1);
		
		JPanel jp2 =new JPanel();
		JLabel jl2 = new JLabel("确认密码");
		JPasswordField jt2 = new JPasswordField(10);
		jp2.add(jl2);
		jp2.add(jt2);
		
		JPanel jp3 = new JPanel();
		JButton jb = new JButton("注册");
		JButton jb1 = new JButton("取消");
		jp3.add(jb);
		jp3.add(jb1);
		
		c.add(jp);
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		
		//注册监听事件
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jt.getText();
				String password = new String(jt1.getPassword());
				String agapassword = new String(jt2.getPassword());
				Operation oper = new Operation();
				Tool tool = new Tool();
				int id = tool.getNewUserID();
				if(username.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return ;
				}
				 if(password.equals("")||agapassword.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return ;
				}
				 if(!password.equals(agapassword)) {
					JOptionPane.showMessageDialog(null,"两次密码不一致");
					return ;
				}
				 if(oper.selectRegUser(username, agapassword)) {
					 JOptionPane.showMessageDialog(null, "用户名密码重复");
					 return ;
				 }
				try {
						String dataUser = id + "," + username + "," + password + "\n";
						Operation operation = new Operation();
						operation.addUser(dataUser);
						System.out.println(dataUser);
					}
					catch(Exception z) {
						z.printStackTrace();
					}
				new UI_login();
			}
				
			});
	}
	
	public static void main(String[] args) {
		new UI_reg().setVisible(true);;   
	}
}

