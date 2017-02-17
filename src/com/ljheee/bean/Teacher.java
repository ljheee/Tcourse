package com.ljheee.bean;

import java.util.Set;
/**
 *教师
 */
public class Teacher {
	
	/*教师名*/
	private String name;
	
	/*[本学期]所教实验课专业*/
	private Set<Major> teachMajors;
	
	
	public Teacher() {
	}


	public Teacher(String name, Set<Major> teachMajors) {
		this.name = name;
		this.teachMajors = teachMajors;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Major> getTeachMajors() {
		return teachMajors;
	}


	public void setTeachMajors(Set<Major> teachMajors) {
		this.teachMajors = teachMajors;
	}
	

}
