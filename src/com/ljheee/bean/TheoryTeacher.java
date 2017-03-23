package com.ljheee.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 理论课任课教师
 * 1-20周
 * @author ljheee
 *
 */
public class TheoryTeacher {

	/*教师名*/
	public String name;
	
	/*本任课教师，1-20周理论课课表*/
	public List<WeekClass> list = new ArrayList<>();
	
	
	public TheoryTeacher() {
	}


	public TheoryTeacher(String name) {
		this.name = name;
		
		for (int i = 0; i < 20; i++) {
			list.add(new WeekClass());
		}
	}
	
	/**
	 * 获取指定周-理论课表
	 * @param i
	 * @return
	 */
	public WeekClass getTheoryTable(int i){
		
		return list.get(i-1);
	}
	
}
