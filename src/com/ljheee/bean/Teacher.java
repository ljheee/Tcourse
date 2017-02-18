package com.ljheee.bean;

import java.util.Iterator;
import java.util.List;
/**
 *教师
 */
public class Teacher {
	
	/*教师名*/
	private String name;
	
	/*[本学期]所教实验课专业*/
	private List<Major> teachMajors;
	
	
	public Teacher() {
	}


	public Teacher(String name, List<Major> teachMajors) {
		this.name = name;
		this.teachMajors = teachMajors;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Major> getTeachMajors() {
		return teachMajors;
	}


	public void setTeachMajors(List<Major> teachMajors) {
		this.teachMajors = teachMajors;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Teacher)obj).name);
	}
	


	@Override
	public String toString() {
		String str = "";
		Iterator<Major> it = teachMajors.iterator();
		for (int i = 0; i < teachMajors.size(); i++) {
			str += it.next().toString();
		}
		
		return "Teacher [name=" + name + ", teachMajors=" + str + "]";
	}
	
	

}
