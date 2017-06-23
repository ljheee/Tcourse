package com.ljheee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 */
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
	
	/**
	 * 添加[单节]课程
	 * @param weekIndex
	 * @param row
	 * @param col
	 * @param classRoom
	 * @return
	 */
	public static boolean addClass2Db(int weekIndex,int row,int col,String classRoom){
		boolean result = false;
		
//		String sql2 = "update week1 set day1='609' where id=2";
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
	
	public static boolean addClass2Db_(int weekIndex,int row,int col,String classRoom){
		boolean result = false;
		
//		String sql2 = "update week1 set day1='609' where id=2";
		try {
			String rooms = getClassRooms(weekIndex, row, col);
			sm = con.createStatement();
			if(rooms == null){//直接update
				result=sm.execute("update week"+weekIndex+" set day"+col+"='"+classRoom+"' where id="+row);
			}else{
				if(rooms.contains(classRoom)){//此教师已用
					
				}
				String newRoom = rooms+"-"+classRoom;
				result=sm.execute("update week"+weekIndex+" set day"+col+"='"+newRoom+"' where id="+row);
			}
			
			
			
			if(result==false){
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 起始到结束周，写入排课
	 * 写入DB多张表
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
	
	/**
	 * 检查begin--end起始到结束周，是否都有空
	 * @param begin
	 * @param end
	 * @param row
	 * @param col
	 * @return
	 */
	public static boolean checkTable(int begin, int end, int row, int col) {
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			if(checkClass(i, row, col)){
				continue;
			}
		}
		return result;
	}
	
	/**
	 * 检查[单周] 某节课
	 * @param tableIndex
	 * @param row
	 * @param col
	 * @return
	 */
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
	/**
	 * 获取单节，已用实验室
	 * @param weekIndex
	 * @param row
	 * @param col
	 * @return
	 */
	private static String getClassRooms(int weekIndex,int row,int col){
//		String sql2 = "SELECT * FROM `week1` where id=3";
		String result = null;
		try {
			sm = con.createStatement();
			ResultSet rs = sm.executeQuery("SELECT * FROM week"+weekIndex+" where id="+row);
			rs.next();
			result = rs.getString("day"+col);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	public static void main(String[] args) {
		addClass2Db_(1, 3, 1, "602");
		System.out.println(getClassRooms(1, 3, 1));;
		
//		if(checkTable(1,9,1,1));
	}

	

}
