package com.ljheee.db;
/**
 * 此实验室已用
 * 在检查实验室是否可用时，就会抛异常
 * checkTable{checkClass}。
 */
public class UsedClassRoomException extends Exception {

	private static final long serialVersionUID = 5487474692359270035L;
	

	public UsedClassRoomException(int tableIndex, int row, int col) {
		super("ClassRoom was Used. 周="+tableIndex+" 星期="+col+" 节数="+row);
	}

}
