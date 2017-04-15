package com.ljheee.bean;

import java.util.ArrayList;
import java.util.List;
/**
 * 1-20周
 * 教师-学生空课时间
 * @author ljheee
 *
 */
public class BothSpare {
	
	/*1-20周课表*/
	public List<WeekClass> list = new ArrayList<>(20);
	
	public BothSpare(){
		for (int i = 0; i < 20; i++) {
			list.add(new WeekClass());
		}
	}
	
	/**
	 * 获取指定周-空课时间
	 * @param i
	 * @return
	 */
	public WeekClass getTheoryTable(int i){
		return list.get(i-1);
	}

}
