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
	
	/*本专业，1-20周理论课课表*/
	public List<WeekClass> list = new ArrayList<>();
	
	public TheoryMajor() {
	}

	public TheoryMajor(int level, String name) {
		this.level = level;
		this.name = name;
	}
	
	
	
}
