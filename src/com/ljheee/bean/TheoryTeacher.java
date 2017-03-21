package com.ljheee.bean;

import java.util.ArrayList;
import java.util.List;

public class TheoryTeacher {

	/*教师名*/
	public String name;
	
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
		
		return list.get(i+1);
	}
	
}
