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
	 * 写入单表，此实验室在此时间段可用
	 * @param weekIndex 周数
	 * @param row		节数
	 * @param col		星期
	 * @param classRoom	实验室
	 * @return
	 */
	public static boolean addClass2Db(int weekIndex,int row,int col,String classRoom){
		boolean result = false;
		
//		String sql2 = "update week1 set day1='609' where id=2";
		try {
			String rooms = getUsedRooms(weekIndex, row, col);
			sm = con.createStatement();
			String newRoom = (rooms==null ? classRoom : rooms+"-"+classRoom);
			result = sm.execute("update week"+weekIndex+" set day"+col+"='"+newRoom+"' where id="+row);

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
			addClass2Db(i, row, col, classRoom);//写入DB前已经检查过，此处直接写DB
		}
		result = true;
		return result;
	}
	
	/**
	 * 检查begin--end起始到结束周，classRoom是否都可用
	 * @param begin 起始周
	 * @param end	结束周
	 * @param row	节数
	 * @param col	星期
	 * @param classRoom	实验室
	 * @return
	 * @throws UsedClassRoomException 
	 */
	public static boolean checkTable(int begin, int end, int row, int col,String classRoom) throws UsedClassRoomException {
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			if(checkClass(i, row, col ,classRoom)){
				result = true;
				continue;
			}else{
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 检查[单周] [row,col]节课，是否可用
	 * @param tableIndex
	 * @param row
	 * @param col
	 * @return
	 * @throws UsedClassRoomException 
	 */
	private static boolean checkClass(int tableIndex, int row, int col,String classRoom) throws UsedClassRoomException {
		boolean result = false;
		try {
			sm = con.createStatement();
			//select day1 from week1 where id=3
			String sql = "select day"+col+" from week"+tableIndex+" where id="+row;
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			String rooms = rs.getString("day"+col);
			if( rooms == null || !rooms.contains(classRoom)){//classRoom此时段无人用
				result = true;
			}else{
				throw new UsedClassRoomException(tableIndex,row,col);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//...........................................................................
	
	/**
	 * 获取单节，已用实验室
	 * @param weekIndex
	 * @param row
	 * @param col
	 * @return
	 */
	private static String getUsedRooms(int weekIndex,int row,int col){
		String result = null;
		try {
			sm = con.createStatement();
			ResultSet rs = sm.executeQuery("SELECT day"+col+" FROM week"+weekIndex+" where id="+row);
			rs.next();
			result = rs.getString("day"+col);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 检查 起始周到结束周，classRoom时候都可用    （方法调用10ms左右）
	 * @param begin     起始周
	 * @param end		结束周
	 * @param row		节数
	 * @param col		星期
	 * @param classRoom	实验室
	 * @return
	 */
	public static boolean checkRooms(int begin, int end, int row, int col,String classRoom){
		boolean result = false;
		for (int i = begin; i <= end; i++) {
			String rooms = getUsedRooms(i, row, col);
			if( rooms == null || !rooms.contains(classRoom)){//classRoom此时段无人用
				result = true;
				continue;
			}else {
				result = false;
				break;
			}
		}
		return result;
	}
	

	public static void main(String[] args) throws UsedClassRoomException {
		
//		if (checkTable(1, 20, 3, 1, "602")) {
//			sureRoom(1, 20, 3 ,1, "600");
//		}
		
//		long tt = System.currentTimeMillis();
//		System.out.println(checkTable(1, 20, 5, 7, "601"));
//		System.out.println(System.currentTimeMillis() - tt);
		
		
		long tt = System.currentTimeMillis();
		System.out.println(checkRooms(1, 20, 3, 1, "603"));
		System.out.println(System.currentTimeMillis() - tt);
		
	}

}
