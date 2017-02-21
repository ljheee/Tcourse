package com.ljheee.bean;

/**
 *教师
 */
public class Teacher {
	
	/*教师名*/
	public String name;
	
	/*[本学期]所教实验课专业*/
	public Major teachMajor;
	
	
	public Teacher() {
	}


	public Teacher(String name, Major teachMajor) {
		this.name = name;
		this.teachMajor = teachMajor;
	}


	@Override
	public String toString() {
		return "Teacher [name=" + name + ", teachMajor=" + teachMajor + "]";
	}

}
