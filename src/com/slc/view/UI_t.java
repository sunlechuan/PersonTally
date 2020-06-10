package com.slc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.slc.oper.Operation;
import com.slc.util.Tool;
import com.slc.util.Usercy;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class UI_t extends JFrame{

	private JComboBox<String> typeCom = new JComboBox<String>();
	private JComboBox<String> typeCom2 = new JComboBox<String>();
	private JTextField moneyText = new JTextField(); 
	private JTextField loteText = new JTextField();
	private JTextField tjText = new JTextField();
	private JTable table = new JTable();
	private DefaultTableModel dtm = new DefaultTableModel();
	private Vector<String> colName = new Vector<String>();
	private String username;
	private String password;
	private int userId;
	private Tool tool = new Tool();
	public UI_t(){
		super("个人账目管理系统");
		this.setBounds(300, 200, 800, 600);
		username = UI_login.u.getUsername();
		password = UI_login.u.getPassword();
		userId = tool.getUserId(username, password);//提前获取userId刷新页面
		init();
		
	}
	
	public void init(){
		JScrollPane p1 = new JScrollPane(setTable());
		final JSplitPane p2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,setInfo(),p1);
		p2.addComponentListener(new ComponentListener(){

			@Override
			public void componentResized(ComponentEvent arg0) {
				p2.setDividerLocation(0.4);
				
			}
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			});
		this.select();
		this.add(p2);
		
	}
	/*
	 * 设置JPanel的界面
	 */
	public JPanel setInfo(){
		JPanel jp = new JPanel();
		jp.setLayout(null);
		JLabel label1 = new JLabel("类型");
		label1.setBounds(100, 50, 30, 30);
		jp.add(label1);
		
		typeCom.addItem("收入");
		typeCom.addItem("支出");
		typeCom.setBounds(150, 50, 60, 30);
		jp.add(typeCom);
		
		JLabel label2 = new JLabel("金额");
		label2.setBounds(250, 50, 30, 30);
		jp.add(label2);
		
		moneyText.setBounds(300, 50, 60, 30);
		jp.add(moneyText);
		
		JLabel label3 = new JLabel("备注");
		label3.setBounds(380, 50, 30, 30);
		loteText.setBounds(430, 50, 60, 30);
		jp.add(label3);
		jp.add(loteText);
		
		JButton addBut = new JButton("增加");
		addBut.setBounds(520, 50, 60, 30);
		jp.add(addBut);
		
		
		addBut.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String type = (String)typeCom.getSelectedItem();//返回当前所选项
				String money = moneyText.getText();
				String remark = loteText.getText();
				tool = new Tool();
				String time = tool.getTime();
				int id = tool.getNewID();
				if(money.equals("") || remark.equals(""))
					return ;
				String data = id + "," + type + "," + money + "," + time + "," + remark + "," + userId +"\n";
				System.out.println(data);
				Operation oper = new Operation();
				oper.add(data);
				select();
			}
		});
		
		JButton xgBut = new JButton("修改");
		xgBut.setBounds(610, 50, 60, 30);
		jp.add(xgBut);
		xgBut.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String data = changeTableData();
				Operation oper = new Operation();
				oper.update(data);
				
			}
		});
		
		JButton deleteBut = new JButton("删除");
		deleteBut.setBounds(700, 50, 60, 30);
		jp.add(deleteBut);
		deleteBut.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				System.out.println(i + "////////////////////");
				dtm.removeRow(i);
				String data = changeTableData();
				Operation oper = new Operation();
				oper.delete(data);
				
			}
		});
		
		JLabel label4 = new JLabel("按类型查询");
		label4.setBounds(100, 100, 70, 30);
		jp.add(label4);
		typeCom2.addItem("");
		typeCom2.addItem("收入");
		typeCom2.addItem("支出");
		typeCom2.setBounds(200, 100, 60, 30);
		jp.add(typeCom2);
		JLabel label5 = new JLabel("按条件查询");
		label5.setBounds(280, 100, 100, 30);
		jp.add(label5);
		tjText.setBounds(380,100,100,30);
		jp.add(tjText);
		JButton selectBut = new JButton("查询");
		selectBut.setBounds(500, 100, 60, 30);
		jp.add(selectBut);
		selectBut.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String type = (String) typeCom2.getSelectedItem();
				String remark = tjText.getText();
				Operation oper = new Operation();
				Vector data = oper.select(type,remark,userId);//查询指定用户的条件
				dtm.setDataVector(data, colName);
				table.setModel(dtm);
			}
		});
		return jp;
	}
	public JTable setTable(){
		colName.add("流水账号");
		colName.add("类型");
		colName.add("金额");
		colName.add("日期");
		colName.add("备注");
		Operation oper = new Operation();
		Vector data = oper.selectUserTally(userId);
		dtm.setDataVector(data, colName);
		table.setModel(dtm);
		return table;
	}
	/*
	 * 从文件中重新读取一遍数据相当于刷新
	 */
	public void select(){
		Operation oper = new Operation();
		Vector data = oper.selectUserTally(userId);
		dtm.setDataVector(data, colName);
		table.setModel(dtm); 
	}
	/*
	 * 获取表格内容
	 */
	public String changeTableData(){
		String data = "";
		int row = table.getRowCount();
		for(int i=0;i<row;i++){
			String line = "";
			for(int j=0;j<5;j++){
				line += dtm.getValueAt(i, j)+",";
			}
			line += userId;
			line += "\n";
			data += line;
			System.out.println(line);
		}
		return data;
	}
}
