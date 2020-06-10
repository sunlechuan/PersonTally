package com.slc.util;

import java.text.SimpleDateFormat;
import java.util.*;

import com.slc.file.FileIO;
import com.slc.oper.Operation;

public class Tool {

	/*
	 * 获取时间
	 */
	public String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}
	/*
	 * 将String转化成Vector类型
	 */
	public Vector StringToVec(String data) {
		Vector bigVec = new Vector();
		if(data != null && !data.equals("")) {
			String[] array = data.split("\n");
			for(int i=0;i<array.length;i++) {
				String[] a = array[i].split(",");
				int x = a.length;
				System.out.println(x+"++++");
				Vector smallVec = new Vector();
				smallVec.add(a[0]);
				smallVec.add(a[1]);
				smallVec.add(a[2]);
				smallVec.add(a[3]);
				smallVec.add(a[4]);
				smallVec.add(a[5]);
				bigVec.add(smallVec);
				}
		}
		return bigVec;
	}
	/*
	 * 获取下一个流水账号
	 */
	public int getNewID() {
		int id = 1;
		FileIO io = new FileIO();
		String data = io.read();
		if(data != null && !data.equals("")) {
			Vector bigVec = StringToVec(data);
			Vector smallVecLast = (Vector)bigVec.get(bigVec.size()-1);
			String str = (String)smallVecLast.get(0);
			id = Integer.parseInt(str) + 1;
		}
		return id;
	}
	
	/*
	 * User 获取下一个用户流水账号
	 */
	public int getNewUserID() {
		int id = 1;
		FileIO io = new FileIO();
		String data = io.readUser();
		if(data != null && !data.equals("")) {
			Vector bigVec = StringToVecUser(data);
			Vector smallVecLast = (Vector)bigVec.get(bigVec.size()-1);
			String str = (String)smallVecLast.get(0);
			id = Integer.parseInt(str) + 1;
		}
		return id;
	}
	
	
	/*
	 * USer 将String转化成Vector类型
	 */
	public Vector StringToVecUser(String data) {
		Vector bigVec = new Vector();
		if(data != null && !data.equals("")) {
			String[] array = data.split("\n");
			for(int i=0;i<array.length;i++) {
				String[] a = array[i].split(",");
				Vector smallVec = new Vector();
				smallVec.add(a[0]);
				smallVec.add(a[1]);
				smallVec.add(a[2]);
				bigVec.add(smallVec);
				}
		}
		return bigVec;
	}
	
	/**
	 * 获取登陆用户的userId
	 */
	public int getUserId(String username,String password) {
		int userId = 0;
		Operation oper = new Operation();
		Vector vecAll = oper.selectUser();
		for(int i=0;i < vecAll.size();i++) {
			Vector smallVec = (Vector)vecAll.get(i);
			boolean select = (smallVec.get(1).toString().equals(username))
					&&
					(smallVec.get(2).toString().equals(password));
			if(select) {
				userId = Integer.valueOf(smallVec.get(0).toString());
			}
		}
		return userId;
	}
}
