package com.slc.file;

import java.io.*;

public class FileIO {
	private String filename = "tally.txt";
	private String regFilename = "register.txt";
	public FileIO() {
		File file = new File(filename);
		File file2 = new File(regFilename);
		if(!(file.exists() && file2.exists())) {
			try {
				file.createNewFile();
				file2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void write(String data,boolean mode) {
		try {
			FileWriter fw = new FileWriter(filename,mode);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//写入用户数据
	public void writeUser(String data,boolean mode) {
		try {
			FileWriter fw = new FileWriter(regFilename,mode);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//读取用户数据
	public String readUser() {
		FileReader fr;
		String line = "";
		String fileStr = "";
		try {
			fr = new FileReader(regFilename);//不能一行行的读
			BufferedReader br = new BufferedReader(fr);//可以一行行的读效率高
			while((line = br.readLine()) != null) {
				fileStr += line + "\n";
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileStr;
	}
	public String read() {
		FileReader fr;
		String line = "";
		String fileStr = "";
		try {
			fr = new FileReader(filename);//不能一行行的读
			BufferedReader br = new BufferedReader(fr);//可以一行行的读效率高
			while((line = br.readLine()) != null) {
				fileStr += line + "\n";
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileStr;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRegFilename() {
		return regFilename;
	}
	public void setRegFilename(String RegFilename) {
		this.regFilename = regFilename;
	}
	
}
