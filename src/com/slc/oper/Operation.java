package com.slc.oper;

import java.util.Vector;

import com.slc.file.FileIO;
import com.slc.util.Tool;

public class Operation {

	FileIO io = new FileIO();
	public void add(String data) {
		io.write(data, true);
	}
	public void addUser(String data) {
		io.writeUser(data, true);
	}
	public void delete(String data) {
		io.write(data, false);
	}
	public void update(String data) {
		io.write(data, false);
	}
	/*
	 * 全部查询
	 */
	public Vector select() {
		String str = io.read();
		Tool tool = new Tool();
		return tool.StringToVec(str);
	}
	
	/*
	 * 全部查询用户
	 */
	public Vector selectUser() {
		String str = io.readUser();
		Tool tool = new Tool();
		return tool.StringToVecUser(str);
	}
	
	/**
	 * 查询登陆用户
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean selectUser(String username,String password) {
		Vector vecAll = selectUser();
		boolean select_user = false;
		for(int i=0;i < vecAll.size();i++) {
			Vector smallVec = (Vector)vecAll.get(i);
			boolean select = (smallVec.get(1).toString().equals(username))
					&&
					(smallVec.get(2).toString().equals(password));
			if(select) {
				select_user = select;
			}
		}
		return select_user;
	}
	
	
	/*
	 * 
	 * 条件查询
	 */
	public Vector select(String type,String remark,int userId) {
		Vector vecData = new Vector();
		Vector vecAll = selectUserTally(userId);
		for(int i = 0;i < vecAll.size();i++) {
			Vector smallVec = (Vector)vecAll.get(i);
			boolean select = (smallVec.get(1).toString().equals(type) || type.equals(""))
					&&
					(smallVec.get(4).toString().equals(remark) || remark.equals(""));
			if(select) {
				vecData.add(smallVec);
			}
		}
		return vecData;
	}
	
	/**
	 * 查询指定用户的账本
	 * @param userId
	 * @return
	 */
	public Vector selectUserTally(int userId) {
		Vector vecData = new Vector();
		Vector vecAll = select();
		String str = Integer.toString(userId);
		
		for(int i = 0;i < vecAll.size();i++) {
			System.out.println(vecAll.size()+"------------");
			Vector smallVec = (Vector)vecAll.get(i);
			boolean select = (smallVec.get(5).toString().equals(str));
			if(select) {
				vecData.add(smallVec);
			}
		}
		return vecData;
	}
	
	
	/**
	 * 查询注册用户
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean selectRegUser(String username,String password) {
		Vector vecAll = selectUser();
		boolean select_user = false;
		for(int i=0;i < vecAll.size();i++) {
			Vector smallVec = (Vector)vecAll.get(i);
			boolean select = (smallVec.get(1).toString().equals(username))
					&&
					(smallVec.get(2).toString().equals(password));
			if(select) {
				select_user = select;
			}
		}
		return select_user;
	}
	
	private void main() {
		Integer var1=new Integer(1);
		   Integer var2=var1;
		   doSomething(var2);
		   System.out.print(var1.intValue());
		   System.out.print(var1==var2);
	}
	public static void doSomething(Integer integer){
	    integer=new Integer(2);
	  }
}
