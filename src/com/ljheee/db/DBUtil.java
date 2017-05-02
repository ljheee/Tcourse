package com.ljheee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static final String DB_URL = "jdbc:mysql://localhost:3306/courseschedule";
	public static final String USER = "abc";
	public static final String PASS = "abc";
	
	static Connection con;
	static PreparedStatement ps;
	static Statement sm;
	static ResultSet rs;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL , USER , PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean addClass2Db(int weekIndex,int row,int col,String classRoom){
		boolean result = false;
		
		String sql = "update ? set ?=? where id=?";
		String sql2 = "update week1 set day1='609' where id=2";
		try {
			sm = con.createStatement();
			result=sm.execute("update week"+weekIndex+" set day"+col+"='"+classRoom+"' where id="+row);
			
			if(result==false){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 写入DB
	 */
	public static boolean sureRoom(int begin, int end,int row,int col,String classRoom){
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			if(addClass2Db(i, row, col, classRoom)){
				continue;
			}
		}
		result = true;
		return result;
	}
	
	
	public static boolean checkTable(int begin, int end, int row, int col) {
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			if(checkClass(i, row, col)){
				continue;
			}
		}
		
		
		
		return result;
	}
	
	
	private static boolean checkClass(int tableIndex, int row, int col) {
		boolean result = false;
		try {
			sm = con.createStatement();
			//select day1 from week1 where id=3
			
//			result=sm.execute("update week"+weekIndex+" set day"+col+"='"+classRoom+"' where id="+row);
			
			if(result==false){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	public static void main(String[] args) {
//		addClass2Db(1, 3, 1, "600");
		
		
		if(checkTable(1,9,1,1));
	}

	

}
