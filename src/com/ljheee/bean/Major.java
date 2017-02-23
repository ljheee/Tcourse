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
		return "Major[专业=" + name + ", 年级=" + level + ", 人数=" + numStudent + ", 分组=" + group
				+ ", 实验课程=" + course + "]";
	}
}
