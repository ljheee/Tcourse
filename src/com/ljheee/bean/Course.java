package com.ljheee.bean;

/**
 *实验课程
 */
public class Course {
	
	/*课程名称*/
	public String name;
	
	/*课时*/
	public String courseHour; //(endWeek-beginWeek)*timeOfWeek
	
	/*开始周*/
	public int beginWeek;
	
	/*结束周*/
	public int endWeek;
	
	/*每周次数*/
	public int timeOfWeek;

	@Override
	public String toString() {
		return "Course [name=" + name + ", courseHour=" + courseHour + "]";
	}

	
}
