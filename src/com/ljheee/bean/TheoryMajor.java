package com.ljheee.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 理论课-专业
 * 1-20周
 * @author ljheee
 *
 */
public class TheoryMajor {
	
	/*年级*/
	int level;
	
	/*专业名*/
	String name;
	
	/*年级+专业名*/
	String levelAndName;
	
	/*本专业，1-20周理论课课表*/
	public List<WeekClass> list = new ArrayList<>();
	
	public TheoryMajor() {
	}

	public TheoryMajor(String levelAndName) {
		this.levelAndName = levelAndName;
		
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
