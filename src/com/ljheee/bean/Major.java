package com.ljheee.bean;

/**
 *专业
 */
public class Major {
	
	/*专业名称*/
	public String name;
	
	/*年级  如2014*/
	public String level;
	
	/*该专业学生数*/
	public String numStudent;
	
	/*实验课分组*/
	public String group;
	
	/*课程*/
	public Course course;

	@Override
	public String toString() {
		return "Major [name=" + name + ", level=" + level + ", numStudent=" + numStudent + ", group=" + group
				+ ", course=" + course + "]";
	}
}
